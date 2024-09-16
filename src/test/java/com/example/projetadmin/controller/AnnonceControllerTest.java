package com.example.projetadmin.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.projetadmin.dto.AnnonceDTO;
import com.example.projetadmin.entities.Annonce;
import com.example.projetadmin.mapper.AnnonceMapper;
import com.example.projetadmin.services.AnnonceService;
import com.example.projetadmin.web.AnnonceController;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(AnnonceController.class)
class AnnonceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AnnonceService annonceService;

    @MockBean
    private AnnonceMapper annonceMapper;

    @InjectMocks
    private AnnonceController annonceController;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(annonceController).build();
    }

    @Test
    void testAddAnnonce() throws Exception {
        AnnonceDTO annonceDto = new AnnonceDTO();
        Annonce annonce = new Annonce();
        
        // Simuler le mapping DTO -> Entity et l'enregistrement
        when(annonceMapper.toEntity(any(AnnonceDTO.class))).thenReturn(annonce);
        when(annonceService.saveAnnonce(any(AnnonceDTO.class))).thenReturn(annonceDto);

        // Test de l'ajout d'une annonce
        mockMvc.perform(post("/annonces")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(annonceDto)))
                .andExpect(status().isOk());
    }

    @Test
    void testGetAllAnnonces() throws Exception {
        // Simuler le service pour récupérer toutes les annonces
        when(annonceService.findAllAnnonces()).thenReturn(List.of(new AnnonceDTO()));

        // Test de la récupération des annonces
        mockMvc.perform(get("/annonces")
                .param("pageNo", "0")
                .param("pageSize", "10")
                .param("sortBy", "id"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testUpdateAnnonce() throws Exception {
        AnnonceDTO annonceDto = new AnnonceDTO();
        
        // Simuler la mise à jour d'une annonce
        when(annonceService.updateAnnonce(anyLong(), any(AnnonceDTO.class))).thenReturn(annonceDto);

        // Test de la mise à jour d'une annonce
        mockMvc.perform(put("/annonces/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(annonceDto)))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteAnnonce() throws Exception {
        // Test de la suppression d'une annonce
        mockMvc.perform(delete("/annonces/{id}", 1L))
                .andExpect(status().isOk());
    }

    @Test
    void testGetAnnonceById() throws Exception {
        AnnonceDTO annonceDto = new AnnonceDTO();
        
        when(annonceService.findAnnonceById(anyLong())).thenReturn(Optional.of(annonceDto));

        mockMvc.perform(get("/annonces/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}
