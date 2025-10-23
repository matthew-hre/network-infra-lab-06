import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * loads and indexes words from a file for prefix matching
 * 
 * assumes words are already in alphabetical order in the file
 * provides case-insensitive prefix lookups, preserving original casing
 */
public class WordIndex {
    private List<String> words;
    
    /**
     * makes a WordIndex and loads words from the specified file
     * 
     * @param filePath path to the words file (one word per line)
     * @throws IOException if the file cannot be read
     */
    public WordIndex(String filePath) throws IOException {
        this.words = new ArrayList<>();
        loadWords(filePath);
    }
    
    /**
     * loads words from the file into the word list
     * each line is treated as a word. leading/trailing whitespace is removed
     * 
     * @param filePath path to the words file
     * @throws IOException if the file cannot be read
     */
    private void loadWords(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    words.add(line);
                }
            }
            System.out.println("Loaded " + words.size() + " words from " + filePath);
        }
    }
    
    /**
     * finds all words that start with the given prefix (case-insensitive)
     * 
     * @param prefix the prefix to search for
     * @return a list of matching words in original casing, or empty if no matches
     */
    public List<String> findWordsWithPrefix(String prefix) {
        if (prefix == null || prefix.isEmpty()) {
            return new ArrayList<>();
        }
        
        List<String> matches = new ArrayList<>();
        String lowerPrefix = prefix.toLowerCase();
        
        for (String word : words) {
            if (word.toLowerCase().startsWith(lowerPrefix)) {
                matches.add(word);
            }
        }
        
        return matches;
    }
    
    /**
     * gets the total number of words in the index
     * 
     * @return the number of words
     */
    public int getWordCount() {
        return words.size();
    }
}
