DROP TABLE IF EXISTS airplanes_crews;
DROP TABLE IF EXISTS airplanes;
DROP TABLE IF EXISTS crews;

CREATE TABLE airplanes
(
    id               BIGINT AUTO_INCREMENT,
    code_name        VARCHAR(30) NOT NULL,
    model            VARCHAR(30) NOT NULL,
    manufacture_date DATE NOT NULL,
    capacity         DOUBLE,
    flight_range     DOUBLE,
    CONSTRAINT airplanes_PK PRIMARY KEY (id)
);

CREATE TABLE crews
(
    id          BIGINT AUTO_INCREMENT,
    first_name  VARCHAR(30) NOT NULL,
    last_name   VARCHAR(30) NOT NULL,
    position    VARCHAR(30) NOT NULL,
    birthday    DATE,
    citizenship VARCHAR(30),
    CONSTRAINT crews_PK PRIMARY KEY (id)
);

CREATE TABLE airplanes_crews
(
    airplanes_id BIGINT NOT NULL,
    crews_id     BIGINT NOT NULL,
    CONSTRAINT airplanes_crews_PK PRIMARY KEY (airplanes_id, crews_id),
    CONSTRAINT airplanes_crews_airplanes_FK FOREIGN KEY (airplanes_id) REFERENCES airplanes (id),
    CONSTRAINT airplanes_crews_crews_FK FOREIGN KEY (crews_id) REFERENCES crews (id)
);