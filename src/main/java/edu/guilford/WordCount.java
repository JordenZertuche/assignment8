package edu.guilford;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class WordCount {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the file name: ");
        String filename = "src/main/resources/" + scanner.nextLine();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            HashMap<String, Integer> wordCount = new HashMap<>(); 
            // creates a new HashMap object called wordCount that stores key-value pairs of type String and Integer

            String line = reader.readLine();
            // reads each line of the input text file, converts it to lower case, removes all non-alphanumeric characters using the regular expression [^a-zA-Z0-9 ], and then splits the line into individual words
            while (line != null) {
                String[] words = line.toLowerCase().replaceAll("[^a-zA-Z0-9 ]", "").split("\\s+");
                for (String word : words) {
                    if (wordCount.containsKey(word)) {
                        wordCount.put(word, wordCount.get(word) + 1);
                    } else {
                        wordCount.put(word, 1);
                    }
                }
                line = reader.readLine();
            }
            reader.close();
            // This code takes the entry set of the wordCount hashmap and converts it to a
            // List of Map.Entry objects, where each entry represents a key-value pair. Then it sorts the list based on
            // the keys (words) in natural order using the comparingByKey() method of the Map.Entry class. This method returns
            // a Comparator that compares the keys of the Map.Entry objects. The sorted list is then used to write the words and their counts to the output file.
            List<Map.Entry<String, Integer>> sortedList = new ArrayList<>(wordCount.entrySet());
            sortedList.sort(Map.Entry.comparingByKey());

            FileWriter writer = new FileWriter("src/main/resources/wordlist.txt"); 
            // gets the occurrence count of each word and writes the word and its count to the output file.
            for (Map.Entry<String, Integer> entry : sortedList) {
                writer.write(entry.getKey() + " " + entry.getValue() + "\n");
            }
            writer.close();

            System.out.println("Word list file written to: src/main/resources/wordlist.txt");

            System.out.print("Enter a word to search for: ");
            String searchWord = scanner.nextLine().toLowerCase();

            // Algorithm for Finding Number of Occurrences of a Word To find the number of occurrences of a specific word, the word is used as a ey to retrieve its count from the hash table.
            // If the word is not found in the hash table, then it does not appear in the text file.
            if (wordCount.containsKey(searchWord)) {

                int count = wordCount.get(searchWord);
                System.out.println("'" + searchWord + "' appears " + count + " times in the text file.");
            } else {
                System.out.println("'" + searchWord + "' does not appear in the text file.");
            }

        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }

    }

}