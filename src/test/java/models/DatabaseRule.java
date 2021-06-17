package models;
import org.junit.rules.ExternalResource;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

public class DatabaseRule extends ExternalResource {

    //Method that open database session before any each test is run.
    @Override
    protected void before() {
        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/wildlife_tracker_test", "moringa", "kidero");
    }

    //Method that cleans the test database after every test is run.
    @Override
    protected void after() {
        try(Connection con = DB.sql2o.open()) {
            String deleteAnimalsQuery = "DELETE FROM animals *;";
            String deleteEndangeredAnimalsQuery = "DELETE FROM endangered_animals *;";
            String deleteSightingsQuery = "DELETE FROM sightings *;";
            String deleteRangerQuery = "DELETE FROM rangers *;";
            String deleteLocationQuery = "DELETE FROM locations *;";
            con.createQuery(deleteAnimalsQuery).executeUpdate();
            con.createQuery(deleteEndangeredAnimalsQuery).executeUpdate();
            con.createQuery(deleteSightingsQuery).executeUpdate();
            con.createQuery(deleteRangerQuery).executeUpdate();
            con.createQuery(deleteLocationQuery).executeUpdate();
        }
    }


}
