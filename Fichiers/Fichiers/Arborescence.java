package Fichiers;
import java.io.File;

/**
 * Crée et affiche l’arborescence de travail (workspace).
 */
public class Arborescence {

    public static void creer() {
        String[] dossiers = {
            "workspace/data/photos",
            "workspace/logs",
            "workspace/tmp"
        };
        for (String chemin : dossiers) {
            File dir = new File(chemin);
            dir.mkdirs();
            System.out.println("Créé : " + dir.getAbsolutePath());
        }
    }

    public static void lister(File dossier) {
        File[] fichiers = dossier.listFiles();
        if (fichiers != null) {
            for (File f : fichiers) {
                if (f.isDirectory()) {
                    System.out.println("[Dossier] " + f.getName());
                    lister(f);
                } else {
                    System.out.println("[Fichier] " + f.getName() + " (" + f.length() + " octets)");
                }
            }
        }
    }
}
