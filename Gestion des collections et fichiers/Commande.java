package srcc;
import java.util.Date;

public class Commande implements Comparable<Commande> {
    // Attributes
    private int NumCommande;
    private Date DateCommande;
    private String NomFournisseur;

    // Constructor
    public Commande(int numCommande, Date dateCommande, String nomFournisseur) {
        this.NumCommande = numCommande;
        this.DateCommande = dateCommande;
        this.NomFournisseur = nomFournisseur;
    }

    // Getters and Setters
    public int getNumCommande() {
        return NumCommande;
    }

    public void setNumCommande(int numCommande) {
        NumCommande = numCommande;
    }

    public Date getDateCommande() {
        return DateCommande;
    }

    public void setDateCommande(Date dateCommande) {
        DateCommande = dateCommande;
    }

    public String getNomFournisseur() {
        return NomFournisseur;
    }

    public void setNomFournisseur(String nomFournisseur) {
        NomFournisseur = nomFournisseur;
    }

    // toString method
    @Override
    public String toString() {
        return "Commande [NumCommande=" + getNumCommande() + ", DateCommande=" + getDateCommande() + ", NomFournisseur="
                + NomFournisseur + "]";
    }

    // equals method
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Commande other = (Commande) obj;
        return this.NumCommande == other.NumCommande;
    }

    // compareTo method
    @Override
    public int compareTo(Commande other) {
        return this.DateCommande.compareTo(other.DateCommande);
    }
}