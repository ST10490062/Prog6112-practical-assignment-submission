package prog6112assignment;

import java.util.*;

public class Prog6112Assignment {

    public static void main(String[] args) {
        //Declarations
        String menuInput;

        int choice = 0;
        ArrayList<Series> mySeries = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        Series series = new Series();

        //header
        System.out.println("Latest Series - 2025");
        System.out.println("*".repeat(20) + "\n");
        //menu loop 
        while (true) {
            System.out.println("Enter 1 to launch menu or any other key to exit.");
            menuInput = input.nextLine();

            if (menuInput.equals("1")) {
                //menu options
                System.out.println("\n Menu Options: ");
                System.out.println("(1) Capture a new series.");
                System.out.println("(2) Search for a series.");
                System.out.println("(3) Update series age restriction.");
                System.out.println("(4) Delete a series");
                System.out.println("(5) Print series report");
                System.out.println("(6) Exit Application.");

                System.out.println("\n Please select a valid option (1-6)");

                try {
                    choice = Integer.parseInt(input.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Please select a valid option (1-6)");
                    continue;

                }

                //menu code
                switch (choice) {
                    case 1:
                        mySeries.add(Series.capture(input));
                        break;
                    case 2:
                        Series.searchSeries(mySeries, input);
                        break;
                    case 3:
                        Series.updateSeries(mySeries, input);
                        break;
                    case 4:
                        Series.deleteSeries(mySeries, input);
                        break;
                    case 5:
                        Series.printSeriesReport(mySeries);
                        break;
                    case 6:
                        exitApplication();
                        break;
                }
            }
        }
    }

    public static void exitApplication() {
        System.out.println("Exiting application...");
        System.exit(0);
    }
}