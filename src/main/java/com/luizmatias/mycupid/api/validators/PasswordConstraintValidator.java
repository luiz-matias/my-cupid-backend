package com.luizmatias.mycupid.api.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.passay.*;

import java.util.Arrays;
import java.util.List;

public class PasswordConstraintValidator implements ConstraintValidator<StrongPassword, String> {

    @Override
    public void initialize(StrongPassword constraintAnnotation) {
    }

    @Override
    public boolean isValid(String passwordField, ConstraintValidatorContext context) {
        PasswordValidator validator = new PasswordValidator(Arrays.asList(
                //Should have between 8 and 50 characters
                new LengthRule(8, 50),
                //Should have at least 1 uppercase digit
                new CharacterRule(EnglishCharacterData.UpperCase, 1),
                //Should have at least 1 lowercase digit
                new CharacterRule(EnglishCharacterData.LowerCase, 1),
                //Should have at least 1 digit
                new CharacterRule(EnglishCharacterData.Digit, 1),
                //Should have at least 1 special digit (symbol)
                new CharacterRule(EnglishCharacterData.Special, 1),
                //Should not have whitespaces
                new WhitespaceRule(),
                //Should not have alphabetical sequences of 5 digits (like abcde)
                new IllegalSequenceRule(EnglishSequenceData.Alphabetical, 5, false),
                //Should not have numerical sequences of 5 digits (like 12345)
                new IllegalSequenceRule(EnglishSequenceData.Numerical, 5, false),
                //Should not have keyboard sequences of 5 digits (like qwerty)
                new IllegalSequenceRule(EnglishSequenceData.USQwerty, 5, false)
        ));

        RuleResult result = validator.validate(new PasswordData(passwordField));

        if (result.isValid()) {
            return true;
        }

        List<String> messages = validator.getMessages(result);
        String messageTemplate = String.join(", ", messages);
        context.buildConstraintViolationWithTemplate(messageTemplate)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();
        return false;
    }

}