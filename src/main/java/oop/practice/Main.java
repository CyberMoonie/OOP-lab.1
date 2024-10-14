package oop.practice;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {
    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File inputFile = new File("src/main/resources/test-input.json");

        // Read the entire input.json and assign to the root JsonNode
        JsonNode root = mapper.readTree(inputFile);

        if (root == null) {
            System.out.println("Error: The JSON file is empty or incorrectly formatted.");
            return;
        }

        String prettyJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(root);
        System.out.println(prettyJson);
    }
}
