package models;

import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RangerTest {
    //BD rule connects to the test database, and is run before and after each test
    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void ranger_instantiatesCorrectly_true(){
        Ranger testRanger = new Ranger("Odero","odero@me.com","A322A");
        assertEquals(true, testRanger instanceof Ranger);
    }

    @Test
    public void getName_rangerInstantiatesWithName_Odero() {
        Ranger testRanger = new Ranger("Odero","odero@me.com","A322A");
        assertEquals("Odero", testRanger.getName());
    }

    @Test
    public void equals_returnsTrueIfNameIsTheSame_false() {
        Ranger testRanger = new Ranger("Odero","odero@me.com","A322A");
        Ranger testRangerTwo = new Ranger("Oluoch","odero@me.com","A322A");
        assertEquals(false, testRanger.equals(testRangerTwo));
    }


    @Test
    public void all_returnsAllInstancesOfRanger_false() {
        Ranger firstRanger = new Ranger("Odero","odero@me.com","A322A");
        firstRanger.save();
        Ranger secondRanger = new Ranger("Oluoch","oluoch@me.com","A422A");
        secondRanger.save();
        assertEquals(true, Ranger.all().get(0).equals(firstRanger));
        assertEquals(true, Ranger.all().get(1).equals(secondRanger));
    }

    @Test
    public void find_returnsRangerWithSameId_secondRanger() {
        Ranger firstRanger = new Ranger("Odero","odero@me.com","A422A");
        firstRanger.save();
        Ranger secondRanger = new Ranger("Oluoch","oluoch@me.com","A422A");
        secondRanger.save();
        assertEquals(Ranger.find(secondRanger.getId()), secondRanger);
    }

    @Test
    public void delete_deletesRangerFromDatabase_0() {
        Ranger testRanger = new Ranger("Oluoch","oluoch@me.com","A422A");
        testRanger.save();
        testRanger.delete();
        assertEquals(0, Animal.all().size());
    }



    @Test
    public void find_returnsNullWhenNoRangerFound_null() {
        assertTrue(Ranger.find(999) == null);
    }



}