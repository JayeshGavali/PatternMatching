import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class StringMatchingFromFile {
    public static void naiveStringMatch(String text, String pattern) {
        int textLength = text.length();
        int patternLength = pattern.length();

        for (int i = 0; i <= textLength - patternLength; i++) {
            int j;
            for (j = 0; j < patternLength; j++) {
                if (text.charAt(i + j) != pattern.charAt(j)) {
                    break;
                }
            }
            if (j == patternLength) {
                System.out.println("Pattern found at index " + i);
            }
        }
    }

    public static void rabinKarpStringMatch(String text, String pattern) {
        int textLength = text.length();
        int patternLength = pattern.length();
        int prime = 101; // A prime number

        int patternHash = pattern.hashCode();
        int textHash = text.substring(0, patternLength).hashCode();

        for (int i = 0; i <= textLength - patternLength; i++) {
            if (textHash == patternHash) {
                int j;
                for (j = 0; j < patternLength; j++) {
                    if (text.charAt(i + j) != pattern.charAt(j)) {
                        break;
                    }
                }
                if (j == patternLength) {
                    System.out.println("Pattern found at index " + i);
                }
            }
            if (i < textLength - patternLength) {
                textHash = (textHash - text.charAt(i) * (int) Math.pow(31, patternLength - 1)) * 31 + text.charAt(i + patternLength);
            }
        }
    }

    public static void main(String[] args) {
        try {
            BufferedReader textReader = new BufferedReader(new FileReader("text.txt"));
            BufferedReader patternReader = new BufferedReader(new FileReader("pattern.txt"));

            String text = textReader.readLine();
            String pattern = patternReader.readLine();

            System.out.println("Text: " + text);
            System.out.println("Pattern: " + pattern);

            System.out.println("Naive String Matching Results:");
            naiveStringMatch(text, pattern);

            System.out.println("Rabin-Karp String Matching Results:");
            rabinKarpStringMatch(text, pattern);

            textReader.close();
            patternReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
