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
@Entity
public class ParticipacaoResposta implements Serializable {

    @EmbeddedId
    private ParticipacaoRespostaID id;

    @ManyToOne
    @MapsId("participacaoId")
    private Participacao participacao;

    @ManyToOne
    @MapsId("perguntaOpcaoId")
    private PerguntaOpcao opcao;

    @ManyToOne
    @MapsId("perguntaId")
    private Pergunta pergunta;

    @CreatedDate()
    private LocalDateTime dataResposta;

}
