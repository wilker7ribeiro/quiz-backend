package br.com.bb.big.bbweekddquizbackend.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ParticipanteMatriculaJaCadastradaException extends BaseException {
    private String matricula;
    public ParticipanteMatriculaJaCadastradaException(String matricula) {
        super(HttpStatus.CONFLICT, "Já existe um participante cadastrado com essa matrícula.");
        this.matricula = matricula;
    }
}
