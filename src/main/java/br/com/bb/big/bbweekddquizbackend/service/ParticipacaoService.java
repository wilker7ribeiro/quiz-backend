package br.com.bb.big.bbweekddquizbackend.service;

import br.com.bb.big.bbweekddquizbackend.dto.ParticipacaoDTO;
import br.com.bb.big.bbweekddquizbackend.dto.ParticipanteDTO;
import br.com.bb.big.bbweekddquizbackend.dto.ResultadoParticipacaoDTO;
import br.com.bb.big.bbweekddquizbackend.dto.RetornoResponderPerguntaDTO;
import br.com.bb.big.bbweekddquizbackend.dto.mapper.ParticipacaoMapper;
import br.com.bb.big.bbweekddquizbackend.dto.mapper.ParticipacaoRespostaMapper;
import br.com.bb.big.bbweekddquizbackend.dto.mapper.ParticipanteMapper;
import br.com.bb.big.bbweekddquizbackend.entity.*;
import br.com.bb.big.bbweekddquizbackend.exception.*;
import br.com.bb.big.bbweekddquizbackend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.validation.ValidationException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ParticipacaoService {

    @Value("${quiz.quantidade_perguntas_por_quiz}")
    private Integer quantidadePerguntasPorQuiz;

    @Value("${quiz.tempo_maximo_duracao_quiz}")
    private Integer tempoMaximoDuracaoQuiz;
    @Value("${quiz.pontuacao_resposta_correta}")
    private Integer pontuacaoPorRespostaCorreta;
    @Value("${quiz.penalidade_resposta_incorreta}")
    private Integer penalidadeRespostaIncorreta;

    private final ParticipacaoRepository repository;
    private final ParticipanteRepository participanteRepository;

    private final ParticipacaoRespostaRepository participacaoRespostaRepository;
    private final ParticipacaoRespostaMapper participacaoRepostaMapper;
    private final ColaboradorRepository colaboradorRepository;
    private final PerguntaRepository perguntaRepository;
    private final PerguntaOpcaoRepository perguntaOpcaoRepository;

    private final QuizRepository quizRepository;
    private final ParticipacaoMapper mapper;

    private final ParticipanteMapper participanteMapper;

    @Autowired
    public ParticipacaoService(
            final ParticipacaoRepository repository, final ParticipacaoMapper mapper,
            final ParticipanteRepository participanteRepository, final ParticipanteMapper participanteMapper,
            final QuizRepository quizRepository, final ColaboradorRepository colaboradorRepository,
            final PerguntaRepository perguntaRepository, final PerguntaOpcaoRepository perguntaOpcaoRepository,
            final ParticipacaoRespostaRepository participacaoRespostaRepository, final ParticipacaoRespostaMapper participacaoRepostaMapper) {
        this.repository = repository;
        this.mapper = mapper;
        this.participanteRepository = participanteRepository;
        this.quizRepository = quizRepository;
        this.colaboradorRepository = colaboradorRepository;
        this.participanteMapper = participanteMapper;
        this.perguntaRepository = perguntaRepository;
        this.participacaoRespostaRepository = participacaoRespostaRepository;
        this.participacaoRepostaMapper = participacaoRepostaMapper;
        this.perguntaOpcaoRepository = perguntaOpcaoRepository;
    }

    public Participante criarParticipante(ParticipanteDTO participanteDTO) throws FuncionarioNaoEncontradoException {
        if(!StringUtils.hasLength(participanteDTO.getMatricula()) && !StringUtils.hasLength(participanteDTO.getEmail())) {
            throw new ValidationException("O campo matricula ou email é obrigatório");
        }
        Optional<Participante> participanteOpt = this.participanteRepository.findByEmailOrMatricula(participanteDTO.getEmail(), participanteDTO.getMatricula());
        if (participanteOpt.isPresent()) {
            return participanteOpt.get();
        } else if(StringUtils.hasLength(participanteDTO.getMatricula())) {
            Colaborador colaborador = colaboradorRepository.findByChaveIgnoreCase(participanteDTO.getMatricula())
                    .orElseThrow(() -> new FuncionarioNaoEncontradoException(participanteDTO.getMatricula()));
            participanteDTO.setNome(colaborador.getNome());
            participanteDTO.setValidado(Boolean.TRUE);
            return this.participanteRepository.save(Participante.builder()
                    .matricula(participanteDTO.getMatricula())
                    .nome(colaborador.getNome())
                    .validado(Boolean.TRUE)
                    .build());
        } else {
            if(!StringUtils.hasLength(participanteDTO.getNome()) || !StringUtils.hasLength(participanteDTO.getEmail())) {
                throw new ValidationException("O campo nome e email é obrigatório");
            }
            return this.participanteRepository.save(Participante.builder()
                    .matricula(participanteDTO.getMatricula())
                    .nome(participanteDTO.getNome())
                    .email(participanteDTO.getEmail())
                    .validado(Boolean.FALSE)
                    .build());
        }
    }

    @Transactional
    public ParticipacaoDTO iniciarParticipacao(Integer quizId, ParticipanteDTO participanteDTO) throws QuizNaoEncontradoException, ParticipanteJaParticipouDoQuizHojeException, FuncionarioNaoEncontradoException {
        LocalDateTime agora = LocalDateTime.now();
        LocalDate hoje = agora.toLocalDate();
        Quiz quiz = this.quizRepository.findById(quizId).orElseThrow(() -> new QuizNaoEncontradoException(quizId));
        Participante participante = criarParticipante(participanteDTO);
        Optional<Participacao> participacaoSalva = this.repository.findByQuizIdAndParticipanteIdAndDataInicio(quizId, participante.getId(), hoje);
        if(participacaoSalva.isPresent()) {
            throw new ParticipanteJaParticipouDoQuizHojeException(quizId, participante.getId(), participacaoSalva.get().getId(), hoje);
        }

        if (Objects.nonNull(participanteDTO.getMatricula()) && !StringUtils.hasLength(participanteDTO.getNome())){
            colaboradorRepository.findById(participanteDTO.getMatricula()).ifPresent(colaborador -> participanteDTO.setNome(colaborador.getNome()));
        }
        Participacao participacao = Participacao.builder()
                .quiz(quiz)
                .dataInicio(agora)
                .participante(participante)
                .build();

        return this.mapper.toDto(this.repository.save(participacao));
    }

    public Optional<ParticipacaoDTO> consultarParticipacao(Integer participacaoId) {
        return this.repository.findByIdWithRespostas(participacaoId).map(this.mapper::toDto);
    }

    @Transactional
    public RetornoResponderPerguntaDTO responderPergunta(Integer participacaoId, Integer perguntaId, Integer opcaoId) throws ParticipacaoNaoEncontradaException, QuizNaoPossuiPerguntaException, PerguntaNaoPossuiOpcaoException, ParticipanteJaRespondeuTodasAsPerguntas, ParticipanteJaRespondeuEssaPergunta, TempoParaResponderOQuizEncerradoException {
        LocalDateTime agora = LocalDateTime.now();
        Participacao participacao = this.repository.findByIdWithQuiz(participacaoId).orElseThrow(() -> new ParticipacaoNaoEncontradaException(participacaoId));
        Integer quizId = participacao.getQuiz().getId();
        if(!this.quizRepository.possuiPerguntaComId(quizId, perguntaId)) {
            throw new QuizNaoPossuiPerguntaException(quizId, perguntaId);
        }
        if(!this.perguntaRepository.possuiOpcaoComId(perguntaId, opcaoId)) {
            throw new PerguntaNaoPossuiOpcaoException(perguntaId, opcaoId);
        }
        Integer quantidadePerguntasJaRespondidas = this.repository.obterQuantidadePerguntasRespondidas(participacaoId);
        if(quantidadePerguntasJaRespondidas >= quantidadePerguntasPorQuiz){
            throw new ParticipanteJaRespondeuTodasAsPerguntas(participacaoId);
        }
        if (Objects.nonNull(participacao.getDataFim()) || agora.isAfter(participacao.getDataInicio().plusSeconds(tempoMaximoDuracaoQuiz))) {
            throw new TempoParaResponderOQuizEncerradoException(participacaoId);
        }
        ParticipacaoRespostaID perguntaRespostaId = ParticipacaoRespostaID.builder()
                .participacaoId(participacaoId)
                .perguntaId(perguntaId)
                .perguntaOpcaoId(opcaoId)
                .build();
        if(this.participacaoRespostaRepository.existsByParticipacaoIdAndPerguntaId(participacaoId, perguntaId)) {
            throw new ParticipanteJaRespondeuEssaPergunta(participacaoId, perguntaId);
        }
        ParticipacaoResposta participacaoResposta = ParticipacaoResposta.builder()
                .dataResposta(agora)
                .participacao(participacao)
                .pergunta(this.perguntaRepository.getReferenceById(perguntaId))
                .opcao(this.perguntaOpcaoRepository.getReferenceById(opcaoId))
                .id(perguntaRespostaId)
                .build();
        ParticipacaoResposta respostaSalva = this.participacaoRespostaRepository.save(participacaoResposta);
        if(quantidadePerguntasJaRespondidas + 1 == quantidadePerguntasPorQuiz) {
            participacao.setDataFim(agora);
            this.repository.save(participacao);
        }
        return RetornoResponderPerguntaDTO.builder()
                .participacaoId(participacaoId)
                .perguntaId(perguntaId)
                .opcaoId(opcaoId)
                .correta(respostaSalva.getOpcao().getCorreta())
                .build();
    }
    @Scheduled(fixedRate = 1000)
    public void finalizarQuizPorTempo() {
        LocalDateTime agora = LocalDateTime.now();
        LocalDateTime dataInicioVencimento = agora.minus(tempoMaximoDuracaoQuiz, ChronoUnit.SECONDS);
        List<Participacao> participacoes = this.repository.findAllByDataFimIsNullAndDataInicioLessThan(dataInicioVencimento);
        if(!CollectionUtils.isEmpty(participacoes)) {
            participacoes.forEach(participacao -> participacao.setDataFim(participacao.getDataInicio().plusSeconds(tempoMaximoDuracaoQuiz)));
            this.repository.saveAll(participacoes);
        }
    }


    public ResultadoParticipacaoDTO obterResultadoParticipacao(Integer participacaoId) throws ParticipacaoNaoEncontradaException {
        Participacao participacao = this.repository.findByIdWithRespostas(participacaoId).orElseThrow(() -> new ParticipacaoNaoEncontradaException(participacaoId));
        Long quantidadeAcertos = participacao.getRespostas().stream().filter(r -> r.getOpcao().getCorreta()).count();
        LocalDateTime agora = LocalDateTime.now();
        participacao = this.repository.save(participacao);
        LocalDateTime dataFim = Objects.requireNonNullElse(participacao.getDataFim(), participacao.getDataInicio().plusSeconds(tempoMaximoDuracaoQuiz));
        long duracao = Math.min(ChronoUnit.SECONDS.between(participacao.getDataInicio(), dataFim), tempoMaximoDuracaoQuiz);
        double multiplizadorDuracao = new BigDecimal((double) tempoMaximoDuracaoQuiz / duracao).setScale(2, RoundingMode.HALF_UP).doubleValue();
        Long pontuacaoRespostasCorretas = quantidadeAcertos * this.pontuacaoPorRespostaCorreta;
        Long penalidadeRespostaSIncorretas = (quantidadePerguntasPorQuiz - quantidadeAcertos) * penalidadeRespostaIncorreta;
        long pontuacaoTotalRespostas = pontuacaoRespostasCorretas - penalidadeRespostaSIncorretas;
        Long pontuacaoTotal = (long) Math.floor(Math.max(pontuacaoTotalRespostas * multiplizadorDuracao, 0));
        Long pontuacaoTotalTempo = pontuacaoTotal - pontuacaoTotalRespostas;
        return ResultadoParticipacaoDTO.builder()
                .dataFim(dataFim)
                .dataInicio(participacao.getDataInicio())
                .participacaoId(participacaoId)
                .multiplicadorTempo(multiplizadorDuracao)
                .quantidadePerguntasCorretas(Math.toIntExact(quantidadeAcertos))
                .quantidadeTotalPerguntas(quantidadePerguntasPorQuiz)
                .pontuacaoRespostasCorretas(pontuacaoRespostasCorretas)
                .penalidadeRespostasIncorretas(penalidadeRespostaSIncorretas)
                .pontuacaoTotalRespostas(pontuacaoTotalRespostas)
                .pontuacaoTotalTempo(pontuacaoTotalTempo)
                .pontuacaoTotal(pontuacaoTotal)
                .build();
    }
}
