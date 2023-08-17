package com.db.apilivraria.mappers;

import com.db.apilivraria.dtos.AluguelDto;
import com.db.apilivraria.models.Aluguel;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public interface AluguelMapper {

    static AluguelDto toDto(Aluguel aluguel){
        AluguelDto aluguelDto = new AluguelDto();
        BeanUtils.copyProperties(aluguel, aluguelDto, "locatario", "livros");

        aluguelDto.setLocatario(LocatarioMapper.toDto(aluguel.getLocatario()));


        aluguelDto.setLivros(aluguel.getLivros().stream()
                .map(LivroMapper::toDto)
                .collect(Collectors.toList()));

        return aluguelDto;
    }

    static Aluguel toEntity(AluguelDto aluguelDto) {
        Aluguel aluguel = new Aluguel();
        BeanUtils.copyProperties(aluguelDto, aluguel, "locatarioModel", "livros");


        aluguel.setLocatario(LocatarioMapper.toEntity(aluguelDto.getLocatario()));


        aluguel.setLivros(aluguelDto.getLivros().stream()
                .map(LivroMapper::toEntity)
                .collect(Collectors.toList()));

        return aluguel;
    }
}