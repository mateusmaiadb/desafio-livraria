package com.db.apilivraria.services;

import com.db.apilivraria.dtos.AutorDto;
import com.db.apilivraria.dtos.LivroDto;
import com.db.apilivraria.models.Autor;
import com.db.apilivraria.models.Livro;
import com.db.apilivraria.repositories.AutorRepository;
import com.db.apilivraria.repositories.LivroRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LivroServiceTest {

    @Mock
    private LivroRepository livroRepository;

    @Mock
    private AutorRepository autorRepository;

    @Mock
    AutorService autorService;

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

    @Test
    @DisplayName("Criar livro com autores existentes")
    void criarLivroWithExistingAutores() {
        LivroDto livroDto = new LivroDto();
        livroDto.setNome("Livro Teste");
        livroDto.setIsbn("1234567890");

        AutorDto autorDto1 = new AutorDto();
        autorDto1.setNome("Autor1");
        autorDto1.setCpf("11111111111");

        AutorDto autorDto2 = new AutorDto();
        autorDto2.setNome("Autor2");
        autorDto2.setCpf("22222222222");

        livroDto.setAutores(Arrays.asList(autorDto1, autorDto2));

        Autor autorExistente1 = new Autor();
        autorExistente1.setId(1L);
        autorExistente1.setNome("Autor1");
        autorExistente1.setCpf("11111111111");

        Autor autorExistente2 = new Autor();
        autorExistente2.setId(2L);
        autorExistente2.setNome("Autor2");
        autorExistente2.setCpf("22222222222");

        when(autorRepository.findByCpf(autorDto1.getCpf())).thenReturn(autorExistente1);
        when(autorRepository.findByCpf(autorDto2.getCpf())).thenReturn(autorExistente2);

        Livro livroSalvo = new Livro();
        livroSalvo.setId(1L);
        livroSalvo.setNome("Livro Teste");
        livroSalvo.setIsbn("1234567890");
        livroSalvo.setAutores(Arrays.asList(autorExistente1, autorExistente2));

        when(livroRepository.save(any(Livro.class))).thenReturn(livroSalvo);

        LivroDto livroCriado = livroService.criarLivro(livroDto);

        assertEquals("Livro Teste", livroCriado.getNome());
        assertEquals("1234567890", livroCriado.getIsbn());
        assertEquals(2, livroCriado.getAutores().size());
        assertEquals("Autor1", livroCriado.getAutores().get(0).getNome());
        assertEquals("Autor2", livroCriado.getAutores().get(1).getNome());
    }

    @Test
    @DisplayName("Excluir livro por ID não encontrado")
    void excluirLivroByIdNotFound() {
        Long livroId = 1L;

        when(livroRepository.findById(livroId)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> livroService.excluirlivro(livroId));
        verify(livroRepository, never()).delete(any(Livro.class));
    }

    @Test
    @DisplayName("Atualizar livro por ID não encontrado")
    void atualizarLivroByIdNotFound() {
        Long livroId = 1L;
        LivroDto livroDto = new LivroDto();
        livroDto.setNome("Livro Atualizado");
        livroDto.setIsbn("9876543210");

        when(livroRepository.findById(livroId)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> livroService.atualizarLivro(livroId, livroDto));
        verify(livroRepository, never()).save(any(Livro.class));
    }
}