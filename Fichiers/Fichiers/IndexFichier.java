package Fichiers;
import java.io.*;

/**
 * Utilise RandomAccessFile pour indexer les produits.
 */
public class IndexFichier {

    public static void ecrire(String ref, long position) {
        try (RandomAccessFile raf = new RandomAccessFile("workspace/data/index.dat", "rw")) {
            raf.seek(raf.length());
            raf.writeUTF(ref);
            raf.writeLong(position);
        } catch (IOException e) {
            Journalisation.logErreur("Ecriture index", e);
        }
    }

    public static long rechercher(String ref) {
        try (RandomAccessFile raf = new RandomAccessFile("workspace/data/index.dat", "r")) {
            while (raf.getFilePointer() < raf.length()) {
                String r = raf.readUTF();
                long pos = raf.readLong();
                if (r.equals(ref)) return pos;
            }
        } catch (IOException e) {
            Journalisation.logErreur("Lecture index", e);
        }
        return -1;
    }
}
