-- Create person table
CREATE TABLE person
(
    id INTEGER NOT NULL ,
    name VARCHAR(255) NOT NULL ,
    location VARCHAR(255),
    birth_date TIMESTAMP,
    primary key (id)
);

-- Insert data into the person table
INSERT INTO person (ID, NAME, LOCATION, BIRTH_DATE)
VALUES (10001, 'Eklak', 'Mahendranagar', CURRENT_DATE());
INSERT INTO person (ID, NAME, LOCATION, BIRTH_DATE)
VALUES (10002, 'Romeo', 'Lalitpur', CURRENT_DATE());
INSERT INTO person (ID, NAME, LOCATION, BIRTH_DATE)
VALUES (10003, 'Raskin', 'Sanagaun', CURRENT_DATE());
INSERT INTO person (ID, NAME, LOCATION, BIRTH_DATE)
VALUES (10004, 'Sushil', 'Machhegaun', CURRENT_DATE());
