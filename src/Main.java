package Main;

import Model.Etudiants;
import Model.Module;
import Service.*;

import java.time.LocalDate;
import java.util.List;
//lire au clavier
import java.util.Scanner;
//verif lexistance du fichier
import java.io.File;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        //creation des objets
        GestionEtudiants gestionEtudiants = new GestionEtudiants();
        GestionModules gestionModules = new GestionModules();
        GestionNotes gestionNotes = new GestionNotes(gestionModules);
        GestionStatistiques gestionStat = new GestionStatistiques();

        // chargement au demarrage
        File file = new File("etudiants.dat");
        if (file.exists()) {
            try {
                gestionEtudiants.getEtudiants()
                        .addAll(GestionFichiers.charger());
                System.out.println("Données chargées.");
            } catch (Exception e) {
                System.out.println("Erreur chargement.");
            }
        } else {
            System.out.println("Aucune sauvegarde trouvée.");
        }

        int choix;

        do {
            System.out.println("\n========== MENU PRINCIPAL ==========");
            System.out.println("1. Gestion des étudiants");
            System.out.println("2. Gestion des modules");
            System.out.println("3. Gestion des notes");
            System.out.println("4. Statistiques");
            System.out.println("5. Afficher bulletin");
            System.out.println("0. Quitter");
            System.out.print("Choix : ");
            choix = Integer.parseInt(sc.nextLine());

            switch (choix) {

                case 1:
                    int chE;
                    do {
                        System.out.println("\n---- GESTION DES ÉTUDIANTS ----");
                        System.out.println("1. Ajouter étudiant");
                        System.out.println("2. Modifier étudiant");
                        System.out.println("3. Supprimer étudiant");
                        System.out.println("4. Afficher étudiants");
                        System.out.println("5. Rechercher étudiant");
                        System.out.println("0. Retour");
                        System.out.print("Choix : ");
                        chE = Integer.parseInt(sc.nextLine());

                        try {
                            switch (chE) {

                                case 1:
                                    System.out.print("Matricule : ");
                                    String mat = sc.nextLine();
                                    System.out.print("Nom : ");
                                    String nom = sc.nextLine();
                                    System.out.print("Prénom : ");
                                    String prenom = sc.nextLine();
                                    System.out.print("Date naissance (yyyy-mm-dd) : ");
                                    LocalDate date = LocalDate.parse(sc.nextLine());
                                    gestionEtudiants.ajouterEtudiant(
                                            new Etudiants(mat, nom, prenom, date));
                                    System.out.println("Étudiant ajouté.");
                                    break;

                                case 2:
                                    System.out.print("Matricule : ");
                                    String matMod = sc.nextLine();
                                    System.out.print("Nouveau nom : ");
                                    String nNom = sc.nextLine();
                                    System.out.print("Nouveau prénom : ");
                                    String nPrenom = sc.nextLine();
                                    System.out.print("Nouvelle date : ");
                                    LocalDate nDate = LocalDate.parse(sc.nextLine());
                                    gestionEtudiants.modifierEtudiant(matMod, nNom, nPrenom, nDate);
                                    System.out.println("Étudiant modifié.");
                                    break;

                                case 3:
                                    System.out.print("Matricule : ");
                                    gestionEtudiants.supprimerEtudiant(sc.nextLine());
                                    System.out.println("Étudiant supprimé.");
                                    break;

                                case 4:
                                    gestionEtudiants.afficherEtudiants();
                                    break;

                                case 5:
                                    System.out.println("1. Par matricule");
                                    System.out.println("2. Par nom");
                                    System.out.println("3. Par intervalle de dates");
                                    System.out.print("Choix : ");
                                    int r = Integer.parseInt(sc.nextLine());

                                    if (r == 1) {
                                        System.out.print("Matricule : ");
                                        System.out.println(
                                                gestionEtudiants.chercherParMatricule(sc.nextLine())
                                        );

                                    } else if (r == 2) {
                                        System.out.print("Nom : ");
                                        List<Etudiants> l =
                                                gestionEtudiants.chercherParNom(sc.nextLine());
                                        l.forEach(System.out::println);

                                    } else if (r == 3) {
                                        System.out.print("Date début (yyyy-mm-dd) : ");
                                        LocalDate d1 = LocalDate.parse(sc.nextLine());

                                        System.out.print("Date fin (yyyy-mm-dd) : ");
                                        LocalDate d2 = LocalDate.parse(sc.nextLine());

                                        List<Etudiants> l =
                                                gestionEtudiants.chercherParIntervalleDates(d1, d2);

                                        if (l.isEmpty()) {
                                            System.out.println("Aucun étudiant trouvé.");
                                        } else {
                                            l.forEach(System.out::println);
                                        }
                                    }
                                    break;
                            }
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }

                    } while (chE != 0);
                    break;

                case 2:
                    int chM;
                    do {
                        System.out.println("\n---- GESTION DES MODULES ----");
                        System.out.println("1. Ajouter module");
                        System.out.println("2. Supprimer module");
                        System.out.println("3. Afficher modules");
                        System.out.println("0. Retour");
                        System.out.print("Choix : ");
                        chM = Integer.parseInt(sc.nextLine());

                        try {
                            switch (chM) {
                                case 1:
                                    System.out.print("Code : ");
                                    String code = sc.nextLine();
                                    System.out.print("Nom : ");
                                    String nomM = sc.nextLine();
                                    System.out.print("Coefficient : ");
                                    double coeff = Double.parseDouble(sc.nextLine());
                                    System.out.print("Obligatoire (true/false) : ");
                                    boolean ob = Boolean.parseBoolean(sc.nextLine());
                                    gestionModules.ajouterModule(
                                            new Module(code, nomM, coeff, ob));
                                    System.out.println("Module ajouté.");
                                    break;

                                case 2:
                                    System.out.print("Code : ");
                                    String codeSupp = sc.nextLine();

                                    if (gestionModules.moduleUtilise(codeSupp,
                                            gestionEtudiants.getEtudiants())) {
                                        System.out.println("Impossible : module déjà utilisé.");
                                    } else {
                                        gestionModules.supprimerModule(codeSupp);
                                        System.out.println("Module supprimé.");
                                    }
                                    break;

                                case 3:
                                    gestionModules.afficherModules()
                                            .forEach(System.out::println);
                                    break;
                            }
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }

                    } while (chM != 0);
                    break;

                case 3:
                    try {
                        System.out.print("Matricule étudiant : ");
                        Etudiants e =
                                gestionEtudiants.chercherParMatricule(sc.nextLine());

                        System.out.print("Code module : ");
                        Module m = gestionModules.chercherParCode(sc.nextLine());

                        System.out.print("Note : ");
                        double note = Double.parseDouble(sc.nextLine());
                        System.out.print("Type de contrôle : ");
                        String type = sc.nextLine();

                        gestionNotes.ajouterNote(e, m, note, type);
                        System.out.println("Note ajoutée.");
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;

                case 4:
                    int chS;
                    do {
                        System.out.println("\n---- STATISTIQUES ----");
                        System.out.println("1. Moyenne d’un étudiant");
                        System.out.println("2. Classement général");
                        System.out.println("3. Modules non validés");
                        System.out.println("4. Moyenne par module");
                        System.out.println("0. Retour");
                        System.out.print("Choix : ");
                        chS = Integer.parseInt(sc.nextLine());

                        try {
                            switch (chS) {

                                case 1:
                                    System.out.print("Matricule : ");
                                    Etudiants e =
                                            gestionEtudiants.chercherParMatricule(sc.nextLine());
                                    System.out.println("Moyenne : " +
                                            gestionStat.moyenneEtudiant(e));
                                    break;

                                case 2:
                                    List<Etudiants> classement =
                                            gestionStat.classement(gestionEtudiants.getEtudiants());

                                    System.out.println("Rang | Matricule | Moyenne");
                                    int rang = 1;
                                    for (Etudiants et : classement) {
                                        System.out.printf("%d | %s | %.2f%n",
                                                rang++, et.getMatricule(), et.calculerMoyenne());
                                    }
                                    break;

                                case 3:
                                    System.out.print("Matricule : ");
                                    Etudiants etu =
                                            gestionEtudiants.chercherParMatricule(sc.nextLine());

                                    gestionStat.modulesNonValides(etu)
                                            .forEach(mod ->
                                                    System.out.println(mod.getCode() + " " + mod.getNom()));
                                    break;

                                case 4:
                                    System.out.print("Code module : ");
                                    Module mod =
                                            gestionModules.chercherParCode(sc.nextLine());

                                    System.out.println("Moyenne : " +
                                            gestionStat.moyenneModule(
                                                    mod, gestionEtudiants.getEtudiants()));
                                    break;
                            }
                        } catch (Exception ex) {
                            System.out.println(ex.getMessage());
                        }

                    } while (chS != 0);
                    break;

                case 5:
                    try {
                        System.out.print("Matricule : ");
                        Etudiants e =
                                gestionEtudiants.chercherParMatricule(sc.nextLine());
                        BulletinService.afficher(e);
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;


            }
        } while (choix != 0);

        // sauvegarde5
        try {
            GestionFichiers.sauvegarder(
                    gestionEtudiants.getEtudiants());
            System.out.println("Données sauvegardées.");
        } catch (Exception e) {
            System.out.println("Erreur sauvegarde.");
        }

        sc.close();
        System.out.println("Fin du programme.");
    }
}
