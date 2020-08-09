INSERT INTO airplanes(code_name, model, manufacture_date, capacity, flight_range)
VALUES ('RT4587', 'boeing', '2009-11-09', 1005.99, 555.0);

INSERT INTO airplanes(code_name, model, manufacture_date, capacity, flight_range)
VALUES ('UY2587', 'boeing', '2007-12-01', 999.99, 498.0);

INSERT INTO crews(first_name, last_name, position, birthday, citizenship)
VALUES ('Keanu', 'Reeves', 'pilot', '1975-09-15', 'USA');

INSERT INTO crews(first_name, last_name, position, birthday, citizenship)
VALUES ('Will', 'Smith', 'pilot', '1966-07-31', 'Japan');

INSERT INTO crews(first_name, last_name, position, birthday, citizenship)
VALUES ('Angelina', 'Jolie', 'stewardess', '1979-01-25', 'Ukraine');

INSERT INTO crews(first_name, last_name, position, birthday, citizenship)
VALUES ('Anna', 'Popovich', 'stewardess', '1988-11-05', 'Poland');

INSERT INTO airplanes_crews(airplanes_id, crews_id)
VALUES (1, 1);

INSERT INTO airplanes_crews(airplanes_id, crews_id)
VALUES (1, 3);

INSERT INTO airplanes_crews(airplanes_id, crews_id)
VALUES (1, 4);

INSERT INTO airplanes_crews(airplanes_id, crews_id)
VALUES (2, 2);

INSERT INTO airplanes_crews(airplanes_id, crews_id)
VALUES (2, 4);