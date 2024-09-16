package com.example.projetadmin.service;

import com.example.projetadmin.dto.AnnonceDTO;
import com.example.projetadmin.entities.Annonce;
import com.example.projetadmin.mapper.AnnonceMapper;
import com.example.projetadmin.repository.AnnonceRepository;
import com.example.projetadmin.repository.ConducteurRepository;
import com.example.projetadmin.repository.VoitureRepository;
import com.example.projetadmin.services.AnnonceService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AnnonceServiceTest {

    @InjectMocks
    private AnnonceService annonceService;

    @Mock
    private AnnonceRepository annonceRepository;

    @Mock
    private AnnonceMapper annonceMapper;

    @Mock
    private ConducteurRepository conducteurRepository;

    @Mock
    private VoitureRepository voitureRepository;

    private Annonce annonce;
    private AnnonceDTO annonceDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        annonce = new Annonce();
        annonce.setId(1L);
        annonce.setNbrPlaceDispo("4");

        annonceDTO = new AnnonceDTO();
        annonceDTO.setId(1L);
        annonceDTO.setNbrPlaceDispo("4");
    }

    @Test
    void testFindAllAnnonces() {
        when(annonceRepository.findAll()).thenReturn(Collections.singletonList(annonce));
        when(annonceMapper.toDto(any(Annonce.class))).thenReturn(annonceDTO);

        List<AnnonceDTO> result = annonceService.findAllAnnonces();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("4", result.get(0).getNbrPlaceDispo());

        verify(annonceRepository, times(1)).findAll();
        verify(annonceMapper, times(1)).toDto(any(Annonce.class));
    }

    @Test
    void testFindAnnonceById() {
        when(annonceRepository.findById(1L)).thenReturn(Optional.of(annonce));
        when(annonceMapper.toDto(any(Annonce.class))).thenReturn(annonceDTO);

        Optional<AnnonceDTO> result = annonceService.findAnnonceById(1L);

        assertTrue(result.isPresent());
        assertEquals("4", result.get().getNbrPlaceDispo());

        verify(annonceRepository, times(1)).findById(1L);
        verify(annonceMapper, times(1)).toDto(any(Annonce.class));
    }

    @Test
    void testSaveAnnonce() {
        when(annonceMapper.toEntity(any(AnnonceDTO.class))).thenReturn(annonce);
        when(annonceRepository.save(any(Annonce.class))).thenReturn(annonce);
        when(annonceMapper.toDto(any(Annonce.class))).thenReturn(annonceDTO);

        AnnonceDTO result = annonceService.saveAnnonce(annonceDTO);

        assertNotNull(result);
        assertEquals("4", result.getNbrPlaceDispo());

        verify(annonceMapper, times(1)).toEntity(any(AnnonceDTO.class));
        verify(annonceRepository, times(1)).save(any(Annonce.class));
        verify(annonceMapper, times(1)).toDto(any(Annonce.class));
    }

    @Test
    void testUpdateAnnonce() {
        when(annonceRepository.findById(anyLong())).thenReturn(Optional.of(annonce));
        when(annonceRepository.save(any(Annonce.class))).thenReturn(annonce);
        when(annonceMapper.toDto(any(Annonce.class))).thenReturn(annonceDTO);

        AnnonceDTO updatedAnnonce = annonceService.updateAnnonce(1L, annonceDTO);

        assertNotNull(updatedAnnonce);
        assertEquals("4", updatedAnnonce.getNbrPlaceDispo());

        verify(annonceRepository, times(1)).findById(1L);
        verify(annonceRepository, times(1)).save(any(Annonce.class));
        verify(annonceMapper, times(1)).toDto(any(Annonce.class));
    }

    @Test
    void testDeleteAnnonce() {
        annonceService.deleteAnnonce(1L);
        verify(annonceRepository, times(1)).deleteById(1L);
    }
}
