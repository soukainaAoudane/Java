package Fichiers;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * Lit le fichier CSV stock_initial.csv et crée les objets Produit.
 */
public class ImportStock {

    public static List<Produit> charger(String chemin) {
        List<Produit> produits = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(chemin),
                        StandardCharsets.UTF_8))) {

            String ligne;
            boolean header = true;
            while ((ligne = br.readLine()) != null) {
                if (header) { header = false; continue; }
                String[] t = ligne.split(";");
                Produit p = new Produit(t[0], t[1],
                        Double.parseDouble(t[2]),
                        Integer.parseInt(t[3]));
                produits.add(p);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return produits;
    }

    public static void afficherStats(List<Produit> produits) {
        double total = 0;
        for (Produit p : produits) total += p.getPrix() * p.getQte();
        System.out.println("Produits importés : " + produits.size());
        System.out.println("Valeur totale du stock : " + total);
    }
}
