package br.com.bb.big.bbweekddquizbackend.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ParticipanteEmailJaCadastradoException extends BaseException {
    private String email;
    public ParticipanteEmailJaCadastradoException(String email) {
        super(HttpStatus.CONFLICT, "Já existe um participante cadastrado com esse email.");
        this.email = email;
    }
}
