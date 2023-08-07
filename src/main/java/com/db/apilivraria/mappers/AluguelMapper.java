package com.db.apilivraria.mappers;

import com.db.apilivraria.dtos.AlugueDto;
import com.db.apilivraria.models.AluguelModel;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public interface AluguelMapper {

    static AlugueDto toDto(AluguelModel aluguelModel){
        AlugueDto aluguelDto = new AlugueDto();
        BeanUtils.copyProperties(aluguelModel, aluguelDto, "locatario", "livros"); // Ignore the properties "locatario" and "livros"

        // Map the locatarioModel to locatarioDto
        aluguelDto.setLocatario(LocatarioMapper.toDto(aluguelModel.getLocatario()));

        // Map the list of livros to the list of livroDto
        aluguelDto.setLivros(aluguelModel.getLivros().stream()
                .map(LivroMapper::toDto)
                .collect(Collectors.toList()));

        return aluguelDto;
    }

    static AluguelModel toEntity(AlugueDto aluguelDto) {
        AluguelModel aluguelModel = new AluguelModel();
        BeanUtils.copyProperties(aluguelDto, aluguelModel, "locatarioModel", "livros"); // Ignore the properties "locatarioModel" and "livros"

        // Map the locatarioDto to locatarioModel
        aluguelModel.setLocatario(LocatarioMapper.toEntity(aluguelDto.getLocatario()));

        // Map the list of livroDto to the list of livros
        aluguelModel.setLivros(aluguelDto.getLivros().stream()
                .map(LivroMapper::toEntity)
                .collect(Collectors.toList()));

        return aluguelModel;
    }

//    static void updateEntityFromDto(AlugueDto aluguelDto, AluguelModel aluguelModel) {
//        BeanUtils.copyProperties(aluguelDto, aluguelModel, "id", "locatarioModel", "livros"); // Ignore the properties "id", "locatarioModel", and "livros"
//
//        // Map the locatarioDto to locatarioModel
//        aluguelModel.setLocatario(LocatarioMapper.toEntity(aluguelDto.getLocatarioModel()));
//
//        // Map the list of livroDto to the list of livros
//        aluguelModel.setLivros(aluguelDto.getLivros().stream()
//                .map(LivroMapper::toEntity)
//                .collect(Collectors.toList()));
//    }
}
