package br.com.bb.big.bbweekddquizbackend.exception;

import org.springframework.http.HttpStatus;

public class ParticipanteJaRespondeuEssaPergunta extends BaseException {
    private final Integer participacaoId;
    private final Integer perguntaId;

    public ParticipanteJaRespondeuEssaPergunta(Integer participacaoId, Integer perguntaId) {
        super(HttpStatus.CONFLICT, "O participante já respondeu essa pergunta.");
        this.participacaoId = participacaoId;
        this.perguntaId = perguntaId;
    }
}
