package com.dev.cinema.lib;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

public class PasswordValidator implements ConstraintValidator<PasswordValidatorAnnotation, Object> {
    private String password;
    private String repeatPassword;

    @Override
    public void initialize(PasswordValidatorAnnotation constraintAnnotation) {
        this.password = constraintAnnotation.field();
        this.repeatPassword = constraintAnnotation.fieldMatch();
    }

    @Override
    public boolean isValid(Object value,
                           ConstraintValidatorContext context) {
        Object passwordValue = new BeanWrapperImpl(value)
                .getPropertyValue(password);
        Object repeatPasswordValue = new BeanWrapperImpl(value)
                .getPropertyValue(repeatPassword);

        if (passwordValue != null) {
            return passwordValue.equals(repeatPasswordValue);
        }
        return repeatPasswordValue == null;
    }
}
