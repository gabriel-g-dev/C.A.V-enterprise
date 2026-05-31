package br.com.fiap.satguard.service;

import br.com.fiap.satguard.model.Satelite;
import br.com.fiap.satguard.dto.SateliteDTO;
import br.com.fiap.satguard.repository.SateliteRepository;
import br.com.fiap.satguard.exception.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SateliteServiceTest {

    @Mock
    private SateliteRepository repository;

    @InjectMocks
    private SateliteService service;

    private Satelite satelite;
    private SateliteDTO sateliteDTO;

    @BeforeEach
    void setUp() {
        satelite = new Satelite();
        satelite.setId(1);
        satelite.setSateliteNome("Hubble");
        satelite.setSateliteStatus("A");
        satelite.setVelocidade(new BigDecimal("7500.50"));

        sateliteDTO = new SateliteDTO(
                "Hubble",
                "Telescopio",
                "A",
                LocalDateTime.now(),
                new BigDecimal("7500.50"),
                1,
                1
        );
    }

    @Test
    void findAll_ShouldReturnPagedSatelites() {
        Pageable pageable = PageRequest.of(0, 10);
        List<Satelite> list = Arrays.asList(satelite);
        Page<Satelite> page = new PageImpl<>(list, pageable, list.size());

        when(repository.findAll(pageable)).thenReturn(page);

        Page<Satelite> result = service.findAll(pageable);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals("Hubble", result.getContent().get(0).getSateliteNome());
        verify(repository, times(1)).findAll(pageable);
    }

    @Test
    void findById_ShouldReturnSatelite_WhenIdExists() {
        when(repository.findById(1)).thenReturn(Optional.of(satelite));

        Satelite result = service.findById(1);

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("Hubble", result.getSateliteNome());
        verify(repository, times(1)).findById(1);
    }

    @Test
    void findById_ShouldThrowException_WhenIdDoesNotExist() {
        when(repository.findById(99)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.findById(99));
        verify(repository, times(1)).findById(99);
    }

    @Test
    void save_ShouldReturnSavedSatelite() {
        when(repository.save(any(Satelite.class))).thenReturn(satelite);

        Satelite result = service.save(sateliteDTO);

        assertNotNull(result);
        assertEquals("Hubble", result.getSateliteNome());
        verify(repository, times(1)).save(any(Satelite.class));
    }

    @Test
    void delete_ShouldCallRepositoryDeleteById() {
        doNothing().when(repository).deleteById(1);

        service.delete(1);

        verify(repository, times(1)).deleteById(1);
    }
}
