import java.util.Date;
public class Main{
    public static void main(String[] args) {
             
            Client client1=new Client("C001", "Soukaina", "Casablanca", "0123456789");
            Client client2=new Client("Imane", "Rabat", "0987654321");
            client1.getLesDemandes().add(new Permanante(1001, "Installation Internet", new java.util.Date()));
            client2.getLesDemandes().add(new Provisoire(2001, "Location de matériel", new Date(), new Date()));
            System.out.println(client1);
            System.out.println(client2);


    }
}