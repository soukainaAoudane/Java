package Classes;

import exceptions.ChampVideException;
import exceptions.KendiFoodException;

public class CodeReduction {
    private String code;
    private int pourcentage;
    
    public CodeReduction(String code, int pourcentage) throws KendiFoodException {
        setCode(code);
        setPourcentage(pourcentage);
    }
    
    public String getCode() { return code; }
    
    public void setCode(String code) throws KendiFoodException {
        if (code == null || code.trim().isEmpty()) {
            throw new ChampVideException("code");
        }
        this.code = code.trim();
    }
    
    public int getPourcentage() { return pourcentage; }
    
    public void setPourcentage(int pourcentage) throws KendiFoodException {
        if (pourcentage < 1 || pourcentage > 50) {
            throw new KendiFoodException("Le pourcentage doit être entre 1 et 50");
        }
        this.pourcentage = pourcentage;
    }
}