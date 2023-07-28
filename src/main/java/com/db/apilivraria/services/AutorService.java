package com.db.apilivraria.services;

import com.db.apilivraria.dtos.AutorDto;
import com.db.apilivraria.mappers.AutorMapper;
import com.db.apilivraria.models.AutorModel;
import com.db.apilivraria.repositories.AutorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class AutorService {

    private AutorRepository autorRepository;

    public AutorService(AutorRepository autorRepository){
        this.autorRepository = autorRepository;
    }

    public AutorDto criarAutor(AutorDto autorDto){
        AutorModel autorModel = AutorMapper.toEntity(autorDto);
        AutorModel autorSalvo = autorRepository.save(autorModel);
        return AutorMapper.toDto(autorSalvo);
    }

    public AutorDto getPessoaById(Long pessoaId) {
        Optional<AutorModel> autorModelOptional = autorRepository.findById(pessoaId);
        if (autorModelOptional.isPresent()) {
            return AutorMapper.toDto(autorModelOptional.get());
        }
        throw new IllegalArgumentException("Autor nao encontrado, id nao esta presente no banco de dados");
    }

    public List<AutorDto> getAllAutores(){
        List<AutorModel> autores = autorRepository.findAll();
        return autores.stream()
                .map(AutorMapper::toDto)
                .collect(Collectors.toList());
    }

    public AutorDto atualizarAutor(Long autorId, AutorDto autorDtoDTO) {
        Optional<AutorModel> autorModelOptional = autorRepository.findById(autorId);
        if (autorModelOptional.isPresent()) {
            AutorModel autorModel = autorModelOptional.get();
            AutorMapper.updateEntityFromDto(autorDtoDTO, autorModel);
            AutorModel autorAtualizado = autorRepository.save(autorModel);
            return AutorMapper.toDto(autorAtualizado);
        }
        throw new IllegalArgumentException("Autor não encontrado, esse ID nao se encontra em nossa base de dados");
    }

    public void excluirAutor(Long autorId){
        Optional<AutorModel> autorModelOptional = autorRepository.findById(autorId);
        if (autorModelOptional.isPresent()){
            autorRepository.delete(autorModelOptional.get());
        }else {
            throw new IllegalArgumentException("Autor nao encontrado, esse ID pode nao estar cadastrado em nosso banco de dados");
        }

    }

}
