package br.com.test;

import java.util.List;

class TestConstants {
    public static final String FIELD_MESSAGE_COMPLEMENTATION = " Field specified is: ";
    public static final String FIELD_MESSAGE_ERROR_INVALID = "Invalid error in field: ";
    public static final String ILLEGAL_ACCESS_FIELD_EXCEPTION = "Illegal acess in field: ";
    public static final String VALID_REGEX = "ABC123";
    public static final String REGEX_PATTERN = "^[A-Z]{3}\\d{3}$";
    public static final String INVALID_REGEX = "abc123";
    public static final String VALID_SIZING = "abcdef";
    public static final String INVALID_SIZING = "abc";
    public static final String VALID_BLANK = "NotEmpty";
    public static final String INVALID_BLANK = "";
    public static final String VALID_MATCH = "DENIED";
    public static final String INVALID_MATCH = "ALLOWED";
    public static final String VALID_SANITIZE = "TestValue";
    public static final String INVALID_SANITIZE = "Test@Value#";
    public static final Integer VALID_LOGIC = 15;
    public static final Integer INVALID_LOGIC = 5;
    public static final String DATE_MIN_RANGE = "2024-06-15";
    public static final String DATE_MAX_RANGE = "2025-01-05";
    public static final String PATTERN_DATE_RANGE = "yyyy-MM-dd";
    public static final String REGEX_FIELD = "regexField";
    public static final String SIZING_FIELD = "sizingField";
    public static final String BLANK_FIELD = "blankField";
    public static final String MATCH_FIELD = "matchField";
    public static final String REMOVE_CHARS_FIELD = "sanitizeField";
    public static final String LOGIC_FIELD = "logicField";
    public static final String DATE_FORMAT_FIELD = "dateFormatField";
    public static final String DATE_RANGE_FIELD = "dateRangeField";
    public static final String COLLECTION_FIELD = "collectionField";
    public static final String EXPRESSION_VALUE = "value > 10";
    public static final List<String> VALID_COLLECTION = List.of("Item1", "Item2");
}
