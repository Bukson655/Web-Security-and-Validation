package pl.sb.security.validator.username;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

class LowercaseUsernameValidator implements ConstraintValidator<LowercaseUsername, String> {

    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
        return username.equals(username.toLowerCase());
    }
}
