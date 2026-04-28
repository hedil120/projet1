package Exception;

public class ModuleNotFoundException extends Exception {

    public ModuleNotFoundException(String code) {
        super("Module avec code '" + code + "' introuvable.");
    }
}
