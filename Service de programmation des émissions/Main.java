import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.util.Date;

public class Main {
  public static void main(String[] args) throws IOException, ClassNotFoundException  {
    // Create some Emission objects
    Emission e1 = new Fiction("Titre1", "Duree1", new Date(), "Realisateur1", "Producteur1");
    Emission e2 = new Fiction("Titre2", "Duree2", new Date(), "Realisateur2", "Producteur2");
    Emission e3 = new Documentaire("Titre3", "Duree3", new Date(), "Theme3");

    // Create Programme 
    Programme p = new Programme();

    // Add Emission objects to Programme
    p.addEmission(e1);
    p.addEmission(e2);
    p.addEmission(e3);

    // Display all Emission objects in Programme
    for (Emission e : p.getListe_emission()) {
      System.out.println(e);
    }

    // Save Emission objects
    p.saveAll("emissions.ser");

    // Load Emission objects
    p.loadAll("emissions.ser"); 
    

  }
}
