package com.db.apilivraria.mappers;

import com.db.apilivraria.dtos.LocatarioDto;
import com.db.apilivraria.models.Locatario;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public interface LocatarioMapper {

    static LocatarioDto toDto(Locatario locatario){
        LocatarioDto locatarioDto = new LocatarioDto();
        BeanUtils.copyProperties(locatario, locatarioDto);
        return locatarioDto;
    }

    static Locatario toEntity(LocatarioDto locatarioDto) {
        Locatario locatario = new Locatario();
        BeanUtils.copyProperties(locatarioDto, locatario);
        return locatario;
    }

    static void updateEntityFromDto(LocatarioDto locatarioDto, Locatario locatario){
        BeanUtils.copyProperties(locatarioDto, locatario, "id");
    }
}
