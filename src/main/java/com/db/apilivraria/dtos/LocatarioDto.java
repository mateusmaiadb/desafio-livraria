package com.db.apilivraria.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class LocatarioDto {

    Long id;

    @NotBlank
    private String nome;

    private String sexo;

    @NotBlank(message = "email obrigatorio")
    @Email(message = "formato de email invalido")
    private String email;

    @NotNull(message = "dataNascimento obrigatorio")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

    @NotBlank
    @Column(unique = true, length = 11)
    private String cpf;
}