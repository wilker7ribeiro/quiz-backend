package br.com.bb.big.bbweekddquizbackend.exception;

import org.springframework.http.HttpStatus;

public class PerguntaNaoPossuiOpcaoException extends BaseException {
    private final Integer perguntaId;
    private final Integer opcaoId;

    public PerguntaNaoPossuiOpcaoException(Integer perguntaId, Integer opcaoId) {
        super(HttpStatus.NOT_ACCEPTABLE, "A pegunta indicada não possui a opção indicada.");
        this.perguntaId = perguntaId;
        this.opcaoId = opcaoId;
    }
}
