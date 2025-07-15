package com.example.batch.winter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WinterShelterMapper {
    @Mapping(target="id", ignore=true)
    WinterShelter toEntity(WinterShelterDto dto);

    WinterShelterDto toDto(WinterShelter entity);

    List<WinterShelterDto> toDtoList(List<WinterShelter> entityList);
    @Mapping(target="id", ignore=true)
    List<WinterShelter> toEntityList(List<WinterShelterDto> dtoList);
}
