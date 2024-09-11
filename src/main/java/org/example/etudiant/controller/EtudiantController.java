package org.example.etudiant.controller;

import jakarta.validation.Valid;
import org.example.etudiant.model.Etudiant;
import org.example.etudiant.service.EtudiantService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;


@Controller
public class EtudiantController {

    // ========== Propriétés ==========

    private final EtudiantService etudiantService; // Service pour gérer les opérations sur les étudiants


    // ========== Constructeur ==========

    /**
     * Constructeur
     * @param etudiantService : Service utilisé pour obtenir et manipuler les données des étudiants
     */
    public EtudiantController(EtudiantService etudiantService) {
        this.etudiantService = etudiantService;
    }


    // ========== Méthodes ==========

    /*
    @RequestMapping : Pour gérer plusieurs types de requêtes HTTP (GET et POST).

    @GetMapping : Uniquement requêtes GET.

    @PostMapping : Uniquement requêtes POST.
    */


    /**
     * Page d'Accueil
     * @return Nom de la vue pour la page d'accueil
     */
//    @RequestMapping("/") // Marche aussi
    @GetMapping("/") // URL : http://localhost:8080/
    public String home(Model model) {
        model.addAttribute("title", "Page d'Accueil - Gestion des étudiants"); // Pour le title de la page
        return "index"; // Renvoie le nom de la vue "index" pour la page d'accueil
    }


    /**
     * Afficher tous les étudiants
     * @param model : Modèle pour ajouter des attributs à la vue
     * @return Nom de la vue pour afficher tous les étudiants
     */
    @GetMapping("/liste") // URL : http://localhost:8080/liste
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
    @GetMapping("/detail/{id}") // URL exemple : http://localhost:8080/detail/1
    public String detailPage(@PathVariable("id") UUID id, Model model) {
        Etudiant etudiant = etudiantService.getEtudiantById(id); // Obtient l'étudiant avec l'identifiant spécifié

        model.addAttribute("etudiant", etudiant); // Ajoute l'étudiant au modèle
        // System.out.println(etudiant);

        model.addAttribute("title", "Détail de l'étudiant " + etudiant.getPrenom() + " " + etudiant.getNom()); // Title dynamique avec le prénom et nom de l'étudiant
        return "detail"; // Renvoie le nom de la vue "detail" pour afficher les détails de l'étudiant
    }

    /*
    @PathVariable : pour gérer les variables de modèle dans le mappage d’URI de la requête et les définir comme paramètres de méthode.
    */


    // ----- Recherche -----

    /**
     * Rechercher étudiant par son nom
     */
    @GetMapping("/recherche") // URL : http://localhost:8080/recherche?nom=tom
    public String recherche(@RequestParam("nom") String nom, Model model) {
        List<Etudiant> etudiants = etudiantService.rechercheByNom(nom);

        model.addAttribute("nom", nom);
        model.addAttribute("etudiants", etudiants);
        model.addAttribute("title", "Recherche d'étudiants"); // Pour le title de la page

        return "recherche"; // Renvoie vers la page de résultat
    }


    /*
    @RequestParam : pour lier les paramètres de requête ou les données de formulaire à un argument de méthode dans un contrôleur.
    */


    // ----- Formulaire -----

    /**
     * Formulaire d'ajout d'étudiant
     */
    @GetMapping("/formulaire") // URL : http://localhost:8080/formulaire
    public String formulaireAjout(Model model) {
        model.addAttribute("etudiant", new Etudiant());
        return "formulaire";
    }

    /**
     * Ajout d'étudiant
     */
    @PostMapping("/ajout") // Marche aussi: @RequestMapping(value = "/ajout", method = RequestMethod.POST)
    public String ajout(@Valid @ModelAttribute("etudiant") Etudiant etudiant, BindingResult bindingResult, Model model) {
//        System.out.println(etudiant.getNom());
//        System.out.println(etudiant.getPrenom());
//        System.out.println(etudiant.getAge());
//        System.out.println(etudiant.getEmail());

        if (bindingResult.hasErrors()) { // Vérification des erreurs de validation
            return "formulaire"; // Si erreurs de validation, retour au formulaire
        }

        etudiant.setId(UUID.randomUUID()); // Générer un UUID pour l'étudiant

        etudiantService.ajouterOuModifierEtudiant(etudiant); // Ajouter l'étudiant au service

        return "redirect:/liste"; // Redirection vers la liste après ajout
    }


    /**
     * Supprimer un étudiant
     */
    @GetMapping("/delete")
    public String delete(@RequestParam("id") UUID id) {
//        System.out.println("Logique de suppression ici");

        etudiantService.supprimerEtudiant(id); // Supprime l'étudiant via le service

        return "redirect:/liste";
    }


    /**
     * Formulaire pour modifier un étudiant
     */
    @GetMapping("/update")
    public String formulaireUpdate(@RequestParam("id") UUID id, Model model) {
        Etudiant etudiant = etudiantService.getEtudiantById(id);
        model.addAttribute("etudiant", etudiant);
        model.addAttribute("choixAction", "update"); // Pour différencier ajout et update

        return "formulaire"; // Même formulaire que pour ajout
    }

    /**
     * Modifie un étudiant
     */
    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("etudiant") Etudiant etudiant, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) { // Vérification des erreurs de validation
            return "formulaire"; // Si erreurs de validation, retour au formulaire
        }

        etudiantService.ajouterOuModifierEtudiant(etudiant); // Modifie l'étudiant existant

        return "redirect:/liste"; // Redirection vers la liste des étudiants après ajout
    }


    // ----- -----

}