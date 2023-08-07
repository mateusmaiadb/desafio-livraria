package com.db.apilivraria.mappers;

import com.db.apilivraria.dtos.LivroDto;
import com.db.apilivraria.models.LivroModel;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public interface LivroMapper {

    static LivroDto toDto(LivroModel livroModel) {
        LivroDto livroDto = new LivroDto();
        BeanUtils.copyProperties(livroModel, livroDto, "autores"); // Ignorar a propriedade "autores"

        // Mapear a lista de autores de LivroModel para a lista de AutorDto
        livroDto.setAutores(livroModel.getAutores().stream()
                .map(AutorMapper::toDto) // Usar o mapper de AutorModel para AutorDto
                .collect(Collectors.toList()));

        return livroDto;
    }

    static LivroModel toEntity(LivroDto livroDto) {
        LivroModel livroModel = new LivroModel();
        BeanUtils.copyProperties(livroDto, livroModel, "autores"); // Ignorar a propriedade "autores"

        // Mapear a lista de autores de AutorDto para a lista de AutorModel
        livroModel.setAutores(livroDto.getAutores().stream()
                .map(AutorMapper::toEntity) // Usar o mapper de AutorDto para AutorModel
                .collect(Collectors.toList()));

        return livroModel;
    }

    static void updateEntityFromDto(LivroDto livroDto, LivroModel livroModel) {
        BeanUtils.copyProperties(livroDto, livroModel, "id", "autores"); // Ignorar as propriedades "id" e "autores"

        // Mapear a lista de autores de AutorDto para a lista de AutorModel
        livroModel.setAutores(livroDto.getAutores().stream()
                .map(AutorMapper::toEntity) // Usar o mapper de AutorDto para AutorModel
                .collect(Collectors.toList()));
    }
}
