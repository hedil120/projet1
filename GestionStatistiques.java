package Service;
import Model.Etudiants;
import Model.Note;
import Model.Module;
import java.util.*;
public class GestionStatistiques
{
    // moyenne general detudiant
    public double moyenneEtudiant(Etudiants e)
    {
        return e.calculerMoyenne();
    }

    // classement general
    public List<Etudiants> classement(List<Etudiants> etudiants)
    {
        //Comparator.comparingDouble(Etudiants::calculerMoyenne) → compare les étudiants selon leur moyenne
        //.reversed() → tri décroissant (
        etudiants.sort(Comparator.comparingDouble(Etudiants::calculerMoyenne).reversed());
        return etudiants;
    }
    // modules non validés (note < 10)
    public List<Module> modulesNonValides(Etudiants e)
    {
        List<Module> nonValides = new ArrayList<>();
        for (Note n : e.getNotes())
        {
            if (n.getValeur() < 10)
            {
                nonValides.add(n.getModule());
            }
        }
        return nonValides;
    }

    // statistiques par module
    public double moyenneModule(Module m, List<Etudiants> etudiants) {
        double somme = 0;
        int nb = 0;
        for (Etudiants e : etudiants)
        {
            for (Note n : e.getNotes())
            {
                if (n.getModule().getCode().equals(m.getCode()))
                {
                    somme += n.getValeur();
                    nb++;
                }
            }
        }
        //Si aucune note  retourne 0
        //Sinon → retourne la moyenne du modul
        return nb == 0 ? 0 : somme / nb;
    }

}
