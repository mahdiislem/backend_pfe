package com.example.projetadmin.repository;


import com.example.projetadmin.entities.Passager;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface PassagerRepository extends JpaRepository<Passager, Long>  {

}