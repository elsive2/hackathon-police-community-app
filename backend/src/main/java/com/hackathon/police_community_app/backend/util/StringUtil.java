package com.hackathon.police_community_app.backend.util;

public class StringUtil {
    public static String formatPhoneNumber(String phone) {
        String cleaned = phone.replaceAll("\\D", "");
        if (cleaned.startsWith("7")) {
            cleaned = "8" + cleaned.substring(1);
        }
        return cleaned;
    }

    /**
     * Форматирует номер телефона из формата 77085357522 в +7 (708) 535-75-22
     * @param rawPhoneNumber номер телефона без форматирования (11 цифр)
     * @return отформатированный номер телефона
     * @throws IllegalArgumentException если номер не содержит 11 цифр
     */
    public static String formatRawPhoneNumber(String rawPhoneNumber) {
        if (rawPhoneNumber == null || rawPhoneNumber.isEmpty()) {
            throw new IllegalArgumentException("Phone number cannot be null or empty");
        }

        String digitsOnly = rawPhoneNumber.replaceAll("[^0-9]", "");

        if (digitsOnly.length() != 11) {
            throw new IllegalArgumentException("Phone number must contain 11 digits");
        }

        return String.format("+7 (%s) %s-%s-%s",
                digitsOnly.substring(1, 4),
                digitsOnly.substring(4, 7),
                digitsOnly.substring(7, 9),
                digitsOnly.substring(9));
    }

}
