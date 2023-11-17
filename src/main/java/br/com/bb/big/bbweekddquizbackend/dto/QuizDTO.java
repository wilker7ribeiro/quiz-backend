package br.com.bb.big.bbweekddquizbackend.dto;

import br.com.bb.big.bbweekddquizbackend.entity.Pergunta;
import lombok.*;

import javax.persistence.*;
import java.util.Set;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuizDTO {

    private Integer id;
    private String nome;
    private Set<PerguntaDTO> perguntas;

}
