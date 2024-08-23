package example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Project4 extends JFrame {
    private JTextField interval1Start, interval1End, interval2Start, interval2End, timeInput;
    private JLabel resultLabel;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");

    public Project4() {
        setTitle("Interval and Time Comparison");
        setLayout(new GridLayout(7, 2));

        interval1Start = new JTextField();
        interval1End = new JTextField();
        interval2Start = new JTextField();
        interval2End = new JTextField();
        timeInput = new JTextField();

        add(new JLabel("Interval 1 Start (HH:MM AM/PM):"));
        add(interval1Start);
        add(new JLabel("Interval 1 End (HH:MM AM/PM):"));
        add(interval1End);
        add(new JLabel("Interval 2 Start (HH:MM AM/PM):"));
        add(interval2Start);
        add(new JLabel("Interval 2 End (HH:MM AM/PM):"));
        add(interval2End);
        add(new JLabel("Time (HH:MM AM/PM):"));
        add(timeInput);

        JButton compareIntervalsButton = new JButton("Compare Intervals");
        compareIntervalsButton.addActionListener(new CompareIntervalsAction());
        JButton checkTimeButton = new JButton("Check Time");
        checkTimeButton.addActionListener(new CheckTimeAction());

        add(compareIntervalsButton);
        add(checkTimeButton);

        resultLabel = new JLabel("Results will appear here.");
        add(resultLabel);

        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // Nested Interval class
    private static class Interval<T extends Comparable<T>> {
        private T start;
        private T end;

        public Interval(T start, T end) {
            if (start.compareTo(end) > 0) {
                throw new IllegalArgumentException("Start time must be before end time.");
            }
            this.start = start;
            this.end = end;
        }

        public boolean subinterval(Interval<T> other) {
            return start.compareTo(other.start) >= 0 && end.compareTo(other.end) <= 0;
        }

        public boolean overlaps(Interval<T> other) {
            return start.compareTo(other.end) < 0 && end.compareTo(other.start) > 0;
        }

        public boolean within(T value) {
            return start.compareTo(value) <= 0 && end.compareTo(value) >= 0;
        }
    }

    // ActionListener for comparing intervals
    private class CompareIntervalsAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                // Retrieve LocalTime values
                LocalTime interval1StartTime = LocalTime.parse(interval1Start.getText().trim(), formatter);
                LocalTime interval1EndTime = LocalTime.parse(interval1End.getText().trim(), formatter);
                LocalTime interval2StartTime = LocalTime.parse(interval2Start.getText().trim(), formatter);
                LocalTime interval2EndTime = LocalTime.parse(interval2End.getText().trim(), formatter);

                // Create interval objects
                Interval<LocalTime> interval1 = new Interval<>(interval1StartTime, interval1EndTime);
                Interval<LocalTime> interval2 = new Interval<>(interval2StartTime, interval2EndTime);

                // Compare intervals and update the result label
                if (interval1.subinterval(interval2)) {
                    resultLabel.setText("Interval 1 is a sub-interval of Interval 2");
                } else if (interval2.subinterval(interval1)) {
                    resultLabel.setText("Interval 2 is a sub-interval of Interval 1");
                } else if (interval1.overlaps(interval2)) {
                    resultLabel.setText("The intervals overlap");
                } else {
                    resultLabel.setText("The intervals are disjoint");
                }
            } catch (DateTimeParseException ex) {
                resultLabel.setText("Invalid time format: " + ex.getMessage());
            } catch (IllegalArgumentException ex) {
                resultLabel.setText("Invalid interval: " + ex.getMessage());
            }
        }
    }

    // ActionListener for checking specific times within intervals
    private class CheckTimeAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                // Retrieve LocalTime values
                LocalTime interval1StartTime = LocalTime.parse(interval1Start.getText().trim(), formatter);
                LocalTime interval1EndTime = LocalTime.parse(interval1End.getText().trim(), formatter);
                LocalTime interval2StartTime = LocalTime.parse(interval2Start.getText().trim(), formatter);
                LocalTime interval2EndTime = LocalTime.parse(interval2End.getText().trim(), formatter);
                LocalTime checkTime = LocalTime.parse(timeInput.getText().trim(), formatter);

                Interval<LocalTime> interval1 = new Interval<>(interval1StartTime, interval1EndTime);
                Interval<LocalTime> interval2 = new Interval<>(interval2StartTime, interval2EndTime);

                boolean inInterval1 = interval1.within(checkTime);
                boolean inInterval2 = interval2.within(checkTime);

                if (inInterval1 && inInterval2) {
                    resultLabel.setText("Both intervals contain the time " + checkTime);
                } else if (inInterval1) {
                    resultLabel.setText("Only Interval 1 contains the time " + checkTime);
                } else if (inInterval2) {
                    resultLabel.setText("Only Interval 2 contains the time " + checkTime);
                } else {
                    resultLabel.setText("Neither interval contains the time " + checkTime);
                }
            } catch (DateTimeParseException ex) {
                resultLabel.setText("Invalid time format: " + ex.getMessage());
            } catch (IllegalArgumentException ex) {
                resultLabel.setText("Invalid interval: " + ex.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Project4();
    }
}
