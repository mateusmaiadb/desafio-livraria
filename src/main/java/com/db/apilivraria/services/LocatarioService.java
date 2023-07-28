package com.db.apilivraria.services;

import com.db.apilivraria.dtos.LocatarioDto;
import com.db.apilivraria.mappers.LocatarioMapper;
import com.db.apilivraria.models.LocatarioModel;
import com.db.apilivraria.repositories.LocatarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LocatarioService {

    private LocatarioRepository locatarioRepository;

    public LocatarioService(LocatarioRepository locatarioRepository){
        this.locatarioRepository = locatarioRepository;
    }


    public List<LocatarioDto> getAllLocatarios() {
        List<LocatarioModel> locatarioModels = locatarioRepository.findAll();
        return locatarioModels.stream()
                .map(LocatarioMapper::toDto)
                .collect(Collectors.toList());
    }

    public LocatarioDto criarLocatario(LocatarioDto locatarioDto){
        LocatarioModel locatarioModel = LocatarioMapper.toEntity(locatarioDto);
        LocatarioModel locatarioCriado = locatarioRepository.save(locatarioModel);
        return LocatarioMapper.toDto(locatarioCriado);
    }

    public LocatarioDto atualizarLocatario(Long locatarioId, LocatarioDto locatarioDto) {
        Optional<LocatarioModel> locatarioModelOptional = locatarioRepository.findById(locatarioId);
        if (locatarioModelOptional.isPresent()) {
            LocatarioModel locatarioModel = locatarioModelOptional.get();
            LocatarioMapper.updateEntityFromDto(locatarioDto, locatarioModel);
            LocatarioModel locatarioAtualizado = locatarioRepository.save(locatarioModel);
            return LocatarioMapper.toDto(locatarioAtualizado);
        }
        throw new IllegalArgumentException("Pessoa não encontrada, esse ID nao se encontra em nossa base de dados");
    }


    public void excluirPessoa(Long pessoaId) {
        Optional<LocatarioModel> locatarioModelOptional = locatarioRepository.findById(pessoaId);
        if (locatarioModelOptional.isPresent()) {
            locatarioRepository.delete(locatarioModelOptional.get());
        } else {
            throw new IllegalArgumentException("Locatario nao encontrado, esse ID pode nao estar cadastrado em nosso banco de dados");
        }
    }
}
