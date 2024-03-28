package org.example;

public class StringUtils {
    public String reverseString(String input) {
        if (input == null) {
            return null;
        }

        StringBuilder reversedString = new StringBuilder();
        for (int i = input.length() - 1; i >= 0; i--) {
            reversedString.append(input.charAt(i));
        }
        return reversedString.toString();
    }

    public boolean isPalindrome(String input) {
        if (input == null) {
            return false;
        }

        String cleanedInput = input.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        String reversedInput = reverseString(cleanedInput);
        return cleanedInput.equals(reversedInput);
    }

    public int countVowels(String input) {
        if (input == null) {
            return 0;
        }

        int count = 0;
        String vowels = "aeiouAEIOU";
        for (int i = 0; i < input.length(); i++) {
            if (vowels.contains(input.charAt(i) + "")) {
                count++;
            }
        }
        return count;
    }
}