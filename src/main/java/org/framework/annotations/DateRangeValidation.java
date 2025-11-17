package org.framework.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * @DateRangeValidationAnnotation: Validates that a date field falls within a specified range.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface DateRangeValidation {
    /**
     * Minimum allowed date.
     */
    String minDate() default " ";
    /**
     * Maximum allowed date.
     */
    String maxDate() default " ";
    /**
     * Date format pattern.
     */
    String pattern() default "dd/MM/yyyy";
}