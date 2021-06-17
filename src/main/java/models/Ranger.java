package models;

import org.sql2o.Connection;
import java.util.List;

//Ranger class.
public class Ranger {
    //instantiating ranger class variables.
    private String name;
    private String email;
    private int id;
    private String  badge;

    //Ranger class constructor
    public Ranger(String name, String email, String badge) {
        this.name = name;
        this.email = email;
        this.badge = badge;
        this.id = id;
    }

    //Getter method to get ranger name
    public String getName() {
        return name;
    }

    //setter method to set ranger name
    public void setName(String name) {
        this.name = name;
    }


    //getter method to get ranger email
    public String getEmail() {
        return email;
    }

    //setter method to set ranger email
    public void setEmail(String email) {
        this.email = email;
    }

    //getter method to get ranger id
    public int getId() {
        return id;
    }

    //getter method to set ranger badge
    public String getBadge() {
        return badge;
    }

    //setter method to set ranger badge
    public void setBadge(String badge) {
        this.badge = badge;
    }

    //overridden method to compare Ranger objects.
    @Override
    public boolean equals(Object otherRanger) {
        if(!(otherRanger instanceof Ranger)) {
            return false;
        } else {
            Ranger newRanger = (Ranger) otherRanger;
            return this.getName().equals(newRanger.getName());
        }
    }

    //save method to save the ranger details into the database.
    public void save() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO rangers (name, email, badge) VALUES (:name, :email, :badge );";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("email", this.email)
                    .addParameter("badge", this.badge)
                    .executeUpdate()
                    .getKey();
        }
    }

    //Static method to list all instances of the ranger class.
    public static List<Ranger> all() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM rangers;";
            return con.createQuery(sql)
                    .executeAndFetch(Ranger.class);
        }
    }

    //static method to retrieve insights that have the ranger id specified
    public static List<Sighting> allRangerSighting(int id) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM sightings WHERE ranger_id=:id ;";
            List<Sighting> sightings = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetch(Sighting.class);
            return sightings;
        }
    }


    //find method that is used to find an instance of this object using the ID
    public static Ranger find(int id) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM rangers WHERE id=:id;";
            Ranger ranger = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Ranger.class);
            return ranger;
        }
    }


    //Method that updates the db entry based on ID
    public void updateName(String name) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "UPDATE rangers SET name=:name WHERE id=:id;";
            con.createQuery(sql)
                    .addParameter("id", id)
                    .addParameter("name", name)
                    .executeUpdate();
        }
    }

    //Method that deletes the db entry based on ID
    public void delete() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "DELETE FROM rangers WHERE id=:id;";
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }

}
