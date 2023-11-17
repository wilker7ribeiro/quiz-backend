package br.com.bb.big.bbweekddquizbackend.exception;

import org.springframework.http.HttpStatus;

public class ParticipanteJaRespondeuTodasAsPerguntas extends BaseException {
    private final Integer participacaoId;

    public ParticipanteJaRespondeuTodasAsPerguntas(Integer participacaoId) {
        super(HttpStatus.NOT_ACCEPTABLE, "O participante jé respondeu todas as perguntas.");
        this.participacaoId = participacaoId;
    }
}
