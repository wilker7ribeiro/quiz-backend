package br.com.bb.big.bbweekddquizbackend.exception;

import org.springframework.http.HttpStatus;

public class TempoParaResponderOQuizEncerradoException extends BaseException {
    private final Integer participacaoId;

    public TempoParaResponderOQuizEncerradoException(Integer participacaoId) {
        super(HttpStatus.NOT_ACCEPTABLE, "O tempo para responder o quiz se expirou");
        this.participacaoId = participacaoId;
    }
}
