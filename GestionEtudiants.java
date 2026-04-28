package Service;

import Model.Etudiants;
import Exception.EtudiantNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GestionEtudiants implements IGestionEtudiant {

    private List<Etudiants> etudiants = new ArrayList<>();

    @Override
    public void ajouterEtudiant(Etudiants e) {
        etudiants.add(e);
    }

    @Override
    public void modifierEtudiant(String m, String n, String p, LocalDate d)
            throws EtudiantNotFoundException {
        Etudiants e = chercherParMatricule(m);
        e.setNom(n);
        e.setPrenom(p);
        e.setDateNaissance(d);
    }

    @Override
    public Etudiants chercherParMatricule(String m)
            throws EtudiantNotFoundException {
        //parcour la liste
        for (Etudiants e : etudiants)
            //Si matricule trouvé → retourne l’étudiant
            if (e.getMatricule().equals(m)) return e;
        throw new EtudiantNotFoundException(m);
    }

    @Override
    public List<Etudiants> chercherParNom(String nom) {
        List<Etudiants> res = new ArrayList<>();
        for (Etudiants e : etudiants)
            //equalsIgnoreCase ignore majus minus
            if (e.getNom().equalsIgnoreCase(nom)) res.add(e);
        return res;
    }

    @Override
    public List<Etudiants> chercherParIntervalleDates(LocalDate d1, LocalDate d2) {
        List<Etudiants> res = new ArrayList<>();
        for (Etudiants e : etudiants) {
            if (!e.getDateNaissance().isBefore(d1)
                    && !e.getDateNaissance().isAfter(d2)) {
                res.add(e);
            }
        }
        return res;
    }

    @Override
    public void supprimerEtudiant(String m)
            throws EtudiantNotFoundException {
        etudiants.remove(chercherParMatricule(m));
    }

    @Override
    public void afficherEtudiants() {
        etudiants.forEach(System.out::println);
    }

    public List<Etudiants> getEtudiants() {
        return etudiants;
    }
}
