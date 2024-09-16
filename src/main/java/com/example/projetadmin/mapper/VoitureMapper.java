package com.example.projetadmin.mapper;

import com.example.projetadmin.dto.VoitureDTO;
import com.example.projetadmin.entities.Voiture;
import org.springframework.stereotype.Component;

@Component
public class VoitureMapper {

    public VoitureDTO toDto(Voiture voiture) {
        VoitureDTO dto = new VoitureDTO();
        dto.setId(voiture.getId());
        dto.setNumSerie(voiture.getNumSerie());
        dto.setMarque(voiture.getMarque());
        dto.setPhotoVoiture(voiture.getPhotoVoiture());
        return dto;
    }

    public Voiture toEntity(VoitureDTO dto) {
        Voiture voiture = new Voiture();
        voiture.setNumSerie(dto.getNumSerie());
        voiture.setMarque(dto.getMarque());
        voiture.setPhotoVoiture(dto.getPhotoVoiture());
        return voiture;
    }
}
