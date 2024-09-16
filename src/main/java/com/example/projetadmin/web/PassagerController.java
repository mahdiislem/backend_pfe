package com.example.projetadmin.web;

import com.example.projetadmin.dto.PassagerDTO;
import com.example.projetadmin.services.PassagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/passagers")
@CrossOrigin
public class PassagerController {

    @Autowired
    private PassagerService passagerService;

    @GetMapping
    public List<PassagerDTO> getAllPassagers() {
        return passagerService.findAllPassagers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PassagerDTO> getPassagerById(@PathVariable Long id) {
        return passagerService.findPassagerById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public PassagerDTO createPassager(@RequestBody PassagerDTO passagerDTO) {
        return passagerService.savePassager(passagerDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PassagerDTO> updatePassager(@PathVariable Long id, @RequestBody PassagerDTO passagerDTO) {
        try {
            PassagerDTO updatedPassager = passagerService.updatePassager(id, passagerDTO);
            return ResponseEntity.ok(updatedPassager);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePassager(@PathVariable Long id) {
        passagerService.deletePassager(id);
        return ResponseEntity.ok().build();
    }
    

    
}
