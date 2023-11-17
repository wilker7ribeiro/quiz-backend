package br.com.bb.big.bbweekddquizbackend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ParticipacaoRespostaID implements Serializable {
    private Integer participacaoId;
    private Integer perguntaId;
    private Integer perguntaOpcaoId;
}
