package br.com.bb.big.bbweekddquizbackend.resource;

import br.com.bb.big.bbweekddquizbackend.dto.ParticipanteDTO;
import br.com.bb.big.bbweekddquizbackend.exception.BaseException;
import br.com.bb.big.bbweekddquizbackend.exception.ParticipanteEmailJaCadastradoException;
import br.com.bb.big.bbweekddquizbackend.exception.ParticipanteNaoEncontradoException;
import br.com.bb.big.bbweekddquizbackend.service.ParticipanteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/participantes")
public class ParticipanteResource {

    private final ParticipanteService service;


    public ParticipanteResource(final ParticipanteService service) {
        this.service = service;
    }

    @Operation(summary = "Obtém detalhes de informações sobre um participante")
    @GetMapping("/{email}")
    public ResponseEntity<ParticipanteDTO> consultaPorEmail(@PathVariable("email") String email) {
        return service.consultaPorEmail(email).map(ResponseEntity::ok).orElse(ResponseEntity.noContent().build());
    }
}
