package com.example.projetadmin.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationDTO {

    private Long id;
    private Long annonceId;
    private Long passagerId;
    private String statut;
}
