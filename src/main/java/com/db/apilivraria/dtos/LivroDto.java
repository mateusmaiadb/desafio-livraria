package com.db.apilivraria.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LivroDto {

    @NotBlank
    private String nome;
    @NotBlank
    private String isbn;
}
