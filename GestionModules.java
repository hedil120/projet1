package Service;
import Model.Etudiants;
import Model.Note;
import java.util.List;

import Model.Module;
import Exception.ModuleNotFoundException;
import java.util.ArrayList;

public class GestionModules implements IGestionModule {

    private List<Module> modules = new ArrayList<>();

    public void ajouterModule(Module m) {
        modules.add(m);
    }

    public void supprimerModule(String code) {
        modules.removeIf(m -> m.getCode().equalsIgnoreCase(code));
    }

    public Module chercherParCode(String code)
            throws ModuleNotFoundException {
        for (Module m : modules)
            if (m.getCode().equalsIgnoreCase(code))
                return m;
        throw new ModuleNotFoundException(code);
    }

    public List<Module> afficherModules() {
        return modules;
    }
    public boolean moduleUtilise(String code, List<Etudiants> etudiants) {
        for (Etudiants e : etudiants) {
            for (Note n : e.getNotes()) {
                if (n.getModule().getCode().equalsIgnoreCase(code)) {
                    return true;
                }
            }
        }
        return false;
    }

}
