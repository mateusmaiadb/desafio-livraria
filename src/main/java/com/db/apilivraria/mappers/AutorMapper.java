package com.db.apilivraria.mappers;

import com.db.apilivraria.dtos.AutorDto;
import com.db.apilivraria.models.Autor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public interface AutorMapper {

    static AutorDto toDto(Autor autor){
        AutorDto autorDto = new AutorDto();
        BeanUtils.copyProperties(autor, autorDto);
        return autorDto;
    }

    static Autor toEntity(AutorDto autorDto) {
        Autor autor = new Autor();
        BeanUtils.copyProperties(autorDto, autor);
        return autor;
    }

    static void updateEntityFromDto(AutorDto autorDto, Autor autor){
        BeanUtils.copyProperties(autorDto, autor, "id");
    }
}
