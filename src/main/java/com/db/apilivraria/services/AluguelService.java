package com.db.apilivraria.services;

import com.db.apilivraria.dtos.AlugueDto;
import com.db.apilivraria.mappers.AluguelMapper;
import com.db.apilivraria.models.AluguelModel;
import com.db.apilivraria.models.LocatarioModel;
import com.db.apilivraria.repositories.AluguelRepository;
import com.db.apilivraria.repositories.LocatarioRepository;
import org.springframework.stereotype.Service;

@Service
public class AluguelService {

    private AluguelRepository aluguelRepository;
    private LocatarioRepository locatarioRepository;

    public AluguelService(AluguelRepository aluguelRepository, LocatarioRepository locatarioRepository) {
        this.aluguelRepository = aluguelRepository;
        this.locatarioRepository = locatarioRepository;
    }

    public AlugueDto alugarLivro(AlugueDto alugueDto) {
        AluguelModel aluguelModel = AluguelMapper.toEntity(alugueDto);

        LocatarioModel locatarioModel = aluguelModel.getLocatario();
        if (locatarioModel.getId() == null) {
            // If the locatario is not yet persisted, save it first
            locatarioModel = locatarioRepository.save(locatarioModel);
            aluguelModel.setLocatario(locatarioModel); // Set the persisted locatario to the aluguel
        }

        aluguelModel = aluguelRepository.save(aluguelModel);

        return AluguelMapper.toDto(aluguelModel);
    }
}
