package br.com.bb.big.bbweekddquizbackend.resource;

import br.com.bb.big.bbweekddquizbackend.dto.ParticipanteDTO;
import br.com.bb.big.bbweekddquizbackend.dto.QuizDTO;
import br.com.bb.big.bbweekddquizbackend.dto.RankingQuizItemDTO;
import br.com.bb.big.bbweekddquizbackend.exception.BaseException;
import br.com.bb.big.bbweekddquizbackend.exception.QuizNaoEncontradoException;
import br.com.bb.big.bbweekddquizbackend.service.QuizService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizResource {

    private final QuizService service;
    public QuizResource(final QuizService service) {
        this.service = service;
    }
    @GetMapping("/{quizId}/ranking/dia/{dia}")
    public ResponseEntity<List<RankingQuizItemDTO>> consultarRankingQuiz(
            @PathVariable("quizId") Integer quizId, @PathVariable("dia") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dia
    ) {
        return ResponseEntity.ok(service.obterRankingPorQuizIdAndDia(quizId, dia));
    }

    @GetMapping("/{quizId}")
    public ResponseEntity<QuizDTO> consultarQuiz(
            @PathVariable("quizId") Integer quizId
    ) throws BaseException {
        return ResponseEntity.ok(service.consultarQuiz(quizId));
    }
}
