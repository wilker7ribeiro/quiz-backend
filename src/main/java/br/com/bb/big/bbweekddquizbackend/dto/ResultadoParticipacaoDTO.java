package br.com.bb.big.bbweekddquizbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResultadoParticipacaoDTO {
    private Integer participacaoId;
    private Integer quantidadePerguntasCorretas;
    private Integer quantidadeTotalPerguntas;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private Long pontuacaoRespostasCorretas;
    private Long penalidadeRespostasIncorretas;
    private Double multiplicadorTempo;
    private Long pontuacaoTotalRespostas;
    private Long pontuacaoTotalTempo;
    private Long pontuacaoTotal;
}
