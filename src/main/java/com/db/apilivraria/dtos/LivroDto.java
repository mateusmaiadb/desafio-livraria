package com.db.apilivraria.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class LivroDto {

    Long id;

    @NotBlank
    private String nome;
    @NotBlank
    private String isbn;

    private List<AutorDto> autores;

    public LivroDto() {
        this.autores = new ArrayList<>();
    }
}
