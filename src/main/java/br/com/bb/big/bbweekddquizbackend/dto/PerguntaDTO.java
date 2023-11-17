package br.com.bb.big.bbweekddquizbackend.dto;

import br.com.bb.big.bbweekddquizbackend.entity.PerguntaOpcao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PerguntaDTO  {
    private Integer id;
    private String descricao;
    private List<PerguntaOpcaoDTO> opcoes;
}
