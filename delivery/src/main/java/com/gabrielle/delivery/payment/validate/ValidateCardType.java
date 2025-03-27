package com.gabrielle.delivery.payment.validate;

import java.util.regex.Pattern;

public class ValidateCardType {
    public static boolean typeCard(String cardFlag, String cardNumber) {
        String REGEX_VISA = "^4[0-9]{15}$";
        String REGEX_MASTERCARD = "^(5[1-5][0-9]{14}|2[2-7][0-9]{14})$";
        String REGEX_AMEX = "^3[47][0-9]{13}$";
        String REGEX_HIPERCARD = "^(606282|3841[046]0)\\d+$";

        return switch (cardFlag.toUpperCase()) {
            case "VISA" -> Pattern.matches(REGEX_VISA, cardNumber);
            case "MASTERCARD" -> Pattern.matches(REGEX_MASTERCARD, cardNumber);
            case "AMEX" -> Pattern.matches(REGEX_AMEX, cardNumber);
            case "HIPERCARD" -> Pattern.matches(REGEX_HIPERCARD, cardNumber);
            default -> false;
        };  
    }
}
