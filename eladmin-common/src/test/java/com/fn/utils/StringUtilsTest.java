package com.fn.utils;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import java.text.SimpleDateFormat;
import java.util.*;

import static com.fn.utils.StringUtils.*;
import static org.junit.Assert.*;

public class StringUtilsTest {

    @Test
    public void testInString() {
        assertTrue(inString("?", "?"));
        assertFalse(inString("?", new String[]{}));
    }

    @Test
    public void testToCamelCase() {
        assertNull(toCamelCase(null));
    }

    @Test
    public void testToCapitalizeCamelCase() {
        assertNull(StringUtils.toCapitalizeCamelCase(null));
        assertEquals("HelloWorld", toCapitalizeCamelCase("hello_world"));
    }

    @Test
    public void testToUnderScoreCase() {
        assertNull(StringUtils.toUnderScoreCase(null));
        assertEquals("hello_world", toUnderScoreCase("helloWorld"));
        assertEquals("\u0000\u0000", toUnderScoreCase("\u0000\u0000"));
        assertEquals("\u0000_a", toUnderScoreCase("\u0000A"));
    }

    @Test
    public void testGetWeekDay() {
        SimpleDateFormat simpleDateformat = new SimpleDateFormat("E");
        assertEquals(simpleDateformat.format(new Date()), getWeekDay());
    }

    @Test
    public void testGetIP() {
        assertEquals("127.0.0.1", getIP(new MockHttpServletRequest()));
    }
}