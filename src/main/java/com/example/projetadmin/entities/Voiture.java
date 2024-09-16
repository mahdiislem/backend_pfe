package com.example.projetadmin.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Blob;
import java.util.List;

@Entity
@Getter
@Setter
public class Voiture implements Serializable  {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long numSerie;

    private String marque;

    private String photoVoiture;
    
    @OneToMany(mappedBy = "voiture")
    private List<Annonce> annonces;
}
