package com.example.projetadmin.mapper;

import com.example.projetadmin.dto.AnnonceDTO;
import com.example.projetadmin.entities.Annonce;
import com.example.projetadmin.entities.Conducteur;
import com.example.projetadmin.entities.Voiture;
import com.example.projetadmin.repository.ConducteurRepository;
import com.example.projetadmin.repository.VoitureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AnnonceMapper {

    @Autowired
    private ConducteurRepository conducteurRepository;

    @Autowired
    private VoitureRepository voitureRepository;

    public AnnonceDTO toDto(Annonce annonce) {
        AnnonceDTO dto = new AnnonceDTO();
        dto.setId(annonce.getId());
        dto.setNbrPlaceDispo(annonce.getNbrPlaceDispo());
        dto.setLieuDeDepart(annonce.getLieuDeDepart());
        dto.setLieuDesti(annonce.getLieuDesti());
        dto.setDateDepart(annonce.getDateDepart());
        dto.setBagage(annonce.getBagage());
        dto.setClimatisation(annonce.getClimatisation());
        dto.setFumeur(annonce.getFumeur());
        dto.setEtat(annonce.getEtat());
        dto.setTimeDepart(annonce.getTimeDepart());
        dto.setTarif(annonce.getTarif());
        dto.setConducteurId(annonce.getConducteur() != null ? annonce.getConducteur().getId() : null);
        dto.setVoitureId(annonce.getVoiture() != null ? annonce.getVoiture().getId() : null);
        return dto;
    }

    public Annonce toEntity(AnnonceDTO dto) {
        Annonce annonce = new Annonce();
        annonce.setNbrPlaceDispo(dto.getNbrPlaceDispo());
        annonce.setLieuDeDepart(dto.getLieuDeDepart());
        annonce.setLieuDesti(dto.getLieuDesti());
        annonce.setDateDepart(dto.getDateDepart());
        annonce.setBagage(dto.getBagage());
        annonce.setTimeDepart(dto.getTimeDepart());
        annonce.setEtat(dto.getEtat());
        annonce.setClimatisation(dto.getClimatisation());
        annonce.setFumeur(dto.getFumeur());
        annonce.setTarif(dto.getTarif());

        if (dto.getConducteurId() != null) {
            Conducteur conducteur = conducteurRepository.findById(dto.getConducteurId()).orElseThrow(() -> new RuntimeException("Conducteur not found"));
            annonce.setConducteur(conducteur);
        }

        if (dto.getVoitureId() != null) {
            Voiture voiture = voitureRepository.findById(dto.getVoitureId()).orElseThrow(() -> new RuntimeException("Voiture not found"));
            annonce.setVoiture(voiture);
        }

        return annonce;
    }
}
