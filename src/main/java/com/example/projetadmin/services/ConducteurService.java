package com.example.projetadmin.services;

import com.example.projetadmin.dto.ConducteurDTO;
import com.example.projetadmin.entities.Conducteur;
import com.example.projetadmin.mapper.ConducteurMapper;
import com.example.projetadmin.repository.ConducteurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ConducteurService {

    @Autowired
    private ConducteurRepository conducteurRepository;

    @Autowired
    private ConducteurMapper conducteurMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<ConducteurDTO> findAllConducteurs() {
        return conducteurRepository.findAll().stream()
                .map(conducteurMapper::toDto)
                .collect(Collectors.toList());
    }

    public Optional<ConducteurDTO> findConducteurById(Long id) {
        return conducteurRepository.findById(id)
                .map(conducteurMapper::toDto);
    }

    public Optional<ConducteurDTO> findConducteurByEmail(String email) {
        return conducteurRepository.findByEmail(email)
                .map(conducteurMapper::toDto);
    }

    public ConducteurDTO saveConducteur(ConducteurDTO conducteurDTO) {
        Conducteur conducteur = conducteurMapper.toEntity(conducteurDTO);
        conducteur.setPassword(passwordEncoder.encode(conducteur.getPassword()));
        return conducteurMapper.toDto(conducteurRepository.save(conducteur));
    }

    public ConducteurDTO updateConducteur(Long id, ConducteurDTO conducteurDTO) {
        return conducteurRepository.findById(id).map(conducteur -> {
            conducteur.setNom(conducteurDTO.getNom());
            conducteur.setPrenom(conducteurDTO.getPrenom());
            conducteur.setEmail(conducteurDTO.getEmail());
            conducteur.setNumDriver(conducteurDTO.getNumDriver());
            if (conducteurDTO.getPassword() != null && !conducteurDTO.getPassword().isEmpty()) {
                conducteur.setPassword(passwordEncoder.encode(conducteurDTO.getPassword()));
            }
            conducteur.setPhotoDriver(conducteurDTO.getPhotoDriver());
            conducteur.setGender(conducteurDTO.getGender());
            return conducteurMapper.toDto(conducteurRepository.save(conducteur));
        }).orElseThrow(() -> new RuntimeException("Conducteur not found with id " + id));
    }

    public void deleteConducteur(Long id) {
        conducteurRepository.deleteById(id);
    }
}
