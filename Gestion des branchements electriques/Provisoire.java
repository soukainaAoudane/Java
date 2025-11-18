import java.util.Date;
public class Provisoire extends Demande{

    // Attributes
    private Date dateDebut;
    private Date dateFin;

    // Constructor
    public Provisoire(long ref, String description, Date dateDebut, Date dateFin) {
        super(ref, description);
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    // toString method
    @Override
    public String toString() {
        return super.toString() + ", Date de début: " + dateDebut + ", Date de fin: " + dateFin;
    }
    
}