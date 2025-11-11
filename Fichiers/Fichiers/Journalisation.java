package Fichiers;
import java.io.*;
import java.time.LocalDateTime;

/**
 * Écrit les erreurs dans logs/app.log.
 */
public class Journalisation {

    public static void logErreur(String operation, IOException e) {
        try (PrintWriter log = new PrintWriter(
                new FileWriter("workspace/logs/app.log", true))) {
            log.printf("%s | %s | %s%n", LocalDateTime.now(), operation, e.getMessage());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
