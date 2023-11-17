package br.com.bb.big.bbweekddquizbackend.exception;

import org.springframework.http.HttpStatus;

public class PerguntaNaoEncontradaException extends BaseException {
    private Integer perguntaId;
    public PerguntaNaoEncontradaException(Integer perguntaId) {
        super(HttpStatus.NOT_FOUND, "Pergunta não encontrada para o ID especificado.");
        this.perguntaId = perguntaId;
    }
}