package br.com.bb.big.bbweekddquizbackend.dto.mapper;

import org.mapstruct.*;

import java.util.List;
public interface EntityMapper<D, E> {
    E toEntity(D dto, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    @MapstructIgnore
    default E toEntity(D dto) {
        return toEntity(dto, new CycleAvoidingMappingContext());
    }

    D toDto (E entity, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);
    @MapstructIgnore
    default D toDto(E entity) {
        return toDto(entity, new CycleAvoidingMappingContext());
    }

    List<E> toEntity(List<D> dtoList, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    @MapstructIgnore
    default List<E> toEntity(List<D> dtoList) {
        return toEntity(dtoList, new CycleAvoidingMappingContext());
    }

    List<D> toDto(List<E> entityList, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    default List<D> toDto(List<E> entityList) {
        return toDto(entityList, new CycleAvoidingMappingContext());
    }

    @Named("partialUpdate")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void partialUpdate(@MappingTarget E entity, D dto, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);
    @MapstructIgnore
    default void partialUpdate(@MappingTarget E entity, D dto) {
        partialUpdate(entity, dto, new CycleAvoidingMappingContext());
    }
}