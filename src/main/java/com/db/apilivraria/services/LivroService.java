package com.db.apilivraria.services;

import com.db.apilivraria.dtos.AutorDto;
import com.db.apilivraria.dtos.LivroDto;
import com.db.apilivraria.mappers.LivroMapper;
import com.db.apilivraria.models.Autor;
import com.db.apilivraria.models.Livro;
import com.db.apilivraria.repositories.AutorRepository;
import com.db.apilivraria.repositories.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LivroService {

    private final LivroRepository livroRepository;
    private final AutorRepository autorRepository;

    public LivroService(LivroRepository livroRepository, AutorRepository autorRepository) {
        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;
    }

    public List<LivroDto> getAllLivros() {
        List<Livro> livros = livroRepository.findAll();
        return livros.stream()
                .map(LivroMapper::toDto)
                .collect(Collectors.toList());
    }

    public LivroDto criarLivro(LivroDto livroDto) {

        Livro livro = new Livro();
        livro.setNome(livroDto.getNome());
        livro.setIsbn(livroDto.getIsbn());

        List<Autor> autoresPersistidos = new ArrayList<>();

        for (AutorDto autorDto : livroDto.getAutores()) {
            Autor autor = new Autor();
            autor.setNome(autorDto.getNome());
            autor.setCpf(autorDto.getCpf());


            Autor autorExistente = autorRepository.findByCpf(autorDto.getCpf());

            if (autorExistente != null) {
                autoresPersistidos.add(autorExistente);
            } else {
                autorRepository.save(autor);
                autoresPersistidos.add(autor);
            }
        }

        livro.setAutores(autoresPersistidos);

        Livro livroCriado = livroRepository.save(livro);

        return LivroMapper.toDto(livroCriado);
    }

    public LivroDto atualizarLivro(Long livroId, LivroDto livroDto) {
        Optional<Livro> livroModelOptional = livroRepository.findById(livroId);
        if (livroModelOptional.isPresent()) {
            Livro livro = livroModelOptional.get();
            LivroMapper.updateEntityFromDto(livroDto, livro);
            Livro livroAtualizado = livroRepository.save(livro);
            return LivroMapper.toDto(livroAtualizado);
        }
        throw new IllegalArgumentException("Livro não encontrado, esse ID nao se encontra em nossa base de dados");
    }

    public void excluirlivro(Long livroId) {
        Optional<Livro> livroModelOptional = livroRepository.findById(livroId);
        if (livroModelOptional.isPresent()) {
            livroRepository.delete(livroModelOptional.get());
        } else {
            throw new IllegalArgumentException("Livro nao encontrado, esse ID pode nao estar cadastrado em nosso banco de dados");
        }
    }
}