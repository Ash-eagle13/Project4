package example;

import java.util.Scanner;

public class TestInterval {
    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        Interval<Integer> interval1, interval2;

        interval1 = inputInterval(1);
        interval2 = inputInterval(2);

        loop:
        while (true) {
            System.out.print("Enter 1 to compare intervals, 2 to check value, anything else to quit: ");
            int response = input.nextInt();

            switch (response) {
                case 1 -> {
                    if (interval2.subinterval(interval1))
                        System.out.println("Interval 1 is a sub-interval of interval 2");
                    else if (interval1.subinterval(interval2))
                        System.out.println("Interval 2 is a sub-interval of interval 1");
                    else if (interval1.overlaps(interval2))
                        System.out.println("The intervals overlap");
                    else
                        System.out.println("The intervals are disjoint");
                }
                case 2 -> {
                    System.out.print("Enter a value to test: ");
                    Integer value = input.nextInt();
                    boolean inInterval1 = interval1.within(value),
                            inInterval2 = interval2.within(value);

                    if (inInterval1 && inInterval2)
                        System.out.println("Both intervals contain the value " + value);
                    else if (inInterval1)
                        System.out.println("Only interval 1 contains the value " + value);
                    else if (inInterval2)
                        System.out.println("Only interval 2 contains the value " + value);
                    else
                        System.out.println("Neither interval contains the value " + value);
                }
                default -> {
                    break loop;
                }
            }
        }
    }

    private static Interval<Integer> inputInterval( int intervalNumber){
            System.out.print("Enter start of interval " + intervalNumber + ": ");
            Integer start = input.nextInt();
            System.out.print("Enter end: ");
            Integer end = input.nextInt();
            return new Interval<>(start, end);

        }
    }

