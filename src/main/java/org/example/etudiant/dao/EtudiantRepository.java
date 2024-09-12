package org.example.etudiant.dao;

import org.example.etudiant.entity.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EtudiantRepository extends JpaRepository<Etudiant, Integer> {

//    Etudiant findByNomAndPrenom(String nom, String prenom);
//
//    Etudiant findByNom(String nom);
//
//    Etudiant findByPrenomOrNom(String prenom, String nom);

    List<Etudiant> findByNomAndPrenom(String nom, String prenom);

    List<Etudiant> findByNom(String nom);

    List<Etudiant> findByNomOrPrenom(String nom, String prenom);

    List<Etudiant> findByNomContainingOrPrenomContaining(String nom, String prenom);


}