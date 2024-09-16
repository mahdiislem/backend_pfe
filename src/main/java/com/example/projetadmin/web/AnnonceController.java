package com.example.projetadmin.web;

import com.example.projetadmin.dto.AnnonceDTO;
import com.example.projetadmin.entities.EtatAnnonce;
import com.example.projetadmin.services.AnnonceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/annonces")
@CrossOrigin // Replace with your frontend URL


public class AnnonceController {

    @Autowired
    private AnnonceService annonceService;

    @GetMapping
    public List<AnnonceDTO> getAllAnnonces() {
        return annonceService.findAllAnnonces();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnnonceDTO> getAnnonceById(@PathVariable Long id) {
        return annonceService.findAnnonceById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public AnnonceDTO createAnnonce(@RequestBody AnnonceDTO annonceDTO) {
        return annonceService.saveAnnonce(annonceDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnnonceDTO> updateAnnonce(@PathVariable Long id, @RequestBody AnnonceDTO annonceDTO) {
        try {
            AnnonceDTO updatedAnnonce = annonceService.updateAnnonce(id, annonceDTO);
            return ResponseEntity.ok(updatedAnnonce);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnnonce(@PathVariable Long id) {
        annonceService.deleteAnnonce(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/conducteur/{conducteurId}")
    public ResponseEntity<List<AnnonceDTO>> getAnnoncesByConducteurId(@PathVariable Long conducteurId) {
        List<AnnonceDTO> annonces = annonceService.findAnnoncesByConducteurId(conducteurId);
        if (annonces.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(annonces);
        }
    }
    
    

    @GetMapping("/nbrPlaceDispo")
    public ResponseEntity<List<AnnonceDTO>> getByNbrPlaceDispo(@RequestParam String nbrPlaceDispo) {
        List<AnnonceDTO> annonces = annonceService.findByNbrPlaceDispo(nbrPlaceDispo);
        if (annonces.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(annonces);
        }
    }

    @GetMapping("/lieuDeDepart")
    public ResponseEntity<List<AnnonceDTO>> getByLieuDeDepart(@RequestParam String lieuDeDepart) {
        List<AnnonceDTO> annonces = annonceService.findByLieuDeDepart(lieuDeDepart);
        if (annonces.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(annonces);
        }
    }

    @GetMapping("/timeDepart")
    public ResponseEntity<List<AnnonceDTO>> getByTimeDepart(@RequestParam String timeDepart) {
        List<AnnonceDTO> annonces = annonceService.findByTimeDepart(timeDepart);
        if (annonces.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(annonces);
        }
    }

    @GetMapping("/lieuDesti")
    public ResponseEntity<List<AnnonceDTO>> getByLieuDesti(@RequestParam String lieuDesti) {
        List<AnnonceDTO> annonces = annonceService.findByLieuDesti(lieuDesti);
        if (annonces.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(annonces);
        }
    }

    @GetMapping("/dateDepart")
    public ResponseEntity<List<AnnonceDTO>> getByDateDepart(@RequestParam String dateDepart) {
        List<AnnonceDTO> annonces = annonceService.findByDateDepart(dateDepart);
        if (annonces.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(annonces);
        }
    }

    @GetMapping("/bagage")
    public ResponseEntity<List<AnnonceDTO>> getByBagage(@RequestParam Boolean bagage) {
        List<AnnonceDTO> annonces = annonceService.findByBagage(bagage);
        if (annonces.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(annonces);
        }
    }

    @GetMapping("/climatisation")
    public ResponseEntity<List<AnnonceDTO>> getByClimatisation(@RequestParam Boolean climatisation) {
        List<AnnonceDTO> annonces = annonceService.findByClimatisation(climatisation);
        if (annonces.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(annonces);
        }
    }

    @GetMapping("/fumeur")
    public ResponseEntity<List<AnnonceDTO>> getByFumeur(@RequestParam Boolean fumeur) {
        List<AnnonceDTO> annonces = annonceService.findByFumeur(fumeur);
        if (annonces.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(annonces);
        }
    }

    @GetMapping("/etat")
    public ResponseEntity<List<AnnonceDTO>> getByEtat(@RequestParam String etat) {
        try {
            EtatAnnonce etatEnum = EtatAnnonce.valueOf(etat.toUpperCase());
            List<AnnonceDTO> annonces = annonceService.findByEtat(etatEnum);
            if (annonces.isEmpty()) {
                return ResponseEntity.notFound().build();
            } else {
                return ResponseEntity.ok(annonces);
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    
    
    

}
