package models;

import org.sql2o.Connection;
import java.util.List;

public class EndangeredAnimal implements AnimalInterface {

    //Declaring our variables
    public String name;
    public int id;
    public boolean endangered;
    private int health;
    private int age;

    //Declaring our constants.
    public static final int MIN_HEALTH_LEVEL = 1;
    public static final int HEALTHY = 8;
    public static final int ILL = 12;
    public static final int OKAY = 0;

    public static final int NEW_BORN = 3;
    public static final int YOUNG = 8;
    public static final int ADULT = 12;

    //Constructor class.
    public EndangeredAnimal(String name, int health, int age) {
        this.name = name;
        this.id = id;
        this.health = health;
        this.age = age;
    }

    //getter method for the health variable
    public int getHealth() {
        return health;
    }

    //getter method for the age variable.
    public int getAge() {
        return age;
    }

    //getter method fo the name variable
    public String getName() {
        return name;
    }

    //getter method for the ID variable.
    public int getId() {
        return id;
    }

    //overridden update name method.
    @Override
    public void updateName(String name) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "UPDATE endangered_animals SET name=:name WHERE id=:id;";
            con.createQuery(sql)
                    .addParameter("id", id)
                    .addParameter("name", name)
                    .executeUpdate();
        }
    }

    //overridden written equal method that compares the instances of this class objects.
    @Override
    public boolean equals(Object otherEndangeredAnimal) {
        if(!(otherEndangeredAnimal instanceof EndangeredAnimal)) {
            return false;
        } else {
            EndangeredAnimal newEndangeredAnimal = (EndangeredAnimal) otherEndangeredAnimal;
            return this.getName().equals(newEndangeredAnimal.getName());
        }
    }

    //Save method that inputs the data into the database.
    public void save() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO endangered_animals (name, health, age) VALUES (:name, :health, :age);";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("health", this.health)
                    .addParameter("age", this.age)
                    .executeUpdate()
                    .getKey();
        }
    }

    //static method that lists all instances of this class.
    public static List<EndangeredAnimal> all() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM endangered_animals;";
            return con.createQuery(sql)
                    .executeAndFetch(EndangeredAnimal.class);
        }
    }

    //Static method that gets all database entries with this class instance ID.
    public static List<Sighting> allEndangeredAnimalSighting(int id) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM sightings WHERE animal_id=:id ;";
            List<Sighting> sightings = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetch(Sighting.class);
            return sightings;
        }
    }


    //static methods that finds a database entry based on a specific ID
    public static EndangeredAnimal find(int id) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM endangered_animals WHERE id=:id;";
            EndangeredAnimal endangeredanimal = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(EndangeredAnimal.class);
            return endangeredanimal;
        }
    }

    ///Public method that utilizes the constant to check if instance is dead or alive.
    public void updateHealth(String health) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "UPDATE endangered_animals SET health=:health WHERE id=:id;";
            con.createQuery(sql)
                    .addParameter("id", id)
                    .addParameter("health", health)
                    .executeUpdate();
        }
    }

    //Method that updates the age of the animal instance
    public void updateAge(String age) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "UPDATE endangered_animals SET age=:age WHERE id=:id;";
            con.createQuery(sql)
                    .addParameter("age", age)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }

    //Method that gets all instances of the database entries where the animal ID exists.
    public List<Sighting> getSightings() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM sightings WHERE animal_id=:id;";
            List<Sighting> sightings = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetch(Sighting.class);
            return sightings;
        }
    }


}
