package br.com.bb.big.bbweekddquizbackend.entity;

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
@Entity
public class PerguntaOpcao implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private Integer perguntaId;

    @Column(nullable = false, length = 510)
    private String descricao;

    @Column(nullable = false)
    private Boolean correta;


}
