package com.db.apilivraria.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "autor")
public class Autor {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "nome obrigatorio")
    private String nome;

    private String sexo;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate anoNascimento;

    @NotBlank(message = "cpf obrigatorio")
    @Column(unique = true, length = 11)
    private String cpf;

    @ManyToMany(mappedBy = "autores")
    private List<Livro> livros = new ArrayList<>();
}
