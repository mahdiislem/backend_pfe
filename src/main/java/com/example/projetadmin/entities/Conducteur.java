package com.example.projetadmin.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Blob;
import java.util.List;


@Getter
@Setter
@Entity
public class Conducteur implements Serializable  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String numDriver;
    private String photoDriver;
    private String Gender;
    private String password; // Consider excluding this from responses
    

	@OneToMany(mappedBy = "conducteur", cascade = CascadeType.ALL)
    private List<Annonce> annonces;
    
    @ManyToOne
    private Administrateur administrateur;




}


