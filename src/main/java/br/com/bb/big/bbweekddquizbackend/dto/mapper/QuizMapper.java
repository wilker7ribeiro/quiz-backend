package br.com.bb.big.bbweekddquizbackend.dto.mapper;

import br.com.bb.big.bbweekddquizbackend.dto.ParticipanteDTO;
import br.com.bb.big.bbweekddquizbackend.dto.QuizDTO;
import br.com.bb.big.bbweekddquizbackend.entity.Participante;
import br.com.bb.big.bbweekddquizbackend.entity.Quiz;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Participante} and its DTO {@link ParticipanteDTO}.
 */
@Mapper(componentModel = "spring")
public interface QuizMapper extends EntityMapper<QuizDTO, Quiz> {

}


