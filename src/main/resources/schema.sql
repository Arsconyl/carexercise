DROP TABLE IF EXISTS cars;
CREATE TABLE cars(
    id SERIAL PRIMARY KEY,
    brand VARCHAR(128) NOT NULL,
    number_of_seats INTEGER NOT NULL,
    color VARCHAR(128) NOT NULL,
    person_id INTEGER
);

DROP TABLE IF EXISTS people;
CREATE TABLE people(
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(128) NOT NULL,
    last_name VARCHAR(128),
    company VARCHAR(128) NOT NULL,
    type INTEGER NOT NULL
);

DROP TABLE IF EXISTS cars_people;
DROP TABLE IF EXISTS people_cars;
CREATE TABLE people_cars(
    car_id INTEGER NOT NULL,
    person_id INTEGER NOT NULL
);
