package org.example.etudiant.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// @ pour indiquer une interface d'annotation
@Constraint(validatedBy = MyValidConstraintValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyValid {

    // @MysValid(value="salut", message = "Le message doit contenir @ !")
    public String value() default "@";
    public String message() default "L'email doit contenir @";
    public Class<?>[] groups() default {}; // regrouper contraintes de validation
    public Class<? extends Payload>[] payload() default {}; // Spécifier le niveau de gravité d'une erreur

}
