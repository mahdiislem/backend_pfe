package com.example.projetadmin.repository;

import com.example.projetadmin.entities.Annonce;
import com.example.projetadmin.entities.Conducteur;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

	public interface ConducteurRepository extends JpaRepository<Conducteur, Long>  {
	    Optional<Conducteur> findByEmail(String email);
		@Query("SELECT a FROM Annonce a WHERE a.conducteur.id = :conducteurId")
	    List<Annonce> consulterAnnonces(@Param("conducteurId") Long conducteurId);

	}
