package com.db.apilivraria.controllers;

import com.db.apilivraria.dtos.AlugueDto;
import com.db.apilivraria.services.AluguelService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class AluguelController {

    private final AluguelService aluguelService;

//    @GetMapping("/list")
//    public ResponseEntity<List<AluguelDto>> getAllPessoas() {
//        List<LivroDto> livroDtos = aluguelService.;
//        return ResponseEntity.ok().body(livroDtos);
//    }


    @PostMapping("/alugar")
    public ResponseEntity<AlugueDto> alugar(@RequestBody AlugueDto alugueDto){
        AlugueDto aluguelado= aluguelService.alugarLivro(alugueDto);
        System.out.println("paramertro: " + alugueDto.toString());
        return new ResponseEntity<>(aluguelado, HttpStatus.CREATED);
    }

//    @PutMapping("/livros/{id}")
//    public ResponseEntity<LivroDto> atualizarLivro(@PathVariable("id") Long id, @RequestBody LivroDto livroDto) {
//        LivroDto livroAtualizado = livroService.atualizarLivro(id, livroDto);
//        return ResponseEntity.ok(livroAtualizado);
//    }
//
//    @DeleteMapping("/livros/{id}")
//    public ResponseEntity<Void> excluirlivro(@PathVariable("id") Long id) {
//        livroService.excluirlivro(id);
//        return ResponseEntity.noContent().build();
//    }
}
