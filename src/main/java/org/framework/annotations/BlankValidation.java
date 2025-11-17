package org.framework.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @BlankValidationAnnotation: Validates whether a field is allowed to be blank.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface BlankValidation {
    /**
     * Whether the field may be blank.
     */
    boolean isBlank() default true;
}