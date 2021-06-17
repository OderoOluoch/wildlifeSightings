package models;

import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SightingTest {

   @Rule
   public DatabaseRule database = new DatabaseRule();

    @Test
    public void sighting_instantiatesCorrectly_true() {
        Sighting testSightings = new Sighting(1,2,3);
        testSightings.save();
    }

    @Test
    public void equals_returnsTrueIfAnimalIdAndRangerIdAreSame_true() {
        Sighting testSightings = new Sighting(1,1,3);
        testSightings.save();
        Sighting testSightingsTwo = new Sighting(1,2,3);
        testSightingsTwo.save();

        assertTrue(testSightingsTwo.equals(testSightingsTwo));

    }

    @Test
    public void save_insertsObjectIntoDatabase_Sighting() {
        Sighting testSightings = new Sighting(1,1,3);
        testSightings.save();
        assertEquals(false, Sighting.all().get(0).equals(testSightings));
    }

    @Test
    public void all_returnsAllInstancesOfSighting_true() {
        Sighting testSightings = new Sighting(1,1,3);
        testSightings.save();

        Sighting testSightingsTwo = new Sighting(3,2,3);
        testSightingsTwo.save();

        assertEquals(false, Sighting.all().get(0).equals(testSightings));
        assertEquals(false, Sighting.all().get(1).equals(testSightingsTwo));
    }

    @Test
    public void find_returnsSightingWithSameId_secondSighting() {
        Sighting testSightings = new Sighting(1,1,3);
        testSightings.save();

        Sighting testSightingsTwo = new Sighting(3,2,3);
        testSightingsTwo.save();

        assertEquals(testSightingsTwo, testSightingsTwo);
    }

    @Test
    public void find_returnsNullWhenNoAnimalFound_null() {
        assertTrue(Animal.find(999) == null);
    }


}