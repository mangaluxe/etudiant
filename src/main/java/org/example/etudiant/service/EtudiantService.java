package org.example.etudiant.service;

import org.example.etudiant.model.Etudiant;
import org.springframework.stereotype.Service;
//import org.apache.commons.text.similarity.LevenshteinDistance;

import java.util.*;


@Service // Annotation de Spring qui marque cette classe comme un service. Spring la gère comme un bean et l'injecte où nécessaire.
public class EtudiantService {

    // ========== Propriétés ==========

    private final Map<UUID, Etudiant> etudiants; // Map pour stocker les étudiants avec leur identifiant UUID comme clé

    /*
    Map<K, V> : C'est une interface en Java qui représente une collection de paires clé-valeur. Une Map associe chaque clé (de type K) à une valeur (de type V). Dans ce cas :

    K est le type de la clé.
    V est le type de la valeur.
     */


    // ========== Constructeur ==========

    public EtudiantService() {
        etudiants = new HashMap<>();

        // Création d'objets Etudiant avec le constructeur :
        Etudiant etudiant1 = new Etudiant(UUID.randomUUID(), "Bogard", "Andy", 18, "andy@email.com"); // UUID.randomUUID() : Génération d'un UUID unique pour l'étudiant
        Etudiant etudiant2 = new Etudiant(UUID.randomUUID(), "Bogard", "Terry", 19, "terry@email.com");
        Etudiant etudiant3 = new Etudiant(UUID.randomUUID(), "Mario", "Luigi", 30, "luigi@email.com");
        Etudiant etudiant4 = new Etudiant(UUID.randomUUID(), "Tom", "Nana", 17, "tomnana@email.com");
        Etudiant etudiant5 = new Etudiant(UUID.randomUUID(), "Connor", "John", 35, "tomnana@email.fr");
        Etudiant etudiant6 = new Etudiant(UUID.randomUUID(), "Jaune", "Julien", 29, "juju@email.fr");

        // Création d'objets Etudiant avec le builder :
//        Etudiant etudiant1 = Etudiant.builder()
////                .id(0)
//                .id(UUID.randomUUID()) // Génération d'un UUID unique pour l'étudiant
//                .nom("Bogard")
//                .prenom("Andy")
//                .age(18)
//                .email("andy@email.com")
//                .build();
//
//        Etudiant etudiant2 = Etudiant.builder()
////                .id(1)
//                .id(UUID.randomUUID())
//                .nom("Bogard")
//                .prenom("Terry")
//                .age(19)
//                .email("terry@email.com")
//                .build();

        etudiants.put(etudiant1.getId(), etudiant1); // Map utilise put() pour ajouter des éléments où chaque élément est une paire clé-valeur. List utilise add() pour ajouter des éléments sans clé
        etudiants.put(etudiant2.getId(), etudiant2);
        etudiants.put(etudiant3.getId(), etudiant3);
        etudiants.put(etudiant4.getId(), etudiant4);
        etudiants.put(etudiant5.getId(), etudiant5);
        etudiants.put(etudiant6.getId(), etudiant6);
    }


    // ========== Méthodes ==========

    /**
     * Obtenir la liste de tous les étudiants
     * @return List<Etudiant>
     */
    public List<Etudiant> getEtudiants() {
        return etudiants.values().stream().toList();

        /*
        etudiants.values() : obtient une collection de toutes les valeurs Etudiant du Map.
        stream() : convertit cette collection en un flux (Stream<Etudiant>).
        toList() : collecte tous les éléments du flux dans une nouvelle List<Etudiant> et retourne cette liste.
        */
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
        return etudiants.values().stream()
                .filter(e -> e.getNom().contains(nom)).findFirst()
                .orElse(null);
    }


    /**
     * Ajouter ou modifier un nouvel étudiant
     */
    public void ajouterOuModifierEtudiant(Etudiant etudiant) {
        etudiants.put(etudiant.getId(), etudiant);
    }


    /**
     * Supprimer un étudiant
     */
    public void supprimerEtudiant(UUID id) {
        etudiants.remove(id); // Supprime l'étudiant du Map grâce à l'UUID
//        etudiants.removeIf(etudiant -> etudiant.getId().equals(id)); // Ne marche pas avec Map, mais List
    }


    // ----- Recherche -----

    public List<Etudiant> rechercheByNom(String nom) {
        return etudiants.values().stream()
                .filter(e -> e.getNom().toLowerCase().contains(nom.toLowerCase()))
                .toList();
    }

}
