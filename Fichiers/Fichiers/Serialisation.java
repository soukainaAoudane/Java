package Fichiers;
import java.io.*;
import java.util.List;

/**
 * Sérialise et désérialise la liste des produits.
 */
public class Serialisation {

    public static void sauvegarder(List<Produit> produits) {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream("workspace/data/produits.ser"))) {
            oos.writeObject(produits);
            System.out.println("Produits sauvegardés !");
        } catch (IOException e) {
            Journalisation.logErreur("Sérialisation produits", e);
        }
    }

    public static List<Produit> charger() {
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream("workspace/data/produits.ser"))) {
            return (List<Produit>) ois.readObject();
        } catch (Exception e) {
            Journalisation.logErreur("Désérialisation produits", new IOException(e));
            return null;
        }
    }
}
