package com.db.apilivraria.services;

import com.db.apilivraria.dtos.AutorDto;
import com.db.apilivraria.dtos.LivroDto;
import com.db.apilivraria.mappers.LivroMapper;
import com.db.apilivraria.models.AutorModel;
import com.db.apilivraria.models.LivroModel;
import com.db.apilivraria.repositories.AutorRepository;
import com.db.apilivraria.repositories.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LivroService {

    private LivroRepository livroRepository;
    private AutorRepository autorRepository;

    public LivroService(LivroRepository livroRepository, AutorRepository autorRepository){
        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;
    }

    public List<LivroDto> getAllPessoas() {
        List<LivroModel> livros = livroRepository.findAll();
        return livros.stream()
                .map(LivroMapper::toDto)
                .collect(Collectors.toList());
    }

//    public LivroDto criarLivro(LivroDto livroDto){
//        LivroModel livroModel = LivroMapper.toEntity(livroDto);
//        LivroModel livroSalvo = livroRepository.save(livroModel);
//        return LivroMapper.toDto(livroSalvo);
//    }

    public LivroDto criarLivro(LivroDto livroDto) {
        LivroModel livroModel = new LivroModel();
        livroModel.setNome(livroDto.getNome());
        livroModel.setIsbn(livroDto.getIsbn());

        // Lista para armazenar os autores persistidos ou já existentes no banco de dados
        List<AutorModel> autoresPersistidos = new ArrayList<>();

        // Percorrendo a lista de autores do livroDto
        for (AutorDto autorDto : livroDto.getAutores()) {
            AutorModel autorModel = new AutorModel();
            autorModel.setNome(autorDto.getNome());
            autorModel.setCpf(autorDto.getCpf());

            // Verificando se o autor já existe no banco de dados pelo CPF
            AutorModel autorExistente = autorRepository.findByCpf(autorDto.getCpf());

            if (autorExistente != null) {
                // Se o autor já existe, adicionamos na lista de autores persistidos
                autoresPersistidos.add(autorExistente);
            } else {
                // Se o autor não existe, salvamos no banco de dados e adicionamos na lista de autores persistidos
                autorRepository.save(autorModel);
                autoresPersistidos.add(autorModel);
            }
        }

        // Associando os autores persistidos ao livro
        livroModel.setAutores(autoresPersistidos);

        System.out.println(livroModel.getAutores().get(1));

        // Salvando o livro no banco de dados
        LivroModel livroCriado = livroRepository.save(livroModel);

        return LivroMapper.toDto(livroCriado);
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
