package models;

import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EndangeredAnimalTest {
    //BD rule connects to the test database, and is run before and after each test
    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void endangeredAnimal_instantiatesCorrectly_true() {
        EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Fox", 1, 1);
        assertEquals(true, testEndangeredAnimal instanceof EndangeredAnimal);
    }

    @Test
    public void getHealth_returnsHealthAttribute_true() {
        EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Fox", 1, 3);
        assertEquals(1, testEndangeredAnimal.getHealth());
    }

    @Test
    public void save_assignsIdAndSavesObjectToDatabase() {
        EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Fox", 1, 3);
        testEndangeredAnimal.save();
        EndangeredAnimal savedEndangeredAnimal = EndangeredAnimal.all().get(0);
        assertEquals(testEndangeredAnimal.getId(), savedEndangeredAnimal.getId());
    }

    @Test
    public void all_returnsAllInstancesOfEndangeredAnimal_true() {
        EndangeredAnimal firstEndangeredAnimal = new EndangeredAnimal("Fox", 1, 3);
        firstEndangeredAnimal.save();
        EndangeredAnimal secondEndangeredAnimal = new EndangeredAnimal("Badger", 1, 3);
        secondEndangeredAnimal.save();
        assertEquals(true, EndangeredAnimal.all().get(0).equals(firstEndangeredAnimal));
        assertEquals(true, EndangeredAnimal.all().get(1).equals(secondEndangeredAnimal));
    }

    @Test
    public void find_returnsAnimalWithSameId_secondAnimal() {
        EndangeredAnimal firstEndangeredAnimal = new EndangeredAnimal("Fox", 1, 3);
        firstEndangeredAnimal.save();
        EndangeredAnimal secondEndangeredAnimal = new EndangeredAnimal("Badger", 1, 3);
        secondEndangeredAnimal.save();
        assertEquals(EndangeredAnimal.find(secondEndangeredAnimal.getId()), secondEndangeredAnimal);
    }

    @Test
    public void update_updatesHealthAttribute_true() {
        EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Fox", 1, 3);
        testEndangeredAnimal.save();
        testEndangeredAnimal.updateHealth(4);
        assertEquals(4, EndangeredAnimal.find(testEndangeredAnimal.getId()).getHealth());
    }

    @Test
    public void update_updatesAgeAttribute_true() {
        EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Fox", 1,2);
        testEndangeredAnimal.save();
        testEndangeredAnimal.updateAge(4);
        assertEquals(4, EndangeredAnimal.find(testEndangeredAnimal.getId()).getAge());
    }


}