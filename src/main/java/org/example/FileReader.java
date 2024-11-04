package org.example;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

class FileReader {

    public String readFileIntoString(String path) throws IOException {
        Path filePath = Paths.get(path);
        validateFile(filePath);

        try {
            return new String(Files.readAllBytes(filePath), StandardCharsets.UTF_8);
        } catch (OutOfMemoryError e) {
            throw new IOException("File too large to read into memory", e);
        }
    }


    public Map<String, String> readDisplayProperties(String path) throws IOException {
        Path filePath = Paths.get(path);
        validateFile(filePath);

        Map<String, String> properties = new HashMap<>();

        try (Stream<String> lines = Files.lines(filePath, StandardCharsets.UTF_8)) {
            lines.forEach(line -> {
                if (line != null && line.contains("=")) {
                    String[] parts = line.split("=", 2);  // Split only on first '='
                    if (parts.length == 2) {
                        properties.put(parts[0].trim(), parts[1].trim());
                    }
                }
            });
        }

        return properties;
    }

    private void validateFile(Path path) {
        if (!Files.exists(path)) {
            throw new IllegalArgumentException("File does not exist: " + path);
        }
        if (!Files.isReadable(path)) {
            throw new IllegalArgumentException("File is not readable: " + path);
        }
    }
}