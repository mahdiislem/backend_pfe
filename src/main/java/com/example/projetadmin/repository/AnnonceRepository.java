package com.example.projetadmin.repository;

import com.example.projetadmin.entities.Annonce;
import com.example.projetadmin.entities.EtatAnnonce;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AnnonceRepository extends JpaRepository<Annonce, Long> {
    @Query("SELECT a FROM Annonce a WHERE a.conducteur.id = :conducteurId")
    List<Annonce> findByConducteurId(@Param("conducteurId") Long conducteurId);
    
    @Query("SELECT a FROM Annonce a WHERE a.nbrPlaceDispo = :nbrPlaceDispo")
    List<Annonce> findByNbrPlaceDispo(@Param("nbrPlaceDispo") String nbrPlaceDispo);

    @Query("SELECT a FROM Annonce a WHERE a.lieuDeDepart = :lieuDeDepart")
    List<Annonce> findByLieuDeDepart(@Param("lieuDeDepart") String lieuDeDepart);

    @Query("SELECT a FROM Annonce a WHERE a.timeDepart = :timeDepart")
    List<Annonce> findByTimeDepart(@Param("timeDepart") String timeDepart);

    @Query("SELECT a FROM Annonce a WHERE a.lieuDesti = :lieuDesti")
    List<Annonce> findByLieuDesti(@Param("lieuDesti") String lieuDesti);

    @Query("SELECT a FROM Annonce a WHERE a.dateDepart = :dateDepart")
    List<Annonce> findByDateDepart(@Param("dateDepart") String dateDepart);

    @Query("SELECT a FROM Annonce a WHERE a.bagage = :bagage")
    List<Annonce> findByBagage(@Param("bagage") Boolean bagage);

    @Query("SELECT a FROM Annonce a WHERE a.climatisation = :climatisation")
    List<Annonce> findByClimatisation(@Param("climatisation") Boolean climatisation);

    @Query("SELECT a FROM Annonce a WHERE a.fumeur = :fumeur")
    List<Annonce> findByFumeur(@Param("fumeur") Boolean fumeur);

    @Query("SELECT a FROM Annonce a WHERE a.etat = :etat")
    List<Annonce> findByEtat(@Param("etat") EtatAnnonce etat);


}
