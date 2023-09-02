package io.github.josiasmartins.validation;

import io.github.josiasmartins.validation.constraintvalidation.NotEmptyListValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) // verifica em tempo de execução
@Target(ElementType.FIELD) // diz que essa anotation pode colocar em uma propriedade
@Constraint(validatedBy = NotEmptyListValidator.class) // diz que é uma anotation de validação
public @interface NotEmptyList {

    String message() default "A lista não pode ser vazia.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
