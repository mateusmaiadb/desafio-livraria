package com.db.apilivraria.controllers;

import com.db.apilivraria.dtos.LivroDto;
import com.db.apilivraria.services.LivroService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
public class LivroController {

    private final LivroService livroService;


    @GetMapping("/list")
    public ResponseEntity<List<LivroDto>> getAllPessoas() {
        List<LivroDto> livroDtos = livroService.getAllPessoas();
        return ResponseEntity.ok().body(livroDtos);
    }


    @PostMapping("/livros")
    public ResponseEntity<LivroDto> criarLivro(@RequestBody LivroDto livroDto){
        LivroDto livroCriado = livroService.criarLivro(livroDto);
        return new ResponseEntity<>(livroCriado, HttpStatus.CREATED);
    }

    @PutMapping("/livros/{id}")
    public ResponseEntity<LivroDto> atualizarLivro(@PathVariable("id") Long id, @RequestBody LivroDto livroDto) {
        LivroDto livroAtualizado = livroService.atualizarLivro(id, livroDto);
        return ResponseEntity.ok(livroAtualizado);
    }

    @DeleteMapping("/livros/{id}")
    public ResponseEntity<Void> excluirlivro(@PathVariable("id") Long id) {
        livroService.excluirlivro(id);
        return ResponseEntity.noContent().build();
    }


}
