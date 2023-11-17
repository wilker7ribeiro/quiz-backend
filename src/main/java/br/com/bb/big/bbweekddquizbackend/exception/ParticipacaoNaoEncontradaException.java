package br.com.bb.big.bbweekddquizbackend.exception;

import org.springframework.http.HttpStatus;

public class ParticipacaoNaoEncontradaException extends BaseException {
    private Integer participacaoId;
    public ParticipacaoNaoEncontradaException(Integer participacaoId) {
        super(HttpStatus.NOT_FOUND, "Participação não encontrada para o ID especificado.");
        this.participacaoId = participacaoId;
    }
}