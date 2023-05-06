package edu.guilford;



public class WordListDriver {
    public static void main(String[] args) {
        WordList wordList = new WordList("", 0);
        wordList.createWordList("inputfile.txt", "outputfile.txt");
    }
}

