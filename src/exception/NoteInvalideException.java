package Exception;

public class NoteInvalideException extends Exception {

    public NoteInvalideException(double note) {
        super("Note invalide : " + note + " (doit être entre 0 et 20)");
    }
}
