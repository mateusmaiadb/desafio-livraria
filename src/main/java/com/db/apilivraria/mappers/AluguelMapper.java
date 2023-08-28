package com.db.apilivraria.mappers;

import com.db.apilivraria.dtos.AluguelDto;
import com.db.apilivraria.dtos.LivroDto;
import com.db.apilivraria.models.Aluguel;
import com.db.apilivraria.models.Livro;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public interface AluguelMapper {

    static AluguelDto toDto(Aluguel aluguel){
        AluguelDto aluguelDto = new AluguelDto();

        aluguelDto.setId(aluguel.getId());
        aluguelDto.setDataRetirada(aluguel.getDataRetirada());
        aluguelDto.setDataDevolucao(aluguel.getDataDevolucao());

        aluguelDto.setLocatario(LocatarioMapper.toDto(aluguel.getLocatario()));

        List<LivroDto> livroDtos = aluguel.getLivros().stream()
                .map(LivroMapper::toDto)
                .collect(Collectors.toList());
        aluguelDto.setLivros(livroDtos);

        return aluguelDto;
    }

    static Aluguel toEntity(AluguelDto aluguelDto) {
        Aluguel aluguel = new Aluguel();

        BeanUtils.copyProperties(aluguelDto, aluguel, "locatario", "livros");

        aluguel.setLocatario(LocatarioMapper.toEntity(aluguelDto.getLocatario()));

        List<Livro> livros = aluguelDto.getLivros().stream()
                .map(LivroMapper::toEntity)
                .collect(Collectors.toList());

        return aluguel;
    }

    static void updateEntityFromDto(AluguelDto aluguelDto, Aluguel aluguel) {
        BeanUtils.copyProperties(aluguelDto, aluguel, "id");
        aluguel.setLocatario(LocatarioMapper.toEntity(aluguelDto.getLocatario()));
        aluguel.setLivros(aluguelDto.getLivros().stream()
                .map(LivroMapper::toEntity)
                .collect(Collectors.toList()));
    }
}