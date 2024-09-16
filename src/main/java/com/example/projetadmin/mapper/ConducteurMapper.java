package com.example.projetadmin.mapper;

import com.example.projetadmin.dto.ConducteurDTO;
import com.example.projetadmin.entities.Conducteur;
import org.springframework.stereotype.Component;

@Component
public class ConducteurMapper {

    public ConducteurDTO toDto(Conducteur conducteur) {
        ConducteurDTO dto = new ConducteurDTO();
        dto.setId(conducteur.getId());
        dto.setNom(conducteur.getNom());
        dto.setPrenom(conducteur.getPrenom());
        dto.setEmail(conducteur.getEmail());
        dto.setNumDriver(conducteur.getNumDriver());
        dto.setPhotoDriver(conducteur.getPhotoDriver());
        dto.setGender(conducteur.getGender());
        dto.setPassword(conducteur.getPassword());

        // Exclude password for security reasons
        return dto;
    }

    public Conducteur toEntity(ConducteurDTO dto) {
        Conducteur conducteur = new Conducteur();
        conducteur.setNom(dto.getNom());
        conducteur.setPrenom(dto.getPrenom());
        conducteur.setEmail(dto.getEmail());
        conducteur.setNumDriver(dto.getNumDriver());
        conducteur.setPhotoDriver(dto.getPhotoDriver());
        conducteur.setGender(dto.getGender());
        conducteur.setPassword(dto.getPassword());
        // Handle password separately if needed
        return conducteur;
    }
}
