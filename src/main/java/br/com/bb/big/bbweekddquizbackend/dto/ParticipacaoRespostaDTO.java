package br.com.bb.big.bbweekddquizbackend.dto;

import br.com.bb.big.bbweekddquizbackend.entity.Pergunta;
import br.com.bb.big.bbweekddquizbackend.entity.PerguntaOpcao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParticipacaoRespostaDTO {
    private PerguntaOpcaoDTO opcao;
    private PerguntaDTO pergunta;
    private LocalDateTime dataResposta;
}
