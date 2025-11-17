package org.framework.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @SizingValidationAnnotation: Validates that the field's length is within a specified range.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface SizingValidation {
    /**
     * Maximum allowed length.
     */
    int maxSizing() default 1;
    /**
     * Minimum required length.
     */
    int minSizing() default 0;
}