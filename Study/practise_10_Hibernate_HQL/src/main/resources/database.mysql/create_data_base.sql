USE db;
DROP TABLE IF EXISTS people;
CREATE TABLE people
(
    id INT AUTO_INCREMENT PRIMARY KEY,
    firstname VARCHAR(255),
    surname VARCHAR(255),
    date_of_birth DATE NULL DEFAULT NULL,
    place_of_birth VARCHAR(255),
    salary FLOAT NULL DEFAULT NULL,
    department INT NULL DEFAULT NULL
);
INSERT INTO people(firstname, surname, date_of_birth, place_of_birth, salary, department)
VALUES
    ('John','Do', '2000-01-01', 'Ottawa', 1000, 1),
    ('Salim','Salem', '1995-02-01', 'London', 2000, 1),
    ('Yuri','Puturin', '1976-08-20', 'Barcelona', 3000, 2),
    ('Juan','De La Cruiz', '1980-06-10', 'St Petersburg', 5000, 2);

DROP TABLE IF EXISTS department;
CREATE TABLE department
(
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255),
    description VARCHAR(255),
    director INT,
    FOREIGN KEY (director) REFERENCES people(id)

);
INSERT INTO department(title, description, director)
VALUES
    ('First', 'first des', 1),
    ('Second', 'second des', 2),
    ('Third', 'second des', 1)