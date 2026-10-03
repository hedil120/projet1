package Model;
//sauvegarder l’objet Module dans un fichier
import java.io.Serializable;
//Serializable heya lobjet li yetkteb wala yet9ra men fichier
public class Module implements Serializable {
    //dentifiant pour sérialisation w evite les erreurs mta3 chargement
    //serialisation heya le processus de conversion d'un objet en un flux d'octets pour le stockage ou le transfert
    private static final long serialVersionUID = 1L;

    private String code;
    private String nom;
    private double coefficient;
    private boolean obligatoire;

    public Module(String code, String nom, double coefficient, boolean obligatoire) {
        this.code = code;
        this.nom = nom;
        this.coefficient = coefficient;
        this.obligatoire = obligatoire;
    }

    public String getCode() { return code; }
    public String getNom() { return nom; }
    public double getCoefficient() { return coefficient; }
    public boolean isObligatoire() { return obligatoire; }

    @Override
    public String toString() {
        return code + " - " + nom + " | Coeff: " + coefficient +
                //Si obligatoire true maaneha "Obligatoire" Sinon "Optionnel"
                " | " + (obligatoire ? "Obligatoire" : "Optionnel");
    }
}
