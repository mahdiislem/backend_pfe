package com.example.projetadmin.services;

import com.example.projetadmin.dto.PassagerDTO;
import com.example.projetadmin.entities.Passager;
import com.example.projetadmin.mapper.PassagerMapper;
import com.example.projetadmin.repository.PassagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PassagerService {

    @Autowired
    private PassagerRepository passagerRepository;

    @Autowired
    private PassagerMapper passagerMapper;

    public List<PassagerDTO> findAllPassagers() {
        return passagerRepository.findAll().stream()
                .map(passagerMapper::toDto)
                .collect(Collectors.toList());
    }

    public Optional<PassagerDTO> findPassagerById(Long id) {
        return passagerRepository.findById(id)
                .map(passagerMapper::toDto);
    }

    public PassagerDTO savePassager(PassagerDTO passagerDTO) {
        Passager passager = passagerMapper.toEntity(passagerDTO);
        return passagerMapper.toDto(passagerRepository.save(passager));
    }
 

    public PassagerDTO updatePassager(Long id, PassagerDTO passagerDTO) {
        return passagerRepository.findById(id).map(passager -> {
            passager.setNom(passagerDTO.getNom());
            passager.setPrenom(passagerDTO.getPrenom());
            passager.setEmail(passagerDTO.getEmail());
            passager.setNumTel(passagerDTO.getNumTel());
            passager.setGenre(passagerDTO.getGenre());
            passager.setPhoto(passagerDTO.getPhoto());
            return passagerMapper.toDto(passagerRepository.save(passager));
        }).orElseThrow(() -> new RuntimeException("Passager not found with id " + id));
    }

    public void deletePassager(Long id) {
        passagerRepository.deleteById(id);
    }

}
