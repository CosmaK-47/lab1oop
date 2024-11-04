package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Assistant {
    private final String assistantName;
    private final List<Display> assignedDisplays;

    public Assistant(String assistantName) {
        if (assistantName == null || assistantName.trim().isEmpty()) {
            throw new IllegalArgumentException("Assistant name cannot be null or empty");
        }
        this.assistantName = assistantName;
        this.assignedDisplays = new ArrayList<>();
    }

    public String getAssistantName() {
        return assistantName;
    }

    public List<Display> getAssignedDisplays() {
        return new ArrayList<>(assignedDisplays); // Return a copy to preserve encapsulation
    }

    public void assignDisplay(Display display) {
        if (display == null) {
            throw new IllegalArgumentException("Display cannot be null");
        }
        assignedDisplays.add(display);
        System.out.println("Added " + display.getModel() + " to " + assistantName + "'s list");
    }

    public void assist() {
        if (assignedDisplays.size() < 2) {
            System.out.println("Need at least 2 displays to compare. Currently have: " + assignedDisplays.size());
            return;
        }

        System.out.println("\n" + assistantName + " comparing " + assignedDisplays.size() + " displays:");

        for (int i = 0; i < assignedDisplays.size() - 1; i++) {
            Display current = assignedDisplays.get(i);
            Display next = assignedDisplays.get(i + 1);

            System.out.println("\nComparison " + (i + 1) + ":");
            System.out.println("================");
            current.compareWithMonitor(next);
            System.out.println("================\n");
        }
    }

    public Optional<Display> buyDisplay(String modelName) {
        for (Display display : assignedDisplays) {
            if (display.getModel().equalsIgnoreCase(modelName)) {
                assignedDisplays.remove(display);
                System.out.println("Successfully purchased: " + display.getModel());
                return Optional.of(display);
            }
        }
        System.out.println("Display model '" + modelName + "' not found");
        return Optional.empty();
    }

    public void removeDisplay(Display display) {
        if (assignedDisplays.remove(display)) {
            System.out.println("Removed " + display.getModel() + " from " + assistantName + "'s list");
        } else {
            System.out.println("Display not found in " + assistantName + "'s list");
        }
    }

    public void clearDisplays() {
        int count = assignedDisplays.size();
        assignedDisplays.clear();
        System.out.println("Cleared " + count + " displays from " + assistantName + "'s list");
    }

    public static void main(String[] args) {
        // Create an assistant
        Assistant assistant = new Assistant("Tech Helper");

        // Create some displays with different specifications
        Display gaming = new Display(2560, 1440, 165, "Gaming Monitor Pro");
        Display office = new Display(1920, 1080, 60, "Office Display");
        Display creative = new Display(3840, 2160, 120, "Creative Studio 4K");

        // Assign displays to assistant
        assistant.assignDisplay(gaming);
        assistant.assignDisplay(office);
        assistant.assignDisplay(creative);

        // Compare all displays
        assistant.assist();

        // Buy a display
        Optional<Display> bought = assistant.buyDisplay("Gaming Monitor Pro");
        bought.ifPresent(display ->
                System.out.println("Purchased display specs: " + display.getWidth() + "x" + display.getHeight() +
                        " @" + display.getRefreshRate() + "Hz"));

        // Compare remaining displays
        assistant.assist();
    }
}