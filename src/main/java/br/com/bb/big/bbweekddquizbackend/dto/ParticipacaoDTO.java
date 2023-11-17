package br.com.bb.big.bbweekddquizbackend.dto;

import br.com.bb.big.bbweekddquizbackend.entity.ParticipacaoResposta;
import br.com.bb.big.bbweekddquizbackend.entity.Participante;
import br.com.bb.big.bbweekddquizbackend.entity.Quiz;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParticipacaoDTO {

    private Integer id;
    private Integer quizId;
    private ParticipanteDTO participante;
    private List<ParticipacaoRespostaDTO> respostas;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;

}
