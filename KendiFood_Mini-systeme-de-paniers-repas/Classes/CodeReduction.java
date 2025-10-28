package Classes;

import exceptions.KendiFoodException;

public class CodePromoInconnuException extends KendiFoodException {
    public CodePromoInconnuException(String code) {
        super("Code promo inconnu : \"" + code + "\"");
    }
}