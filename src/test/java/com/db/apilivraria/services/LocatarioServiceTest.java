package com.db.apilivraria.services;

import com.db.apilivraria.dtos.LocatarioDto;
import com.db.apilivraria.models.Locatario;
import com.db.apilivraria.repositories.LocatarioRepository;
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
class LocatarioServiceTest {

    @Mock
    private LocatarioRepository locatarioRepository;

    @InjectMocks
    private LocatarioService locatarioService;

    Locatario locatario;
    Locatario locatario2;

    LocatarioDto locatarioDto;

    @BeforeEach
    void setUp() {
        locatario = new Locatario();
        locatario2 = new Locatario();
        locatarioDto = new LocatarioDto();

        locatario.setId(1L);
        locatario.setNome("Mateus");
        locatario.setSexo("M");
        locatario.setCpf("12345678521");
        locatario.setEmail("mateus@gmail.com");
        locatario.setDataNascimento(LocalDate.of(1994,7,20));

        locatario2.setId(1L);
        locatario2.setNome("Reginaldo");
        locatario2.setSexo("M");
        locatario2.setCpf("85452635415");
        locatario2.setEmail("reginaldo@gmail.com");
        locatario2.setDataNascimento(LocalDate.of(1990,8,15));

        locatarioDto.setNome("Joana");
        locatarioDto.setDataNascimento(LocalDate.of(1997,9,27));
        locatarioDto.setCpf("45645123697");

    }

    @Test
    @DisplayName("Busca todos os locatarios")
    void getAllLocatarios() {
        List<Locatario> locatarios = Arrays.asList(locatario, locatario2);
        when(locatarioRepository.findAll()).thenReturn(locatarios);

        List<LocatarioDto> locatarioDtos = locatarioService.getAllLocatarios();

        assertEquals(2, locatarios.size());
        assertEquals("Mateus", locatarios.get(0).getNome());
        assertEquals("Reginaldo", locatarios.get(1).getNome());
    }

    @Test
    @DisplayName("Cria Locatario")
    void criarLocatario() {
        locatarioDto.getNome();
        locatarioDto.getCpf();
        locatarioDto.getDataNascimento();

        Locatario locatarioSalvo = new Locatario();
        locatarioSalvo.setId(1L);
        locatarioSalvo.setNome("Mateus");
        locatarioSalvo.setDataNascimento(LocalDate.of(1994,7,20));
        locatarioSalvo.setCpf("12456987452");

        when(locatarioRepository.save(any(Locatario.class))).thenReturn(locatarioSalvo);

        var locatarioCriado = locatarioService.criarLocatario(locatarioDto);

        assertEquals("Mateus", locatarioCriado.getNome());
        assertEquals(LocalDate.of(1994,7,20), locatarioCriado.getDataNascimento());
        assertEquals("12456987452", locatarioCriado.getCpf());
    }

    @Test
    @DisplayName("Atualiza locatario por id")
    void atualizarLocatario() {
        Long locatarioId = 1L;
        locatarioDto.setId(locatarioId);
        locatarioDto.setNome("Pedro");

        when(locatarioRepository.findById(locatarioId)).thenReturn(Optional.of(locatario));
        when(locatarioRepository.save(any(Locatario.class))).thenReturn(locatario);

        var locatarioAtualizadoDto = locatarioService.atualizarLocatario(locatarioId, locatarioDto);

        assertEquals(locatarioId, locatarioAtualizadoDto.getId());
        assertEquals("Pedro", locatarioAtualizadoDto.getNome());
    }

    @Test
    @DisplayName("Exclui locatario pelo id")
    void excluirLocatario() {
        locatario.getId();

        when(locatarioRepository.findById(locatario.getId())).thenReturn(Optional.of(locatario));

        locatarioService.excluirLocatario(locatario.getId());

        verify(locatarioRepository, times(1)).delete(locatario);
    }

    @Test
    @DisplayName("Excluir locatário não encontrado")
    void testExcluirLocatarioNaoEncontrado() {
        Long locatarioId = 1L;
        when(locatarioRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> locatarioService.excluirLocatario(locatarioId));
    }
}