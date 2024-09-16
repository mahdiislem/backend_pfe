package com.example.projetadmin.dto;


import java.util.Set;

import com.example.projetadmin.entities.EtatAnnonce;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnnonceDTO {

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
    private Long conducteurId;
    private Long voitureId;

}
