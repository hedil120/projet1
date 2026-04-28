package Model;

import java.io.Serializable;

public class Note implements Serializable {
    private static final long serialVersionUID = 1L;



    private double valeur;
    private Module module;
    private String typeControle;

    public Note(double valeur, Module module, String typeControle) {
        this.valeur = valeur;
        this.module = module;
        this.typeControle = typeControle;
    }

    public double getValeur() { return valeur; }
    public void setValeur(double valeur) { this.valeur = valeur; }
    public Module getModule() { return module; }
}
