package com.db.apilivraria.services;

import com.db.apilivraria.dtos.LivroDto;
import com.db.apilivraria.models.Livro;
import com.db.apilivraria.repositories.LivroRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LivroServiceTest {

    @Mock
    private LivroRepository livroRepository;

    @InjectMocks
    private LivroService livroService;

    Livro livro;

    Livro livro2;

    LivroDto livroDto;

    @BeforeEach
    void setUp() {
        livro = new Livro();
        livro2 = new Livro();
        livroDto = new LivroDto();

        livro.setId(1L);
        livro.setNome("Clean code");
        livro.setIsbn("1254887978");

        livro2.setId(2L);
        livro2.setNome("Java e Orientacao a objetos");
        livro2.setIsbn("123");

        livroDto.setId(1L);
        livroDto.setNome("Alice no país das maravilhas");
        livroDto.setIsbn("1254787878");

    }

    @Test
    @DisplayName("Busca todos os livros no banco")
    void getAllLivros() {
        List<Livro> livros = Arrays.asList(livro, livro2);
        when(livroRepository.findAll()).thenReturn(livros);

        List<LivroDto> livroDtos = livroService.getAllLivros();

        assertEquals(2, livros.size());
        assertEquals("Clean code", livros.get(0).getNome());
        assertEquals("Java e Orientacao a objetos", livros.get(1).getNome());

        assertEquals("1254887978", livros.get(0).getIsbn());
    }

    @Test
    void criarLivro() {

        livroDto.getNome();
        livroDto.getId();
        livroDto.getIsbn();

        Livro livroSalvo = new Livro();
        livroSalvo.setId(1L);
        livroSalvo.setNome("Nova vida");
        livroSalvo.setIsbn("154789");

        when(livroRepository.save(any(Livro.class))).thenReturn(livroSalvo);

        var livroCriado = livroService.criarLivro(livroDto);

        assertEquals("Nova vida", livroCriado.getNome());
        assertEquals("154789", livroCriado.getIsbn());

    }

    @Test
    void atualizarLivro() {
        Long livroId = 1L;
        livroDto.setId(livroId);
        livroDto.setNome("Codigo limpo");

        when(livroRepository.findById(livroId)).thenReturn(Optional.of(livro));
        when(livroRepository.save(any(Livro.class))).thenReturn(livro);

        var livroAtualizado = livroService.atualizarLivro(livroId, livroDto);

        assertEquals(livroId, livroAtualizado.getId());
        assertEquals("Codigo limpo", livroAtualizado.getNome());
    }

    @Test
    void excluirlivro() {
        livro.getId();

        when(livroRepository.findById(livro.getId())).thenReturn(Optional.of(livro));

        livroService.excluirlivro(livro.getId());

        verify(livroRepository, times(1)).delete(livro);
    }
}