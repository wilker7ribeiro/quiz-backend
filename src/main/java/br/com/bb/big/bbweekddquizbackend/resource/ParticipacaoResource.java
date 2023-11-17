package br.com.bb.big.bbweekddquizbackend.resource;

import br.com.bb.big.bbweekddquizbackend.dto.ParticipacaoDTO;
import br.com.bb.big.bbweekddquizbackend.dto.ParticipanteDTO;
import br.com.bb.big.bbweekddquizbackend.dto.ResultadoParticipacaoDTO;
import br.com.bb.big.bbweekddquizbackend.dto.RetornoResponderPerguntaDTO;
import br.com.bb.big.bbweekddquizbackend.exception.*;
import br.com.bb.big.bbweekddquizbackend.service.ParticipacaoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/participacao")
public class ParticipacaoResource {

    private final ParticipacaoService service;

    public ParticipacaoResource(final ParticipacaoService service) {
        this.service = service;
    }

    @Operation(summary = "Inicia uma participação")
    @PostMapping("/quiz/{quizId}")
    public ResponseEntity<ParticipacaoDTO> iniciarParticipacao(@PathVariable("quizId") Integer quizId, @Valid @RequestBody ParticipanteDTO participanteDTO) throws BaseException{
        return ResponseEntity.ok(this.service.iniciarParticipacao(quizId, participanteDTO));
    }

    @Operation(summary = "Obtém detalhes de informações sobre um participante")
    @GetMapping("/{participacaoId}")
    public ResponseEntity<ParticipacaoDTO> consultarParticipacao(@PathVariable("participacaoId") Integer participacaoId) {
        return service.consultarParticipacao(participacaoId).map(ResponseEntity::ok).orElse(ResponseEntity.noContent().build());
    }


    @Operation(summary = "Responde uma pergunta")
    @PostMapping("/{participacaoId}/responder/{perguntaId}/opcao/{opcaoId}")
    public ResponseEntity<RetornoResponderPerguntaDTO> responderPergunta(
            @PathVariable("participacaoId") Integer participacaoId,
            @PathVariable("perguntaId") Integer perguntaId,
            @PathVariable("opcaoId") Integer opcaoId
    ) throws BaseException {
        return ResponseEntity.ok(this.service.responderPergunta(participacaoId, perguntaId, opcaoId));
    }

    @Operation(summary = "Obter o resultado da participação")
    @GetMapping("/resultado/{participacaoId}")
    public ResponseEntity<ResultadoParticipacaoDTO> obterResultadoParticipacao(
            @PathVariable("participacaoId") Integer participacaoId
    ) throws BaseException {
        return ResponseEntity.ok(this.service.obterResultadoParticipacao(participacaoId));
    }

}
