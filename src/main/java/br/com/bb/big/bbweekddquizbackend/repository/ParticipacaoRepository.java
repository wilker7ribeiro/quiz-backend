package br.com.bb.big.bbweekddquizbackend.repository;

import br.com.bb.big.bbweekddquizbackend.entity.Participacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ParticipacaoRepository extends JpaRepository<Participacao, Integer> {

    @Query(
            " SELECT participacao FROM Participacao participacao " +
            " WHERE participacao.quiz.id = :quizId AND participacao.participante.id = :participanteId " +
            "    AND DATE(participacao.dataInicio) = :dataInicio")
    Optional<Participacao> findByQuizIdAndParticipanteIdAndDataInicio(
            @Param("quizId") Integer quizId,
            @Param("participanteId") Integer participanteId,
            @Param("dataInicio") LocalDate dataInicio
    );

    @Query(
        " SELECT participacao FROM Participacao participacao" +
        "   JOIN FETCH participacao.participante participante " +
        "   LEFT JOIN FETCH participacao.respostas resposta " +
        "   LEFT JOIN FETCH resposta.opcao opcao " +
        "   LEFT JOIN FETCH resposta.pergunta pergunta " +
        "   LEFT JOIN FETCH pergunta.opcoes pergunta_opcao " +
        " WHERE participacao.id = :participacaoId"
    )
    Optional<Participacao> findByIdWithRespostas(@Param("participacaoId") Integer participacaoId);


    @Query("SELECT participacao FROM Participacao participacao JOIN FETCH participacao.quiz " +
            " WHERE participacao.id = :participacaoId ")
    Optional<Participacao> findByIdWithQuiz(@Param("participacaoId") Integer participacaoId);

    @Query(
            " SELECT count(resposta) FROM Participacao participacao" +
            "   JOIN participacao.respostas resposta " +
            " WHERE participacao.id = :participacaoId"
    )
    Integer obterQuantidadePerguntasRespondidas(Integer participacaoId);

    List<Participacao> findAllByDataFimIsNullAndDataInicioLessThan(LocalDateTime dataInicioVencimento);
}
