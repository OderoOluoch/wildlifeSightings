package models;

import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LocationTest {

    //BD rule connects to the test database, and is run before and after each test
    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void location_instantiatesCorrectly_true(){
        Location testLocation = new Location("Mountain Top");
        assertEquals(true, testLocation instanceof Location);
    }

    @Test
    public void getName_locationInstantiatesWithName_MountainTop() {
        Location testLocation = new Location("Mountain Top");
        assertEquals("Mountain Top", testLocation.getName());
    }

    @Test
    public void equals_returnsTrueIfNameIsTheSame_false() {
        Location testLocation = new Location("Mountain Top");
        Location secondTestLocation = new Location("Mountain Top");
        assertEquals(false, testLocation.equals(secondTestLocation));
    }


    @Test
    public void all_returnsAllInstancesOfLocation_false() {
        Location testLocation = new Location("Mountain Top");
        testLocation.save();
        Location secondTestLocation = new Location("Mountain Top");
        secondTestLocation.save();
        assertEquals(false, Location.all().get(0).equals(testLocation));
        assertEquals(false, Location.all().get(1).equals(secondTestLocation));
    }


    @Test
    public void delete_deletesLocationFromDatabase_0() {
        Location testLocation = new Location("Mountain Top");
        testLocation.save();
        testLocation.delete();
        assertEquals(0, Animal.all().size());
    }



    @Test
    public void find_returnsNullWhenNoLocationFound_null() {
        assertTrue(Ranger.find(999) == null);
    }


}