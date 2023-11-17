package br.com.bb.big.bbweekddquizbackend.repository;

import br.com.bb.big.bbweekddquizbackend.entity.Pergunta;
import br.com.bb.big.bbweekddquizbackend.entity.PerguntaOpcao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerguntaOpcaoRepository extends JpaRepository<PerguntaOpcao, Integer> {
}
