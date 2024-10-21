package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        // Read JSON file
        File inputFile = new File("src/main/resources/input.json");
        JsonNode data = mapper.readTree(inputFile).get("data");

        // Initialize Universes
        Classifiers classifiers = new Classifiers(); // Use Classifiers instead of Classifier
        List<JsonNode> starWarsIndividuals = new ArrayList<>();
        List<JsonNode> hitchhikersIndividuals = new ArrayList<>();
        List<JsonNode> marvelIndividuals = new ArrayList<>();
        List<JsonNode> ringsIndividuals = new ArrayList<>();

        // Classify and store entries, handling missing properties
        for (JsonNode entry : data) {
            String universe = classifiers.classifyUniverse(entry); // Call classifyUniverse on Classifiers

            switch (universe) {
                case "starWars":
                    starWarsIndividuals.add(entry);
                    break;
                case "hitchhiker":
                    hitchhikersIndividuals.add(entry);
                    break;
                case "marvel":
                    marvelIndividuals.add(entry);
                    break;
                case "rings":
                    ringsIndividuals.add(entry);
                    break;
                default:
                    System.out.println("Unknown universe for entry: " + entry);
            }
        }

        // Create Universe records
        Universe starWars = new Universe("starWars", starWarsIndividuals);
        Universe hitchhikers = new Universe("hitchhiker", hitchhikersIndividuals);
        Universe marvel = new Universe("marvel", marvelIndividuals);
        Universe rings = new Universe("rings", ringsIndividuals);

        // Write output JSON files
        mapper.writeValue(new File("src/main/resources/output/starwars.json"), starWars.individuals());
        mapper.writeValue(new File("src/main/resources/output/hitchhiker.json"), hitchhikers.individuals());
        mapper.writeValue(new File("src/main/resources/output/marvel.json"), marvel.individuals());
        mapper.writeValue(new File("src/main/resources/output/rings.json"), rings.individuals());

        System.out.println("Files have been generated successfully.");
    }
}

// Classifiers class
// Classifiers class
class Classifiers {

    // Method to classify based on planet, if provided
    public String classifyUniverse(JsonNode entry) {
        if (entry.has("planet") && !entry.get("planet").isNull()) {
            String planet = entry.get("planet").asText();
            switch (planet.toLowerCase()) {
                case "kashyyyk":
                case "endor":
                    return "starWars";
                case "betelgeuse":
                case "vogsphere":
                    return "hitchhiker";
                case "asgard":
                    return "marvel";
                case "earth":
                    return "rings";
                default:
                    return "unknown";
            }
        }
        // If no planet, use the combined single IF logic to classify
        return classifyBasedOnAllFactors(entry);
    }

    // Single IF to classify based on other factors (age, species, isHumanoid, traits) with null checks
    public String classifyBasedOnAllFactors(JsonNode entry) {
        String universe = "unknown";
        String planet = "unknown";

        // Single if statement combining all checks
        if ((entry.has("age") && !entry.get("age").isNull()) ||
                (entry.has("species") && !entry.get("species").isNull()) ||
                (entry.has("isHumanoid") && !entry.get("isHumanoid").isNull()) ||
                (entry.has("traits") && entry.get("traits").isArray() && entry.get("traits").size() > 0 && !entry.get("traits").get(0).isNull())) {

            // Classify by age
            if (entry.has("age") && !entry.get("age").isNull()) {
                int age = entry.get("age").asInt();
                if (age > 1000) {
                    universe = "rings";
                    planet = "earth";
                } else if (age > 100) {
                    universe = "starWars";
                    planet = "endor";
                } else {
                    universe = "marvel";
                    planet = "asgard";
                }
            }

            // Classify by species
            if (entry.has("species") && !entry.get("species").isNull()) {
                String species = entry.get("species").asText().toLowerCase();
                if (species.equals("wookie") || species.equals("human")) {
                    universe = "starWars";
                    planet = "kashyyyk";
                } else if (species.equals("timelord") || species.equals("vogon")) {
                    universe = "hitchhiker";
                    planet = "betelgeuse";
                } else if (species.equals("asgardian")) {
                    universe = "marvel";
                    planet = "asgard";
                } else if (species.equals("elf") || species.equals("hobbit")) {
                    universe = "rings";
                    planet = "earth";
                }
            }

            // Classify by isHumanoid
            if (entry.has("isHumanoid") && !entry.get("isHumanoid").isNull()) {
                boolean isHumanoid = entry.get("isHumanoid").asBoolean();
                if (isHumanoid) {
                    universe = "marvel";
                    planet = "asgard";
                } else {
                    universe = "hitchhiker";
                    planet = "vogsphere";
                }
            }

            // Classify by traits
            if (entry.has("traits") && entry.get("traits").isArray() && entry.get("traits").size() > 0 && !entry.get("traits").get(0).isNull()) {
                String trait = entry.get("traits").get(0).asText().toLowerCase();
                if (trait.equals("bulky")) {
                    universe = "starWars";
                    planet = "endor";
                } else if (trait.equals("mystic")) {
                    universe = "rings";
                    planet = "earth";
                }
            }
        }

        // Assign the planet if one was determined
        if (!planet.equals("unknown")) {
            ((ObjectNode) entry).put("planet", planet);
        }

        return universe;
    }
}


// Record to represent a Universe
record Universe(
        String name,
        List<JsonNode> individuals
){}
