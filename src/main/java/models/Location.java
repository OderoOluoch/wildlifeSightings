package models;
import org.sql2o.Connection;
import java.util.List;


public class Location {

    //Instantiating variables.
    public String name;
    public int id;

    //location constructor class.
    public Location(String name) {
        this.name = name;
        this.id = id;
    }

    //Getter method to get location name
    public String getName() {
        return name;
    }

    //Setter method to set location name
    public void setName(String name) {
        this.name = name;
    }

    //Getter method to get location id
    public int getId() {
        return id;
    }

    //Getter method to set location id
    public void setId(int id) {
        this.id = id;
    }

    //overridden method to compare location objects
    @Override
    public boolean equals(Object otherAnimal) {
        if(!(otherAnimal instanceof Animal)) {
            return false;
        } else {
            Animal newAnimal = (Animal) otherAnimal;
            return this.getName().equals(newAnimal.getName());
        }
    }


    //save method to get insert data into the database.
    public void save() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO locations (name) VALUES (:name);";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .executeUpdate()
                    .getKey();
        }
    }

    //static list method to get all instances of the location class.
    public static List<Location> all() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM locations;";
            return con.createQuery(sql)
                    .executeAndFetch(Location.class);
        }
    }


    //static method to get all sightings with this specific location
    public static List<Sighting> allLocationSighting(int id) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM sightings WHERE location_id=:id ;";
            List<Sighting> sightings = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetch(Sighting.class);
            return sightings;
        }
    }

    //Static method to get a specific location based on an ID
    public static Location find(int id) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM locations WHERE id=:id;";
            Location location = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Location.class);
            return location;
        }

    }

    //void method to update location name
    public void updateName(String name) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "UPDATE locations SET name=:name WHERE id=:id;";
            con.createQuery(sql)
                    .addParameter("id", id)
                    .addParameter("name", name)
                    .executeUpdate();
        }
    }

    //void method to delete location instance
    public void delete() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "DELETE FROM locations WHERE id=:id;";
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }

}
