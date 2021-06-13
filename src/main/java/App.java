import models.Animal;
import models.EndangeredAnimal;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
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
            return new ModelAndView(model, "animalList.hbs");
        }, new HandlebarsTemplateEngine());

        get("/animals/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "animalForm.hbs");
        }, new HandlebarsTemplateEngine());

        post("/animals/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            boolean endangered = req.queryParamsValues("endangered")!=null;
            String name = req.queryParams("name");
            System.out.println("Name One" + name);
            if (endangered) {
                String health = req.queryParams("health");
                String age = req.queryParams("age");
                System.out.println("Name Two" + name);
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
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());





        //Endangered animals
        get("/endangered", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            ArrayList<EndangeredAnimal> endangeredAnimals = (ArrayList<EndangeredAnimal>) EndangeredAnimal.all();
            model.put("endangeredAnimals", endangeredAnimals);
            return new ModelAndView(model, "endangeredAnimalList.hbs");
        }, new HandlebarsTemplateEngine());
    }
}
