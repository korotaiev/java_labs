package Lab4;

import java.util.Arrays;

/**
 * Represents a word composed of an array of Letter objects.
 * Implements composition: a Word HAS-A array of Letters.
 * 
 * @author Korotaiev Mykhailo
 * @version 1.0
 */
public class Word {
    private final Letter[] letters;

    /**
     * Constructs a Word from a string.
     * 
     * @param word the string representation
     * @throws IllegalArgumentException if word is null, empty, or contains non-letters
     */
    public Word(String word) {
        if (word == null || word.isEmpty()) {
            throw new IllegalArgumentException("Word cannot be null or empty");
        }
        
        this.letters = new Letter[word.length()];
        for (int i = 0; i < word.length(); i++) {
            this.letters[i] = new Letter(word.charAt(i));
        }
    }

    /**
     * Constructs a Word from an array of Letters.
     * 
     * @param letters the array of Letter objects
     * @throws IllegalArgumentException if array is null or empty
     */
    public Word(Letter[] letters) {
        if (letters == null || letters.length == 0) {
            throw new IllegalArgumentException("Letters array cannot be null or empty");
        }
        this.letters = Arrays.copyOf(letters, letters.length);
    }

    public int length() {
        return letters.length;
    }

    /**
     * Gets the letter at the specified index.
     * 
     * @param index the index
     * @return the Letter at position
     * @throws IndexOutOfBoundsException if index is out of range
     */
    public Letter getLetter(int index) {
        if (index < 0 || index >= letters.length) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + letters.length);
        }
        return letters[index];
    }

    public Letter getFirstLetter() {
        return letters[0];
    }

    public Letter[] getLetters() {
        return Arrays.copyOf(letters, letters.length);
    }

    /**
     * Checks if this word starts with a consonant.
     * 
     * @return true if first letter is consonant, false otherwise
     */
    public boolean startsWithConsonant() {
        return letters[0].isConsonant();
    }

    /**
     * Checks if this word starts with a vowel.
     * 
     * @return true if first letter is vowel, false otherwise
     */
    public boolean startsWithVowel() {
        return letters[0].isVowel();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Letter letter : letters) {
            sb.append(letter.getValue());
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Word word = (Word) obj;
        return Arrays.equals(letters, word.letters);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(letters);
    }
}