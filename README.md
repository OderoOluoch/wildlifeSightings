## Wildlife Tracker
Odero Oluoch


### Description
A Java based app that rangers can use to
report sightings of animals.
This system also contains a
database of all animals, all rangers
and all possible locations that these
animals can be sighted.


### Setup

To create the necessary databases, launch postgres, then psql, and run the following commands:

* `CREATE DATABASE wildlife_tracker;`
* `\c wildlife_tracker;`
* `CREATE TABLE animals (id serial PRIMARY KEY, name varchar);`
* `CREATE TABLE endangered_animals (id serial PRIMARY KEY, name varchar, health int, age int);`
* `CREATE TABLE locations (id serial PRIMARY KEY, name varchar);`
* `CREATE TABLE  rangers (id serial PRIMARY KEY, name varchar, email varchar,  badge varchar);`
* `CREATE TABLE sightings (id serial PRIMARY KEY, animal_id int, location_id int, ranger_id int, sighted_at TIMESTAMP);`
* `CREATE DATABASE wildlife_tracker_test WITH TEMPLATE wildlife_tracker;`

## Technologies used
- Java
- Gradle
- J-Unit5
- HandleBars


## Bugs being worked on
- It is open for critique and improvements

## Support and contact details
To help grow and make this product better, reach out to [email](mailto:oderoluoch@gmail.com).
### LICENSE
[![License: ISC](https://img.shields.io/badge/License-ISC-yellow.svg)](/LICENSE)

Copyright &copy; 2021 **[Odero OLuoch](www.github.com/OderoOluoch)**
