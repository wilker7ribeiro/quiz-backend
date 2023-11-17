package br.com.bb.big.bbweekddquizbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RetornoResponderPerguntaDTO {
    private Integer participacaoId;
    private Integer perguntaId;
    private Integer opcaoId;
    private Boolean correta;
}
