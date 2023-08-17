package com.db.apilivraria.controllers;

import com.db.apilivraria.dtos.AutorDto;
import com.db.apilivraria.services.AutorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class AutorController {

    private final AutorService autorService;

    @GetMapping("/autores")
    public ResponseEntity<List<AutorDto>> getAllAutores(){
        List<AutorDto> autorDtoList = autorService.getAllAutores();
        return ResponseEntity.ok().body(autorDtoList);
    }

    @GetMapping("/autores/{id}")
    public ResponseEntity<AutorDto> getAutorById(@PathVariable("id") Long id) {
        AutorDto autorDto = autorService.getAutorById(id);
        return ResponseEntity.ok(autorDto);
    }

    @PostMapping("/autores")
    public ResponseEntity<AutorDto> criarAutor(@RequestBody AutorDto autorDto) {
        AutorDto autorCriado = autorService.criarAutor(autorDto);
        return new ResponseEntity<>(autorCriado, HttpStatus.CREATED);
    }

    @PutMapping("/autores/{id}")
    public ResponseEntity<AutorDto> atualizarAutor(@PathVariable("id") Long id, @RequestBody AutorDto autorDto) {
        AutorDto autorAtualizado = autorService.atualizarAutor(id, autorDto);
        return ResponseEntity.ok(autorAtualizado);
    }


    @DeleteMapping("/autores/{id}")
    public ResponseEntity<Void> excluirAutor(@PathVariable("id") Long id) {
        autorService.excluirAutor(id);
        return ResponseEntity.noContent().build();
    }

}
