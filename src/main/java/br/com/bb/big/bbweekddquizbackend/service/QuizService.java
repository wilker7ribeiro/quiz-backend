package br.com.bb.big.bbweekddquizbackend.service;

import br.com.bb.big.bbweekddquizbackend.dto.QuizDTO;
import br.com.bb.big.bbweekddquizbackend.dto.RankingQuizItemDTO;
import br.com.bb.big.bbweekddquizbackend.dto.mapper.PerguntaMapper;
import br.com.bb.big.bbweekddquizbackend.dto.mapper.QuizMapper;
import br.com.bb.big.bbweekddquizbackend.entity.Pergunta;
import br.com.bb.big.bbweekddquizbackend.entity.Quiz;
import br.com.bb.big.bbweekddquizbackend.exception.QuizNaoEncontradoException;
import br.com.bb.big.bbweekddquizbackend.repository.PerguntaRepository;
import br.com.bb.big.bbweekddquizbackend.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class QuizService {
    @Value("${quiz.quantidade_perguntas_por_quiz}")
    private Long quantidadePerguntasPorQuiz;
    @Value("${quiz.tempo_maximo_duracao_quiz}")
    private Integer tempoMaximoDuracaoQuiz;
    @Value("${quiz.pontuacao_resposta_correta}")
    private Long pontuacaoPorRespostaCorreta;
    @Value("${quiz.penalidade_resposta_incorreta}")
    private Long penalidadeRespostaIncorreta;
    private final QuizRepository repository;
    private final PerguntaRepository perguntaRepository;
    private final QuizMapper mapper;
    private final PerguntaMapper perguntaMapper;

    @Autowired
    public QuizService(
            final QuizRepository repository, final QuizMapper mapper,
            final PerguntaRepository perguntaRepository, final PerguntaMapper perguntaMapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
        this.perguntaRepository = perguntaRepository;
        this.perguntaMapper = perguntaMapper;
    }

    public List<RankingQuizItemDTO> obterRankingPorQuizIdAndDia(Integer quizId, LocalDate dia) throws QuizNaoEncontradoException {
        if(!this.repository.existsById(quizId)) throw new QuizNaoEncontradoException(quizId);
        List<RankingQuizItemDTO> rankingLista = new ArrayList<>();
        List<Object[]> items = this.repository.obterRankinQuiz(quizId, dia, quantidadePerguntasPorQuiz, tempoMaximoDuracaoQuiz, pontuacaoPorRespostaCorreta, penalidadeRespostaIncorreta, Pageable.ofSize(10));
        for (int i = 0; i < items.size(); i++) {
            rankingLista.add(new RankingQuizItemDTO(i + 1, (String) items.get(i)[0], Math.max((Long) items.get(i)[1], 0)));
        }
        return rankingLista;
    }

    public QuizDTO consultarQuiz(Integer quizId) throws QuizNaoEncontradoException {
        Quiz quiz = this.repository.findById(quizId).orElseThrow(() -> new QuizNaoEncontradoException(quizId));
        List<Pergunta> perguntas = this.perguntaRepository.obterPerguntasAleatorias(quiz.getId(), Pageable.ofSize(Math.toIntExact(quantidadePerguntasPorQuiz)));
        return QuizDTO.builder()
                .id(quizId)
                .nome(quiz.getNome())
                .perguntas(new HashSet<>(this.perguntaMapper.toDto(perguntas)))
                .build();
    }
}
