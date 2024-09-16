package com.example.projetadmin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.projetadmin.entities.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByAnnonceId(Long annonceId);

}
