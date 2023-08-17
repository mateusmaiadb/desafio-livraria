package com.db.apilivraria.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;


@Data
public class AutorDto {

    private Long id;

    @NotBlank(message = "nome obrigatorio")
    private String nome;

    private String sexo;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate anoNascimento;

    @NotBlank(message = "cpf obrigatorio")
    private String cpf;
}
