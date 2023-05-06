package edu.guilford;
import java.io.*;
import java.util.*;

public class WordList implements Comparable<WordList> {

    private String word;
    private int count;

    public WordList(String word, int count) {
        this.word = word;
        this.count = count;
    }

    public static void main(String[] args) throws FileNotFoundException {
        WordList wordList = new WordList("", 0);
        wordList.createWordList("input.txt", "output.txt");
        wordList.analyzeWordList("output.txt", "analyzed.txt");
        wordList.promptUserForWord("analyzed.txt");
    }

    private void promptUserForWord(String string) {
    }

    public void createWordList(String inputFile, String outputFile) {
        try {
            // read the input text file
            File inputFileObj = new File(inputFile);
            Scanner scanner = new Scanner(inputFileObj);

            // create a TreeMap to store the words in alphabetical order
            TreeMap<String, Integer> wordMap = new TreeMap<>();

            // iterate over each word in the text file
            while (scanner.hasNext()) {
                String word = scanner.next().replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
                if (word.length() > 0) {
                    // add the word to the TreeMap
                    if (wordMap.containsKey(word)) {
                        wordMap.put(word, wordMap.get(word) + 1);
                    } else {
                        wordMap.put(word, 1);
                    }
                }
            }

            // close the scanner
            scanner.close();

            // write the word list to an output file
            File outputFileObj = new File(outputFile);
            PrintWriter writer = new PrintWriter(outputFileObj);
            for (String word : wordMap.keySet()) {
                writer.println(word);
            }
            writer.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void analyzeWordList(String inputFile, String outputFile) throws FileNotFoundException {
    }

    @Override
    public int compareTo(WordList o) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'compareTo'");
    }
}