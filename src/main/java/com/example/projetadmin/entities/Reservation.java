package com.example.projetadmin.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "annonce_id", nullable = false)
    private Annonce annonce;

    @ManyToOne
    @JoinColumn(name = "passager_id", nullable = false)
    private Passager passager;

    private String statut; // Pour stocker le statut de la réservation (par exemple, "confirmée", "en attente", etc.)
}
