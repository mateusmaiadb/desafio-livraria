package com.db.apilivraria.services;

import com.db.apilivraria.dtos.AutorDto;
import com.db.apilivraria.models.Autor;
import com.db.apilivraria.repositories.AutorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AutorServiceTest {
    @Mock
    private AutorRepository autorRepository;

    @InjectMocks
    private AutorService autorService;

    Autor autor1;

    Autor autor2;

    AutorDto autorDto;

    @BeforeEach
    void setUp() {
        autor1 = new Autor();
        autor2 = new Autor();
        autorDto = new AutorDto();

        autor1.setId(1L);
        autor1.setNome("Mateus");
        autor1.setCpf("12356489782");
        autor1.setSexo("M");
        autor1.setAnoNascimento(LocalDate.of(1994, 7, 20));

        autor2.setId(2L);
        autor2.setNome("Paloma");
        autor2.setCpf("12356489765");
        autor2.setSexo("F");
        autor2.setAnoNascimento(LocalDate.of(1997, 9, 27));

        autorDto.setNome("Jonas");
        autorDto.setCpf("45256978435");
        autorDto.setSexo("M");
        autorDto.setAnoNascimento(LocalDate.of(1994, 7, 20));
    }

    @Test
    @DisplayName("Cria um novo autor")
    void criarAutor() {

        autorDto.getNome();
        autorDto.getCpf();
        autorDto.getSexo();
        autorDto.getAnoNascimento();

        Autor autorSalvo = new Autor();
        autorSalvo.setId(1L);
        autorSalvo.setNome("Mateus");
        autorSalvo.setCpf("78956214578");
        autorSalvo.setSexo("M");
        autorSalvo.setAnoNascimento(LocalDate.of(1998, 7, 20));

        when(autorRepository.save(any(Autor.class))).thenReturn(autorSalvo);

        var autorCriado = autorService.criarAutor(autorDto);

        assertEquals("Mateus", autorCriado.getNome());
        assertEquals("78956214578", autorCriado.getCpf());
        assertEquals(LocalDate.of(1998, 7, 20), autorCriado.getAnoNascimento());
        assertEquals("M", autorCriado.getSexo());
    }


    @Test
    @DisplayName("Retorna busca por id")
    void getPessoaById() {
        when(autorRepository.findById(1L)).thenReturn(Optional.of(autor1));
        var autor = autorService.getAutorById(1L);
        assertEquals("Mateus", autor1.getNome());
    }

    @Test
    @DisplayName("Lista todas as pessoas no banco")
    void getAllAutores() {
        List<Autor> autorList = Arrays.asList(autor1, autor2);
        when(autorRepository.findAll()).thenReturn(autorList);

        List<AutorDto> autorDtos = autorService.getAllAutores();

        assertEquals(2, autorList.size());
        assertEquals("Mateus", autorList.get(0).getNome());
        assertEquals("Paloma", autorList.get(1).getNome());
    }

    @Test
    @DisplayName("atualiza autor pelo id")
    void atualizarAutor() {
        Long autorId = 1L;
        autorDto.setId(autorId);
        autorDto.setNome("Pedro");

        when(autorRepository.findById(autorId)).thenReturn(Optional.of(autor1));
        when(autorRepository.save(any(Autor.class))).thenReturn(autor1);

        var autorAtualizadoDto = autorService.atualizarAutor(autorId, autorDto);

        assertEquals(autorId, autorAtualizadoDto.getId());
        assertEquals("Pedro", autorAtualizadoDto.getNome());

    }

    @Test
    @DisplayName("Exclui Autor por id")
    void excluirAutor() {
        autor1.getId();

        when(autorRepository.findById(autor1.getId())).thenReturn(Optional.of(autor1));

        autorService.excluirAutor(autor1.getId());

        verify(autorRepository, times(1)).delete(autor1);
    }
}