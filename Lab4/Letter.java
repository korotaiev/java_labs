package Lab4;

/**
 * Represents a single letter character.
 * Encapsulates a character and provides methods to determine if it's a vowel or consonant.
 * 
 * @author Korotaiev Mykhailo
 * @version 1.0
 */
public class Letter {
    private static final String VOWELS = "aeiouAEIOU";
    private final char value;

    /**
     * Constructs a Letter with the specified character value.
     * 
     * @param value the character value of the letter
     * @throws IllegalArgumentException if the character is not a letter
     */
    public Letter(char value) {
        if (!isLetter(value)) {
            throw new IllegalArgumentException("Character must be a letter: " + value);
        }
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    /**
     * Checks if this letter is a vowel.
     * 
     * @return true if vowel, false otherwise
     */
    public boolean isVowel() {
        return VOWELS.indexOf(value) != -1;
    }

    /**
     * Checks if this letter is a consonant.
     * 
     * @return true if consonant, false otherwise
     */
    public boolean isConsonant() {
        return !isVowel();
    }

    private static boolean isLetter(char ch) {
        return (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z');
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Letter letter = (Letter) obj;
        return value == letter.value;
    }

    @Override
    public int hashCode() {
        return Character.hashCode(value);
    }
}