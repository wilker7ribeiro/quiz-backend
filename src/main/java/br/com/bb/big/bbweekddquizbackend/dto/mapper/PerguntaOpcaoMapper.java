package br.com.bb.big.bbweekddquizbackend.dto.mapper;

import br.com.bb.big.bbweekddquizbackend.dto.ParticipanteDTO;
import br.com.bb.big.bbweekddquizbackend.dto.PerguntaDTO;
import br.com.bb.big.bbweekddquizbackend.dto.PerguntaOpcaoDTO;
import br.com.bb.big.bbweekddquizbackend.entity.Participante;
import br.com.bb.big.bbweekddquizbackend.entity.Pergunta;
import br.com.bb.big.bbweekddquizbackend.entity.PerguntaOpcao;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link PerguntaOpcao} and its DTO {@link PerguntaOpcaoDTO}.
 */
@Mapper(componentModel = "spring")
public interface PerguntaOpcaoMapper extends EntityMapper<PerguntaOpcaoDTO, PerguntaOpcao> {

}


