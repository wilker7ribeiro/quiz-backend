package br.com.bb.big.bbweekddquizbackend.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class QuizNaoEncontradoException extends BaseException {
    private Integer quizId;
    public QuizNaoEncontradoException(Integer quizId) {
        super(HttpStatus.NOT_FOUND, "Não foi encontrado o quiz com o id passado.");
        this.quizId = quizId;
    }
}
