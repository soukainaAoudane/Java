package Fichiers;
import java.io.*;

/**
 * Copie un fichier binaire (photo de produit).
 */
public class CopieBinaire {

    public static void copier(String source, String destination) {
        long start = System.currentTimeMillis();
        try (InputStream in = new FileInputStream(source);
             OutputStream out = new FileOutputStream(destination)) {

            byte[] buffer = new byte[8192];
            int n;
            while ((n = in.read(buffer)) != -1) {
                out.write(buffer, 0, n);
            }

            long end = System.currentTimeMillis();
            System.out.println("Copie terminée en " + (end - start) + " ms");

        } catch (IOException e) {
            Journalisation.logErreur("Copie fichier binaire", e);
        }
    }
}
