package com.db.apilivraria.mappers;

import com.db.apilivraria.dtos.LivroDto;
import com.db.apilivraria.models.LivroModel;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public interface LivroMapper {

    static LivroDto toDto(LivroModel livroModel){
        LivroDto livroDto = new LivroDto();
        BeanUtils.copyProperties(livroModel, livroDto);
        return livroDto;
    }

    static LivroModel toEntity(LivroDto livroDto) {
        LivroModel livroModel = new LivroModel();
        BeanUtils.copyProperties(livroDto, livroModel);
        return livroModel;
    }

    static void updateEntityFromDto(LivroDto livroDto, LivroModel livroModel){
        BeanUtils.copyProperties(livroDto,livroModel, "id");
    }

}
