package org.example.etudiant.service;

import org.example.etudiant.model.Etudiant;
import org.springframework.stereotype.Service;

import java.util.*;

@Service // Annotation de Spring qui marque cette classe comme un service. Spring la gère comme un bean et l'injecte où nécessaire.
public class EtudiantService {

    private final Map<UUID, Etudiant> etudiants; // Map pour stocker les étudiants avec leur identifiant UUID comme clé

    /*
    Map<K, V> : C'est une interface en Java qui représente une collection de paires clé-valeur. Une Map associe chaque clé (de type K) à une valeur (de type V). Dans ce cas :

    K est le type de la clé.
    V est le type de la valeur.
     */


    // Constructeur. Initialise la Map et ajoute des étudiants
    public EtudiantService() {
        etudiants = new HashMap<>();

        Etudiant etudiant1 = Etudiant.builder()
//                .id(0)
                .id(UUID.randomUUID()) // Génération d'un UUID unique pour l'étudiant
                .nom("Bogard")
                .prenom("Andy")
                .age(18)
                .email("andy@email.com")
                .build();

        Etudiant etudiant2 = Etudiant.builder()
//                .id(1)
                .id(UUID.randomUUID())
                .nom("Bogard")
                .prenom("Terry")
                .age(19)
                .email("terry@email.com")
                .build();

        Etudiant etudiant3 = Etudiant.builder()
//                .id(2)
                .id(UUID.randomUUID())
                .nom("Mario")
                .prenom("Luigi")
                .age(30)
                .email("luigi@email.com")
                .build();

        etudiants.put(etudiant1.getId(), etudiant1); // Ajout des étudiants à la Map avec leur UUID comme clé
        etudiants.put(etudiant2.getId(), etudiant2);
        etudiants.put(etudiant3.getId(), etudiant3);
    }


    /**
     * Obtenir la liste de tous les étudiants
     * @return List<Etudiant>
     */
    public List<Etudiant> getEtudiants() {
        return etudiants.values().stream().toList();
    }


    /**
     * Obtenir un étudiant par son identifiant UUID
     * @return Etudiant
     */
    public Etudiant getEtudiantById(UUID id) {
        return etudiants.get(id);
    }


    /**
     * Obtenir un étudiant par son nom
     * @return Etudiant
     */
    public Etudiant getEtudiantByNom(String nom) {
        return etudiants.values().stream().filter(e -> e.getNom().equals(nom)).findFirst().orElse(null);
    }


    /**
     * Inscrire un nouvel étudiant
     * @param etudiant
     */
    public void ajouterEtudiant(Etudiant etudiant) {
        etudiants.put(etudiant.getId(), etudiant);

    }



}
