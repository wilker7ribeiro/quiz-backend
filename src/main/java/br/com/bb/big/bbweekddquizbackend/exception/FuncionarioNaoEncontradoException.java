package br.com.bb.big.bbweekddquizbackend.exception;

import org.springframework.http.HttpStatus;

import javax.validation.constraints.Pattern;

public class FuncionarioNaoEncontradoException extends BaseException {
    private final String matricula;

    public FuncionarioNaoEncontradoException(String matricula) {
        super(HttpStatus.NOT_ACCEPTABLE, "Funcionário com matrícula não encontrado");
        this.matricula = matricula;
    }
}
