package Lab4;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a sentence composed of words and punctuation marks.
 * This class implements the composition relationship: a Sentence HAS-A collection of Words and Punctuation.
 * 
 * @author Korotaiev Mykhailo
 * @version 1.0
 */
public class Sentence {
    private final List<Object> elements; // Can contain Word or Punctuation objects

    /**
     * Constructs an empty Sentence.
     */
    public Sentence() {
        this.elements = new ArrayList<>();
    }

    /**
     * Constructs a Sentence from a string.
     * Parses the string into words and punctuation marks.
     * 
     * @param sentence the string representation of the sentence
     * @throws IllegalArgumentException if the sentence is null
     */
    public Sentence(String sentence) {
        if (sentence == null) {
            throw new IllegalArgumentException("Sentence cannot be null");
        }

        this.elements = new ArrayList<>();
        parseSentence(sentence);
    }

    /**
     * Parses a string into words and punctuation marks.
     * 
     * @param sentence the string to parse
     */
    private void parseSentence(String sentence) {
        StringBuilder wordBuffer = new StringBuilder();

        for (int i = 0; i < sentence.length(); i++) {
            char ch = sentence.charAt(i);

            if (isLetter(ch)) {
                wordBuffer.append(ch);
            } else {
                if (wordBuffer.length() > 0) {
                    elements.add(new Word(wordBuffer.toString()));
                    wordBuffer.setLength(0);
                }
                elements.add(new Punctuation(ch));
            }
        }

        if (wordBuffer.length() > 0) {
            elements.add(new Word(wordBuffer.toString()));
        }
    }

    /**
     * Checks if a character is a letter.
     * 
     * @param ch the character to check
     * @return true if the character is a letter, false otherwise
     */
    private boolean isLetter(char ch) {
        return (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z');
    }

    /**
     * Adds a word to this sentence.
     * 
     * @param word the Word to add
     */
    public void addWord(Word word) {
        if (word != null) {
            elements.add(word);
        }
    }

    /**
     * Adds a punctuation mark to this sentence.
     * 
     * @param punctuation the Punctuation to add
     */
    public void addPunctuation(Punctuation punctuation) {
        if (punctuation != null) {
            elements.add(punctuation);
        }
    }

    /**
     * Gets all elements (words and punctuation) in this sentence.
     * 
     * @return a list of sentence elements
     */
    public List<Object> getElements() {
        return new ArrayList<>(elements);
    }

    /**
     * Gets all words in this sentence.
     * 
     * @return a list of Word objects
     */
    public List<Word> getWords() {
        List<Word> words = new ArrayList<>();
        for (Object element : elements) {
            if (element instanceof Word) {
                words.add((Word) element);
            }
        }
        return words;
    }

    /**
     * Counts the number of words in this sentence.
     * 
     * @return the number of words
     */
    public int getWordCount() {
        int count = 0;
        for (Object element : elements) {
            if (element instanceof Word) {
                count++;
            }
        }
        return count;
    }

    /**
     * Removes words of specified length that start with a consonant.
     * 
     * @param length the target word length
     * @return a new Sentence with the filtered words
     */
    public Sentence removeWordsOfLength(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("Length must be positive");
        }

        Sentence newSentence = new Sentence();
        for (Object element : elements) {
            if (element instanceof Word) {
                Word word = (Word) element;
                if (!(word.length() == length && word.startsWithConsonant())) {
                    newSentence.elements.add(word);
                }
            } else {
                newSentence.elements.add(element);
            }
        }
        return newSentence;
    }

    /**
     * Cleans excessive whitespace, replacing sequences of spaces and tabs with a single space.
     * 
     * @return a new Sentence with cleaned whitespace
     */
    public Sentence cleanWhitespace() {
        Sentence newSentence = new Sentence();
        boolean previousWasSpace = false;

        for (Object element : elements) {
            if (element instanceof Punctuation) {
                Punctuation punct = (Punctuation) element;
                if (punct.isWhitespace()) {
                    if (!previousWasSpace && !newSentence.elements.isEmpty()) {
                        newSentence.elements.add(new Punctuation(' '));
                        previousWasSpace = true;
                    }
                } else {
                    newSentence.elements.add(punct);
                    previousWasSpace = false;
                }
            } else {
                newSentence.elements.add(element);
                previousWasSpace = false;
            }
        }

        // Remove trailing space if present
        if (!newSentence.elements.isEmpty()) {
            Object lastElement = newSentence.elements.get(newSentence.elements.size() - 1);
            if (lastElement instanceof Punctuation && ((Punctuation) lastElement).isWhitespace()) {
                newSentence.elements.remove(newSentence.elements.size() - 1);
            }
        }

        return newSentence;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Object element : elements) {
            sb.append(element.toString());
        }
        return sb.toString();
    }
}