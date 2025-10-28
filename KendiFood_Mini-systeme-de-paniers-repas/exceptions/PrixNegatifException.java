package exceptions;

public class PrixNegatifException extends KendiFoodException {
    public PrixNegatifException() {
        super("Le prix ne peut pas être négatif");
    }
}