package pl.sb.security.validator.password;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Target({FIELD})
@Retention(RUNTIME)
public @interface ValidPassword {
    String message() default "Password must contain at last one capital, one lowercase letter and one special symbol.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}