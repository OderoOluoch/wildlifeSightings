package models;

import org.sql2o.Connection;

import java.util.List;

public class EndangeredAnimal {

    public String name;
    public int id;
    public boolean endangered;
    private int health;
    private int age;


    public static final int MAX_HEALTH_LEVEL = 9;
    public static final int HEALTHY = 8;
    public static final int ILL = 12;
    public static final int OKAY = 0;

    public static final int NEW_BORN = 3;
    public static final int YOUNG = 8;
    public static final int ADULT = 12;


    public EndangeredAnimal(String name, int health, int age) {
        this.name = name;
        this.id = id;
        this.health = health;
        this.age = age;
    }

    public int getHealth() {
        return health;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object otherEndangeredAnimal) {
        if(!(otherEndangeredAnimal instanceof EndangeredAnimal)) {
            return false;
        } else {
            EndangeredAnimal newEndangeredAnimal = (EndangeredAnimal) otherEndangeredAnimal;
            return this.getName().equals(newEndangeredAnimal.getName());
        }
    }

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

    public static List<EndangeredAnimal> all() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM endangered_animals;";
            return con.createQuery(sql)
                    .executeAndFetch(EndangeredAnimal.class);
        }
    }

    public static List<Sighting> allEndangeredAnimalSighting(int id) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM sightings WHERE animal_id=:id ;";
            return con.createQuery(sql)
                    .executeAndFetch(Sighting.class);
        }
    }

    public static EndangeredAnimal find(int id) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM endangered_animals WHERE id=:id;";
            EndangeredAnimal endangeredanimal = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(EndangeredAnimal.class);
            return endangeredanimal;
        }
    }

    public void updateHealth(String health) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "UPDATE endangered_animals SET health=:health WHERE id=:id;";
            con.createQuery(sql)
                    .addParameter("id", id)
                    .addParameter("health", health)
                    .executeUpdate();
        }
    }

    public void updateAge(String age) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "UPDATE endangered_animals SET age=:age WHERE id=:id;";
            con.createQuery(sql)
                    .addParameter("age", age)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }

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
