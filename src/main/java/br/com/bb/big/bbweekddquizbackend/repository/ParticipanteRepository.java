package br.com.bb.big.bbweekddquizbackend.repository;

import br.com.bb.big.bbweekddquizbackend.entity.Participante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParticipanteRepository extends JpaRepository<Participante, Integer> {
    Optional<Participante> findByMatriculaIgnoreCase(String matricula);

    Optional<Participante> findByEmailIgnoreCase(String email);
}
