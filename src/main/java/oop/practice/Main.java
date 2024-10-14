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
        private Integer age;
        private List<String> traits;
        private String planet;
        private String universe;

        // Constructor
        public Object(int id, Integer age, List<String> traits, String planet) {
            this.id = id;
            this.age = age;
            this.traits = traits;
            this.planet = planet;
            this.universe = classifyUniverse();
        }

        private String classifyUniverse() {
            if (traits == null) {
                return "Unknown";
            }

            boolean hasHairy = traits.contains("HAIRY");
            boolean hasTall = traits.contains("TALL");
            boolean hasBlonde = traits.contains("BLONDE");
            boolean hasShort = traits.contains("SHORT");
            boolean hasBulky = traits.contains("BULKY");
            boolean hasGreen = traits.contains("GREEN");
            boolean hasPointyEars = traits.contains("POINTY_EARS");
            boolean hasExtraArms = traits.contains("EXTRA_ARMS");
            boolean hasExtraHead = traits.contains("EXTRA_HEAD");

            if (age != null && age > 5000) {
                return "Lord of the Rings";
            }

            if ("Kashyyyk".equals(planet) || "Endor".equals(planet)) {
                return "Star Wars";
            }

            if (hasHairy && hasTall) {
                return "Star Wars";
            } else if (hasBlonde && hasTall) {
                return "Marvel";
            } else if (hasShort && hasHairy) {
                return "Star Wars";
            } else if (hasShort && hasBulky) {
                return "Lord of the Rings";
            } else if (hasBlonde && hasPointyEars) {
                return "Lord of the Rings";
            } else if (hasExtraArms || hasGreen || (hasBulky && hasGreen)) {
                return "Hitchhiker's Guide to the Galaxy";
            } else if (hasHairy) {
                return "Star Wars";
            } else if (hasBulky && hasShort) {
                return "Lord of the Rings";
            } else if (hasBulky) {
                return "Star Wars";
            } else if (hasExtraHead) {
                return "Hitchhiker's Guide to the Galaxy";
            }

            return "Unknown";
        }

        // Getters
        public int getId() {
            return id;
        }

        public Integer getAge() {
            return age;
        }

        public String getUniverse() {
            return universe;
        }

        @Override
        public String toString() {
            return "ID: " + id + 
                   "\nAge: " + (age != null ? age : "Not Provided") + 
                   "\nTraits: " + (traits != null && !traits.isEmpty() ? traits : "None") + 
                   "\nPlanet: " + (planet != null ? planet : "Not Provided") + 
                   "\nUniverse: " + universe + 
                   "\n";
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

        List<Object> Objects = new ArrayList<>();

        JsonNode inputArray = root.get("input");
        for (JsonNode node : inputArray) {
            int id = node.get("id").asInt();  
            Integer age = node.has("age") ? node.get("age").asInt() : null; 
            List<String> traits = new ArrayList<>();
            if (node.has("traits")) {
                for (JsonNode trait : node.get("traits")) {
                    traits.add(trait.asText());
                }
            }
            String planet = node.has("planet") ? node.get("planet").asText() : null; 

            Objects.add(new Object(id, age, traits, planet));
        }

        System.out.println("Object Details:");
        for (Object Object : Objects) {
            System.out.println(Object);
        }
    }
}
