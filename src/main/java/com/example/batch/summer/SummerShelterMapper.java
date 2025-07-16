package com.example.batch.summer;


import com.example.batch.winter.WinterShelter;
import com.example.batch.winter.WinterShelterDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SummerShelterMapper {
    @Mapping(target="id", ignore=true)
    SummerShelter toEntity(SummerShelterDto dto);

    SummerShelterDto toDto(SummerShelter entity);

    List<SummerShelterDto> toDtoList(List<SummerShelter> entityList);
    @Mapping(target="id", ignore=true)
    List<SummerShelter> toEntityList(List<SummerShelterDto> dtoList);
}
