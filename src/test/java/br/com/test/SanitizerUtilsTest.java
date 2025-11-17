package br.com.test;

import org.springframework.stereotype.Component;
import java.lang.reflect.Field;
import java.util.Arrays;

import static br.com.test.TestConstants.*;

@Component
class SanitizerUtilsTest {
    public static String exceptionPersonalize(Field field, String... exceptionMessage) {
        return Arrays.stream(exceptionMessage).findFirst().orElse(FIELD_MESSAGE_ERROR_INVALID) + FIELD_MESSAGE_COMPLEMENTATION + field.getName();
    }
}
