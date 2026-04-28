package Service;

import Model.Etudiants;

//tekteb f fichier
import java.io.FileInputStream;
//lire men fichier
import java.io.FileOutputStream;
//tekteb objet fi fichier
import java.io.ObjectInputStream;
//lire des objets men fichier
import java.io.ObjectOutputStream;
//ta9ra les exceptions
import java.io.IOException;
//utilise liste
import java.util.List;

public class GestionFichiers {

    // Sauvegarder les étudiants
    public static void sauvegarder(List<Etudiants> etudiants) throws IOException {
        //cree fichier etuduants
        ObjectOutputStream oos =
                new ObjectOutputStream(new FileOutputStream("etudiants.dat"));
        //ecrit  liste des étudiants fel fichier
        oos.writeObject(etudiants);
        oos.close();
    }

    // supprime lzq avertissement cast mta3 l compilateur
    @SuppressWarnings("unchecked")
    public static List<Etudiants> charger()
            throws IOException, ClassNotFoundException {
        //thel l fichier bech ya9ra
        ObjectInputStream ois =
                new ObjectInputStream(new FileInputStream("etudiants.dat"));
        //ta9ra
        List<Etudiants> etudiants =
                (List<Etudiants>) ois.readObject();

        //tsaker l fichier
        ois.close();
        return etudiants;
    }
}
