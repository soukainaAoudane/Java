package Classes;

import exceptions.ChampVideException;
import exceptions.KendiFoodException;
import exceptions.PrixNegatifException;

class Article {
    private String id;
    private String libelle;
    private int prixCentimes;
    private int stock;
    
    public Article(String id, String libelle, int prixCentimes, int stock) throws KendiFoodException {
        setId(id);
        setLibelle(libelle);
        setPrixCentimes(prixCentimes);
        setStock(stock);
    }
    
    public String getId() { return id; }
    
    public void setId(String id) throws KendiFoodException {
        if (id == null || id.trim().isEmpty()) {
            throw new ChampVideException("id");
        }
        this.id = id.trim();
    }
    
    public String getLibelle() { return libelle; }
    
    public void setLibelle(String libelle) throws KendiFoodException {
        if (libelle == null || libelle.trim().isEmpty()) {
            throw new ChampVideException("libellé");
        }
        this.libelle = libelle.trim();
    }
    
    public int getPrixCentimes() { return prixCentimes; }
    
    public void setPrixCentimes(int prixCentimes) throws KendiFoodException {
        if (prixCentimes < 0) {
            throw new PrixNegatifException();
        }
        this.prixCentimes = prixCentimes;
    }
    
    public int getStock() { return stock; }
    
    public void setStock(int stock) throws KendiFoodException {
        if (stock < 0) {
            throw new KendiFoodException("Le stock ne peut pas être négatif");
        }
        this.stock = stock;
    }
    
    @Override
    public String toString() {
        return String.format("- %s | %s | %d cts | stock=%d", id, libelle, prixCentimes, stock);
    }
}