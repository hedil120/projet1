package Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
//sauvegarde l'objet dans fichier
import java.io.Serializable;

public class Etudiants implements Serializable {
    private static final long serialVersionUID = 1L;


    private String matricule;
    private String nom;
    private String prenom;
    private LocalDate dateNaissance;
    private List<Note> notes = new ArrayList<>();

    public Etudiants(String matricule, String nom, String prenom, LocalDate dateNaissance) {
        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
    }

    public String getMatricule() { return matricule; }
    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
    public LocalDate getDateNaissance() { return dateNaissance; }
    public List<Note> getNotes() { return notes; }

    public void setNom(String nom) { this.nom = nom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public void ajouterNote(Note n) {
        notes.add(n);
    }

    public double calculerMoyenne() {
        double somme = 0, coeff = 0;
        //parcour les notes
        for (Note n : notes) {
            somme += n.getValeur() * n.getModule().getCoefficient();
            coeff += n.getModule().getCoefficient();
        }
        //Si aucun coefficient → moyenne = 0
        // Sinon → moyenne = somme / coefficients
        return coeff == 0 ? 0 : somme / coeff;
    }

    public String getMention() {
        double m = calculerMoyenne();
        if (m >= 16) return "TRÈS BIEN";
        if (m >= 14) return "BIEN";
        if (m >= 12) return "ASSEZ BIEN";
        if (m >= 10) return "PASSABLE";
        return "AJOURNÉ";
    }

    @Override
    //redefinie laffichage
    //toString transforme objet en texte
    public String toString() {
        return matricule + " - " + prenom + " " + nom + " (" + dateNaissance + ")";
    }
}
