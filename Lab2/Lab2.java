package Lab2;

public class Lab2 {

    private static final String VOWELS = "aeiouAEIOU";

    private static boolean isVowel(char ch) {
        for (int i = 0; i < VOWELS.length(); i++) {
            if (VOWELS.charAt(i) == ch) return true;
        }
        return false;
    }

    private static boolean isLetter(char ch) {
        return (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z');
    }

    private static boolean isConsonant(char ch) {
        return isLetter(ch) && !isVowel(ch);
    }

    private static StringBuffer extractWord(StringBuffer text, int startIndex) {
        StringBuffer word = new StringBuffer();
        int i = startIndex;
        while (i < text.length() && isLetter(text.charAt(i))) {
            word.append(text.charAt(i));
            i++;
        }
        return word;
    }

    private static boolean shouldRemoveWord(StringBuffer word, int targetLength) {
        return word.length() == targetLength && isConsonant(word.charAt(0));
    }

    private static StringBuffer removeWordsOfLength(StringBuffer text, int wordLength) {
        if (text == null) throw new IllegalArgumentException("Text cannot be null");
        if (wordLength <= 0) throw new IllegalArgumentException("Word length must be positive");

        StringBuffer result = new StringBuffer();
        int i = 0;

        while (i < text.length()) {
            char currentChar = text.charAt(i);
            if (isLetter(currentChar)) {
                StringBuffer word = extractWord(text, i);
                if (!shouldRemoveWord(word, wordLength)) result.append(word);
                i += word.length();
            } else {
                result.append(currentChar);
                i++;
            }
        }
        return result;
    }

    private static StringBuffer cleanSpaces(StringBuffer text) {
        if (text == null || text.length() == 0) return text;

        StringBuffer result = new StringBuffer();
        boolean previousWasSpace = false;

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (Character.isWhitespace(ch)) {
                if (!previousWasSpace && result.length() > 0) {
                    result.append(' ');
                    previousWasSpace = true;
                }
            } else {
                result.append(ch);
                previousWasSpace = false;
            }
        }

        if (result.length() > 0 && result.charAt(result.length() - 1) == ' ')
            result.deleteCharAt(result.length() - 1);

        return result;
    }

    private static int countWords(StringBuffer text) {
        if (text == null || text.length() == 0) return 0;

        int count = 0;
        boolean inWord = false;

        for (int i = 0; i < text.length(); i++) {
            if (isLetter(text.charAt(i))) {
                if (!inWord) {
                    count++;
                    inWord = true;
                }
            } else {
                inWord = false;
            }
        }
        return count;
    }

    private static void printStatistics(StringBuffer original, StringBuffer processed) {
        int originalWords = countWords(original);
        int processedWords = countWords(processed);
        int removedWords = originalWords - processedWords;

        System.out.println("\nOriginal word count: " + originalWords);
        System.out.println("Removed words: " + removedWords);
        System.out.println("Remaining words: " + processedWords);
    }

    public static void main(String[] args) {
        try {
            System.out.println("C3 = 11 % 3 = 2 -> Type: StringBuffer");
            System.out.println("C17 = 11 % 17 = 11 -> Remove words of specified length starting with consonant\n");

            String inputText = "Developers write code every day. "
                    + "Some build apps while others test systems. "
                    + "Smart tools help them fix bugs fast. "
                    + "Every project needs teamwork and clear logic.";

            StringBuffer text = new StringBuffer(inputText);
            int targetLength = 3;

            System.out.println("Original text:");
            System.out.println(text.toString());
            System.out.println("\nExample with word length = 3:");

            StringBuffer result = removeWordsOfLength(text, targetLength);
            result = cleanSpaces(result);

            System.out.println(result.toString());

            printStatistics(text, result);

            System.out.println("\nExample with word length = 4:");

            targetLength = 4;
            StringBuffer text2 = new StringBuffer(inputText);
            StringBuffer result2 = removeWordsOfLength(text2, targetLength);
            result2 = cleanSpaces(result2);

            System.out.println(result2.toString());
            printStatistics(text2, result2);

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
