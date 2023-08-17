package com.db.apilivraria.mappers;

import com.db.apilivraria.dtos.LivroDto;
import com.db.apilivraria.models.Livro;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public interface LivroMapper {

    static LivroDto toDto(Livro livro) {
        LivroDto livroDto = new LivroDto();
        BeanUtils.copyProperties(livro, livroDto, "autores");

        livroDto.setAutores(livro.getAutores().stream()
                .map(AutorMapper::toDto)
                .collect(Collectors.toList()));

        return livroDto;
    }

    static Livro toEntity(LivroDto livroDto) {
        Livro livro = new Livro();
        BeanUtils.copyProperties(livroDto, livro, "autores");


        livro.setAutores(livroDto.getAutores().stream()
                .map(AutorMapper::toEntity)
                .collect(Collectors.toList()));

        return livro;
    }

    static void updateEntityFromDto(LivroDto livroDto, Livro livro) {
        BeanUtils.copyProperties(livroDto, livro, "id", "autores");

        livro.setAutores(livroDto.getAutores().stream()
                .map(AutorMapper::toEntity)
                .collect(Collectors.toList()));
    }
}
