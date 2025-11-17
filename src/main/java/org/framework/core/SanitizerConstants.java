package org.framework.core;

import lombok.experimental.UtilityClass;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@UtilityClass
class SanitizerConstants {
    public static final String FIELD_MESSAGE_COMPLEMENTATION = " Field specified is: ";
    public static final String FIELD_MESSAGE_ERROR_INVALID = "Invalid error in field: ";
    public static final String ILLEGAL_ACCESS_FIELD_EXCEPTION = "Illegal acess in field: ";
    public static final String RUNTIME_ERROR_GENERATE_PDF = "ERROR WITH GENERATE PDF SANITIZER!";
    public static final String DATE_FORMAT_VALIDATION_EXCEPTION = "Date validate is wrong in field: ";
    public static final String DOCS_DIRECTORY_VALUE = "./docs";
    public static final String DOCS_SANITIZER_VALUE = "./docs/SanitizerReport.pdf";
    public static final String PATH_HEADER_SANITIZER_FRAME = "/headerContent.jpg";
    public static final String FOOTER_INFO_CONTENT = "SanitizerReport - Page";
    public static final String MODEL_EXCEPTION_REPORT = "Report";
    public static final String MODEL_EXCEPTION_THROWABLE = "Throws";
    public static final String TITLE_NAME_REPORT = "Related of validation";
    public static final DateTimeFormatter CUSTOM_FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    public static final String FORMATED_DATE = LocalDateTime.now().format(CUSTOM_FORMATTER);
    public static final String PARAGRAPH_TITLE_CONTENT_REPORT = "Incorrect results in compilation code (of timestamp: " + FORMATED_DATE + "):";
    public static final Integer SIZE_FONT_VALUE_HEADER = 16;
    public static final Integer SIZE_FONT_VALUE_CONTENT = 12;
    public static final Integer FOOTER_FONT_VALUE_CONTENT = 10;
    public static final Integer PADDING_BOTTOM_FOOTER = 20;
    public static final Integer ROTATE_FOOTER_DEFAULT = 0;
    public static final DateFormat DATE_FORMAT = new DateFormat() {
        @Override
        public StringBuffer format(Date date, StringBuffer toAppendTo, FieldPosition fieldPosition) {
            return null;
        }

        @Override
        public Date parse(String source, ParsePosition pos) {
            return null;
        }
    };
}
