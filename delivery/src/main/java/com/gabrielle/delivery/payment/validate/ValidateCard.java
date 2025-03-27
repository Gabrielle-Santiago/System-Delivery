package com.gabrielle.delivery.payment.validate;

public class ValidateCard {

    public static boolean validatenumberCard(String numberCard) {
        return numberCard.matches("\\d{13,19}") && validateLuhn(numberCard);
    }

    private static boolean validateLuhn(String numberCard) {
        int sum = 0;
        boolean alternate = false;

        for (int i = numberCard.length() - 1; i >= 0; i--) {
            int digit = Character.getNumericValue(numberCard.charAt(i));
            if (alternate) {
                digit *= 2;
                if (digit > 9) {
                    digit -= 9;
                }
            }
            
            sum += digit;
            alternate = !alternate;
        }
        return (sum % 10 == 0);
    }
}
