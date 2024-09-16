package com.example.projetadmin.services;
import com.example.projetadmin.dto.AdministrateurDTO;
import com.example.projetadmin.entities.Administrateur;
import com.example.projetadmin.mapper.AdministrateurMapper;
import com.example.projetadmin.repository.AdministrateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;



@Service
public class AdministrateurService {

	@Autowired
    private AdministrateurRepository administrateurRepository;

    @Autowired
    private AdministrateurMapper administrateurMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<AdministrateurDTO> findAllAdministrateurs() {
        return administrateurRepository.findAll().stream()
                .map(administrateurMapper::toDto)
                .collect(Collectors.toList());
    }

    public Optional<AdministrateurDTO> findAdministrateurById(Long id) {
        return administrateurRepository.findById(id)
                .map(administrateurMapper::toDto);
    }


    public AdministrateurDTO saveAdministrateur(AdministrateurDTO administrateurDTO) {
        Administrateur administrateur = administrateurMapper.toEntity(administrateurDTO);
        administrateur.setPassword(passwordEncoder.encode(administrateur.getPassword()));
        return administrateurMapper.toDto(administrateurRepository.save(administrateur));
    }

    public AdministrateurDTO updateAdministrateur(Long id, AdministrateurDTO administrateurDTO) {
        return administrateurRepository.findById(id).map(administrateur -> {
            administrateur.setNom(administrateurDTO.getNom());
            administrateur.setPrenom(administrateurDTO.getPrenom());
            administrateur.setEmail(administrateurDTO.getEmail());
            if (administrateurDTO.getPassword() != null && !administrateurDTO.getPassword().isEmpty()) {
                administrateur.setPassword(passwordEncoder.encode(administrateurDTO.getPassword()));
            }
            return administrateurMapper.toDto(administrateurRepository.save(administrateur));
        }).orElseThrow(() -> new RuntimeException("Administrateur not found with id " + id));
    }
    public Optional<AdministrateurDTO> findAdministrateurByEmail(String email) {
        return administrateurRepository.findByEmail(email)
                .map(administrateurMapper::toDto);
    }

    public void deleteAdministrateur(Long id) {
        administrateurRepository.deleteById(id);
    }
}