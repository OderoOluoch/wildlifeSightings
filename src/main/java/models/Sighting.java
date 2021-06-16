package models;
import org.sql2o.Connection;

import java.sql.Timestamp;
import java.util.List;

public class Sighting {
    private int animal_id;
    private int location_id;
    private int ranger_id;
    private int id;

    private Timestamp sighted_at;

    public Sighting(int animal_id,int location_id, int ranger_id){
        this.animal_id = animal_id;
        this.location_id = location_id;
        this.ranger_id = ranger_id;
        this.id = id;
    }

    public int getAnimal_id() {
        return animal_id;
    }

    public void setAnimal_id(int animal_id) {
        this.animal_id = animal_id;
    }

    public int getLocationID() {
        return location_id;
    }

    public void setLocationID(int location) {
        this.location_id = location;
    }

    public int getRanger_ID() {
        return ranger_id;
    }

    public void setRanger_NameID(int ranger_id) {
        this.ranger_id = ranger_id;
    }

    public Timestamp getSighted_at() {
        return sighted_at;
    }

    public void setSighted_at(Timestamp sighted_at) {
        this.sighted_at = sighted_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public static List<Sighting> all() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM sightings;";
            return con.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(Sighting.class);
        }
    }

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
