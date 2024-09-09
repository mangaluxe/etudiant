package org.example.etudiant.controller;

import org.example.etudiant.model.Etudiant;
import org.example.etudiant.service.EtudiantService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@Controller
public class EtudiantController {

    // ----- Propriétés -----

    private final EtudiantService etudiantService; // Service pour gérer les opérations sur les étudiants


    // ----- Constructeur -----

    /**
     * Constructeur
     * @param etudiantService : Service utilisé pour obtenir et manipuler les données des étudiants
     */
    public EtudiantController(EtudiantService etudiantService) {
        this.etudiantService = etudiantService;
    }


    // ----- Méthodes avec routes -----

    /**
     * Page d'Accueil
     * @return Nom de la vue pour la page d'accueil
     */
    @RequestMapping("/") // URL : http://localhost:8080/
    public String home(Model model) {
        model.addAttribute("title", "Page d'Accueil - Gestion des étudiants"); // Pour le title de la page
        return "index"; // Renvoie le nom de la vue "index" pour la page d'accueil
    }


    /**
     * Afficher tous les étudiants
     * @param model : Modèle pour ajouter des attributs à la vue
     * @return Nom de la vue pour afficher tous les étudiants
     */
    @RequestMapping("/liste") // URL : http://localhost:8080/liste
    public String liste(Model model) {
        model.addAttribute("etudiants", etudiantService.getEtudiants()); // Ajoute la liste des étudiants au modèle
        model.addAttribute("title", "Liste des étudiants"); // Pour le title de la page
        return "liste"; // Renvoie le nom de la vue "liste" pour afficher tous les étudiants
    }


    /**
     * Afficher les détails d'un étudiant
     * @param id : Id de l'étudiant
     * @param model : Modèle pour ajouter des attributs à la vue
     * @return Nom de la vue pour afficher les détails d'un étudiant
     */
    @RequestMapping("/detail/{etudiantId}") // URL exemple : http://localhost:8080/detail/1
    public String detailPage(@PathVariable("etudiantId") UUID id, Model model) {
        Etudiant etudiant = etudiantService.getEtudiantById(id); // Obtient l'étudiant avec l'identifiant spécifié
        model.addAttribute("etudiant", etudiant); // Ajoute l'étudiant au modèle
        model.addAttribute("title", "Détail de l'étudiant " + etudiant.getPrenom() + " " + etudiant.getNom()); // Title dynamique avec le prénom et nom de l'étudiant
        return "detail"; // Renvoie le nom de la vue "detail" pour afficher les détails de l'étudiant
    }



    /**
     * Inscrire un nouvel étudiant
     */
    @GetMapping("/inscription") // URL : http://localhost:8080/inscription
    public String form(Model model) {
        model.addAttribute("etudiant", new Etudiant());
        model.addAttribute("title", "Inscription"); // Pour le title de la page
        return "inscription"; // Nom de la vue contenant le formulaire
    }


//    @PostMapping("/ajouter") // URL : http://localhost:8080/ajouter
//    public String ajouterEtudiant(Etudiant etudiant) {
//
//    }


    // Ajout manuel sans formulaire :
    @GetMapping("/ajouter") // URL : http://localhost:8080/ajouter?nom=Toto&prenom=Tata&age=25&email=toto@email.com
    public String ajouterEtudiant(@RequestParam("nom") String nom,
                                  @RequestParam("prenom") String prenom,
                                  @RequestParam("age") int age,
                                  @RequestParam("email") String email) {
        Etudiant etudiant = Etudiant.builder()
                .id(UUID.randomUUID())
                .nom(nom)
                .prenom(prenom)
                .age(age)
                .email(email)
                .build();
        etudiantService.ajouterEtudiant(etudiant);

        return "redirect:/liste"; // Redirection vers la liste des étudiants après ajout
    }


    @RequestMapping("/recherche") // URL : http://localhost:8080/recherche?nom=Bogard
    public String rechercheEtudiant(@RequestParam(value = "nom", required = false) String nom, Model model) {
        Etudiant etudiant = etudiantService.getEtudiantByNom(nom);

        model.addAttribute("etudiant", etudiant);
        model.addAttribute("title", "Recherche d'étudiants"); // Pour le title de la page
        return "recherche"; // Renvoie vers la page de résultat
    }


}