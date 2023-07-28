package com.db.apilivraria.dtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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

    @NotBlank(message = "dataNascimento obrigatorio")
    private LocalDate dataNascimento;

    @NotBlank
    @Column(unique = true, length = 11)
    private String cpf;
}
