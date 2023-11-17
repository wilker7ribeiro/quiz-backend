package br.com.bb.big.bbweekddquizbackend.dto.mapper;

import br.com.bb.big.bbweekddquizbackend.dto.ParticipanteDTO;
import br.com.bb.big.bbweekddquizbackend.dto.PerguntaDTO;
import br.com.bb.big.bbweekddquizbackend.entity.Participante;
import br.com.bb.big.bbweekddquizbackend.entity.Pergunta;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Pergunta} and its DTO {@link PerguntaDTO}.
 */
@Mapper(componentModel = "spring", uses = PerguntaOpcaoMapper.class)
public interface PerguntaMapper extends EntityMapper<PerguntaDTO, Pergunta> {

}


