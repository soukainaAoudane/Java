public class ClientFidel extends Client {
    private String CodeFidelite;
    private float TauxReduction;

    public ClientFidel(String codeClient, String nomClient, String adrClient, String telClient, String codeFidelite, float tauxReduction) {
        super(codeClient, nomClient, adrClient, telClient);
        this.CodeFidelite = codeFidelite;
        this.TauxReduction = tauxReduction;
    }

    @Override
    public String toString() {
        super.toString();
        return "ClientFidel [CodeFidelite=" + CodeFidelite + ", TauxReduction=" + TauxReduction + ", " + super.toString() + "]";
    }
}