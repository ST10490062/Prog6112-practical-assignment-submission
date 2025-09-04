package prog6112assignment;
import java.util.*;

public class Series {
    
    //Data Members
    public int SeriesID;
    public String SeriesName;
    public String SeriesAge;
    public String SeriesNumberOfEpisodes;
    
    //Constructor
    public Series() {
    }

    public Series(int SeriesID, String SeriesName, String SeriesAge, String SeriesNumberOfEpisodes) {
        this.SeriesID = SeriesID;
        this.SeriesName = SeriesName;
        this.SeriesAge = SeriesAge;
        this.SeriesNumberOfEpisodes = SeriesNumberOfEpisodes;
    }
    
  //method to capture the series
public static Series capture(Scanner input){
    System.out.println("CAPTURE NEW SERIES");
    System.out.println("*".repeat(20) + "\n");
    
    System.out.println("Enter the series ID: ");
    int SeriesID = Integer.parseInt(input.nextLine());
    System.out.println("Enter the series name: ");
    String SeriesName = input.nextLine();
    
    // Validate age restriction
    String SeriesAge;
    while (true) {
        System.out.println("Enter the series age restriction: ");
        String ageInput = input.nextLine();
        
        try {
            int age = Integer.parseInt(ageInput);
            if (age >= 2 && age <= 18) {
                SeriesAge = ageInput;
                break;
            } else {
                System.out.println("You have entered an incorrect series age!!!");
                System.out.print("Please re-enter the series age >> ");
            }
        } catch (NumberFormatException e) {
            System.out.println("You have entered an incorrect series age!!!");
            System.out.print("Please re-enter the series age >> ");
        }
    }
    
    System.out.println("Enter the number of episodes for " + SeriesName + ":");
    String SeriesNumberOfEpisodes = input.nextLine();
    System.out.println("Series processed successfully!!!");
    
    // Create series with the captured values
    Series series = new Series(SeriesID, SeriesName, SeriesAge, SeriesNumberOfEpisodes);
    
    return series;
}
    
    //method to search the series 
    public static void searchSeries(ArrayList<Series> mySeries, Scanner input){
        String SeriesToSearch;
        System.out.println("Enter Series ID to search: ");
        SeriesToSearch = input.nextLine();
        System.out.println("*********SERIES INFORMATION******");
        
        boolean found = false;  
        
        for (Series series : mySeries){
           if (SeriesToSearch.equalsIgnoreCase(String.valueOf(series.getSeriesID()))){
              System.out.println("Series ID is : " + series.getSeriesID());
              System.out.println("Series Name : " + series.getSeriesName());
              System.out.println("Series Age Restriction : " + series.getSeriesAge()); 
              System.out.println("Series Number of Episodes: " + series.getSeriesNumberOfEpisodes());
              found = true;  
              break;  
            }
        }
        
        // If series not found
        if (!found) {
            System.out.println("Series with Series ID " + SeriesToSearch + " not found.");
        }
    }
   
    //method to update series
    public static void updateSeries(ArrayList<Series> mySeries, Scanner input) {
        System.out.println("Enter the series id to update: ");
        int seriesIdToUpdate = Integer.parseInt(input.nextLine());
        
        boolean found = false;
        
        for (Series series : mySeries) {
            if (series.getSeriesID() == seriesIdToUpdate) {
                System.out.println("Enter the series name: ");
                series.SeriesName = input.nextLine();
                System.out.println("Enter the age restriction: ");
                series.SeriesAge = input.nextLine();
                System.out.println("Enter the number of episodes: ");
                series.SeriesNumberOfEpisodes = input.nextLine();
                
                System.out.println("Series updated successfully!");
                found = true;
                break;
            }
        }
        
        if (!found) {
            System.out.println("Series with Series ID " + seriesIdToUpdate + " not found.");
        }
    }
    
    //method to delete series
    public static void deleteSeries(ArrayList<Series> mySeries, Scanner input) {
        System.out.println("Enter the series id to delete: ");
        int seriesIdToDelete = Integer.parseInt(input.nextLine());
        
        boolean found = false;
        
        for (int i = 0; i < mySeries.size(); i++) {
            Series series = mySeries.get(i);
            if (series.getSeriesID() == seriesIdToDelete) {
                System.out.println("Are you sure you want to delete series " + seriesIdToDelete + " from the system? Yes (y) to delete.");
                String confirmation = input.nextLine();
                
                if (confirmation.equalsIgnoreCase("y")) {
                    mySeries.remove(i);
                    System.out.println("---Series with Series Id: " + seriesIdToDelete + " WAS deleted!");
                } else {
                    System.out.println("---Deletion cancelled.");
                }
                found = true;
                break;
            }
        }
        
        if (!found) {
            System.out.println("Series with Series ID " + seriesIdToDelete + " not found.");
        }
    }
    
    //method to print series report
    public static void printSeriesReport(ArrayList<Series> mySeries) {
        System.out.println("Print series report - 2025");
        System.out.println("*".repeat(30));
        
        if (mySeries.isEmpty()) {
            System.out.println("No series found in the system.");
            return;
        }
        
        for (int i = 0; i < mySeries.size(); i++) {
            Series series = mySeries.get(i);
            System.out.println("Series " + (i + 1));
            System.out.println("--- SERIES ID: " + series.getSeriesID());
            System.out.println("SERIES NAME: " + series.getSeriesName());
            System.out.println("SERIES AGE RESTRICTION: " + series.getSeriesAge());
            System.out.println("NUMBER OF EPISODES: " + series.getSeriesNumberOfEpisodes());
            System.out.println("---");
        }
    }
    
    //Getters
    public int getSeriesID() {
        return SeriesID;
    }

    public String getSeriesName() {
        return SeriesName;
    }

    public String getSeriesAge() {
        return SeriesAge;
    }

    public String getSeriesNumberOfEpisodes() {
        return SeriesNumberOfEpisodes;
    }
}