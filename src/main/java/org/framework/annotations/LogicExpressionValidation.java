package org.framework.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @LogicExpressionValidationAnnotation: Validates the field based on a logical expression evaluated at runtime.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface LogicExpressionValidation {
    /**
     * Expression to evaluate.
     */
    String expression() default "1>2";
}