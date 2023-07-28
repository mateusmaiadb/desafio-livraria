package com.db.apilivraria.mappers;

import com.db.apilivraria.dtos.AutorDto;
import com.db.apilivraria.models.AutorModel;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public interface AutorMapper {

    static AutorDto toDto(AutorModel autorModel){
        AutorDto autorDto = new AutorDto();
        BeanUtils.copyProperties(autorModel, autorDto);
        return autorDto;
    }

    static AutorModel toEntity(AutorDto autorDto) {
        AutorModel autorModel = new AutorModel();
        BeanUtils.copyProperties(autorDto, autorModel);
        return autorModel;
    }

    static void updateEntityFromDto(AutorDto autorDto, AutorModel autorModel){
        BeanUtils.copyProperties(autorDto,autorModel, "id");
    }
}
