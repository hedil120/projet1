package Service;

import Model.Etudiants;
import Model.Note;

public class BulletinService {

    public static void afficher(Etudiants e) {

        System.out.println("================= BULLETIN =================");
        System.out.println("Nom : " + e.getNom());
        System.out.println("Prénom : " + e.getPrenom());
        System.out.println("Matricule : " + e.getMatricule());
        System.out.println();

        System.out.println("MODULE        NOTE   COEFF");
        System.out.println("--------------------------------");

        for (Note n : e.getNotes()) {
            System.out.printf(
                    "%-12s %-6.1f %-5.0f%n",
                    n.getModule().getCode(),
                    n.getValeur(),
                    n.getModule().getCoefficient()
            );
        }

        System.out.println();
        System.out.printf("Moyenne générale : %.2f%n", e.calculerMoyenne());
        System.out.println("Mention : " + e.getMention());
        System.out.println("============================================");
    }
}
