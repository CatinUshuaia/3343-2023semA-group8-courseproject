package demo.testDemo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import demo.core.Location;

public class LocationTest {

    @Test
    public void testDistanceWithRest() {
        Location location = new Location(3, 4);

        int distance = location.DistanceWithRest();

        assertEquals(5, distance);
    }

    @Test
    public void testDistanceWithAnotherOrder() {
        Location location1 = new Location(1, 2);
        Location location2 = new Location(4, 6);

        int distance = location1.DistanceWithAnotherOrder(location2);

        assertEquals(5, distance);
    }
}