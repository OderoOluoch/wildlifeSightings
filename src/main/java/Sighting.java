import org.sql2o.Connection;

import java.util.List;

public class Sighting {
    private int animal_id;
    private String location;
    private String ranger_Name;
    private int id;

    public Sighting(int animal_id,String location, String ranger_name){
        this.animal_id = animal_id;
        this.location = location;
        this.ranger_Name = ranger_name;
        this.id = id;
    }

    public int getAnimal_id() {
        return animal_id;
    }

    public void setAnimal_id(int animal_id) {
        this.animal_id = animal_id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRanger_Name() {
        return ranger_Name;
    }

    public void setRanger_Name(String ranger_Name) {
        this.ranger_Name = ranger_Name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object sighting){
        if(!(sighting instanceof Sighting)){
            return false;
        }else {
            Sighting newSighting = (Sighting) sighting;
            return  this.getAnimal_id() == (newSighting.getAnimal_id()) &&
                    this.getLocation().equals(newSighting.getLocation()) &&
                    this.getRanger_Name().equals(newSighting.getRanger_Name());
        }
    }

    public void save() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO sightings (animal_id, location, ranger_name) VALUES (:animal_id, :location, :ranger_name);";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("animal_id", this.animal_id)
                    .addParameter("location", this.location)
                    .addParameter("ranger_name", this.ranger_Name)
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
