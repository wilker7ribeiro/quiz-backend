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
public class Colaborador implements Serializable {
    @Id
    private String chave;

    @Column(nullable = false)
    private String nome;

}
