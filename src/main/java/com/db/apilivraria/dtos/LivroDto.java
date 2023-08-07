package com.db.apilivraria.dtos;

import com.db.apilivraria.models.AutorModel;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class LivroDto {

    @NotBlank
    private String nome;
    @NotBlank
    private String isbn;

    private List<AutorDto> autores;

    public LivroDto() {
        this.autores = new ArrayList<>();
    }
}
