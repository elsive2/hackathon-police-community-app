package com.hackathon.police_community_app.backend.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, String> {
    private static final String PHONE_PATTERN = "^\\+7 \\(\\d{3}\\) \\d{3}-\\d{2}-\\d{2}$";

    @Override
    public boolean isValid(String phone, ConstraintValidatorContext context) {
        if (phone == null) {
            return false;
        }
        return phone.matches(PHONE_PATTERN);
    }
}
