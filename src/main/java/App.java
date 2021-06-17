import models.*;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");

        //Animal End Points.
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/animals", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            ArrayList<Animal> animals = (ArrayList<Animal>) Animal.all();
            model.put("animals", animals);
            return new ModelAndView(model, "animals/animalList.hbs");
        }, new HandlebarsTemplateEngine());

        get("/animals/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "animals/animalForm.hbs");
        }, new HandlebarsTemplateEngine());

        post("/animals/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            boolean endangered = req.queryParamsValues("endangered")!=null;
            String name = req.queryParams("name");
            if (endangered) {
                Integer health = Integer.parseInt(req.queryParams("health"));
                Integer age = Integer.parseInt(req.queryParams("age"));
                EndangeredAnimal endangeredAnimal = new EndangeredAnimal(name, health, age);
                endangeredAnimal.save();
            } else {
                Animal animal = new Animal(name);
                animal.save();
            }
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        get("/animals/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            Animal animal = Animal.find(Integer.parseInt(req.params("id")));
            model.put("animal", animal);

            List<Sighting> animalSightings =  Animal.allAnimalSighting(Integer.parseInt(req.params("id")));
            model.put("animalSightings", animalSightings);



            return new ModelAndView(model, "animals/animalDetail.hbs");
        }, new HandlebarsTemplateEngine());

        get("/animals/:id/update", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            Animal animal = Animal.find(Integer.parseInt(req.params("id")));
            model.put("animal", animal);
            return new ModelAndView(model, "animals/animalDetail.hbs");
        }, new HandlebarsTemplateEngine());

        post("/animals/:id/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            Animal animal = Animal.find(Integer.parseInt(req.params("id")));
            animal.delete();
            model.put("animal", animal);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());





        //Endangered animals
        get("/endangered", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            ArrayList<EndangeredAnimal> endangeredAnimals = (ArrayList<EndangeredAnimal>) EndangeredAnimal.all();
            model.put("endangeredAnimals", endangeredAnimals);
            return new ModelAndView(model, "endangered/endangeredAnimalList.hbs");
        }, new HandlebarsTemplateEngine());


        get("/endangered/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            EndangeredAnimal endangeredAnimal = EndangeredAnimal.find(Integer.parseInt(req.params("id")));
            model.put("endangeredAnimal", endangeredAnimal);
            List<Sighting> animalSightings =  EndangeredAnimal.allEndangeredAnimalSighting(Integer.parseInt(req.params("id")));
            model.put("animalSightings", animalSightings);
            return new ModelAndView(model, "endangered/endangeredAnimalDetail.hbs");
        }, new HandlebarsTemplateEngine());

        get("/endangered/:id/update", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            Animal animal = Animal.find(Integer.parseInt(req.params("id")));
            model.put("animal", animal);
            return new ModelAndView(model, "endangered/animalDetail.hbs");
        }, new HandlebarsTemplateEngine());

        post("/endangered/:id/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            EndangeredAnimal animal = EndangeredAnimal.find(Integer.parseInt(req.params("id")));
            animal.delete();
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());


        //Sightings end points
        get("/sightings", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            ArrayList<Sighting> sightings = (ArrayList<Sighting>) Sighting.all();
            model.put("sightings", sightings);
            return new ModelAndView(model, "sightings/sightingsList.hbs");
        }, new HandlebarsTemplateEngine());

        get("/sightings/new_ends", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            ArrayList<Animal> animals = (ArrayList<Animal>) Animal.all();
            ArrayList<Location> locations = (ArrayList<Location>) Location.all();
            ArrayList<Ranger> rangers = (ArrayList<Ranger>) Ranger.all();
            model.put("animals", animals);
            model.put("locations", locations);
            model.put("rangers", rangers);

            return new ModelAndView(model, "endangered/endangeredSIghtingform.hbs");
        }, new HandlebarsTemplateEngine());

        get("/sightings/new_non_ends", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            ArrayList<Animal> animals = (ArrayList<Animal>) Animal.all();
            ArrayList<Location> locations = (ArrayList<Location>) Location.all();
            ArrayList<Ranger> rangers = (ArrayList<Ranger>) Ranger.all();
            model.put("animals", animals);
            model.put("locations", locations);
            model.put("rangers", rangers);
            return new ModelAndView(model, "animals/NonengangeredSightingForm.hbs");
        }, new HandlebarsTemplateEngine());


        get("/sightings/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            Sighting sighting = Sighting.find(Integer.parseInt(req.params("id")));
            model.put("sighting", sighting);
            return new ModelAndView(model, "sightings/sightingsDetail.hbs");
        }, new HandlebarsTemplateEngine());

        get("/sightings/:id/update", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            Sighting sighting = Sighting.find(Integer.parseInt(req.params("id")));
            model.put("sighting", sighting);
            return new ModelAndView(model, "animals/animalDetail.hbs");
        }, new HandlebarsTemplateEngine());

        get("/sightings/:id/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            Sighting sighting = Sighting.find(Integer.parseInt(req.params("id")));
            sighting.delete();
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        post("/sightings/new_ends", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();

            int animalSelected = Integer.parseInt(request.queryParams("animalSelected"));
            int locationSelected = Integer.parseInt(request.queryParams("locationSelected"));
            int rangerSelected = Integer.parseInt(request.queryParams("rangerSelected"));

            Sighting sighting = new Sighting(animalSelected,locationSelected,rangerSelected);
            sighting.save();

            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        post("/sightings/new_non_ends", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();

            int animalSelected = Integer.parseInt(request.queryParams("animalSelected"));
            int locationSelected = Integer.parseInt(request.queryParams("locationSelected"));
            int rangerSelected = Integer.parseInt(request.queryParams("rangerSelected"));

            Sighting sighting = new Sighting(animalSelected,locationSelected,rangerSelected);
            sighting.save();

            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());




        //Ranger end points
        get("/ranger", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            ArrayList<Ranger> rangers = (ArrayList<Ranger>) Ranger.all();
            model.put("rangers", rangers);
            return new ModelAndView(model, "ranger/rangerList.hbs");
        }, new HandlebarsTemplateEngine());

        get("/ranger/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "ranger/rangerForm.hbs");
        }, new HandlebarsTemplateEngine());


        post("/ranger/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String name = req.queryParams("name");
            String email = req.queryParams("email");
            String badge = req.queryParams("badge");
            Ranger ranger = new Ranger(name,email,badge);
            ranger.save();
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());


        get("/ranger/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            Ranger ranger = Ranger.find(Integer.parseInt(req.params("id")));
            model.put("ranger", ranger);
            List<Sighting> rangerSightings = Ranger.allRangerSighting(Integer.parseInt(req.params("id")));
            model.put("rangerSightings", rangerSightings);

            return new ModelAndView(model, "ranger/rangerDetail.hbs");
        }, new HandlebarsTemplateEngine());

        get("/ranger/:id/update", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            Ranger ranger = Ranger.find(Integer.parseInt(req.params("id")));
            model.put("ranger", ranger);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        post("/ranger/:id/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            Ranger ranger = Ranger.find(Integer.parseInt(req.params("id")));
            ranger.delete();
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());





        //Location end Points
        get("/locations", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            ArrayList<Location> locations = (ArrayList<Location>) Location.all();
            model.put("locations", locations);
            return new ModelAndView(model, "location/locationList.hbs");
        }, new HandlebarsTemplateEngine());

        get("/locations/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "location/locationForm.hbs");
        }, new HandlebarsTemplateEngine());

        post("/locations/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String locationName = request.queryParams("name");
            Location location = new Location(locationName);
            location.save();
            model.put("location", location);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());



        get("/locations/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            Location location = Location.find((Integer.parseInt(req.params("id")))) ;
            model.put("location", location);
            List<Sighting> locationSightings = Location.allLocationSighting(Integer.parseInt(req.params("id")));
            model.put("locationSightings", locationSightings);
            return new ModelAndView(model, "location/locationDetail.hbs");
        }, new HandlebarsTemplateEngine());

        get("/locations/:id/update", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            Location location = Location.find((Integer.parseInt(req.params("id")))) ;
            model.put("location", location);
            return new ModelAndView(model, "location/locationDetail.hbs");
        }, new HandlebarsTemplateEngine());

        post("/locations/:id/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            Location location = Location.find((Integer.parseInt(req.params("id")))) ;
            location.delete();
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

    }
}
