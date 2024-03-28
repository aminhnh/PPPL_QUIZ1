package org.example;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class StringUtilsTest {
    private StringUtils stringUtils;

    @BeforeEach
    void init() {
        stringUtils = new StringUtils();
    }
    @AfterEach
    void clear() {
        stringUtils = null;
    }

    @Test
    void testReverseString() {
        assertEquals("AINUD", stringUtils.reverseString("DUNIA"));
    }
    @Test
    void testReverseStringNull() {
        assertNull(stringUtils.reverseString(null));
    }
    @Test
    void testReverseStringEmptyString() {
        assertEquals("", stringUtils.reverseString(""));
    }

    @Test
    void testIsPalindromeWithPalindrome() {
        assertTrue(stringUtils.isPalindrome("racecar"));
    }
    @Test
    void testIsPalindromeWithoutPalindrome() {
        assertFalse(stringUtils.isPalindrome("azara"));
    }
    @Test
    void testIsPalindromeRandomCapitalization() {
        assertTrue(stringUtils.isPalindrome("rACecaR"));
    }
    @Test
    void testIsPalindromeNull() {
        assertFalse(stringUtils.isPalindrome(null));
    }
    @Test
    void testIsPalindromeEmptyString() {
        assertTrue(stringUtils.isPalindrome(""));
    }

    @Test
    void testCountVowels() {
        assertEquals(3, stringUtils.countVowels("heloo"));
    }
    @Test
    void testCountVowelsNull() {
        assertEquals(0, stringUtils.countVowels(null));
    }
    @Test
    void testCountVowelsEmptyString() {
        assertEquals(0, stringUtils.countVowels(""));
    }
    @Test
    void testCountVowelsWithPunctuation() {
        assertEquals(3, stringUtils.countVowels("heloo!!!"));
    }
}