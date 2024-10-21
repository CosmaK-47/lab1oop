package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UniverseStorage {
    private final Map<String, List<JsonNode>> universeData;

    public UniverseStorage() {
        universeData = new HashMap<>();
        // Initialize lists for each universe
        universeData.put("starWars", new ArrayList<>());
        universeData.put("hitchhiker", new ArrayList<>());
        universeData.put("marvel", new ArrayList<>());
        universeData.put("rings", new ArrayList<>());
        universeData.put("unknown", new ArrayList<>());
    }

    // Add an entry to the respective universe category
    public void addToUniverse(String universe, JsonNode entry) {
        universeData.computeIfAbsent(universe, k -> new ArrayList<>()).add(entry);
    }

    // Retrieve entries for a specific universe category
    public List<JsonNode> getUniverseData(String universe) {
        return universeData.getOrDefault(universe, new ArrayList<>());
    }

    // Retrieve all entries grouped by universe
    public Map<String, List<JsonNode>> getAllUniverseData() {
        return universeData;
    }
}
