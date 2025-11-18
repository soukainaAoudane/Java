import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GestionUtilisateurs {
    private List<Utilisateur> utilisateurs;

    // Constructor
    public GestionUtilisateurs() {
        this.utilisateurs = new ArrayList<>();
    }

    // ajouterUtilisateur method
    public void ajouterUtilisateur(Utilisateur utilisateur) {
        utilisateurs.add(utilisateur);
    }

    // trierUtilisateurs method
    public void trierUtilisateurs() {
        Collections.sort(utilisateurs);
    }

    // afficherUtilisateurs method
    public void afficherUtilisateurs() {
        for (Utilisateur utilisateur : utilisateurs) {
            System.out.println(utilisateur);
        }
    }

    // sauvegarderDansFichier method
    public void sauvegarderDansFichier(String nomFichier) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomFichier))) {
            oos.writeObject(utilisateurs);
            System.out.println("Utilisateurs sauvegardés avec succès dans " + nomFichier);
        } catch (IOException e) {
            System.err.println("Erreur lors de la sauvegarde: " + e.getMessage());
        }
    }

    // chargerDepuisFichier method
    public void chargerDepuisFichier(String nomFichier) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomFichier))) {
            utilisateurs = (List<Utilisateur>) ois.readObject();
            System.out.println("Utilisateurs chargés avec succès depuis " + nomFichier);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erreur lors du chargement: " + e.getMessage());
        }
    }
}