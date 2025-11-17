package br.com.test;

import org.framework.annotations.*;
import org.framework.exception.SanitizerValidationException;

import javax.script.ScriptEngineManager;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Pattern;

import static br.com.test.SanitizerUtilsTest.exceptionPersonalize;
import static br.com.test.TestConstants.ILLEGAL_ACCESS_FIELD_EXCEPTION;

class SanitizerValidationRulesTest {
    public static void validateRegex(Field field, Object target, List<String> sanitizerForkingReport, String... exceptionMessage) {
        RegexValidation regexValidation = field.getAnnotation(RegexValidation.class);
        String regexExpression = regexValidation.regexExpression();
        Pattern pattern = Pattern.compile(regexExpression);
        try {
            field.setAccessible(true);
            Object attributeValue = field.get(target);
            if (attributeValue instanceof String && !pattern.matcher((String) attributeValue).matches()) {
                sanitizerForkingReport.add(exceptionPersonalize(field, exceptionMessage));
            }
        } catch (IllegalAccessException e) {
            sanitizerForkingReport.add(ILLEGAL_ACCESS_FIELD_EXCEPTION + field.getName());
        }
    }

    public static void validateSizing(Field field, Object target, List<String> sanitizerForkingReport, String... exceptionMessage) {
        SizingValidation sizingValidation = field.getAnnotation(SizingValidation.class);
        try {
            field.setAccessible(true);
            Object attributeValue = field.get(target);
            if (attributeValue instanceof String && (((String) attributeValue).length() > sizingValidation.maxSizing() || ((String) attributeValue).length() < sizingValidation.minSizing())) {
                sanitizerForkingReport.add(exceptionPersonalize(field, exceptionMessage));
            }
        } catch (IllegalAccessException e) {
            sanitizerForkingReport.add(ILLEGAL_ACCESS_FIELD_EXCEPTION + field.getName());
        }
    }

    public static void validateBlank(Field field, Object target, List<String> sanitizerForkingReport, String... exceptionMessage) {
        try {
            field.setAccessible(true);
            Object attributeValue = field.get(target);
            if (attributeValue == null || (attributeValue instanceof String && ((String) attributeValue).trim().isEmpty())) {
                sanitizerForkingReport.add(exceptionPersonalize(field, exceptionMessage));
            }
        } catch (IllegalAccessException e) {
            sanitizerForkingReport.add(ILLEGAL_ACCESS_FIELD_EXCEPTION + field.getName());
        }
    }

    public static void validateMatch(Field field, Object target, List<String> sanitizerForkingReport, String... exceptionMessage) {
        MatchValidation matchValidation = field.getAnnotation(MatchValidation.class);
        try {
            field.setAccessible(true);
            Object attributeValue = field.get(target);
            if (((String) attributeValue).equalsIgnoreCase(matchValidation.variable())) {
                sanitizerForkingReport.add(exceptionPersonalize(field, exceptionMessage));
            }
        } catch (IllegalAccessException e) {
            sanitizerForkingReport.add(ILLEGAL_ACCESS_FIELD_EXCEPTION + field.getName());
        }
    }

    public static void validadeRemoved(Field field, Object target, List<String> sanitizerForkingReport) {
        RemoveCharsValidation removeCharsValidation = field.getAnnotation(RemoveCharsValidation.class);
        try {
            field.setAccessible(true);
            Object attributeValue = field.get(target);
            if (attributeValue instanceof String value) {
                for (String invalidChar : removeCharsValidation.removes()) {
                    if (value.contains(invalidChar)) {
                        field.set(target, attributeValue.toString().replace(invalidChar, ""));
                    }
                }
            }
        } catch (IllegalAccessException e) {
            sanitizerForkingReport.add(ILLEGAL_ACCESS_FIELD_EXCEPTION + field.getName());
        }
    }

    public static boolean validateLogicExpress(Field field, Object target) {
        LogicExpressionValidation logicExpressionValidation = field.getAnnotation(LogicExpressionValidation.class);
        try {
            field.setAccessible(true);
            Object attributeValue = field.get(target);
            return (Boolean) new ScriptEngineManager().getEngineByName("JavaScript").eval(logicExpressionValidation.expression().replace("value", attributeValue.toString()));
        } catch (Exception e) {
            return false;
        }
    }

    public static void validateCollection(Field field, Object target, List<String> sanitizerForkingReport, String... exceptionMessage) {
        try {
            field.setAccessible(true);
            Object attributeValue = field.get(target);
            if (attributeValue instanceof List<?> list && list.isEmpty()) {
                sanitizerForkingReport.add(exceptionPersonalize(field, exceptionMessage));
            }
        } catch (IllegalAccessException e) {
            sanitizerForkingReport.add(ILLEGAL_ACCESS_FIELD_EXCEPTION + field.getName());
        }
    }

    public static void validateDateRange(Field field, Object target, List<String> sanitizerForkingReport, String... exceptionMessage) {
        DateRangeValidation dateRangeValidation = field.getAnnotation(DateRangeValidation.class);
        try {
            field.setAccessible(true);
            Object attributeValue = field.get(target);
            LocalDate parseDates = LocalDate.parse(attributeValue.toString(), DateTimeFormatter.ofPattern(dateRangeValidation.pattern()));
            if (parseDates.isBefore(LocalDate.parse(dateRangeValidation.minDate())) || parseDates.isAfter(LocalDate.parse(dateRangeValidation.maxDate()))) {
                sanitizerForkingReport.add(exceptionPersonalize(field, exceptionMessage));
            }
        } catch (Exception e) {
            sanitizerForkingReport.add(ILLEGAL_ACCESS_FIELD_EXCEPTION + field.getName());
        }
    }

    public static void validateRegexThrows(Field field, Object target, String... exceptionMessage) {
        RegexValidation regexValidation = field.getAnnotation(RegexValidation.class);
        String regexExpression = regexValidation.regexExpression();
        Pattern pattern = Pattern.compile(regexExpression);
        try {
            field.setAccessible(true);
            Object attributeValue = field.get(target);
            if (attributeValue instanceof String && !pattern.matcher((String) attributeValue).matches()) {
                throw new SanitizerValidationException(exceptionPersonalize(field, exceptionMessage));
            }
        } catch (IllegalAccessException e) {
            throw new SanitizerValidationException(ILLEGAL_ACCESS_FIELD_EXCEPTION + field.getName());
        }
    }

    public static void validateSizingThrows(Field field, Object target, String... exceptionMessage) {
        SizingValidation sizingValidation = field.getAnnotation(SizingValidation.class);
        try {
            field.setAccessible(true);
            Object attributeValue = field.get(target);
            if (attributeValue instanceof String && (((String) attributeValue).length() > sizingValidation.maxSizing() || ((String) attributeValue).length() < sizingValidation.minSizing())) {
                throw new SanitizerValidationException(exceptionPersonalize(field, exceptionMessage));
            }
        } catch (IllegalAccessException e) {
            throw new SanitizerValidationException(ILLEGAL_ACCESS_FIELD_EXCEPTION + field.getName());
        }
    }

    public static void validateBlankThrows(Field field, Object target, String... exceptionMessage) {
        try {
            field.setAccessible(true);
            Object attributeValue = field.get(target);
            if (attributeValue == null || (attributeValue instanceof String && ((String) attributeValue).trim().isEmpty())) {
                throw new SanitizerValidationException(exceptionPersonalize(field, exceptionMessage));
            }
        } catch (IllegalAccessException e) {
            throw new SanitizerValidationException(ILLEGAL_ACCESS_FIELD_EXCEPTION + field.getName());
        }
    }

    public static void validateMatchThrows(Field field, Object target, String... exceptionMessage) {
        MatchValidation matchValidation = field.getAnnotation(MatchValidation.class);
        try {
            field.setAccessible(true);
            Object attributeValue = field.get(target);
            if (((String) attributeValue).equalsIgnoreCase(matchValidation.variable())) {
                throw new SanitizerValidationException(exceptionPersonalize(field, exceptionMessage));
            }
        } catch (IllegalAccessException e) {
            throw new SanitizerValidationException(ILLEGAL_ACCESS_FIELD_EXCEPTION + field.getName());
        }
    }

    public static void validadeRemovedThrows(Field field, Object target, String... exceptionMessage) {
        RemoveCharsValidation removeCharsValidation = field.getAnnotation(RemoveCharsValidation.class);
        try {
            field.setAccessible(true);
            Object attributeValue = field.get(target);
            if (attributeValue instanceof String value) {
                for (String invalidChar : removeCharsValidation.removes()) {
                    if (value.contains(invalidChar)) {
                        throw new SanitizerValidationException(exceptionPersonalize(field, exceptionMessage));
                    }
                }
            }
        } catch (IllegalAccessException e) {
            throw new SanitizerValidationException(ILLEGAL_ACCESS_FIELD_EXCEPTION + field.getName());
        }
    }

    public static void validateLogicExpressThrows(Field field, Object target, String... exceptionMessage) {
        LogicExpressionValidation logicExpressionValidation = field.getAnnotation(LogicExpressionValidation.class);
        try {
            field.setAccessible(true);
            Object attributeValue = field.get(target);
            if ((boolean) new ScriptEngineManager().getEngineByName("JavaScript").eval(logicExpressionValidation.expression().replace("value", attributeValue.toString())))
                throw new SanitizerValidationException(exceptionPersonalize(field, exceptionMessage));
        } catch (Exception e) {
            throw new SanitizerValidationException(ILLEGAL_ACCESS_FIELD_EXCEPTION + field.getName());
        }
    }

    public static void validateCollectionThrows(Field field, Object target, String... exceptionMessage) {
        try {
            field.setAccessible(true);
            Object attributeValue = field.get(target);
            if (attributeValue instanceof List<?> list && list.isEmpty()) {
                throw new SanitizerValidationException(exceptionPersonalize(field, exceptionMessage));
            }
        } catch (IllegalAccessException e) {
            throw new SanitizerValidationException(ILLEGAL_ACCESS_FIELD_EXCEPTION + field.getName());
        }
    }

    public static void validateDateRangeThrows(Field field, Object target, String... exceptionMessage) {
        DateRangeValidation dateRangeValidation = field.getAnnotation(DateRangeValidation.class);
        try {
            field.setAccessible(true);
            Object attributeValue = field.get(target);
            LocalDate parseDates = LocalDate.parse(attributeValue.toString(), DateTimeFormatter.ofPattern(dateRangeValidation.pattern()));
            if (parseDates.isBefore(LocalDate.parse(dateRangeValidation.minDate())) || parseDates.isAfter(LocalDate.parse(dateRangeValidation.maxDate()))) {
                throw new SanitizerValidationException(exceptionPersonalize(field, exceptionMessage));
            }
        } catch (Exception e) {
            throw new SanitizerValidationException(ILLEGAL_ACCESS_FIELD_EXCEPTION + field.getName());
        }
    }
}


