import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * loads and indexes words from a file for prefix matching
 * 
 * uses a TreeMap to keep words in sorted order, with case-insensitive
 * prefix lookups. casing is preserved in responses
 */
public class WordIndex {
    private TreeMap<String, String> wordMap;
    
    /**
     * makes a WordIndex and loads words from the specified file
     * 
     * @param filePath path to the words file (one word per line)
     * @throws IOException if the file cannot be read
     */
    public WordIndex(String filePath) throws IOException {
        this.wordMap = new TreeMap<>();
        loadWords(filePath);
    }
    
    /**
     * loads words from the file into the word map
     * each line is treated as a word. leading/trailing whitespace is removed
     * 
     * @param filePath path to the words file
     * @throws IOException if the file cannot be read
     */
    private void loadWords(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int count = 0;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    // store with lowercase key for case-insensitive matching
                    wordMap.put(line.toLowerCase(), line);
                    count++;
                }
            }
            System.out.println("Loaded " + count + " words from " + filePath);
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
        
        String lowerPrefix = prefix.toLowerCase();
        List<String> matches = new ArrayList<>();
        
        // use tailMap to find the starting point
        // all words >= lowerPrefix will be in the submap
        SortedMap<String, String> subMap = wordMap.tailMap(lowerPrefix);
        
        for (Map.Entry<String, String> entry : subMap.entrySet()) {
            String key = entry.getKey();
            // stop when we've gone past words with this prefix
            if (!key.startsWith(lowerPrefix)) {
                break;
            }
            matches.add(entry.getValue());
        }
        
        return matches;
    }
    
    /**
     * gets the total number of words in the index
     * 
     * @return the number of words
     */
    public int getWordCount() {
        return wordMap.size();
    }
}
