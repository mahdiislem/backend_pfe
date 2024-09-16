package com.example.projetadmin.services;

import com.example.projetadmin.dto.VoitureDTO;
import com.example.projetadmin.entities.Voiture;
import com.example.projetadmin.mapper.VoitureMapper;
import com.example.projetadmin.repository.VoitureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VoitureService {

    @Autowired
    private VoitureRepository voitureRepository;

    @Autowired
    private VoitureMapper voitureMapper;

    public List<VoitureDTO> findAllVoitures() {
        return voitureRepository.findAll().stream()
                .map(voitureMapper::toDto)
                .collect(Collectors.toList());
    }

    public Optional<VoitureDTO> findVoitureById(Long id) {
        return voitureRepository.findById(id)
                .map(voitureMapper::toDto);
    }

    public VoitureDTO saveVoiture(VoitureDTO voitureDTO) {
        Voiture voiture = voitureMapper.toEntity(voitureDTO);
        return voitureMapper.toDto(voitureRepository.save(voiture));
    }

    public VoitureDTO updateVoiture(Long id, VoitureDTO voitureDTO) {
        return voitureRepository.findById(id).map(voiture -> {
            voiture.setNumSerie(voitureDTO.getNumSerie());
            voiture.setMarque(voitureDTO.getMarque());
            voiture.setPhotoVoiture(voitureDTO.getPhotoVoiture());
            return voitureMapper.toDto(voitureRepository.save(voiture));
        }).orElseThrow(() -> new RuntimeException("Voiture not found with id " + id));
    }

    public void deleteVoiture(Long id) {
        voitureRepository.deleteById(id);
    }
}
