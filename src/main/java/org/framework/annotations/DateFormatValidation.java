package org.framework.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * @DateFormatValidationAnnotation: Validates that the field's date format matches the expected pattern.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface DateFormatValidation {
    /**
     * Expected date format pattern.
     */
    String pattern() default "dd/MM/yyyy";
}