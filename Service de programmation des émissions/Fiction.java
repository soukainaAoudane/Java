import java.util.Date;
public class Fiction extends Emission {

   // Attribute
   private String realisateur;
   private String producteur;

   // Constructor with all attributes
    public Fiction(String titreEm, String dureeEm, Date dateEm, String realisateur, String producteur) {
         super(titreEm, dureeEm, dateEm);
         this.realisateur = realisateur;
         this.producteur = producteur;
    }

    // getInfos method implementation
    @Override
    public String getInfos(){
        return "Titre: " + getTitreEm() + ", la duree: " + getDureeEm() + ", Date d'emission: " + getDateEm() + "\n Realisateur: " + realisateur + ".\n Producteur: " + producteur;
    }
    

}
