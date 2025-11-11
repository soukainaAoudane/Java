import java.io.File;
import java.util.List;
import Fichiers.Arborescence;
import Fichiers.CopieBinaire;
import Fichiers.IndexFichier;
import Fichiers.ImportStock;
import Fichiers.Serialisation;
import Fichiers.Vente;

import Fichiers.Produit;

/**
 * Exécute toutes les étapes du TP Java I/O.
 */
public class Main {
    public static void main(String[] args) {

        //  Organisation des dossiers
        Arborescence.creer();

        //  Importation du stock
        List<Produit> produits = ImportStock.charger("workspace/data/stock_initial.csv");
        ImportStock.afficherStats(produits);

        //  Écriture des ventes
        Vente.ecrireBuffered("BK002", 2, 361.00);
        Vente.ecrirePrint("BK003", 1, 199.90);

        //  Copie d’un fichier binaire
        CopieBinaire.copier("dummy.bin", "workspace/data/photos/BK001.jpg");

        //  Index produit
        IndexFichier.ecrire("BK003", 123456789L);
        System.out.println("Position BK003 : " + IndexFichier.rechercher("BK003"));

        //  Sérialisation
        Serialisation.sauvegarder(produits);
        List<Produit> produits2 = Serialisation.charger();
        System.out.println("Produits restaurés : " + (produits2 != null ? produits2.size() : 0));

        //  Lister contenu final
        Arborescence.lister(new File("workspace"));
    }
}
