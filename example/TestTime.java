package example;

import java.util.Scanner;

public class TestTime {
    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        Time time1, time2;
        String timeString, response = "Y";

        do {
            try {
                System.out.print("Enter time 1: ");
                timeString = input.nextLine();
                time1 = new Time(timeString);

                System.out.print("Enter time 2: ");
                timeString = input.nextLine();
                time2 = new Time(timeString);

            } catch (InvalidTime error) {
                System.out.println(error);
                continue;
            }

            int comparison = time1.compareTo(time2);
            if (comparison < 0)
                System.out.println(time1 + " is less than " + time2);
            else if (comparison > 0)
                System.out.println(time1 + " is greater than " + time2);
            else
                System.out.println(time1 + " is equal to " + time2);

            System.out.print("Enter Y to continue, anything else to quit: ");
            response = input.nextLine();
        } while (response.equalsIgnoreCase("Y"));
    }
}
