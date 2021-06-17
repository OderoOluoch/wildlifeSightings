package models;

//Importations of packages

import org.sql2o.Connection;

import java.util.ArrayList;
import java.util.List;

//Animal class implements the AnimalsInterface
public class Animal implements AnimalInterface {
    public String name;
    public int id;

    //Constructor class.
    public Animal(String name) {
        this.name = name;
        this.id = id;
    }

    //Getter method to get Animal name
    public String getName() {
        return name;
    }

    //Getter method to get Animal id
    public int getId() {
        return id;
    }

    //Overridden method from the interface - updates animal name.
    @Override
    public void updateName(String name) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "UPDATE animals SET name=:name WHERE id=:id;";
            con.createQuery(sql)
                    .addParameter("id", id)
                    .addParameter("name", name)
                    .executeUpdate();
        }
    }

    //Overridden method
    @Override
    public boolean equals(Object otherAnimal) {
        if(!(otherAnimal instanceof Animal)) {
            return false;
        } else {
            Animal newAnimal = (Animal) otherAnimal;
            return this.getName().equals(newAnimal.getName());
        }
    }

    //Method to save Animal into the database.
    public void save() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO animals (name) VALUES (:name);";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .executeUpdate()
                    .getKey();
        }
    }

    //Method to list all instances
    public static List<Animal> all() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM animals;";
            return con.createQuery(sql)
                    .executeAndFetch(Animal.class);
        }
    }

    //Method to get all instances that the animal has been sighted.
//    public static List<Sighting> allAnimalSighting(int id) {
//        try(Connection con = DB.sql2o.open()) {
//            String sql = "SELECT * FROM sightings WHERE animal_id=:id ;";
//            List<Sighting> sightings = con.createQuery(sql)
//                    .addParameter("id", id)
//                    .executeAndFetch(Sighting.class);
//            return sightings;
//        }
//    }

    //Method to get all instances that the animal has been sighted.
    public static List<Sighting> allAnimalSighting(int id) {
        System.out.println(id);
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM sightings WHERE animal_id=:id ;";
            return (ArrayList<Sighting>) con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetch(Sighting.class);

        }
    }

    //Method to find an animal based on an ID.
    public static Animal find(int id) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM animals WHERE id=:id;";
            Animal animal = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Animal.class);
            return animal;
        }
    }

//Method to delete and animal identified by ID.
    public void delete() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "DELETE FROM animals WHERE id=:id;";
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }

    //method to get all sightings entries where tha animal id is.
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

