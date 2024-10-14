package oop.practice;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {
    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File inputFile = new File("src/main/resources/input.json");

        JsonNode root = mapper.readTree(inputFile);

        if (root == null) {
            System.out.println("Error: The JSON file is empty or incorrectly formatted.");
            return;
        }

        // Print the entire JSON content to the terminal
        String prettyJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(root);
        System.out.println(prettyJson);
        
        JsonNode inputArray = root.get("input");
        if (inputArray != null && inputArray.isArray()) {
            int indexToPrint = 16; 
            
            if (indexToPrint < inputArray.size()) {
                JsonNode specificObject = inputArray.get(indexToPrint);
                //Print an Specific JSON object
                System.out.println("\nSpecific JSON Object at index " + indexToPrint + ":\n" +
                    mapper.writerWithDefaultPrettyPrinter().writeValueAsString(specificObject));
            } else {
                System.out.println("Error: Index out of bounds.");
            }
        } else {
            System.out.println("Error: 'input' array not found in the JSON file.");
        }
    }
}
