package org.example;

class TextData {
    // Attributes
    private String fileName;
    private String text;
    private int numberOfVowels;
    private int numberOfConsonants;
    private int numberOfLetters;
    private int numberOfSentences;
    private String longestWord;

    // Constructor
    public TextData(String fileName, String text) {
        this.fileName = fileName;
        this.text = text;
        this.numberOfVowels = countVowels();
        this.numberOfConsonants = countConsonants();
        this.numberOfLetters = text.replaceAll("[^a-zA-Z]", "").length();
        this.numberOfSentences = text.split("[.!?]").length;
        this.longestWord = findLongestWord();
    }

    // Methods
    public String getFilename() {
        return fileName;
    }

    public int getNumberOfVowels() {
        return numberOfVowels;
    }

    public int getNumberOfConsonants() {
        return numberOfConsonants;
    }

    public int getNumberOfLetters() {
        return numberOfLetters;
    }

    public int getNumberOfSentences() {
        return numberOfSentences;
    }

    public String getLongestWord() {
        return longestWord;
    }

    // Helper method to count vowels
    private int countVowels() {
        return (int) text.chars().filter(c -> "AEIOUaeiou".indexOf(c) != -1).count();
    }

    // Helper method to count consonants
    private int countConsonants() {
        return (int) text.chars().filter(c -> Character.isLetter(c) && "AEIOUaeiou".indexOf(c) == -1).count();
    }

    // Helper method to find the longest word
    private String findLongestWord() {
        String[] words = text.split("\\W+");
        String longest = "";
        for (String word : words) {
            if (word.length() > longest.length()) {
                longest = word;
            }
        }
        return longest;
    }

    // Display data
    @Override
    public String toString() {
        return "Filename: " + fileName +
                "\nNumber of Vowels: " + numberOfVowels +
                "\nNumber of Consonants: " + numberOfConsonants +
                "\nNumber of Letters: " + numberOfLetters +
                "\nNumber of Sentences: " + numberOfSentences +
                "\nLongest Word: " + longestWord + "\n";
    }
}
