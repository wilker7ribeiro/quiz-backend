package br.com.bb.big.bbweekddquizbackend.service;

import br.com.bb.big.bbweekddquizbackend.dto.ParticipanteDTO;
import br.com.bb.big.bbweekddquizbackend.dto.mapper.ParticipanteMapper;
import br.com.bb.big.bbweekddquizbackend.entity.Colaborador;
import br.com.bb.big.bbweekddquizbackend.entity.Participante;
import br.com.bb.big.bbweekddquizbackend.exception.FuncionarioNaoEncontradoException;
import br.com.bb.big.bbweekddquizbackend.exception.ParticipanteEmailJaCadastradoException;
import br.com.bb.big.bbweekddquizbackend.exception.ParticipanteNaoEncontradoException;
import br.com.bb.big.bbweekddquizbackend.repository.ColaboradorRepository;
import br.com.bb.big.bbweekddquizbackend.repository.ParticipanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.validation.ValidationException;
import java.util.Optional;

@Service
public class ParticipanteService {

    private final ParticipanteRepository repository;
    private final ParticipanteMapper mapper;

    @Autowired
    public ParticipanteService(final ParticipanteRepository repository, final ParticipanteMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


    public Optional<ParticipanteDTO> consultaPorEmail(String email) {
        return this.repository.findByEmail(email).map(this.mapper::toDto);
    }


}
