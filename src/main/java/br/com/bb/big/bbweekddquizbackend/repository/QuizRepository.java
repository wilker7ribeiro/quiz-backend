package br.com.bb.big.bbweekddquizbackend.repository;

import br.com.bb.big.bbweekddquizbackend.entity.Quiz;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Integer> {

    // PF = ((PRC*RC)*(1-PRI*RI))+((PRC*RC)*(1-(DRQ/DQ)))

    @Query(value =
            " SELECT " +
                    "   COALESCE(participacao.participante.nome, participacao.participante.matricula) AS nome," +
                    "   FLOOR(((:pontuacaoPorRespostaCorreta * cast(COUNT(participacaoResposta) as big_decimal)) * (1 - (:penalidadeRespostaIncorreta * (cast(:quantidadePerguntasPorQuiz as big_decimal) - cast(COUNT(participacaoResposta) as big_decimal))))) * 10) /10" +
                    "     + " +
                    "   FLOOR(((:pontuacaoPorRespostaCorreta * cast(COUNT(participacaoResposta) as big_decimal)) * (1 - floor((floor(EXTRACT(EPOCH FROM participacao.dataFim - participacao.dataInicio)) / :tempoMaximoDuracaoQuiz) * 100000) / 100000)) * 10) /10" +
                    " AS pontuacao  " +
                    "  FROM Participacao participacao " +
                    "    JOIN participacao.respostas participacaoResposta " +
                    "  WHERE participacaoResposta.opcao.correta = true AND participacao.quiz.id = :quizId " +
                    "   AND DATE(participacao.dataInicio) = :dia AND participacao.dataFim IS NOT NULL " +
                    "   AND participacao.participante.validado = true" +
                    "  GROUP BY participacao.dataInicio, participacao.dataFim, COALESCE(participacao.participante.nome, participacao.participante.matricula)" +
                    " ORDER BY pontuacao DESC"
    )
    List<Object[]> obterRankinQuiz(
            @Param("quizId") Integer quizId,
            @Param("dia") LocalDate dia,
            @Param("quantidadePerguntasPorQuiz") Long quantidadePerguntasPorQuiz,
            @Param("tempoMaximoDuracaoQuiz") Integer tempoMaximoDuracaoQuiz,
            @Param("pontuacaoPorRespostaCorreta") BigDecimal pontuacaoPorRespostaCorreta,
            @Param("penalidadeRespostaIncorreta") BigDecimal penalidadeRespostaIncorreta,
            Pageable pageable);

    @Query(
        " SELECT CASE WHEN COUNT(pergunta.id) > 0 THEN true ELSE false END " +
        " FROM Quiz quiz " +
        "   JOIN quiz.perguntas pergunta" +
        " WHERE quiz.id = :quizId AND pergunta.id = :perguntaId "
    )
    Boolean possuiPerguntaComId(@Param("quizId") Integer quizId, @Param("perguntaId") Integer perguntaId);

}
