package com.example.projetadmin.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;


@Entity
@Getter
@Setter
public class Administrateur implements Serializable {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    private String prenom;

    private String email;

    private String password;
    
    @OneToMany(mappedBy = "administrateur")
    private List<Conducteur> conducteurs;


}