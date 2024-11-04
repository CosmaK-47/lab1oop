package org.example;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        // Check if at least one argument is provided
        if (args.length == 0) {
            System.out.println("Please provide file paths as command-line arguments.");
            return;
        }

        FileReader fileReader = new FileReader();

        // Process each file path from command-line arguments
        for (String filePath : args) {
            try {
                System.out.println("Reading file: " + filePath);

                // Reading file content as a string
                String textContent = fileReader.readFileIntoString(filePath);

                // Creating a TextData object with file name and content
                TextData textData = new TextData(filePath, textContent);

                // Display the statistics from TextData
                System.out.println(textData);

            } catch (IOException e) {
                System.out.println("Error reading file: " + filePath + " - " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
