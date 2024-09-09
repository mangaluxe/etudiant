package org.example.etudiant.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data // Annotation Lombok qui génère automatiquement les getters/setters, toString...
@AllArgsConstructor // Annotation Lombok qui génère un constructeur avec tous les arguments.
@NoArgsConstructor // Annotation Lombok qui génère un constructeur sans arguments.
@Builder // Annotation Lombok qui permet de créer des instances de la classe via le modèle de construction
public class Etudiant {
//    private int id;
    private UUID id; // Utilisation de UUID pour garantir que chaque identifiant est unique
    private String nom;
    private String prenom;
    private int age;
    private String email;
}

/*
@Builder : Permet de créer une instance de la classe en utilisant le modèle de construction. Exemple :

    Etudiant etudiant = Etudiant.builder()
                         .id(UUID.randomUUID())
                         .nom("Dupont")
                         .prenom("Jean")
                         .age(21)
                         .email("jean.dupont@example.com")
                         .build();
*/