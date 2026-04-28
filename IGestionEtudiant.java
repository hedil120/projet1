package Service;

import Model.Etudiants;
import Exception.EtudiantNotFoundException;
import java.time.LocalDate;
import java.util.List;

public interface IGestionEtudiant {

    void ajouterEtudiant(Etudiants e);

    void modifierEtudiant(String m, String n, String p, LocalDate d)
            throws EtudiantNotFoundException;

    Etudiants chercherParMatricule(String m)
            throws EtudiantNotFoundException;

    List<Etudiants> chercherParNom(String nom);

    List<Etudiants> chercherParIntervalleDates(LocalDate d1, LocalDate d2);

    void supprimerEtudiant(String m)
            throws EtudiantNotFoundException;

    void afficherEtudiants();
}
