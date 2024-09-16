package com.example.projetadmin.dto;

import java.sql.Blob;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConducteurDTO {

    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String numDriver;
    private String photoDriver;
    private String gender;
    private String password; 

}
