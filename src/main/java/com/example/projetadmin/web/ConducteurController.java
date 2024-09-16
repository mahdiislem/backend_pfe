package com.example.projetadmin.web;

import com.example.projetadmin.dto.ConducteurDTO;
import com.example.projetadmin.services.ConducteurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/conducteurs")
@CrossOrigin
public class ConducteurController {

    @Autowired
    private ConducteurService conducteurService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public List<ConducteurDTO> getAllConducteurs() {
        return conducteurService.findAllConducteurs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConducteurDTO> getConducteurById(@PathVariable Long id) {
        return conducteurService.findConducteurById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/signup")
    public ConducteurDTO createConducteur(@RequestBody ConducteurDTO conducteurDTO) {
        return conducteurService.saveConducteur(conducteurDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody ConducteurDTO conducteurDTO) {
        Optional<ConducteurDTO> conducteurOptional = conducteurService.findConducteurByEmail(conducteurDTO.getEmail());
        if (conducteurOptional.isPresent() && passwordEncoder.matches(conducteurDTO.getPassword(), conducteurOptional.get().getPassword())) {
            return ResponseEntity.ok(conducteurOptional.get());
        } else {
            return ResponseEntity.status(401).body("Invalid email or password");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConducteurDTO> updateConducteur(@PathVariable Long id, @RequestBody ConducteurDTO conducteurDTO) {
        try {
            ConducteurDTO updatedConducteur = conducteurService.updateConducteur(id, conducteurDTO);
            return ResponseEntity.ok(updatedConducteur);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConducteur(@PathVariable Long id) {
        conducteurService.deleteConducteur(id);
        return ResponseEntity.ok().build();
    }
}
