package pl.sb.security.validator.password;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {

    private static final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–{}:;',?/*~$^.+=<>]).{8,100}$";
    private static final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);

    @Override
    public boolean isValid(final String password, ConstraintValidatorContext constraintValidatorContext) {
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
