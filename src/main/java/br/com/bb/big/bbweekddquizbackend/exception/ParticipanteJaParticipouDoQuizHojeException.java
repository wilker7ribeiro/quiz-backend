package br.com.bb.big.bbweekddquizbackend.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDate;

public class ParticipanteJaParticipouDoQuizHojeException extends BaseException{
    private final Integer quizId;
    private final Integer participanteId;
    private final Integer participacaoId;
    private final LocalDate data;

    public ParticipanteJaParticipouDoQuizHojeException(Integer quizId, Integer participanteId, Integer participacaoId, LocalDate data) {
        super(HttpStatus.CONFLICT, "O participante já participou deste quiz hoje.");
        this.quizId = quizId;
        this.participanteId = participanteId;
        this.participacaoId = participacaoId;
        this.data = data;
    }
}
