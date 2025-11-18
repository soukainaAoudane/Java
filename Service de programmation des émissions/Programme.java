import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Programme {
    
    // Attribute
    ArrayList<Emission> Liste =new ArrayList<>();

    // getListe_emission method to return the Liste
    public ArrayList<Emission> getListe_emission(){
        return Liste;
    }
    
    // ajouterEmission method to add an Emission to the Liste
    public boolean addEmission(Emission e){
        return Liste.add(e);
    }


    // delEmission method to remove an Emission from the Liste
    public Emission delEmission(int p) throws ErrIndexException {
        if(p < 0 || p >= Liste.size()){
            throw new ErrIndexException("Index out of bounds: " + p);
        }
        return Liste.remove(p) ;
    }

    // delEmission method to remove an Emission by object
    public boolean delEmission(Emission e){
        return Liste.remove(e);
    }

    // setEmission method to set an Emission at a specific position
    public Emission setEmission(int p,Emission e) throws ErrIndexException {
        if(p < 0 || p >= Liste.size()){
            throw new ErrIndexException("Index out of bounds: " + p);
        }
        return Liste.set(p, e);
    }

    // toString method for string representation of the Programme
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Liste des émissions :\n");
        for(Emission e : Liste){
            sb.append(e.toString()).append("\n");
        }
        return sb.toString();
    }

    // saveAll method to save all Emissions to a file
    public boolean saveAll(String Fichier) {
        try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(Fichier))) {
            for (Emission e : Liste) {
                os.writeObject(e);
            }
            os.close();
        } catch (IOException e) {
            System.out.println("Erreur d'accès au fichier : " + e.getMessage());
            return false;
        }
        return true;
    }   
    
    // loadAll method to load all Emissions from a file
    public boolean loadAll(String Fichier) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(Fichier))) {
            System.out.println("Les émissions enregistrées dans le fichier sont :");
            while (true) {
                Emission e = (Emission) in.readObject();
                System.out.println(e);
            }
        } catch (IOException e) {
            System.out.println("Erreur d'accès au fichier : " + e.getMessage());
            return false;
        } catch (ClassNotFoundException em) {
            System.out.println("Classe non trouvée : " + em.getMessage());
            return false;
        }
    }
}
