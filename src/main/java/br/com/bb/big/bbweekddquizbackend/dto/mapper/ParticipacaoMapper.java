package br.com.bb.big.bbweekddquizbackend.dto.mapper;

import br.com.bb.big.bbweekddquizbackend.dto.ParticipacaoDTO;
import br.com.bb.big.bbweekddquizbackend.dto.ParticipanteDTO;
import br.com.bb.big.bbweekddquizbackend.entity.Participacao;
import br.com.bb.big.bbweekddquizbackend.entity.Participante;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity {@link Participacao} and its DTO {@link ParticipacaoDTO}.
 */
@Mapper(componentModel = "spring", uses = {QuizMapper.class, ParticipacaoRespostaMapper.class})
public interface ParticipacaoMapper extends EntityMapper<ParticipacaoDTO, Participacao> {
    @Mapping(target = "quizId", source = "quiz.id")
    ParticipacaoDTO toDto (Participacao entity, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

}


