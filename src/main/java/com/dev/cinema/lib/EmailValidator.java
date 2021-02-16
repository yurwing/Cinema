package com.dev.cinema.lib;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<EmailValidatorAnnotation, String> {
    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        return email.substring(email.indexOf("@")).equals("@gmail.com") && email.indexOf('@') != 0;
    }
}
