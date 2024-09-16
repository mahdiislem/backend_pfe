package com.example.projetadmin.web;

import com.example.projetadmin.dto.AdministrateurDTO;
import com.example.projetadmin.services.AdministrateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/administrateurs")
@CrossOrigin
public class AdministrateurController {

    @Autowired
    private AdministrateurService administrateurService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public List<AdministrateurDTO> getAllAdministrateurs() {
        return administrateurService.findAllAdministrateurs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdministrateurDTO> getAdministrateurById(@PathVariable Long id) {
        return administrateurService.findAdministrateurById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/signup")
    public AdministrateurDTO createAdministrateur(@RequestBody AdministrateurDTO administrateurDTO) {
        return administrateurService.saveAdministrateur(administrateurDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AdministrateurDTO administrateurDTO) {
        Optional<AdministrateurDTO> administrateurOptional = administrateurService.findAdministrateurByEmail(administrateurDTO.getEmail());
        if (administrateurOptional.isPresent() && passwordEncoder.matches(administrateurDTO.getPassword(), administrateurOptional.get().getPassword())) {
            return ResponseEntity.ok(administrateurOptional.get());
        } else {
            return ResponseEntity.status(401).body("Invalid email or password");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdministrateurDTO> updateAdministrateur(@PathVariable Long id, @RequestBody AdministrateurDTO administrateurDTO) {
        try {
            AdministrateurDTO updatedAdministrateur = administrateurService.updateAdministrateur(id, administrateurDTO);
            return ResponseEntity.ok(updatedAdministrateur);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdministrateur(@PathVariable Long id) {
        administrateurService.deleteAdministrateur(id);
        return ResponseEntity.ok().build();
    }
}