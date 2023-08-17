package com.db.apilivraria.controllers;

import com.db.apilivraria.dtos.LocatarioDto;
import com.db.apilivraria.services.LocatarioService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@AllArgsConstructor
@RestController
public class LocatarioController {

    private final LocatarioService locatarioService;

    @GetMapping("/locatarios")
    public ResponseEntity<List<LocatarioDto>> getAllLocatarios() {
        List<LocatarioDto> locatarioDtoList = locatarioService.getAllLocatarios();
        return ResponseEntity.ok().body(locatarioDtoList);
    }

    @PostMapping("/locatarios")
    public ResponseEntity<LocatarioDto> criarLocatario(@RequestBody LocatarioDto locatarioDto){
        LocatarioDto locatarioCriado = locatarioService.criarLocatario(locatarioDto);
        return new ResponseEntity<>(locatarioCriado, HttpStatus.CREATED);
    }

    @PutMapping("/locatario/{id}")
    public ResponseEntity<LocatarioDto> atualizarLocatario(@PathVariable("id") Long id, @RequestBody LocatarioDto locatarioDto) {
        LocatarioDto locatarioAtualizado = locatarioService.atualizarLocatario(id, locatarioDto);
        return ResponseEntity.ok(locatarioAtualizado);
    }

    @DeleteMapping("/locatario/{id}")
    public ResponseEntity<Void> excluirLocatario(@PathVariable("id") Long id) {
        locatarioService.excluirLocatario(id);
        return ResponseEntity.noContent().build();
    }
}
