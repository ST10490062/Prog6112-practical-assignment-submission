package prog6112assignment;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;

public class SeriesTest {

    private ArrayList<Series> seriesList;

    @Before
    public void setUp() {
        seriesList = new ArrayList<>();
    }

    @Test
    public void TestSearchSeries() {
        // Arrange
        int seriesId = 1;
        Series expected = new Series(seriesId, "Test Series", "12", "10");
        seriesList.add(expected);

        // Act
        Series found = searchSeries(String.valueOf(seriesId));

        // Assert
        assertNotNull("Series should be found", found);
        assertEquals(expected.getSeriesID(), found.getSeriesID());
        assertEquals(expected.getSeriesName(), found.getSeriesName());
        assertEquals(expected.getSeriesAge(), found.getSeriesAge());
        assertEquals(expected.getSeriesNumberOfEpisodes(), found.getSeriesNumberOfEpisodes());
    }

    @Test
    public void TestSearchSeries_SeriesNotFound() {
        // Arrange
        seriesList.add(new Series(2, "Existing Series", "12", "5"));

        // Act
        Series found = searchSeries("999");

        // Assert
        assertNull("No series should be found with an incorrect ID", found);
    }

    @Test
    public void TestUpdateSeries() {
        // Arrange
        int seriesId = 3;
        seriesList.add(new Series(seriesId, "Old Name", "12", "5"));
        String newName = "Updated Name";
        String newAge = "16";
        String newEpisodes = "8";

        // Act
        boolean updated = updateSeries(String.valueOf(seriesId), newName, newAge, newEpisodes);
        Series updatedSeries = searchSeries(String.valueOf(seriesId));

        // Assert
        assertTrue("Series should be updated successfully", updated);
        assertNotNull(updatedSeries);
        assertEquals(newName, updatedSeries.getSeriesName());
        assertEquals(newAge, updatedSeries.getSeriesAge());
        assertEquals(newEpisodes, updatedSeries.getSeriesNumberOfEpisodes());
    }

    @Test
    public void TestDeleteSeries() {
        // Arrange
        int seriesId = 4;
        Series series = new Series(seriesId, "To Delete", "12", "5");
        seriesList.add(series);

        // Act
        boolean deleted = deleteSeries(String.valueOf(seriesId));
        Series afterDelete = searchSeries(String.valueOf(seriesId));

        // Assert
        assertTrue("Series should be deleted", deleted);
        assertNull("Deleted series should not be found", afterDelete);
    }

    @Test
    public void TestDeleteSeries_SeriesNotFound() {
        // Arrange
        seriesList.add(new Series(5, "Existing Series", "12", "5"));

        // Act
        boolean deleted = deleteSeries("999");

        // Assert
        assertFalse("Delete should fail for non-existent series", deleted);
    }

    @Test
    public void TestSeriesAgeRestriction_AgeValid() {
        // Arrange
        String validAge = "12";

        // Act
        boolean result = isAgeValid(validAge);

        // Assert
        assertTrue("Valid age should return true", result);
    }

    @Test
    public void TestSeriesAgeRestriction_SeriesAgeInValid() {
        // Arrange
        String invalidAge = "-5";

        // Act
        boolean result = isAgeValid(invalidAge);

        // Assert
        assertFalse("Invalid age should return false", result);
    }

    // -----------------------
    // Helper methods (mimic your app logic)
    // -----------------------
    private Series searchSeries(String seriesId) {
        for (Series series : seriesList) {
            if (String.valueOf(series.getSeriesID()).equals(seriesId)) {
                return series;
            }
        }
        return null;
    }

    private boolean updateSeries(String seriesId, String newName, String newAge, String newEpisodes) {
        Series series = searchSeries(seriesId);
        if (series != null) {
            series.SeriesName = newName;
            series.SeriesAge = newAge;
            series.SeriesNumberOfEpisodes = newEpisodes;
            return true;
        }
        return false;
    }

    private boolean deleteSeries(String seriesId) {
        Series series = searchSeries(seriesId);
        if (series != null) {
            seriesList.remove(series);
            return true;
        }
        return false;
    }

    private boolean isAgeValid(String ageStr) {
        try {
            int age = Integer.parseInt(ageStr);
            return age >= 2 && age <= 18;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
