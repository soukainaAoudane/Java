import java.io.Serializable;
import java.util.Date;

public class Demande implements Serializable, Comparable<Demande> {

    // Attributs
    private long ref;
    private Date dateDemande;
    private String description;

    // Constructor with all attributes
    public Demande(long ref, Date dateDemande, String description) {
        this.ref = ref;
        this.dateDemande = (dateDemande != null) ? dateDemande : new Date();
        this.description = description;
    }

    // Constructor without dateDemande
    public Demande(long ref, String description) {
        this.ref = ref;
        this.dateDemande = new Date();   // Date actuelle
        this.description = description;
    }

    // Getters and Setters
    public long getRef() {
        return ref;
    }

    public void setRef(long ref) {
        this.ref = ref;
    }

    public Date getDateDemande() {
        return dateDemande;
    }

    public void setDateDemande(Date dateDemande) {
        this.dateDemande = dateDemande;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // toString method
    @Override
    public String toString() {
        return "Reference : " + ref
                + ", Date de demande : " + dateDemande
                + ", Description : " + description;
    }

    // compareTo method
    @Override
    public int compareTo(Demande other) {
        return Long.compare(this.ref, other.ref);
    }
}
