package com.db.apilivraria.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class LocatarioDto {

    @NotBlank
    private String nome;

    private String sexo;

    @NotBlank(message = "email obrigatorio")
    @Email(message = "formato de email invalido")
    private String email;

    @NotNull(message = "dataNascimento obrigatorio") // Use @NotNull for LocalDate fields
    private LocalDate dataNascimento;

    @NotBlank
    @Column(unique = true, length = 11)
    private String cpf;

    // Getters and setters...
}