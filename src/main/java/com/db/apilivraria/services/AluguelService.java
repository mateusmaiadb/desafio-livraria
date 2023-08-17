package com.db.apilivraria.services;

import com.db.apilivraria.dtos.AluguelDto;
import com.db.apilivraria.mappers.AluguelMapper;
import com.db.apilivraria.models.Aluguel;
import com.db.apilivraria.models.Locatario;
import com.db.apilivraria.repositories.AluguelRepository;
import com.db.apilivraria.repositories.LocatarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AluguelService {

    private final AluguelRepository aluguelRepository;
    private final LocatarioRepository locatarioRepository;

    public AluguelService(AluguelRepository aluguelRepository, LocatarioRepository locatarioRepository) {
        this.aluguelRepository = aluguelRepository;
        this.locatarioRepository = locatarioRepository;
    }

    public List<AluguelDto> getAllAlugueis() {
        List<Aluguel> aluguels = aluguelRepository.findAll();
        return aluguels.stream()
                .map(AluguelMapper::toDto)
                .collect(Collectors.toList());
    }

    public AluguelDto alugarLivro(AluguelDto aluguelDto) {

        Aluguel aluguel = AluguelMapper.toEntity(aluguelDto);

        Locatario locatario = aluguel.getLocatario();

        if (locatario.getId() == null) {
            locatario = locatarioRepository.save(locatario);
            aluguel.setLocatario(locatario);
        }

        aluguel = aluguelRepository.save(aluguel);

        return AluguelMapper.toDto(aluguel);
    }

    public void excluirAluguel(Long id) {
        Optional<Aluguel> aluguelModel = aluguelRepository.findById(id);
        if (aluguelModel.isPresent()) {
            aluguelRepository.delete(aluguelModel.get());
        } else {
            throw new IllegalArgumentException("Locatario nao encontrado, esse ID pode nao estar cadastrado em nosso banco de dados");
        }
    }
}