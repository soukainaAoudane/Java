import java.util.Date;

public abstract class Emission implements Comparable {

    // Attributes
    private String TitreEm;
    private String DureeEm;
    private Date DateEm;

    // Constructor with all attributes
    public Emission(String titreEm, String dureeEm, Date dateEm) {
        this.TitreEm = titreEm;
        this.DureeEm = dureeEm;
        this.DateEm = dateEm;
    }

    // Constuctor without date
    public Emission(String titreEm, String dureeEm) {
        this.TitreEm = titreEm;
        this.DureeEm = dureeEm;
        this.DateEm = new Date(); // Sets to current date
    }   

    // Getters and Setters
    public String getTitreEm() {
        return TitreEm;
    }
    public void setTitreEm(String titreEm) {
        TitreEm = titreEm;
    }
    public String getDureeEm() {
        return DureeEm;
    }
    public void setDureeEm(String dureeEm) {
        DureeEm = dureeEm;
    }
    public Date getDateEm() {
        return DateEm;
    }
    public void setDateEm(Date dateEm) {
        DateEm = dateEm;
    }

    // compareTo method for comparing Emission objects based on TitreEm
    @Override
    public int compareTo(Object o) {
        return this.TitreEm.compareTo(((Emission)o).TitreEm);
    }

    // toString method for string representation
    @Override
    public String toString() {
        return "Titre: " + TitreEm + ", la duree: " + DureeEm + ", Date d'emission: " + DateEm;
    }

    // getInfos abstract method to be implemented by subclasses
    public abstract String getInfos();


}
