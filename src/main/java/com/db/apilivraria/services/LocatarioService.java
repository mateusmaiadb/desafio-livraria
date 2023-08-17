package com.db.apilivraria.services;

import com.db.apilivraria.dtos.LocatarioDto;
import com.db.apilivraria.mappers.LocatarioMapper;
import com.db.apilivraria.models.Locatario;
import com.db.apilivraria.repositories.LocatarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LocatarioService {

    private final LocatarioRepository locatarioRepository;

    public LocatarioService(LocatarioRepository locatarioRepository) {
        this.locatarioRepository = locatarioRepository;
    }


    public List<LocatarioDto> getAllLocatarios() {
        List<Locatario> locatarios = locatarioRepository.findAll();
        return locatarios.stream()
                .map(LocatarioMapper::toDto)
                .collect(Collectors.toList());
    }

    public LocatarioDto criarLocatario(LocatarioDto locatarioDto) {
        Locatario locatario = LocatarioMapper.toEntity(locatarioDto);
        Locatario locatarioCriado = locatarioRepository.save(locatario);
        return LocatarioMapper.toDto(locatarioCriado);
    }

    public LocatarioDto atualizarLocatario(Long locatarioId, LocatarioDto locatarioDto) {
        Optional<Locatario> locatarioModelOptional = locatarioRepository.findById(locatarioId);
        if (locatarioModelOptional.isPresent()) {
            Locatario locatario = locatarioModelOptional.get();
            LocatarioMapper.updateEntityFromDto(locatarioDto, locatario);
            Locatario locatarioAtualizado = locatarioRepository.save(locatario);
            return LocatarioMapper.toDto(locatarioAtualizado);
        }
        throw new IllegalArgumentException("Pessoa não encontrada, esse ID nao se encontra em nossa base de dados");
    }

    public void excluirLocatario(Long id) {
        Optional<Locatario> locatarioModelOptional = locatarioRepository.findById(id);
        if (locatarioModelOptional.isPresent()) {
            locatarioRepository.delete(locatarioModelOptional.get());
        } else {
            throw new IllegalArgumentException("Locatario nao encontrado, esse ID pode nao estar cadastrado em nosso banco de dados");
        }
    }
}