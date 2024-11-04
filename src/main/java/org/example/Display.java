package org.example;

public class Display {
    private final int width;
    private final int height;
    private final int refreshRate;
    private final String model;


    public Display(int width, int height, int refreshRate, String model) {
        validateParameters(width, height, refreshRate, model);
        this.width = width;
        this.height = height;
        this.refreshRate = refreshRate;
        this.model = model;
    }

    private void validateParameters(int width, int height, int refreshRate, String model) {
        if (width <= 0) {
            throw new IllegalArgumentException("Width must be positive, got: " + width);
        }
        if (height <= 0) {
            throw new IllegalArgumentException("Height must be positive, got: " + height);
        }
        if (refreshRate <= 0) {
            throw new IllegalArgumentException("Refresh rate must be positive, got: " + refreshRate);
        }
        if (model == null || model.trim().isEmpty()) {
            throw new IllegalArgumentException("Model cannot be null or empty");
        }
    }

    // Getters
    public String getModel() {
        return model;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getRefreshRate() {
        return refreshRate;
    }

    public long getTotalPixels() {
        return (long) width * height;
    }

    public String getAspectRatio() {
        int gcd = calculateGCD(width, height);
        return (width / gcd) + ":" + (height / gcd);
    }

    private int calculateGCD(int a, int b) {
        while (b > 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public ComparisonResult compareWithMonitor(Display other) {
        if (other == null) {
            throw new IllegalArgumentException("Cannot compare with null display");
        }

        ComparisonResult result = new ComparisonResult();

        // Resolution comparison
        long thisPixels = getTotalPixels();
        long otherPixels = other.getTotalPixels();

        result.setResolutionComparison(
                String.format("%dx%d (%s) vs %dx%d (%s)",
                        width, height, getAspectRatio(),
                        other.getWidth(), other.getHeight(), other.getAspectRatio()
                )
        );

        if (thisPixels > otherPixels) {
            result.setResolutionWinner(this.model);
        } else if (thisPixels < otherPixels) {
            result.setResolutionWinner(other.model);
        }

        // Refresh rate comparison
        result.setRefreshRateComparison(
                String.format("%dHz vs %dHz", refreshRate, other.getRefreshRate())
        );

        if (refreshRate > other.getRefreshRate()) {
            result.setRefreshRateWinner(this.model);
        } else if (refreshRate < other.getRefreshRate()) {
            result.setRefreshRateWinner(other.model);
        }

        return result;
    }

    @Override
    public String toString() {
        return String.format("%s (%dx%d @%dHz)",
                model, width, height, refreshRate);
    }
}

class ComparisonResult {
    private String resolutionComparison;
    private String refreshRateComparison;
    private String resolutionWinner;
    private String refreshRateWinner;

    // Getters and setters
    public String getResolutionComparison() {
        return resolutionComparison;
    }

    public void setResolutionComparison(String resolutionComparison) {
        this.resolutionComparison = resolutionComparison;
    }

    public String getRefreshRateComparison() {
        return refreshRateComparison;
    }

    public void setRefreshRateComparison(String refreshRateComparison) {
        this.refreshRateComparison = refreshRateComparison;
    }

    public String getResolutionWinner() {
        return resolutionWinner;
    }

    public void setResolutionWinner(String resolutionWinner) {
        this.resolutionWinner = resolutionWinner;
    }

    public String getRefreshRateWinner() {
        return refreshRateWinner;
    }

    public void setRefreshRateWinner(String refreshRateWinner) {
        this.refreshRateWinner = refreshRateWinner;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Resolution: ").append(resolutionComparison);
        if (resolutionWinner != null) {
            sb.append("\n→ ").append(resolutionWinner).append(" has higher resolution");
        } else {
            sb.append("\n→ Equal resolution");
        }

        sb.append("\nRefresh Rate: ").append(refreshRateComparison);
        if (refreshRateWinner != null) {
            sb.append("\n→ ").append(refreshRateWinner).append(" has higher refresh rate");
        } else {
            sb.append("\n→ Equal refresh rate");
        }

        return sb.toString();
    }
}