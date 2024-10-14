package oop.practice;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {
    
    // Internal class to represent each Object
    static class Object {
        private int id;
        private Integer age; // Handle cases where age might not be present

        // Constructor
        public Object(int id, Integer age) {
            this.id = id;
            this.age = age;
        }

        // Getters
        public int getId() {
            return id;
        }

        public Integer getAge() {
            return age;
        }

        @Override
        public String toString() {
            return "ID: " + id + ", Age: " + (age != null ? age : "Not Provided");
        }
    }

    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File inputFile = new File("src/main/resources/input.json");

        // Read the entire input.json
        JsonNode root = mapper.readTree(inputFile);
        if (root == null || !root.has("input")) {
            System.out.println("Error: The JSON file is empty or incorrectly formatted.");
            return;
        }

        // Create a list to store Object objects
        List<Object> Objects = new ArrayList<>();

        // Access the "input" array and populate the list
        JsonNode inputArray = root.get("input");
        for (JsonNode node : inputArray) {
            int id = node.get("id").asInt(); 
            Integer age = node.has("age") ? node.get("age").asInt() : null; // Handle missing age
            Objects.add(new Object(id, age));
        }

        // Print the IDs and Ages of the Objects
        System.out.println("Objects IDs and Ages:");
        for (Object Object : Objects) {
            System.out.println(Object);
        }
    }
}
