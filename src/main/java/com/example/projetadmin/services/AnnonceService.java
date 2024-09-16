package com.example.projetadmin.services;

import com.example.projetadmin.dto.AnnonceDTO;
import com.example.projetadmin.entities.Annonce;
import com.example.projetadmin.entities.Conducteur;
import com.example.projetadmin.entities.EtatAnnonce;
import com.example.projetadmin.entities.Voiture;
import com.example.projetadmin.mapper.AnnonceMapper;
import com.example.projetadmin.repository.AnnonceRepository;
import com.example.projetadmin.repository.ConducteurRepository;
import com.example.projetadmin.repository.VoitureRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AnnonceService {

    @Autowired
    private AnnonceRepository annonceRepository;
    @Autowired
    private ConducteurRepository conducteurRepository;
    
    @Autowired
    private VoitureRepository voitureRepository;


    @Autowired
    private AnnonceMapper annonceMapper;

    public List<AnnonceDTO> findAllAnnonces() {
        return annonceRepository.findAll().stream()
                .map(annonceMapper::toDto)
                .collect(Collectors.toList());
    }

    public Optional<AnnonceDTO> findAnnonceById(Long id) {
        return annonceRepository.findById(id)
                .map(annonceMapper::toDto);
    }

    public AnnonceDTO saveAnnonce(AnnonceDTO annonceDTO) {
        Annonce annonce = annonceMapper.toEntity(annonceDTO);
        return annonceMapper.toDto(annonceRepository.save(annonce));
    }
    
    public List<AnnonceDTO> findAnnoncesByConducteurId(Long conducteurId) {
        return annonceRepository.findByConducteurId(conducteurId).stream()
                .map(annonceMapper::toDto)
                .collect(Collectors.toList());
    }
    
    
    


    public AnnonceDTO updateAnnonce(Long id, AnnonceDTO annonceDTO) {
        return annonceRepository.findById(id).map(annonce -> {
            annonce.setNbrPlaceDispo(annonceDTO.getNbrPlaceDispo());
            annonce.setTarif(annonceDTO.getTarif());
            annonce.setLieuDeDepart(annonceDTO.getLieuDeDepart());
            annonce.setTimeDepart(annonceDTO.getTimeDepart());
            annonce.setLieuDesti(annonceDTO.getLieuDesti());
            annonce.setDateDepart(annonceDTO.getDateDepart());
            annonce.setBagage(annonceDTO.getBagage());
            annonce.setClimatisation(annonceDTO.getClimatisation());
            annonce.setFumeur(annonceDTO.getFumeur());
            annonce.setEtat(annonceDTO.getEtat());

            // If you have complex objects or entities, make sure to fetch them from the database
            // and set them accordingly. Here is an example for conducteur and voiture.
            if (annonceDTO.getConducteurId() != null) {
                Conducteur conducteur = conducteurRepository.findById(annonceDTO.getConducteurId())
                        .orElseThrow(() -> new RuntimeException("Conducteur not found with id " + annonceDTO.getConducteurId()));
                annonce.setConducteur(conducteur);
            }

            if (annonceDTO.getVoitureId() != null) {
                Voiture voiture = voitureRepository.findById(annonceDTO.getVoitureId())
                        .orElseThrow(() -> new RuntimeException("Voiture not found with id " + annonceDTO.getVoitureId()));
                annonce.setVoiture(voiture);
            }
            return annonceMapper.toDto(annonceRepository.save(annonce));
        }).orElseThrow(() -> new RuntimeException("Annonce not found with id " + id));
    }

    public void deleteAnnonce(Long id) {
        annonceRepository.deleteById(id);
    }
    
    
    
    
    
    
    public List<AnnonceDTO> findByConducteurId(Long conducteurId) {
        return annonceRepository.findByConducteurId(conducteurId).stream()
                .map(annonceMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<AnnonceDTO> findByNbrPlaceDispo(String nbrPlaceDispo) {
        return annonceRepository.findByNbrPlaceDispo(nbrPlaceDispo).stream()
                .map(annonceMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<AnnonceDTO> findByLieuDeDepart(String lieuDeDepart) {
        return annonceRepository.findByLieuDeDepart(lieuDeDepart).stream()
                .map(annonceMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<AnnonceDTO> findByTimeDepart(String timeDepart) {
        return annonceRepository.findByTimeDepart(timeDepart).stream()
                .map(annonceMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<AnnonceDTO> findByLieuDesti(String lieuDesti) {
        return annonceRepository.findByLieuDesti(lieuDesti).stream()
                .map(annonceMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<AnnonceDTO> findByDateDepart(String dateDepart) {
        return annonceRepository.findByDateDepart(dateDepart).stream()
                .map(annonceMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<AnnonceDTO> findByBagage(Boolean bagage) {
        return annonceRepository.findByBagage(bagage).stream()
                .map(annonceMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<AnnonceDTO> findByClimatisation(Boolean climatisation) {
        return annonceRepository.findByClimatisation(climatisation).stream()
                .map(annonceMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<AnnonceDTO> findByFumeur(Boolean fumeur) {
        return annonceRepository.findByFumeur(fumeur).stream()
                .map(annonceMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<AnnonceDTO> findByEtat(EtatAnnonce etat) {
        return annonceRepository.findByEtat(etat).stream()
                .map(annonceMapper::toDto)
                .collect(Collectors.toList());
    }

}
