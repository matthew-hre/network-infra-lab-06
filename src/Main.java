import java.io.IOException;

/**
 * main entry point
 */
public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Word Prefix Server\n");
        
        String wordsFilePath = "data/words.txt";
        WordIndex wordIndex;
        
        try {
            wordIndex = new WordIndex(wordsFilePath);
            System.out.println("Word index initialized successfully.\n");
        } catch (IOException e) {
            System.err.println("Failed to load words file: " + wordsFilePath);
            System.err.println("Error: " + e.getMessage());
            System.exit(1);
            return;
        }
    }
}
