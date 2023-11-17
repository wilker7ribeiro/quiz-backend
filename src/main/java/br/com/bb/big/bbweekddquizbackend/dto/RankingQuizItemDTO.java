package br.com.bb.big.bbweekddquizbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RankingQuizItemDTO {
    private Integer posicao;
    private String nome;
    private Long pontuacao;
}
