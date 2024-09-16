package com.example.projetadmin.services;

import com.example.projetadmin.dto.ReservationDTO;
import com.example.projetadmin.entities.Annonce;
import com.example.projetadmin.entities.Passager;
import com.example.projetadmin.entities.Reservation;
import com.example.projetadmin.repository.AnnonceRepository;
import com.example.projetadmin.repository.PassagerRepository;
import com.example.projetadmin.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private AnnonceRepository annonceRepository;

    @Autowired
    private PassagerRepository passagerRepository;

    public List<ReservationDTO> findAllReservations() {
        return reservationRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public Optional<ReservationDTO> findReservationById(Long id) {
        return reservationRepository.findById(id)
                .map(this::toDto);
    }
    // Nouvelle méthode pour récupérer les réservations par annonceId
    public List<ReservationDTO> findReservationsByAnnonceId(Long annonceId) {
        return reservationRepository.findByAnnonceId(annonceId).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }


    public ReservationDTO saveReservation(ReservationDTO reservationDTO) {
        Annonce annonce = annonceRepository.findById(reservationDTO.getAnnonceId())
                .orElseThrow(() -> new RuntimeException("Annonce not found with id " + reservationDTO.getAnnonceId()));
        Passager passager = passagerRepository.findById(reservationDTO.getPassagerId())
                .orElseThrow(() -> new RuntimeException("Passager not found with id " + reservationDTO.getPassagerId()));

        Reservation reservation = new Reservation();
        reservation.setAnnonce(annonce);
        reservation.setPassager(passager);
        reservation.setStatut(reservationDTO.getStatut());
        
        return toDto(reservationRepository.save(reservation));
    }

    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }

    private ReservationDTO toDto(Reservation reservation) {
        ReservationDTO dto = new ReservationDTO();
        dto.setId(reservation.getId());
        dto.setAnnonceId(reservation.getAnnonce().getId());
        dto.setPassagerId(reservation.getPassager().getId());
        dto.setStatut(reservation.getStatut());
        return dto;
    }
}
