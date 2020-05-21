package com.mytodo.bean.validation;

import com.mytodo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = ValidUsernameValidator.class)
public @interface ValidUsername {
    String message() default "{username.duplication}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}

class ValidUsernameValidator implements ConstraintValidator<ValidUsername, String> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
        if (userRepository.existsByUsername(username)) {
            return false;
        }
        return true;
    }
}
