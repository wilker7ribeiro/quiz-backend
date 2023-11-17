package br.com.bb.big.bbweekddquizbackend.repository;

import br.com.bb.big.bbweekddquizbackend.entity.Participante;
import br.com.bb.big.bbweekddquizbackend.entity.Pergunta;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PerguntaRepository extends JpaRepository<Pergunta, Integer> {
    @Query(
            " SELECT CASE WHEN COUNT(opcao.id) > 0 THEN true ELSE false END " +
            " FROM Pergunta pergunta " +
            "   JOIN pergunta.opcoes opcao" +
            " WHERE pergunta.id = :perguntaId AND opcao.id = :opcaoId "
    )
    Boolean possuiOpcaoComId(@Param("perguntaId") Integer perguntaId, @Param("opcaoId")  Integer opcaoId);

    @Query(
            " SELECT pergunta FROM Quiz quiz" +
            "  JOIN quiz.perguntas pergunta " +
            "  JOIN FETCH pergunta.opcoes opcao " +
            " WHERE quiz.id = :quizId" +
            " ORDER BY rand()"
    )
    List<Pergunta> obterPerguntasAleatorias(@Param("quizId") Integer quizId, Pageable pageable);
}
