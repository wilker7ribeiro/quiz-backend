package br.com.bb.big.bbweekddquizbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.ValidationException;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParticipanteDTO {
    @Email
    private String email;
    private String nome;
    private Integer id;

    @Pattern(regexp="[a-zA-Z]\\d{6,7}")
    private String matricula;
    private Boolean validado;

}