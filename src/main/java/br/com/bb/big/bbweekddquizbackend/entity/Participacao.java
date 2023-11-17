package br.com.bb.big.bbweekddquizbackend.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Participacao implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="quizId", nullable=false)
    private Quiz quiz;

    @ManyToOne
    @JoinColumn(name="participanteId", nullable=false)
    private Participante participante;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "participacao")
    @EqualsAndHashCode.Exclude
    private Set<ParticipacaoResposta> respostas;

    @Column
    private LocalDateTime dataInicio;

    @Column
    private LocalDateTime dataFim;

}
