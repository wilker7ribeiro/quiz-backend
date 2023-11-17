package br.com.bb.big.bbweekddquizbackend.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Pergunta implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false, length = 510)
    private String descricao;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "perguntaId")
    @EqualsAndHashCode.Exclude
    private Set<PerguntaOpcao> opcoes;

}
