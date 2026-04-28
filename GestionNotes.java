package Service;

import Model.Etudiants;
import Model.Module;
import Model.Note;
import Exception.NoteInvalideException;

public class GestionNotes implements IGestionNote {

    private GestionModules gestionModules;

    public GestionNotes(GestionModules gestionModules) {
        this.gestionModules = gestionModules;
    }

    @Override
    public void ajouterNote(Etudiants e, Module m, double valeur, String type)
            throws NoteInvalideException {

        if (valeur < 0 || valeur > 20) {
            throw new NoteInvalideException(valeur);
        }

        Note n = new Note(valeur, m, type);
        e.ajouterNote(n);
    }

    @Override
    public void modifierNote(Etudiants e, String codeModule, double nouvelleValeur)
            throws NoteInvalideException {

        if (nouvelleValeur < 0 || nouvelleValeur > 20) {
            throw new NoteInvalideException(nouvelleValeur);
        }

        for (Note n : e.getNotes()) {
            //parcours les notes
            if (n.getModule().getCode().equalsIgnoreCase(codeModule)) {
                n.setValeur(nouvelleValeur);
                return;
            }
        }
    }

    @Override
    public void supprimerNote(Etudiants e, String codeModule) {
        e.getNotes().removeIf(
                n -> n.getModule().getCode().equalsIgnoreCase(codeModule)
        );
    }
}
