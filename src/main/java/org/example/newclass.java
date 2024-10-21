package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class newclass {
    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        // Read JSON file
        File inputFile = new File("src/main/resources/test-input.json");
        JsonNode data = mapper.readTree(inputFile).get("data");
    }
}
