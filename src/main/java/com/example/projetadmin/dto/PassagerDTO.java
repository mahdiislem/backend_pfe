	package com.example.projetadmin.dto;
	
	import java.sql.Blob;
	
	import lombok.Getter;
	import lombok.Setter;
	
	@Getter
	@Setter
	public class PassagerDTO {
	
	    private Long id;
	    private String nom;
	    private String prenom;
	    private String email;
	    private String numTel;
	    private String genre;
	    private String photo;
	}
