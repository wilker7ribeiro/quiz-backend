package br.com.bb.big.bbweekddquizbackend.repository;

import br.com.bb.big.bbweekddquizbackend.entity.Colaborador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ColaboradorRepository extends JpaRepository<Colaborador, String> {

    Optional<Colaborador> findByChaveIgnoreCase(String chave);
}
