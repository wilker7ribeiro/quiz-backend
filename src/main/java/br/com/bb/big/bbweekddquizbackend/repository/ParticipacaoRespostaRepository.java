package br.com.bb.big.bbweekddquizbackend.repository;

import br.com.bb.big.bbweekddquizbackend.entity.ParticipacaoResposta;
import br.com.bb.big.bbweekddquizbackend.entity.ParticipacaoRespostaID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipacaoRespostaRepository extends JpaRepository<ParticipacaoResposta, ParticipacaoRespostaID> {
    Boolean existsByParticipacaoIdAndPerguntaId(Integer participacaoId, Integer perguntaId);
}
