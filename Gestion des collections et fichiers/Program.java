package srcc;

import java.util.*;

public class Program {
    public static void main(String[] args) {
        // Création de commandes
        Commande commande1 = new Commande(1, new Date(), "Supplier A");
        Commande commande2 = new Commande(2, new Date(), "Supplier B");
        Commande commande3 = new Commande(3, new Date(), "Supplier C");
        Commande commande4 = new Commande(1, new Date(), "Supplier D"); // Même numéro que commande1

        // Création de clients
        Client client = new Client(1, "Soukaina AOUDANE", "CASABLANCA");
        client.EnregistrerCommande(commande1);
        client.EnregistrerCommande(commande2);

        Client client2 = new Client(2, "IMANE DAHBI", "FES");
        client2.EnregistrerCommande(commande3);

        // Test suppression
        System.out.println("Avant suppression:");
        System.out.println(client);
        
        client.SupprimerCommande(1);
        System.out.println("\nAprès suppression:");
        System.out.println(client);

        // Utilisation d'une List pour stocker plusieurs clients
        List<Client> listeClients = new ArrayList<>();
        listeClients.add(client);
        listeClients.add(client2);

        // Triez les commandes d'un client avec Collections.sort()
        Collections.sort(client2.getListCommandes());
        System.out.println("\nCommandes triées par date:");
        for (Commande cmd : client2.getListCommandes()) {
            System.out.println(cmd);
        }

        // Utilisation d'un Set pour éviter les doublons
        Set<Commande> setCommandes = new HashSet<>();
        setCommandes.add(commande1);
        setCommandes.add(commande2);
        setCommandes.add(commande4);
        System.out.println("\nSet de commandes (taille: " + setCommandes.size() + ")");

        // Utilisation d'une Map pour associer chaque client à son codeClient
        Map<Integer, Client> mapClients = new HashMap<>();
        for (Client cl : listeClients) {
            mapClients.put(cl.getCodeClient(), cl);
        }
        System.out.println("\nMap des clients:");
        for (Map.Entry<Integer, Client> entry : mapClients.entrySet()) {
            System.out.println("Code: " + entry.getKey() + " -> " + entry.getValue());
        }
    }
}
