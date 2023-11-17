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
public class Participante implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @Column(nullable = true)
    private String nome;

    @Column( nullable = true)
    private String email;

    @Column(nullable = true)
    private String matricula;

    @Column(nullable = false)
    private Boolean validado;


}
