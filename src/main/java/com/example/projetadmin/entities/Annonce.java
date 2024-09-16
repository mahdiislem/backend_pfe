package com.example.projetadmin.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
public class Annonce implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nbrPlaceDispo;
    private String lieuDeDepart;
    private String timeDepart;
    private String lieuDesti;
    private String dateDepart;
    private Boolean bagage;
    private Boolean climatisation;
    private Boolean fumeur;
    private EtatAnnonce etat;

    private String tarif;

    @ManyToOne
    private Conducteur conducteur;

    @ManyToOne
    private Voiture voiture;

    @OneToMany(mappedBy = "annonce", cascade = CascadeType.ALL)
    private Set<Reservation> reservations;

}
