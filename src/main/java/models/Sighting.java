package models;
import org.sql2o.Connection;

import java.sql.Timestamp;
import java.util.List;

//Sigtings class.
public class Sighting {
    private int animal_id;
    private int location_id;
    private int ranger_id;
    private int id;

    //Sightings time stamp.
    private Timestamp sighted_at;

    //Sightings constructor method.
    public Sighting(int animal_id,int location_id, int ranger_id){
        this.animal_id = animal_id;
        this.location_id = location_id;
        this.ranger_id = ranger_id;
        this.id = id;
    }


    //Getter method to get animal ID
    public int getAnimal_id() {
        return animal_id;
    }

    //Setter method to Set animal ID
    public void setAnimal_id(int animal_id) {
        this.animal_id = animal_id;
    }


    //Getter method to get location ID
    public int getLocationID() {
        return location_id;
    }

    //Setter method to set location ID
    public void setLocationID(int location) {
        this.location_id = location;
    }

    //Getter method to get ranger ID
    public int getRanger_ID() {
        return ranger_id;
    }

    //Setter method to set ranger ID
    public void setRanger_NameID(int ranger_id) {
        this.ranger_id = ranger_id;
    }

    //Getter method to get sighted time stamp
    public Timestamp getSighted_at() {
        return sighted_at;
    }

    //setter method to set sighted time stamp.
    public void setSighted_at(Timestamp sighted_at) {
        this.sighted_at = sighted_at;
    }

    //Getter method to get  ID
    public int getId() {
        return id;
    }

    //Setter method to set  ID
    public void setId(int id) {
        this.id = id;
    }

    //Method to save
    public void save() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO sightings (animal_id, location_id, ranger_id, sighted_at ) VALUES (:animal_id, :location_id, :ranger_id, now());";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("animal_id", this.animal_id)
                    .addParameter("location_id", this.location_id)
                    .addParameter("ranger_id", this.ranger_id)
                    .throwOnMappingFailure(false)
                    .executeUpdate()
                    .getKey();
        }
    }

    //Method to get all instances of the class.

    public static List<Sighting> all() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM sightings;";
            return con.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(Sighting.class);
        }
    }

    //Find method to retrieve and instance based on ID
    public static Sighting find(int id) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM sightings WHERE id=:id;";
            Sighting sighting = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Sighting.class);
            return sighting;
        } catch (IndexOutOfBoundsException exception) {
            return null;
        }
    }


}
