package com.db.apilivraria.services;

import com.db.apilivraria.dtos.LivroDto;
import com.db.apilivraria.mappers.LivroMapper;
import com.db.apilivraria.models.LivroModel;
import com.db.apilivraria.repositories.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LivroService {

    private LivroRepository livroRepository;

    public LivroService(LivroRepository livroRepository){
        this.livroRepository = livroRepository;
    }

    public List<LivroDto> getAllPessoas() {
        List<LivroModel> livros = livroRepository.findAll();
        return livros.stream()
                .map(LivroMapper::toDto)
                .collect(Collectors.toList());
    }

    public LivroDto criarLivro(LivroDto livroDto){
        LivroModel livroModel = LivroMapper.toEntity(livroDto);
        LivroModel livroSalvo = livroRepository.save(livroModel);
        return LivroMapper.toDto(livroSalvo);
    }

    public LivroDto atualizarLivro(Long livroId, LivroDto livroDto) {
        Optional<LivroModel> livroModelOptional = livroRepository.findById(livroId);
        if (livroModelOptional.isPresent()) {
            LivroModel livroModel = livroModelOptional.get();
            LivroMapper.updateEntityFromDto(livroDto, livroModel);
            LivroModel livroAtualizado = livroRepository.save(livroModel);
            return LivroMapper.toDto(livroAtualizado);
        }
        throw new IllegalArgumentException("Livro não encontrado, esse ID nao se encontra em nossa base de dados");
    }

    public void excluirlivro(Long livroId) {
        Optional<LivroModel> livroModelOptional = livroRepository.findById(livroId);
        if (livroModelOptional.isPresent()) {
            livroRepository.delete(livroModelOptional.get());
        } else {
            throw new IllegalArgumentException("Livro nao encontrado, esse ID pode nao estar cadastrado em nosso banco de dados");
        }
    }


}
