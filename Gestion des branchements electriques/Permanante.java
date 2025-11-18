import java.util.Date;
public class Permanante extends Demande {

    // Attributs
    private Date dateInstallation;

    // Constructor
    public Permanante(long ref, String description, Date dateInstallation) {
        super(ref, description);
        this.dateInstallation = dateInstallation;
    }

    // toString method
    @Override
    public String toString(){
        return super.toString() + ", Date d'installation: " + dateInstallation; 
    }
    
}
