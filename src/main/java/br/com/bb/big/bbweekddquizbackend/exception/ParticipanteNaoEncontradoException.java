package br.com.bb.big.bbweekddquizbackend.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ParticipanteNaoEncontradoException extends BaseException{
    private final String email;

    public ParticipanteNaoEncontradoException(String email) {
        super(HttpStatus.CONFLICT, "Não foi encontrado o participante com o email "+email);
        this.email = email;
    }
}
