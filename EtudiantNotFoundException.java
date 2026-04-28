package Exception;

public class EtudiantNotFoundException extends Exception {

    public EtudiantNotFoundException(String matricule) {
        super("Étudiant avec matricule '" + matricule + "' introuvable.");
    }
}
