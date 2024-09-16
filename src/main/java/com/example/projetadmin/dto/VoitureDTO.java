package com.example.projetadmin.dto;

import java.sql.Blob;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VoitureDTO {

    private Long id;
    private Long numSerie;
    private String marque;
    private String photoVoiture;
}
