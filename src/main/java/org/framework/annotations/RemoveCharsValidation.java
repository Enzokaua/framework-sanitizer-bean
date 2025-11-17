package org.framework.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @RemoveCharsValidationAnnotation: Specifies a list of characters that should be removed from the field value
 * before validation or processing.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface RemoveCharsValidation {
    /**
     * Characters or strings to remove.
     */
    String[] removes() default {"/", ";"};
}