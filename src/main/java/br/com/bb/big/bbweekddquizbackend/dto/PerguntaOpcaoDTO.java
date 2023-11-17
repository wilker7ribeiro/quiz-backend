package br.com.bb.big.bbweekddquizbackend.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PerguntaOpcaoDTO {

    private Integer id;
    private Integer perguntaId;
    private String descricao;
    @JsonIgnore
    private Boolean correta;

}
