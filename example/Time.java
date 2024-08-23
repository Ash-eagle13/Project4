package example;

public class Time implements Comparable<Time> {
    private int hours;
    private int minutes;
    private String meridian;

    // Constructor that accepts a string in the format "HH:MM AM/PM"
    public Time(String timeString) throws InvalidTime {
        // Example input: "12:30 PM"
        String[] parts = timeString.split(" ");
        if (parts.length != 2) {
            throw new InvalidTime("Invalid time format. Expected 'HH:MM AM/PM'.");
        }
        String[] timeParts = parts[0].split(":");
        if (timeParts.length != 2) {
            throw new InvalidTime("Invalid time format. Expected 'HH:MM'.");
        }
        try {
            this.hours = Integer.parseInt(timeParts[0]);
            this.minutes = Integer.parseInt(timeParts[1]);
            this.meridian = parts[1].toUpperCase(); // Convert "AM" or "PM" to uppercase
            validateTime();
        } catch (NumberFormatException e) {
            throw new InvalidTime("Invalid numeric values for hours or minutes.");
        }
    }

    // Additional validation logic
    private void validateTime() throws InvalidTime {
        if (hours < 1 || hours > 12 || minutes < 0 || minutes > 59) {
            throw new InvalidTime("Invalid hours or minutes values.");
        }
        if (!meridian.equals("AM") && !meridian.equals("PM")) {
            throw new InvalidTime("Meridian should be 'AM' or 'PM'.");
        }
    }

    // Override toString to represent the time
    @Override
    public String toString() {
        return String.format("%02d:%02d %s", hours, minutes, meridian);
    }

    @Override
    public int compareTo(Time o) {
        return 0;
    }

    // Add additional methods as needed
}
