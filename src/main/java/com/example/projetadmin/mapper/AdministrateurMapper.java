package com.example.projetadmin.mapper;

import com.example.projetadmin.dto.AdministrateurDTO;
import com.example.projetadmin.entities.Administrateur;
import org.springframework.stereotype.Component;

@Component
public class AdministrateurMapper {

    public AdministrateurDTO toDto(Administrateur administrateur) {
        AdministrateurDTO dto = new AdministrateurDTO();
        dto.setId(administrateur.getId());
        dto.setNom(administrateur.getNom());
        dto.setPrenom(administrateur.getPrenom());
        dto.setEmail(administrateur.getEmail());
        dto.setPassword(administrateur.getPassword());
        return dto;
    }

    public Administrateur toEntity(AdministrateurDTO dto) {
        Administrateur administrateur = new Administrateur();
        administrateur.setNom(dto.getNom());
        administrateur.setPrenom(dto.getPrenom());
        administrateur.setEmail(dto.getEmail());
        administrateur.setPassword(dto.getPassword());
        return administrateur;
    }
}