DROP TABLE IF EXISTS people;
CREATE TABLE people
(
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    firstname VARCHAR(255),
    surname VARCHAR(255),
    date_of_birth DATE,
    place_of_birth VARCHAR(255),
    salary FLOAT,
    family_status INT
);

INSERT INTO people(firstname, surname, date_of_birth, place_of_birth, salary, family_status)
VALUES ('John', 'Do', '2000-01-01', 'Ottawa', 1000, 0),
       ('Salim', 'Salem', '1995-02-01', 'London', 2000, 1),
       ('Yuri', 'Puturin', '1976-08-20', 'Barcelona', 3000, 0),
       ('Juan', 'De La Cruiz', '1980-06-10', 'St Petersburg', 5000, 0);