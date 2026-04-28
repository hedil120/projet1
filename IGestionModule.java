package Service;
import Model.Etudiants;
import java.util.List;
import Model.Module;
import Exception.ModuleNotFoundException;
import java.util.List;

public interface IGestionModule {

    void ajouterModule(Module m);

    void supprimerModule(String code);

    Module chercherParCode(String code)
            throws ModuleNotFoundException;

    List<Module> afficherModules();
    boolean moduleUtilise(String code, List<Etudiants> etudiants);

}
