package Lab4;

/**
 * Represents a punctuation mark or whitespace character.
 * Encapsulates non-letter characters that separate words and sentences.
 * 
 * @author Korotaiev Mykhailo
 * @version 1.0
 */
public class Punctuation {
    private final char value;

    /**
     * Constructs a Punctuation with the specified character value.
     * 
     * @param value the character value
     */
    public Punctuation(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    /**
     * Checks if this punctuation is a whitespace character.
     * 
     * @return true if whitespace, false otherwise
     */
    public boolean isWhitespace() {
        return Character.isWhitespace(value);
    }

    /**
     * Checks if this punctuation is a sentence terminator.
     * 
     * @return true if '.', '!', or '?', false otherwise
     */
    public boolean isSentenceTerminator() {
        return value == '.' || value == '!' || value == '?';
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Punctuation that = (Punctuation) obj;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Character.hashCode(value);
    }
}