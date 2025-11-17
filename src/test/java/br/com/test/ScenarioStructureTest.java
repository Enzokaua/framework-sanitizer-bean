package br.com.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static br.com.test.TestConstants.*;
import static org.junit.jupiter.api.Assertions.*;

class ScenarioStructureTest {
    private ScenarioCaseTest model;
    private List<String> invalidFields;

    @BeforeEach
    void setup() {
        model = new ScenarioCaseTest();
        invalidFields = new ArrayList<>();
    }

    @Test
    void detectedInvalidFields() {
        for (Field field : model.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(org.framework.annotations.RegexValidation.class)) {
                SanitizerValidationRulesTest.validateRegex(field, model, invalidFields);
                assertTrue(invalidFields.stream().anyMatch(msg -> msg.contains(REGEX_FIELD)));
            }
            if (field.isAnnotationPresent(org.framework.annotations.SizingValidation.class)) {
                SanitizerValidationRulesTest.validateSizing(field, model, invalidFields);
                assertTrue(invalidFields.stream().anyMatch(msg -> msg.contains(SIZING_FIELD)));
            }
            if (field.isAnnotationPresent(org.framework.annotations.BlankValidation.class)) {
                SanitizerValidationRulesTest.validateBlank(field, model, invalidFields);
                assertTrue(invalidFields.stream().anyMatch(msg -> msg.contains(BLANK_FIELD)));
            }
            if (field.isAnnotationPresent(org.framework.annotations.MatchValidation.class)) {
                SanitizerValidationRulesTest.validateMatch(field, model, invalidFields);
                assertTrue(invalidFields.stream().anyMatch(msg -> msg.contains(MATCH_FIELD)));
            }
            if (field.isAnnotationPresent(org.framework.annotations.RemoveCharsValidation.class)) {
                SanitizerValidationRulesTest.validadeRemoved(field, model, invalidFields);
                assertTrue(invalidFields.stream().anyMatch(msg -> msg.contains(REMOVE_CHARS_FIELD)));
            }
            if (field.isAnnotationPresent(org.framework.annotations.LogicExpressionValidation.class)) {
                SanitizerValidationRulesTest.validateLogicExpress(field, model);
                assertTrue(invalidFields.stream().anyMatch(msg -> msg.contains(LOGIC_FIELD)));
            }
            if (field.isAnnotationPresent(org.framework.annotations.DateRangeValidation.class)) {
                SanitizerValidationRulesTest.validateDateRange(field, model, invalidFields);
                assertTrue(invalidFields.stream().anyMatch(msg -> msg.contains(DATE_RANGE_FIELD)));
            }
            if (field.isAnnotationPresent(org.framework.annotations.CollectionValidation.class)) {
                SanitizerValidationRulesTest.validateCollection(field, model, invalidFields);
                assertTrue(invalidFields.stream().anyMatch(msg -> msg.contains(COLLECTION_FIELD)));
            }
        }
    }
}
