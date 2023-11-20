package br.com.bb.big.bbweekddquizbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
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
    private BigDecimal pontuacaoRespostasCorretas;
    private BigDecimal multiplicadorRespostasIncorreta;
    private BigDecimal multiplicadorTempo;
    private BigDecimal pontuacaoTotalRespostas;
    private BigDecimal pontuacaoTotalTempo;
    private BigDecimal pontuacaoTotal;
}
