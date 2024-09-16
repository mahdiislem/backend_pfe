package com.example.projetadmin.repository;


import com.example.projetadmin.entities.Administrateur;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministrateurRepository extends JpaRepository<Administrateur, Long> {
    Optional<Administrateur> findByEmail(String email);

}
