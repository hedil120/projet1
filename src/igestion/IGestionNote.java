package Service;

import Model.Etudiants;
import Model.Module;

import Exception.NoteInvalideException;

public interface IGestionNote {

    void ajouterNote(Etudiants e, Module m, double valeur, String type)
            throws NoteInvalideException;

    void modifierNote(Etudiants e, String codeModule, double nouvelleValeur)
            throws NoteInvalideException;

    void supprimerNote(Etudiants e, String codeModule);
}
