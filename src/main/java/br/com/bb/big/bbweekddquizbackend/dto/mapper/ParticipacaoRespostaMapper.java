package br.com.bb.big.bbweekddquizbackend.dto.mapper;

import br.com.bb.big.bbweekddquizbackend.dto.ParticipacaoDTO;
import br.com.bb.big.bbweekddquizbackend.dto.ParticipacaoRespostaDTO;
import br.com.bb.big.bbweekddquizbackend.dto.ParticipanteDTO;
import br.com.bb.big.bbweekddquizbackend.entity.Participacao;
import br.com.bb.big.bbweekddquizbackend.entity.ParticipacaoResposta;
import br.com.bb.big.bbweekddquizbackend.entity.Participante;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link ParticipacaoResposta} and its DTO {@link ParticipacaoRespostaDTO}.
 */
@Mapper(componentModel = "spring", uses = {PerguntaMapper.class, PerguntaOpcaoMapper.class})
public interface ParticipacaoRespostaMapper extends EntityMapper<ParticipacaoRespostaDTO, ParticipacaoResposta> {

}


