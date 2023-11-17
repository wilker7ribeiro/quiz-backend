package br.com.bb.big.bbweekddquizbackend.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class HttpErrorResponse {
    private Integer code;
    private String mensagem;
}
