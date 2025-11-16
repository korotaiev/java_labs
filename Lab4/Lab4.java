package Lab4;

/**
 * This class demonstrates composition relationships between Letter, Word, Sentence, and Text classes.
 * 
 * Task: Remove words of specified length that start with a consonant from the text.
 * Also replaces sequences of tabs and spaces with a single space.
 * 
 * C3 = 11 % 3 = 2 -> Type: StringBuffer
 * C17 = 11 % 17 = 11 -> Remove words of specified length starting with consonant
 * 
 * @author Korotaiev Mykhailo
 * @version 1.0
 */
public class Lab4 {

    /**
     * Prints statistics about word counts before and after processing.
     * 
     * @param original the original Text object
     * @param processed the processed Text object after word removal
     */
    private static void printStatistics(Text original, Text processed) {
        int originalWords = original.getWordCount();
        int processedWords = processed.getWordCount();
        int removedWords = originalWords - processedWords;

        System.out.println("\n=== Statistics ===");
        System.out.println("Original word count: " + originalWords);
        System.out.println("Removed words: " + removedWords);
        System.out.println("Remaining words: " + processedWords);
        System.out.println("Sentences: " + original.getSentenceCount());
    }

    /**
     * Prints detailed information about removed words.
     * 
     * @param original the original Text object
     * @param targetLength the length of words to remove
     */
    private static void printRemovedWords(Text original, int targetLength) {
        System.out.println("\n=== Words removed (length=" + targetLength + ", starting with consonant) ===");
        
        boolean foundAny = false;
        for (Word word : original.getAllWords()) {
            if (word.length() == targetLength && word.startsWithConsonant()) {
                System.out.print("\"" + word + "\" ");
                foundAny = true;
            }
        }
        
        if (!foundAny) {
            System.out.print("(none)");
        }
        System.out.println();
    }

    /**
     * Demonstrates the text processing functionality.
     * 
     * @param inputText the input text to process
     * @param targetLength the length of words to remove
     */
    private static void demonstrateProcessing(String inputText, int targetLength) {
        System.out.println("\n" + "=".repeat(70));
        System.out.println("Processing with word length = " + targetLength);
        System.out.println("=".repeat(70));

        // Create Text object from input string
        Text originalText = new Text(inputText);

        System.out.println("\nOriginal text:");
        System.out.println(originalText);

        // Show which words will be removed
        printRemovedWords(originalText, targetLength);

        // Process the text: remove words and clean whitespace
        Text processedText = originalText.removeWordsOfLength(targetLength);
        processedText = processedText.cleanWhitespace();

        System.out.println("\nProcessed text:");
        System.out.println(processedText);

        // Print statistics
        printStatistics(originalText, processedText);
    }

    /**
     * Main entry point of the program.
     * Demonstrates the composition relationships and text processing functionality.
     * 
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        try {
            // Print task information
            System.out.println("C3 = 11 % 3 = 2 -> Type: StringBuffer");
            System.out.println("C17 = 11 % 17 = 11 -> Remove words of specified length starting with consonant");

            // Sample text for processing
            String inputText = "Developers write code every day. "
                    + "Some build apps while others test systems. "
                    + "Smart tools help them fix bugs fast. "
                    + "Every project needs teamwork and clear logic.";

            // Demonstrate with word length = 3
            demonstrateProcessing(inputText, 3);

            // Demonstrate with word length = 4
            demonstrateProcessing(inputText, 4);

            // Demonstrate with word length = 5
            demonstrateProcessing(inputText, 5);

            // Additional demonstration with text containing multiple spaces and tabs

            System.out.println("\nCleaning excessive whitespace:");


            String messyText = "This  is   a    test\t\twith    multiple\tspaces.";
            Text messyTextObj = new Text(messyText);
            
            System.out.println("\nOriginal text with excessive whitespace:");
            System.out.println("\"" + messyTextObj + "\"");
            
            Text cleanedText = messyTextObj.cleanWhitespace();
            System.out.println("\nCleaned text:");
            System.out.println("\"" + cleanedText + "\"");

        } catch (IllegalArgumentException e) {
            System.err.println("Input error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}