package org.framework.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @CollectionValidationAnnotation: Validates whether a collection field is allowed to be blank or empty.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface CollectionValidation {
    /**
     * Whether the collection may be empty.
     */
    boolean isBlank() default true;
}