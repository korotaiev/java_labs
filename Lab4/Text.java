package Lab4;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a text composed of multiple sentences.
 * This class implements the composition relationship: a Text HAS-A collection of Sentences.
 * 
 * @author Korotaiev Mykhailo
 * @version 1.0
 */
public class Text {
    private final List<Sentence> sentences;

    /**
     * Constructs an empty Text.
     */
    public Text() {
        this.sentences = new ArrayList<>();
    }

    /**
     * Constructs a Text from a string.
     * Parses the string into sentences based on sentence terminators (. ! ?).
     * 
     * @param text the string representation of the text
     * @throws IllegalArgumentException if the text is null
     */
    public Text(String text) {
        if (text == null) {
            throw new IllegalArgumentException("Text cannot be null");
        }

        this.sentences = new ArrayList<>();
        parseText(text);
    }

    /**
     * Parses a string into sentences.
     * Sentences are separated by '.', '!', or '?'.
     * 
     * @param text the string to parse
     */
    private void parseText(String text) {
        StringBuilder sentenceBuffer = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            sentenceBuffer.append(ch);

            if (ch == '.' || ch == '!' || ch == '?') {
                // Check if there's more content after this punctuation
                boolean hasMoreContent = false;
                for (int j = i + 1; j < text.length(); j++) {
                    if (!Character.isWhitespace(text.charAt(j))) {
                        hasMoreContent = true;
                        break;
                    }
                }

                if (hasMoreContent || i == text.length() - 1) {
                    sentences.add(new Sentence(sentenceBuffer.toString()));
                    sentenceBuffer.setLength(0);
                }
            }
        }

        // Add any remaining text as a sentence
        if (sentenceBuffer.length() > 0) {
            String remaining = sentenceBuffer.toString().trim();
            if (!remaining.isEmpty()) {
                sentences.add(new Sentence(remaining));
            }
        }
    }

    /**
     * Adds a sentence to this text.
     * 
     * @param sentence the Sentence to add
     */
    public void addSentence(Sentence sentence) {
        if (sentence != null) {
            sentences.add(sentence);
        }
    }

    /**
     * Gets all sentences in this text.
     * 
     * @return a list of Sentence objects
     */
    public List<Sentence> getSentences() {
        return new ArrayList<>(sentences);
    }

    /**
     * Gets the number of sentences in this text.
     * 
     * @return the number of sentences
     */
    public int getSentenceCount() {
        return sentences.size();
    }

    /**
     * Counts the total number of words in this text.
     * 
     * @return the total word count
     */
    public int getWordCount() {
        int count = 0;
        for (Sentence sentence : sentences) {
            count += sentence.getWordCount();
        }
        return count;
    }

    /**
     * Removes words of specified length that start with a consonant from all sentences.
     * 
     * @param length the target word length
     * @return a new Text with the filtered words
     * @throws IllegalArgumentException if the length is not positive
     */
    public Text removeWordsOfLength(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("Length must be positive");
        }

        Text newText = new Text();
        for (Sentence sentence : sentences) {
            Sentence filteredSentence = sentence.removeWordsOfLength(length);
            newText.addSentence(filteredSentence);
        }
        return newText;
    }

    /**
     * Cleans excessive whitespace in all sentences.
     * Replaces sequences of tabs and spaces with a single space.
     * 
     * @return a new Text with cleaned whitespace
     */
    public Text cleanWhitespace() {
        Text newText = new Text();
        for (Sentence sentence : sentences) {
            Sentence cleanedSentence = sentence.cleanWhitespace();
            newText.addSentence(cleanedSentence);
        }
        return newText;
    }

    /**
     * Gets all words from all sentences in this text.
     * 
     * @return a list of all Word objects
     */
    public List<Word> getAllWords() {
        List<Word> allWords = new ArrayList<>();
        for (Sentence sentence : sentences) {
            allWords.addAll(sentence.getWords());
        }
        return allWords;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < sentences.size(); i++) {
            sb.append(sentences.get(i).toString());
            if (i < sentences.size() - 1) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }
}