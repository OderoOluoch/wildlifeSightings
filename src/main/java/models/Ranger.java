package models;

import org.sql2o.Connection;
import java.util.List;

public class Ranger {
    private String name;
    private String email;
    private int id;
    private String  badge;

    public Ranger(String name, String email, String badge) {
        this.name = name;
        this.email = email;
        this.badge = badge;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }


    public String getBadge() {
        return badge;
    }

    public void setBadge(String badge) {
        this.badge = badge;
    }

    @Override
    public boolean equals(Object otherRanger) {
        if(!(otherRanger instanceof Ranger)) {
            return false;
        } else {
            Ranger newRanger = (Ranger) otherRanger;
            return this.getName().equals(newRanger.getName());
        }
    }

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
    public static List<Ranger> all() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM rangers;";
            return con.createQuery(sql)
                    .executeAndFetch(Ranger.class);
        }
    }

    public static List<Sighting> allRangerSighting(int id) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM sightings WHERE ranger_id=:id ;";
            return con.createQuery(sql)
                    .executeAndFetch(Sighting.class);
        }
    }

    public static Ranger find(int id) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM rangers WHERE id=:id;";
            Ranger ranger = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Ranger.class);
            return ranger;
        }
    }

    public void updateName(String name) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "UPDATE rangers SET name=:name WHERE id=:id;";
            con.createQuery(sql)
                    .addParameter("id", id)
                    .addParameter("name", name)
                    .executeUpdate();
        }
    }

    public void delete() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "DELETE FROM rangers WHERE id=:id;";
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }

    public List<Sighting> getSightings() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM sightings WHERE ranger_id=:id;";
            List<Sighting> sightings = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetch(Sighting.class);
            return sightings;
        }
    }
}
