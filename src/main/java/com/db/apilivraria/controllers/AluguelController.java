package com.db.apilivraria.controllers;

import com.db.apilivraria.dtos.AluguelDto;
import com.db.apilivraria.services.AluguelService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class AluguelController {

    private final AluguelService aluguelService;

    @PostMapping("/alugar")
    public ResponseEntity<AluguelDto> alugar(@RequestBody AluguelDto aluguelDto){
        AluguelDto alugado = aluguelService.alugarLivro(aluguelDto);
        return new ResponseEntity<>(alugado, HttpStatus.CREATED);
    }


    @DeleteMapping("/alugueis/{id}")
    public ResponseEntity<Void> excluirAluguel(@PathVariable("id") Long id) {
        aluguelService.excluirAluguel(id);
        return ResponseEntity.noContent().build();
    }
}
