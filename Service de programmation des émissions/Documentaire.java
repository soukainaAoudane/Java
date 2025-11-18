import java.util.Date;
public class Documentaire extends Emission {
  
    // Attribute
    private String theme;

    // Constructor with all attributes
    public Documentaire(String titreEm, String dureeEm, Date dateEm, String theme) {
        super(titreEm, dureeEm, dateEm);
        this.theme = theme;
    }

    // getInfos method implementation
    @Override
    public String getInfos(){
        return "Titre: " + getTitreEm() + ", la duree: " + getDureeEm() + ", Date d'emission: " + getDateEm() + ". Theme: " + theme;
    }
  
}