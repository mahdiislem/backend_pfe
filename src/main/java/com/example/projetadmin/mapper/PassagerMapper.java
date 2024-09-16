package com.example.projetadmin.mapper;

import com.example.projetadmin.dto.PassagerDTO;
import com.example.projetadmin.entities.Passager;
import org.springframework.stereotype.Component;

@Component
public class PassagerMapper {

    public PassagerDTO toDto(Passager passager) {
        PassagerDTO dto = new PassagerDTO();
        dto.setId(passager.getId());
        dto.setNom(passager.getNom());
        dto.setPrenom(passager.getPrenom());
        dto.setEmail(passager.getEmail());
        dto.setNumTel(passager.getNumTel());
        dto.setGenre(passager.getGenre());
        dto.setPhoto(passager.getPhoto());
        return dto;
    }

    public Passager toEntity(PassagerDTO dto) {
        Passager passager = new Passager();
        passager.setNom(dto.getNom());
        passager.setPrenom(dto.getPrenom());
        passager.setEmail(dto.getEmail());
        passager.setNumTel(dto.getNumTel());
        passager.setGenre(dto.getGenre());
        passager.setPhoto(dto.getPhoto());
        return passager;
    }
}
