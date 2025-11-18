import java.io.*;
import java.util.ArrayList;

public class Client implements Serializable {

    // Attributes
    private String code;
    private String nom;
    private String adresse;
    private String tel;
    private static int nombreClient =0;
    private ArrayList<Demande> lesDemandes;

    // Constructor with all parameters
    public Client(String code, String nom, String adresse, String tel){
        nombreClient++;
        this.code = code;
        this.nom = nom;
        this.adresse = adresse;
        this.tel = tel;
        this.lesDemandes = new ArrayList<>();
    }

    // Constructor without code
    public Client(String nom, String adresse, String tel){
        nombreClient++;
        this.nom = nom;
        this.adresse = adresse;
        this.tel = tel;
        this.lesDemandes = new ArrayList<>();
    }

    // Getters and setters
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getAdresse() { return adresse; }
    public void setAdresse(String adresse) { this.adresse = adresse; }

    public String getTel() { return tel; }
    public void setTel(String tel) { this.tel = tel; }

    public static int getNombreClient() { return nombreClient; }
    public static void setNombreClient(int nombreClient) { Client.nombreClient = nombreClient; }

    public ArrayList<Demande> getLesDemandes() { return lesDemandes; }

    // toString method
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Nom : ").append(nom)
          .append("\nAdresse : ").append(adresse)
          .append("\nCode : ").append(code)
          .append("\nTéléphone : ").append(tel)
          .append("\nListe des demandes : \n");

        for (Demande d : lesDemandes) {
            sb.append(d.toString()).append("\n");
        }

        return sb.toString();
    }

    // Add a demande
    public boolean addDemande(Demande d){
        return lesDemandes.add(d);
    }

    // Search demande by reference
    public Demande searchDemande(long ref){
        for (Demande d : lesDemandes) {
            if (d.getRef() == ref) {
                return d;
            }
        }
        return null;
    }

    // Delete demande by reference
    public boolean delDemande(long ref){
        Demande d = searchDemande(ref);
        if (d != null) {
            return lesDemandes.remove(d);
        }
        return false;
    }

    // Save demandes to file
    public boolean saveDemandes(String nomFichier){
        try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(nomFichier))) {
            for (Demande d : lesDemandes) {
                os.writeObject(d);
            }
            return true;
        } catch (IOException e) {
            System.out.println("Erreur d'accès au fichier : " + e.getMessage());
            return false;
        }
    }
}
