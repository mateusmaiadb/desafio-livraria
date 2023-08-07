package com.db.apilivraria.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "livro")

public class LivroModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "nome obrigatorio")
    private String nome;

    @NotBlank(message = "isbn obrigatorio")
    private String isbn;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "livro_autor",
            joinColumns = @JoinColumn(name = "livro_id"),
            inverseJoinColumns = @JoinColumn(name = "autor_id")
    )
    private List<AutorModel> autores = new ArrayList<>();

    @JsonProperty("autores") // Anotação para incluir o campo na serialização JSON durante a consulta
    public List<AutorModel> getAutores() {
        return autores;
    }

    @JsonProperty("autores") // Anotação para incluir o campo na serialização JSON durante a consulta
    public void setAutores(List<AutorModel> autores) {
        this.autores = autores;
    }

    @Override
    public String toString() {
        return "LivroModel{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", isbn='" + isbn + '\'' +
                // ... outras informações relevantes, exceto a lista de autores
                '}';
    }


}
