package exceptions;

public class ChampVideException extends KendiFoodException {
    public ChampVideException(String champ) {
        super("Le champ '" + champ + "' ne peut pas être vide");
    }
}