public class Program {
    public static void main(String[] args) {
        // Example usage
        Client client = new Client("C001", "John Doe", "123 Main St", "555-1234");
        Commande commande1 = new Commande(1, new java.util.Date(), "Supplier A");
        Commande commande2 = new Commande(2, new java.util.Date(), "Supplier B");

        client.EnregistrerCommande(commande1);
        client.EnregistrerCommande(commande2);

        System.out.println(client);

        client.SupprimerCommande(1);
        System.out.println(client);
    }
}
