import java.io.*;
import java.util.Scanner;
import Classes.Article;
import Classes.CodeReduction;
import Classes.LignePanier;
import exceptions.KendiFoodException;
import exceptions.StockInsuffisantException;


public class KendiFood {
    private Article[] catalogue;
    private int tailleCatalogue;
    private int capaciteCatalogue;
    
    private LignePanier[] panier;
    private int taillePanier;
    private int capacitePanier;
    
    private CodeReduction[] codesReduction;
    private int tailleCodes;
    
    private int[][] statistiques;
    
    private Scanner scanner;
    
    private static final int CAPACITE_INITIALE_CATALOGUE = 10;
    private static final int CAPACITE_INITIALE_PANIER = 5;
    private static final int INCREMENT_CAPACITE = 5;
    
    public KendiFood() {
        capaciteCatalogue = CAPACITE_INITIALE_CATALOGUE;
        catalogue = new Article[capaciteCatalogue];
        tailleCatalogue = 0;
        
        capacitePanier = CAPACITE_INITIALE_PANIER;
        panier = new LignePanier[capacitePanier];
        taillePanier = 0;
        
        codesReduction = new CodeReduction[10];
        tailleCodes = 0;
        
        statistiques = new int[CAPACITE_INITIALE_CATALOGUE][2];
        
        scanner = new Scanner(System.in);
        
        initialiserDonneesTest();
    }
    
    // Phase 1: Gestion du catalogue
    public void ajouterArticle(Article article) throws KendiFoodException {
        int index = trouverIndexParId(article.getId());
        if (index != -1) {
            throw new KendiFoodException("Un article avec l'ID '" + article.getId() + "' existe déjà");
        }
        
        if (tailleCatalogue >= capaciteCatalogue) {
            agrandirCatalogue();
        }
        
        catalogue[tailleCatalogue] = article;
        tailleCatalogue++;
    }
    
    public int trouverIndexParId(String id) {
        for (int i = 0; i < tailleCatalogue; i++) {
            if (catalogue[i].getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }
    
    private void agrandirCatalogue() {
        int nouvelleCapacite = capaciteCatalogue + INCREMENT_CAPACITE;
        Article[] nouveauCatalogue = new Article[nouvelleCapacite];
        
        for (int i = 0; i < tailleCatalogue; i++) {
            nouveauCatalogue[i] = catalogue[i];
        }
        
        catalogue = nouveauCatalogue;
        capaciteCatalogue = nouvelleCapacite;
        
        int[][] nouvellesStats = new int[nouvelleCapacite][2];
        for (int i = 0; i < tailleCatalogue; i++) {
            nouvellesStats[i] = statistiques[i];
        }
        statistiques = nouvellesStats;
    }
    
    public void afficherCatalogue() {
        System.out.println("\n[CATALOGUE]");
        if (tailleCatalogue == 0) {
            System.out.println("Aucun article dans le catalogue.");
            return;
        }
        
        for (int i = 0; i < tailleCatalogue; i++) {
            System.out.println(catalogue[i]);
        }
    }
    
    // Phase 2: Gestion du panier
    public void ajouterAuPanier(String id, int quantite) throws KendiFoodException {
        if (quantite <= 0) {
            throw new KendiFoodException("La quantité doit être positive");
        }
        
        int indexArticle = trouverIndexParId(id);
        if (indexArticle == -1) {
            throw new KendiFoodException("Article non trouvé: " + id);
        }
        
        Article article = catalogue[indexArticle];
        
        if (quantite > article.getStock()) {
            throw new StockInsuffisantException(id, article.getStock());
        }
        
        int indexPanier = trouverIndexDansPanier(id);
        if (indexPanier != -1) {
            LignePanier ligne = panier[indexPanier];
            int nouvelleQuantite = ligne.getQuantite() + quantite;
            
            if (nouvelleQuantite > article.getStock() + ligne.getQuantite()) {
                throw new StockInsuffisantException(id, article.getStock());
            }
            
            ligne.setQuantite(nouvelleQuantite);
        } else {
            if (taillePanier >= capacitePanier) {
                agrandirPanier();
            }
            
            panier[taillePanier] = new LignePanier(article, quantite);
            taillePanier++;
        }
        
        article.setStock(article.getStock() - quantite);
    }
    
    public boolean supprimerDuPanier(String id) throws KendiFoodException {
        int index = trouverIndexDansPanier(id);
        if (index == -1) {
            return false;
        }
        
        LignePanier ligne = panier[index];
        Article article = ligne.getArticle();
        article.setStock(article.getStock() + ligne.getQuantite());
        
        for (int i = index; i < taillePanier - 1; i++) {
            panier[i] = panier[i + 1];
        }
        
        panier[taillePanier - 1] = null;
        taillePanier--;
        
        return true;
    }
    
    private int trouverIndexDansPanier(String id) {
        for (int i = 0; i < taillePanier; i++) {
            if (panier[i].getArticle().getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }
    
    private void agrandirPanier() {
        int nouvelleCapacite = capacitePanier + INCREMENT_CAPACITE;
        LignePanier[] nouveauPanier = new LignePanier[nouvelleCapacite];
        
        for (int i = 0; i < taillePanier; i++) {
            nouveauPanier[i] = panier[i];
        }
        
        panier = nouveauPanier;
        capacitePanier = nouvelleCapacite;
    }
    
    public void afficherPanier() {
        System.out.println("\n[PANIER]");
        if (taillePanier == 0) {
            System.out.println("Le panier est vide.");
            return;
        }
        
        int maxIdLength = 0;
        for (int i = 0; i < taillePanier; i++) {
            int length = panier[i].getArticle().getId().length();
            if (length > maxIdLength) {
                maxIdLength = length;
            }
        }
        
        for (int i = 0; i < taillePanier; i++) {
            LignePanier ligne = panier[i];
            String id = ligne.getArticle().getId();
            String format = "- %-" + maxIdLength + "s x %d => %d cts";
            System.out.println(String.format(format, id, ligne.getQuantite(), ligne.getSousTotal()));
        }
        
        System.out.println("Total brut : " + totalBrut() + " cts");
    }
    
    public int totalBrut() {
        int total = 0;
        for (int i = 0; i < taillePanier; i++) {
            total += panier[i].getSousTotal();
        }
        return total;
    }
    
    // Phase 3: Codes de réduction et reçu
    public void ajouterCodeReduction(CodeReduction code) throws KendiFoodException {
        for (int i = 0; i < tailleCodes; i++) {
            if (codesReduction[i].getCode().equals(code.getCode())) {
                throw new KendiFoodException("Un code avec la valeur '" + code.getCode() + "' existe déjà");
            }
        }
        
        if (tailleCodes >= codesReduction.length) {
            CodeReduction[] nouveauxCodes = new CodeReduction[codesReduction.length + 5];
            for (int i = 0; i < tailleCodes; i++) {
                nouveauxCodes[i] = codesReduction[i];
            }
            codesReduction = nouveauxCodes;
        }
        
        codesReduction[tailleCodes] = code;
        tailleCodes++;
    }
    
    public int appliquerCode(String code, int totalBrut) throws KendiFoodException {
        CodeReduction codeReduction = trouverCode(code);
        if (codeReduction == null) {
            throw new KendiFoodException("Code de réduction inconnu: " + code);
        }
        
        int reduction = (totalBrut * codeReduction.getPourcentage()) / 100;
        return totalBrut - reduction;
    }
    
    private CodeReduction trouverCode(String code) {
        for (int i = 0; i < tailleCodes; i++) {
            if (codesReduction[i].getCode().equals(code)) {
                return codesReduction[i];
            }
        }
        return null;
    }
    
    public String genererRecu(String codeOptionnel) {
        StringBuilder recu = new StringBuilder();
        
        recu.append("===== REÇU KendiFood =====\n");
        
        if (taillePanier == 0) {
            recu.append("Panier vide\n");
            return recu.toString();
        }
        
        int maxIdLength = 0;
        for (int i = 0; i < taillePanier; i++) {
            int length = panier[i].getArticle().getId().length();
            if (length > maxIdLength) {
                maxIdLength = length;
            }
        }
        
        for (int i = 0; i < taillePanier; i++) {
            LignePanier ligne = panier[i];
            String format = "%s x%d -> %d cts";
            recu.append(String.format(format, 
                String.format("%-" + maxIdLength + "s", ligne.getArticle().getId()),
                ligne.getQuantite(), 
                ligne.getSousTotal())).append("\n");
        }
        
        int total = totalBrut();
        recu.append("Total brut : ").append(total).append(" cts\n");
        
        if (codeOptionnel != null && !codeOptionnel.trim().isEmpty()) {
            try {
                CodeReduction codeObj = trouverCode(codeOptionnel);
                if (codeObj != null) {
                    int totalNet = appliquerCode(codeOptionnel, total);
                    recu.append("Code appliqué : ").append(codeOptionnel)
                        .append(" (-").append(codeObj.getPourcentage()).append("%)\n")
                        .append("Total à payer : ").append(totalNet).append(" cts\n");
                }
            } catch (KendiFoodException e) {
                recu.append("Code non appliqué : ").append(e.getMessage()).append("\n")
                    .append("Total à payer : ").append(total).append(" cts\n");
            }
        } else {
            recu.append("Total à payer : ").append(total).append(" cts\n");
        }
        
        return recu.toString();
    }
    
    // Phase 5: Robustesse
    public int saisirEntier(String message) {
        while (true) {
            try {
                System.out.print(message);
                String input = scanner.nextLine();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Quantité invalide (non numérique). Réessayez.");
            }
        }
    }
    
    public void chargerDepuisFichier(String chemin) {
        boolean succes = false;
        while (!succes) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(chemin));
                String ligne;
                int articlesCharges = 0;
                
                while ((ligne = reader.readLine()) != null) {
                    try {
                        String[] parties = ligne.split("\\|");
                        if (parties.length >= 4) {
                            String id = parties[0].trim();
                            String libelle = parties[1].trim();
                            int prix = Integer.parseInt(parties[2].trim());
                            int stock = Integer.parseInt(parties[3].trim());
                            
                            Article article = new Article(id, libelle, prix, stock);
                            ajouterArticle(article);
                            articlesCharges++;
                        }
                    } catch (Exception e) {
                        System.out.println("Ligne ignorée (mal formée): " + ligne);
                    }
                }
                
                reader.close();
                System.out.println(articlesCharges + " articles chargés depuis " + chemin);
                succes = true;
                
            } catch (FileNotFoundException e) {
                System.out.println("Chemin introuvable : " + chemin + " --- saisissez un autre chemin.");
                System.out.print("Nouveau chemin : ");
                chemin = scanner.nextLine();
            } catch (IOException e) {
                System.out.println("Erreur de lecture : " + e.getMessage());
                break;
            }
        }
    }
    
    public void sauvegarderVersFichier(String chemin) {
        boolean succes = false;
        while (!succes) {
            try {
                PrintWriter writer = new PrintWriter(new FileWriter(chemin));
                
                for (int i = 0; i < tailleCatalogue; i++) {
                    Article article = catalogue[i];
                    writer.println(article.getId() + "|" + 
                                  article.getLibelle() + "|" + 
                                  article.getPrixCentimes() + "|" + 
                                  article.getStock());
                }
                
                writer.close();
                System.out.println("Catalogue sauvegardé dans " + chemin);
                succes = true;
                
            } catch (IOException e) {
                System.out.println("Erreur d'écriture : " + e.getMessage() + " --- saisissez un autre chemin.");
                System.out.print("Nouveau chemin : ");
                chemin = scanner.nextLine();
            }
        }
    }
    
    private void initialiserDonneesTest() {
        try {
            ajouterArticle(new Article("KIT_BOL1", "Bol végétarien", 5990, 12));
            ajouterArticle(new Article("TOMATE3", "Tomates x3", 990, 40));
            ajouterArticle(new Article("FROM250", "Fromage 250g", 2450, 8));
            
            ajouterCodeReduction(new CodeReduction("KENDI10", 10));
            ajouterCodeReduction(new CodeReduction("KENDI20", 20));
            ajouterCodeReduction(new CodeReduction("BIENVENUE", 15));
            
        } catch (KendiFoodException e) {
            System.out.println("Erreur lors de l'initialisation: " + e.getMessage());
        }
    }
    
    public void menuPrincipal() {
        while (true) {
            System.out.println("\n=== KENDIFOOD ===");
            System.out.println("1. Afficher le catalogue");
            System.out.println("2. Ajouter un article au panier");
            System.out.println("3. Supprimer un article du panier");
            System.out.println("4. Afficher le panier");
            System.out.println("5. Générer un reçu");
            System.out.println("6. Charger le catalogue depuis un fichier");
            System.out.println("7. Sauvegarder le catalogue vers un fichier");
            System.out.println("8. Quitter");
            System.out.print("Choix : ");
            
            String choix = scanner.nextLine();
            
            try {
                switch (choix) {
                    case "1":
                        afficherCatalogue();
                        break;
                    case "2":
                        System.out.print("ID de l'article : ");
                        String idAjout = scanner.nextLine();
                        int qte = saisirEntier("Quantité : ");
                        ajouterAuPanier(idAjout, qte);
                        System.out.println("Article ajouté au panier.");
                        break;
                    case "3":
                        System.out.print("ID de l'article à supprimer : ");
                        String idSuppression = scanner.nextLine();
                        if (supprimerDuPanier(idSuppression)) {
                            System.out.println("Article supprimé du panier.");
                        } else {
                            System.out.println("Article non trouvé dans le panier.");
                        }
                        break;
                    case "4":
                        afficherPanier();
                        break;
                    case "5":
                        System.out.print("Code de réduction (laisser vide si aucun) : ");
                        String code = scanner.nextLine();
                        if (code.trim().isEmpty()) {
                            code = null;
                        }
                        System.out.println(genererRecu(code));
                        break;
                    case "6":
                        System.out.print("Chemin du fichier : ");
                        String cheminChargement = scanner.nextLine();
                        chargerDepuisFichier(cheminChargement);
                        break;
                    case "7":
                        System.out.print("Chemin du fichier : ");
                        String cheminSauvegarde = scanner.nextLine();
                        sauvegarderVersFichier(cheminSauvegarde);
                        break;
                    case "8":
                        System.out.println("Au revoir !");
                        return;
                    default:
                        System.out.println("Choix invalide.");
                }
            } catch (KendiFoodException e) {
                System.out.println("Erreur : " + e.getMessage());
            }
        }
    }
    
    public static void main(String[] args) {
        KendiFood systeme = new KendiFood();
        systeme.menuPrincipal();
    }
}