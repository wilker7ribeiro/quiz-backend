package br.com.bb.big.bbweekddquizbackend.exception;

import org.springframework.http.HttpStatus;

public class QuizNaoPossuiPerguntaException extends BaseException {
    private final Integer quizId;
    private final Integer perguntaId;

    public QuizNaoPossuiPerguntaException(Integer quizId, Integer perguntaId) {
        super(HttpStatus.NOT_ACCEPTABLE, "O quiz indicado não possui a pergunta indicada.");
        this.quizId = quizId;
        this.perguntaId = perguntaId;
    }
}
