package com.example.projetadmin.web;

import com.example.projetadmin.dto.VoitureDTO;
import com.example.projetadmin.services.VoitureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/voitures")
@CrossOrigin
public class VoitureController {

    @Autowired
    private VoitureService voitureService;

    @GetMapping
    public List<VoitureDTO> getAllVoitures() {
        return voitureService.findAllVoitures();
    }

    @GetMapping("/{id}")
    public ResponseEntity<VoitureDTO> getVoitureById(@PathVariable Long id) {
        return voitureService.findVoitureById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public VoitureDTO createVoiture(@RequestBody VoitureDTO voitureDTO) {
        return voitureService.saveVoiture(voitureDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VoitureDTO> updateVoiture(@PathVariable Long id, @RequestBody VoitureDTO voitureDTO) {
        try {
            VoitureDTO updatedVoiture = voitureService.updateVoiture(id, voitureDTO);
            return ResponseEntity.ok(updatedVoiture);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVoiture(@PathVariable Long id) {
        voitureService.deleteVoiture(id);
        return ResponseEntity.ok().build();
    }
}
