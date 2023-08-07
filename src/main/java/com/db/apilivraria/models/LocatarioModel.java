package com.db.apilivraria.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "locatario")

public class LocatarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    private String sexo;

    @NotBlank(message = "email obrigatorio")
    @Column(unique = true)
    @Email(message = "formato de email invalido")
    private String email;

    @NotBlank(message = "dataNascimento obrigatorio")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

    @NotBlank
    @Column(unique = true, length = 11)
    private String cpf;
}
