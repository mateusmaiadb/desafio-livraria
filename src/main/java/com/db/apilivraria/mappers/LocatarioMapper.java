package com.db.apilivraria.mappers;

import com.db.apilivraria.dtos.LocatarioDto;
import com.db.apilivraria.models.LocatarioModel;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public interface LocatarioMapper {

    static LocatarioDto toDto(LocatarioModel locatarioModel){
        LocatarioDto locatarioDto = new LocatarioDto();
        BeanUtils.copyProperties(locatarioModel, locatarioDto);
        return locatarioDto;
    }

    static LocatarioModel toEntity(LocatarioDto locatarioDto) {
        LocatarioModel locatarioModel = new LocatarioModel();
        BeanUtils.copyProperties(locatarioDto, locatarioModel);
        return locatarioModel;
    }

    static void updateEntityFromDto(LocatarioDto locatarioDto, LocatarioModel locatarioModel){
        BeanUtils.copyProperties(locatarioDto,locatarioModel, "id");
    }
}
