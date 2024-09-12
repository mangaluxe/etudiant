package org.example.etudiant.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.etudiant.validation.MyValid;

@Data // Annotation Lombok qui génère automatiquement les getters/setters, toString...
@AllArgsConstructor // Annotation Lombok qui génère un constructeur avec tous les arguments.
@NoArgsConstructor // Annotation Lombok qui génère un constructeur sans arguments.
@Builder // Annotation Lombok qui permet de créer des instances de la classe via le modèle de construction
@Entity // Indique que cette classe est une entité JPA (correspond à une table dans la base de données)
public class Etudiant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Indiquent que le champ id est la clé primaire et qu'il sera auto-généré par la base de données (génération automatique de l'ID)
    private int id;

    // @NotNull
    @NotBlank(message = "Champ obligatoire")
    private String nom;

    @NotBlank(message = "Champ obligatoire")
    @Size(min = 3, max = 100, message = "Entre 3 et 100 caractères")
    private String prenom;

    @Min(value = 16, message = "Mini 16 ans")
    @Max(value = 99, message = "Max 99 ans")
    private int age;

    @MyValid(message = "Gros mots interdits") // Validation personnalisée
    @NotBlank(message = "Champ obligatoire")
    @Email(message = "Format d’email : exemple@email.com")
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