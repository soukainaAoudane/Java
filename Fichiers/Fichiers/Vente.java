package Fichiers;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

/**
 * Écrit les ventes dans ventes.txt en texte UTF-8.
 */
public class Vente {

    public static void ecrireBuffered(String ref, int qte, double montant) {
        try (BufferedWriter bw = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream("workspace/data/ventes.txt", true),
                        StandardCharsets.UTF_8))) {

            bw.write(LocalDateTime.now() + ";" + ref + ";" + qte + ";" + montant);
            bw.newLine();
            bw.flush();

        } catch (IOException e) {
            Journalisation.logErreur("Ecriture vente (Buffered)", e);
        }
    }

    public static void ecrirePrint(String ref, int qte, double montant) {
        try (PrintWriter out = new PrintWriter(
                new OutputStreamWriter(
                        new FileOutputStream("workspace/data/ventes.txt", true),
                        StandardCharsets.UTF_8), true)) {

            out.printf("%s;%s;%d;%.2f%n", LocalDateTime.now(), ref, qte, montant);

        } catch (IOException e) {
            Journalisation.logErreur("Ecriture vente (PrintWriter)", e);
        }
    }
}
