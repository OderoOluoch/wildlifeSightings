package models;

//Imports the sql2o class.
import org.sql2o.Sql2o;

//This is where our app creates a session that opens a db connection.
public class DB {
    public static Sql2o sql2o = new Sql2o("jdbc:postgresql://localhost:5432/wildlife_tracker", "moringa", "kidero");
}
