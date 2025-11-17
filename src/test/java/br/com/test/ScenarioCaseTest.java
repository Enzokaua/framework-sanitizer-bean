package br.com.test;

import org.framework.annotations.RegexValidation;
import org.framework.exception.SanitizerValidationException;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.framework.annotations.*;

import static br.com.test.TestConstants.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ScenarioCaseTest {

    @Test
    void regexValidateFailedSucess() throws Exception {
        Field field = mock(Field.class);
        RegexValidation regexValidation = mock(RegexValidation.class);
        when(field.getAnnotation(RegexValidation.class)).thenReturn(regexValidation);
        when(regexValidation.regexExpression()).thenReturn(REGEX_PATTERN);
        when(field.getName()).thenReturn(REGEX_FIELD);
        when(field.get(any())).thenReturn(INVALID_REGEX);
        List<String> errors = new ArrayList<>();
        SanitizerValidationRulesTest.validateRegex(field, new Object(), errors);
        assertFalse(errors.isEmpty());
        assertTrue(errors.getFirst().contains(REGEX_FIELD));
    }

    @Test
    void regexValidateSucess() throws Exception {
        Field field = mock(Field.class);
        RegexValidation regexValidation = mock(RegexValidation.class);
        when(field.getAnnotation(RegexValidation.class)).thenReturn(regexValidation);
        when(regexValidation.regexExpression()).thenReturn(REGEX_PATTERN);
        when(field.getName()).thenReturn(REGEX_FIELD);
        when(field.get(any())).thenReturn(VALID_REGEX);
        List<String> errors = new ArrayList<>();
        SanitizerValidationRulesTest.validateRegex(field, new Object(), errors);
        assertTrue(errors.isEmpty());
    }

    @Test
    void blankValidationFailedSucess() throws Exception {
        Field field = mock(Field.class);
        BlankValidation blankValidation = mock(BlankValidation.class);
        when(field.getAnnotation(BlankValidation.class)).thenReturn(blankValidation);
        when(field.getName()).thenReturn(BLANK_FIELD);
        when(field.get(any())).thenReturn(INVALID_BLANK);
        List<String> errors = new ArrayList<>();
        SanitizerValidationRulesTest.validateBlank(field, new Object(), errors);
        assertFalse(errors.isEmpty());
        assertTrue(errors.getFirst().contains(BLANK_FIELD));
    }

    @Test
    void blankValidationSucess() throws Exception {
        Field field = mock(Field.class);
        BlankValidation blank = mock(BlankValidation.class);
        when(field.getAnnotation(BlankValidation.class)).thenReturn(blank);
        when(field.get(any())).thenReturn(VALID_BLANK);
        when(field.getName()).thenReturn(BLANK_FIELD);
        List<String> errors = new ArrayList<>();
        SanitizerValidationRulesTest.validateBlank(field, new Object(), errors);
        assertTrue(errors.isEmpty());
    }

    @Test
    void sizingValidationFailedSucess() throws Exception {
        Field field = mock(Field.class);
        SizingValidation sizingValidation = mock(SizingValidation.class);
        when(field.getAnnotation(SizingValidation.class)).thenReturn(sizingValidation);
        when(sizingValidation.minSizing()).thenReturn(5);
        when(sizingValidation.maxSizing()).thenReturn(10);
        when(field.getName()).thenReturn(SIZING_FIELD);
        when(field.get(any())).thenReturn(INVALID_SIZING);
        List<String> errors = new ArrayList<>();
        SanitizerValidationRulesTest.validateSizing(field, new Object(), errors);
        assertFalse(errors.isEmpty());
        assertTrue(errors.getFirst().contains(SIZING_FIELD));
    }

    @Test
    void sizingValidationSucess() throws Exception {
        Field field = mock(Field.class);
        SizingValidation sizing = mock(SizingValidation.class);
        when(field.getAnnotation(SizingValidation.class)).thenReturn(sizing);
        when(sizing.minSizing()).thenReturn(5);
        when(sizing.maxSizing()).thenReturn(10);
        when(field.getName()).thenReturn(SIZING_FIELD);
        when(field.get(any())).thenReturn(VALID_SIZING);
        List<String> errors = new ArrayList<>();
        SanitizerValidationRulesTest.validateSizing(field, new Object(), errors);
        assertTrue(errors.isEmpty());
    }

    @Test
    void matchValidationFailedSucess() throws Exception {
        Field field = mock(Field.class);
        MatchValidation match = mock(MatchValidation.class);
        when(field.getAnnotation(MatchValidation.class)).thenReturn(match);
        when(match.variable()).thenReturn(VALID_MATCH);
        when(field.get(any())).thenReturn(VALID_MATCH);
        when(field.getName()).thenReturn(MATCH_FIELD);
        List<String> errors = new ArrayList<>();
        SanitizerValidationRulesTest.validateMatch(field, new Object(), errors);
        assertFalse(errors.isEmpty());
    }

    @Test
    void matchValidationSucess() throws Exception {
        Field field = mock(Field.class);
        MatchValidation match = mock(MatchValidation.class);
        when(field.getAnnotation(MatchValidation.class)).thenReturn(match);
        when(match.variable()).thenReturn(VALID_MATCH);
        when(field.get(any())).thenReturn(INVALID_MATCH);
        when(field.getName()).thenReturn(MATCH_FIELD);
        List<String> errors = new ArrayList<>();
        SanitizerValidationRulesTest.validateMatch(field, new Object(), errors);
        assertTrue(errors.isEmpty());
    }

    @Test
    void removeCharsValidationFailedSucess() throws Exception {
        Field field = mock(Field.class);
        RemoveCharsValidation rem = mock(RemoveCharsValidation.class);
        when(field.getAnnotation(RemoveCharsValidation.class)).thenReturn(rem);
        when(rem.removes()).thenReturn(new String[]{"@", "#"});
        when(field.get(any())).thenReturn(INVALID_SANITIZE);
        when(field.getName()).thenReturn(REMOVE_CHARS_FIELD);
        List<String> errors = new ArrayList<>();
        SanitizerValidationRulesTest.validadeRemoved(field, new Object(), errors);
        assertTrue(errors.isEmpty());
    }

    @Test
    void removeCharsValidationSucess() throws Exception {
        Field field = mock(Field.class);
        RemoveCharsValidation rem = mock(RemoveCharsValidation.class);
        when(field.getAnnotation(RemoveCharsValidation.class)).thenReturn(rem);
        when(rem.removes()).thenReturn(new String[]{"@", "#"});
        when(field.get(any())).thenReturn(VALID_SANITIZE);
        when(field.getName()).thenReturn(REMOVE_CHARS_FIELD);
        List<String> errors = new ArrayList<>();
        SanitizerValidationRulesTest.validadeRemoved(field, new Object(), errors);
        assertTrue(errors.isEmpty());
    }

    @Test
    void logicValidationFailedSucess() throws Exception {
        Field field = mock(Field.class);
        LogicExpressionValidation logic = mock(LogicExpressionValidation.class);
        when(field.getAnnotation(LogicExpressionValidation.class)).thenReturn(logic);
        when(logic.expression()).thenReturn(EXPRESSION_VALUE);
        when(field.get(any())).thenReturn(INVALID_LOGIC);
        when(field.getName()).thenReturn(LOGIC_FIELD);
        boolean result = SanitizerValidationRulesTest.validateLogicExpress(field, new Object());
        assertFalse(result);
    }

    @Test
    void logicValidationSucess() throws Exception {
        Field field = mock(Field.class);
        LogicExpressionValidation logic = mock(LogicExpressionValidation.class);
        when(field.getAnnotation(LogicExpressionValidation.class)).thenReturn(logic);
        when(logic.expression()).thenReturn(EXPRESSION_VALUE);
        when(field.get(any())).thenReturn(VALID_LOGIC);
        when(field.getName()).thenReturn(LOGIC_FIELD);
        boolean result = SanitizerValidationRulesTest.validateLogicExpress(field, new Object());
        assertFalse(result);
    }

    @Test
    void dateRangeValidationFailedSucess() throws Exception {
        Field field = mock(Field.class);
        DateRangeValidation dateRange = mock(DateRangeValidation.class);
        when(field.getAnnotation(DateRangeValidation.class)).thenReturn(dateRange);
        when(dateRange.minDate()).thenReturn(DATE_MIN_RANGE);
        when(dateRange.maxDate()).thenReturn(DATE_MIN_RANGE);
        when(dateRange.pattern()).thenReturn(PATTERN_DATE_RANGE);
        when(field.get(any())).thenReturn(DATE_MAX_RANGE);
        when(field.getName()).thenReturn(DATE_RANGE_FIELD);
        List<String> errors = new ArrayList<>();
        SanitizerValidationRulesTest.validateDateRange(field, new Object(), errors);
        assertFalse(errors.isEmpty());
    }

    @Test
    void dateRangeValidationSucess() throws Exception {
        Field field = mock(Field.class);
        DateRangeValidation dateRange = mock(DateRangeValidation.class);
        when(field.getAnnotation(DateRangeValidation.class)).thenReturn(dateRange);
        when(dateRange.minDate()).thenReturn(DATE_MIN_RANGE);
        when(dateRange.maxDate()).thenReturn(DATE_MAX_RANGE);
        when(dateRange.pattern()).thenReturn(PATTERN_DATE_RANGE);
        when(field.get(any())).thenReturn(DATE_MIN_RANGE);
        when(field.getName()).thenReturn(DATE_RANGE_FIELD);
        List<String> errors = new ArrayList<>();
        SanitizerValidationRulesTest.validateDateRange(field, new Object(), errors);
        assertTrue(errors.isEmpty());
    }

    @Test
    void collectionValidationFailedSucess() throws Exception {
        Field field = mock(Field.class);
        CollectionValidation coll = mock(CollectionValidation.class);
        when(field.getAnnotation(CollectionValidation.class)).thenReturn(coll);
        when(field.get(any())).thenReturn(Collections.emptyList());
        when(field.getName()).thenReturn(COLLECTION_FIELD);
        List<String> errors = new ArrayList<>();
        SanitizerValidationRulesTest.validateCollection(field, new Object(), errors);
        assertFalse(errors.isEmpty());
    }

    @Test
    void collectionValidationSucess() throws Exception {
        Field field = mock(Field.class);
        CollectionValidation coll = mock(CollectionValidation.class);
        when(field.getAnnotation(CollectionValidation.class)).thenReturn(coll);
        when(field.get(any())).thenReturn(VALID_COLLECTION);
        when(field.getName()).thenReturn(COLLECTION_FIELD);
        List<String> errors = new ArrayList<>();
        SanitizerValidationRulesTest.validateCollection(field, new Object(), errors);
        assertTrue(errors.isEmpty());
    }

    @Test
    void validateRegexThrowsFailed() throws Exception {
        Field field = mock(Field.class);
        RegexValidation regex = mock(RegexValidation.class);
        when(field.getAnnotation(RegexValidation.class)).thenReturn(regex);
        when(regex.regexExpression()).thenReturn(REGEX_PATTERN);
        when(field.getName()).thenReturn(REGEX_FIELD);
        when(field.get(any())).thenReturn(INVALID_REGEX);
        assertThrows(SanitizerValidationException.class, () -> SanitizerValidationRulesTest.validateRegexThrows(field, new Object()));
    }

    @Test
    void validateRegexThrowsSuccess() throws Exception {
        Field field = mock(Field.class);
        RegexValidation regex = mock(RegexValidation.class);
        when(field.getAnnotation(RegexValidation.class)).thenReturn(regex);
        when(regex.regexExpression()).thenReturn(REGEX_PATTERN);
        when(field.getName()).thenReturn(REGEX_FIELD);
        when(field.get(any())).thenReturn(VALID_REGEX);
        assertDoesNotThrow(() -> SanitizerValidationRulesTest.validateRegexThrows(field, new Object()));
    }

    @Test
    void validateSizingThrowsFailed() throws Exception {
        Field field = mock(Field.class);
        SizingValidation sizing = mock(SizingValidation.class);
        when(field.getAnnotation(SizingValidation.class)).thenReturn(sizing);
        when(sizing.minSizing()).thenReturn(4);
        when(sizing.maxSizing()).thenReturn(5);
        when(field.getName()).thenReturn(SIZING_FIELD);
        when(field.get(any())).thenReturn(INVALID_SIZING);
        assertThrows(SanitizerValidationException.class, () -> SanitizerValidationRulesTest.validateSizingThrows(field, new Object()));
    }

    @Test
    void validateSizingThrowsSuccess() throws Exception {
        Field field = mock(Field.class);
        SizingValidation sizing = mock(SizingValidation.class);
        when(field.getAnnotation(SizingValidation.class)).thenReturn(sizing);
        when(sizing.minSizing()).thenReturn(3);
        when(sizing.maxSizing()).thenReturn(7);
        when(field.getName()).thenReturn(SIZING_FIELD);
        when(field.get(any())).thenReturn(VALID_SIZING);
        assertDoesNotThrow(() -> SanitizerValidationRulesTest.validateSizingThrows(field, new Object()));
    }

    @Test
    void validateBlankThrowsFailed() throws Exception {
        Field field = mock(Field.class);
        when(field.getName()).thenReturn(BLANK_FIELD);
        when(field.get(any())).thenReturn(INVALID_BLANK);
        assertThrows(SanitizerValidationException.class, () -> SanitizerValidationRulesTest.validateBlankThrows(field, new Object()));
    }

    @Test
    void validateBlankThrowsSuccess() throws Exception {
        Field field = mock(Field.class);
        when(field.get(any())).thenReturn(VALID_BLANK);
        when(field.getName()).thenReturn(BLANK_FIELD);
        assertDoesNotThrow(() -> SanitizerValidationRulesTest.validateBlankThrows(field, new Object()));
    }

    @Test
    void validateMatchThrowsFailed() throws Exception {
        Field field = mock(Field.class);
        MatchValidation match = mock(MatchValidation.class);
        when(field.getAnnotation(MatchValidation.class)).thenReturn(match);
        when(match.variable()).thenReturn(VALID_MATCH);
        when(field.get(any())).thenReturn(VALID_MATCH);
        when(field.getName()).thenReturn(MATCH_FIELD);
        assertThrows(SanitizerValidationException.class, () -> SanitizerValidationRulesTest.validateMatchThrows(field, new Object()));
    }

    @Test
    void validateMatchThrowsSuccess() throws Exception {
        Field field = mock(Field.class);
        MatchValidation match = mock(MatchValidation.class);
        when(field.getAnnotation(MatchValidation.class)).thenReturn(match);
        when(match.variable()).thenReturn(VALID_MATCH);
        when(field.get(any())).thenReturn(INVALID_MATCH);
        when(field.getName()).thenReturn(MATCH_FIELD);
        assertDoesNotThrow(() -> SanitizerValidationRulesTest.validateMatchThrows(field, new Object()));
    }

    @Test
    void validadeRemovedThrowsFailed() throws Exception {
        Field field = mock(Field.class);
        RemoveCharsValidation rem = mock(RemoveCharsValidation.class);
        when(field.getAnnotation(RemoveCharsValidation.class)).thenReturn(rem);
        when(rem.removes()).thenReturn(new String[]{"@", "#"});
        when(field.get(any())).thenReturn(INVALID_SANITIZE);
        when(field.getName()).thenReturn(REMOVE_CHARS_FIELD);
        assertThrows(SanitizerValidationException.class, () -> SanitizerValidationRulesTest.validadeRemovedThrows(field, new Object()));
    }

    @Test
    void validadeRemovedThrowsSuccess() throws Exception {
        Field field = mock(Field.class);
        RemoveCharsValidation rem = mock(RemoveCharsValidation.class);
        when(field.getAnnotation(RemoveCharsValidation.class)).thenReturn(rem);
        when(rem.removes()).thenReturn(new String[]{"@", "#"});
        when(field.get(any())).thenReturn(VALID_SANITIZE);
        when(field.getName()).thenReturn(REMOVE_CHARS_FIELD);
        assertDoesNotThrow(() -> SanitizerValidationRulesTest.validadeRemovedThrows(field, new Object()));
    }

    @Test
    void validateLogicExpressionThrowsFailed() throws Exception {
        Field field = mock(Field.class);
        LogicExpressionValidation logic = mock(LogicExpressionValidation.class);
        when(field.getAnnotation(LogicExpressionValidation.class)).thenReturn(logic);
        when(logic.expression()).thenReturn(EXPRESSION_VALUE);
        when(field.get(any())).thenReturn(INVALID_LOGIC);
        when(field.getName()).thenReturn(LOGIC_FIELD);
        assertThrows(SanitizerValidationException.class, () -> SanitizerValidationRulesTest.validateLogicExpressThrows(field, new Object()));
    }

    @Test
    void validateCollectionThrowsFailed() throws Exception {
        Field field = mock(Field.class);
        when(field.get(any())).thenReturn(Collections.emptyList());
        when(field.getName()).thenReturn(COLLECTION_FIELD);
        assertThrows(SanitizerValidationException.class, () -> SanitizerValidationRulesTest.validateCollectionThrows(field, new Object()));
    }

    @Test
    void validateCollectionThrowsSuccess() throws Exception {
        Field field = mock(Field.class);
        when(field.get(any())).thenReturn(VALID_COLLECTION);
        when(field.getName()).thenReturn(COLLECTION_FIELD);
        assertDoesNotThrow(() -> SanitizerValidationRulesTest.validateCollectionThrows(field, new Object()));
    }

    @Test
    void validateDateRangeThrowsFailed() throws Exception {
        Field field = mock(Field.class);
        DateRangeValidation range = mock(DateRangeValidation.class);
        when(field.getAnnotation(DateRangeValidation.class)).thenReturn(range);
        when(range.minDate()).thenReturn(DATE_MIN_RANGE);
        when(range.maxDate()).thenReturn(DATE_MIN_RANGE);
        when(range.pattern()).thenReturn(PATTERN_DATE_RANGE);
        when(field.get(any())).thenReturn(DATE_MAX_RANGE);
        when(field.getName()).thenReturn(DATE_RANGE_FIELD);
        assertThrows(SanitizerValidationException.class, () -> SanitizerValidationRulesTest.validateDateRangeThrows(field, new Object()));
    }

    @Test
    void validateDateRangeThrowsSuccess() throws Exception {
        Field field = mock(Field.class);
        DateRangeValidation range = mock(DateRangeValidation.class);
        when(field.getAnnotation(DateRangeValidation.class)).thenReturn(range);
        when(range.minDate()).thenReturn(DATE_MIN_RANGE);
        when(range.maxDate()).thenReturn(DATE_MAX_RANGE);
        when(range.pattern()).thenReturn(PATTERN_DATE_RANGE);
        when(field.get(any())).thenReturn(DATE_MIN_RANGE);
        when(field.getName()).thenReturn(DATE_RANGE_FIELD);
        assertDoesNotThrow(() -> SanitizerValidationRulesTest.validateDateRangeThrows(field, new Object()));
    }
}
