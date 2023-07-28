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
    public ResponseEntity<AutorDto> getPessoaById(@PathVariable("id") Long id) {
        AutorDto autorDto = autorService.getPessoaById(id);
        return ResponseEntity.ok(autorDto);
    }

    @PostMapping("/autores")
    public ResponseEntity<AutorDto> criarPessoa(@RequestBody AutorDto autorDto) {
        AutorDto autorCriado = autorService.criarAutor(autorDto);
        return new ResponseEntity<>(autorCriado, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AutorDto> atualizarAutor(@PathVariable("id") Long id, @RequestBody AutorDto autorDto) {
        AutorDto autorAtualizado = autorService.atualizarAutor(id, autorDto);
        return ResponseEntity.ok(autorAtualizado);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirPessoa(@PathVariable("id") Long id) {
        autorService.excluirAutor(id);
        return ResponseEntity.noContent().build();
    }

}
