package org.example;

import com.fasterxml.jackson.databind.JsonNode;

public class Classifier {

    public String classifyUniverse(JsonNode entry) {
        if (entry.has("planet")) {
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
        return "unknown";
    }
}
