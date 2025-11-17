package org.framework.core;

import jakarta.annotation.PostConstruct;
import org.framework.annotations.*;
import org.framework.exception.SanitizerValidationException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.framework.core.SanitizerConstants.*;
import static org.framework.core.SanitizerValidationRules.*;

@Component
public class SanitizerFramework {
    /**
     * Executes validation on all fields of the given target object based on the
     * annotations present in its class. Validation rules, exception messages,
     * and the validation mode are dynamically loaded from the framework's
     * exception properties configuration file.
     *
     * <p>The properties file provides two key configurations:
     *
     * <ul>
     *   <li><b>Exception messages</b> — Each validation annotation has a
     *       corresponding message key (e.g., {@code exception.regex.field},
     *       {@code exception.sizing.field}, etc.). These messages are injected
     *       into the validation workflow to describe the specific rule violation.</li>
     *
     *   <li><b>Validation type</b> — Defined by the property
     *       {@code exception.validation.type}, which controls how validation
     *       results are handled. Supported values are:
     *       <ul>
     *           <li><b>Report</b> – All validation errors are collected and
     *               appended to a list. After processing all fields, a complete
     *               validation report is generated.</li>
     *           <li><b>Throw</b> – The first validation failure immediately
     *               triggers a {@link SanitizerValidationException} with the
     *               configured message.</li>
     *       </ul>
     *   </li>
     * </ul>
     *
     * <p>During execution, the method iterates over all declared fields of the
     * target class and applies the corresponding validation rule for any field
     * annotated with:
     * {@link RegexValidation}, {@link SizingValidation}, {@link BlankValidation},
     * {@link MatchValidation}, {@link DateFormatValidation},
     * {@link RemoveCharsValidation}, {@link LogicExpressionValidation},
     * {@link DateRangeValidation}, or {@link CollectionValidation}.
     *
     * @param target the object whose fields should be validated
     * @throws SanitizerValidationException if the validation type is configured
     *         as "throw" and a rule violation occurs
     * @throws IOException if loading the properties configuration fails
     */
    @PostConstruct
    public static void classValidate(Object target) throws SanitizerValidationException, IOException {
        List<String> frameworkInvalidated = new ArrayList<>();
        if (SanitizerUtils.exceptionPropertiesConfiguration().getProperty("exception.validation.type").equalsIgnoreCase(MODEL_EXCEPTION_REPORT)) {
            for (Field field : target.getClass().getDeclaredFields()) {
                if (field.isAnnotationPresent(RegexValidation.class))
                    validateRegex(field, target, frameworkInvalidated, SanitizerUtils.exceptionPropertiesConfiguration().getProperty("exception.regex.field"));
                if (field.isAnnotationPresent(SizingValidation.class))
                    validateSizing(field, target, frameworkInvalidated, SanitizerUtils.exceptionPropertiesConfiguration().getProperty("exception.sizing.field"));
                if (field.isAnnotationPresent(BlankValidation.class))
                    validateBlank(field, target, frameworkInvalidated, SanitizerUtils.exceptionPropertiesConfiguration().getProperty("exception.blank.field"));
                if (field.isAnnotationPresent(MatchValidation.class))
                    validateMatch(field, target, frameworkInvalidated, SanitizerUtils.exceptionPropertiesConfiguration().getProperty("exception.match.field"));
                if (field.isAnnotationPresent(DateFormatValidation.class))
                    validateDatePattern(field, target, frameworkInvalidated, SanitizerUtils.exceptionPropertiesConfiguration().getProperty("exception.dateFormat.field"));
                if (field.isAnnotationPresent(RemoveCharsValidation.class))
                    validadeRemoved(field, target, frameworkInvalidated);
                if (field.isAnnotationPresent(LogicExpressionValidation.class))
                    validateLogicExpress(field, target);
                if (field.isAnnotationPresent(DateRangeValidation.class))
                    validateDateRange(field, target, frameworkInvalidated, SanitizerUtils.exceptionPropertiesConfiguration().getProperty("exception.dateRange.field"));
                if (field.isAnnotationPresent(CollectionValidation.class))
                    validateCollection(field, target, frameworkInvalidated, SanitizerUtils.exceptionPropertiesConfiguration().getProperty("exception.collection.field"));
            }
            SanitizerUtils.generateReport(frameworkInvalidated);
        } else if (SanitizerUtils.exceptionPropertiesConfiguration().getProperty("exception.validation.type").equalsIgnoreCase(MODEL_EXCEPTION_THROWABLE)) {
            for (Field field : target.getClass().getDeclaredFields()) {
                if (field.isAnnotationPresent(RegexValidation.class))
                    validateRegexThrows(field, target, SanitizerUtils.exceptionPropertiesConfiguration().getProperty("exception.regex.field"));
                if (field.isAnnotationPresent(SizingValidation.class))
                    validateSizingThrows(field, target, SanitizerUtils.exceptionPropertiesConfiguration().getProperty("exception.sizing.field"));
                if (field.isAnnotationPresent(BlankValidation.class))
                    validateBlankThrows(field, target, SanitizerUtils.exceptionPropertiesConfiguration().getProperty("exception.blank.field"));
                if (field.isAnnotationPresent(MatchValidation.class))
                    validateMatchThrows(field, target, SanitizerUtils.exceptionPropertiesConfiguration().getProperty("exception.match.field"));
                if (field.isAnnotationPresent(DateFormatValidation.class))
                    validateDatePatternThrows(field, target, SanitizerUtils.exceptionPropertiesConfiguration().getProperty("exception.dateFormat.field"));
                if (field.isAnnotationPresent(RemoveCharsValidation.class))
                    validadeRemovedThrows(field, target, SanitizerUtils.exceptionPropertiesConfiguration().getProperty("exception.sanitize.field"));
                if (field.isAnnotationPresent(LogicExpressionValidation.class))
                    validateLogicExpressThrows(field, target, SanitizerUtils.exceptionPropertiesConfiguration().getProperty("exception.logic.field"));
                if (field.isAnnotationPresent(DateRangeValidation.class))
                    validateDateRangeThrows(field, target, SanitizerUtils.exceptionPropertiesConfiguration().getProperty("exception.dateRange.field"));
                if (field.isAnnotationPresent(CollectionValidation.class))
                    validateCollectionThrows(field, target, SanitizerUtils.exceptionPropertiesConfiguration().getProperty("exception.collection.field"));
            }
        }
    }
}