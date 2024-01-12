CREATE TABLE patient (
    id SERIAL PRIMARY KEY ,
    nom VARCHAR ,
    dtn DATE
);

CREATE TABLE dent(
    idDent SERIAL PRIMARY KEY ,
    code VARCHAR UNIQUE NOT NULL,
    nom VARCHAR NOT NULL
);
insert into dent ( code, nom) VALUES
      ('MSG1', 'Première molaire supérieure gauche'),
      ('MSG2', 'Deuxième molaire supérieure gauche'),
      ('MSG3', 'Troisième molaire supérieure gauche'),
      ('PSG4', 'Première prémolaire supérieure gauche'),
      ('PSG5', 'Deuxième prémolaire supérieure gauche'),
      ('CSG6', 'Canine supérieure gauche'),
      ('ILSG7', 'Incisive latérale supérieure gauche'),
      ('ICSD8', 'Incisive centrale supérieure droite'),
      ('ICSD9', 'Incisive centrale supérieure droite'),
      ('ILSD10', 'Incisive latérale supérieure droite'),
      ('CSD11', 'Canine supérieure droite'),
      ('PSD12', 'Première prémolaire supérieure droite'),
      ('PSD13', 'Deuxième prémolaire supérieure droite'),
      ('MSD14', 'Première molaire supérieure droite'),
      ('MSD15', 'Deuxième molaire supérieure droite'),
      ('MSD16', 'Troisième molaire supérieure droite'),
      ('MIG21', 'Première molaire inférieure gauche'),
      ('MIG22', 'Deuxième molaire inférieure gauche'),
      ('MIG23', 'Troisième molaire inférieure gauche'),
      ('PIG24', 'Première prémolaire inférieure gauche'),
      ('PIG25', 'Deuxième prémolaire inférieure gauche'),
      ('CIG26', 'Canine inférieure gauche'),
      ('ICIG27', 'Incisive latérale inférieure gauche'),
      ('ICIG28', 'Incisive centrale inférieure gauche'),
      ('ICID29', 'Incisive centrale inférieure  droite'),
      ('ILID30', 'Incisive latérale inférieure droite'),
      ('CID31', 'Canine inférieure droite'),
      ('PID32', 'Première prémolaire inférieure droite'),
      ('PID33', 'Deuxième prémolaire inférieure droite'),
      ('MID34', 'Première molaire inférieure droite'),
      ('MID35', 'Deuxième molaire inférieure droite'),
      ('MID36', 'Troisième molaire inférieure droite');

CREATE TABLE patientEtatDent (
    id SERIAL PRIMARY KEY ,
    date DATE NOT NULL ,
    idpatient INT REFERENCES patient,
    codedent VARCHAR REFERENCES dent(code) ,
    etat INT CHECK ( etat >= 0 AND etat <= 10 )
);
CREATE TABLE priorisation (
    id SERIAL PRIMARY KEY ,
    nom VARCHAR NOT NULL
);
insert into priorisation (nom) VALUES
    ('Beaute'),
    ('Sante');
CREATE TABLE importanceDent(
    id SERIAL PRIMARY KEY ,
    IdPriorisation INT REFERENCES priorisation,
    code VARCHAR REFERENCES dent(code),
    value double precision
);
INSERT INTO importanceDent (IdPriorisation, code, value) VALUES
    (1, 'MSG1', 3), (1, 'MSG2', 4), (1, 'MSG3', 5),
    (1, 'PSG4', 6), (1, 'PSG5', 7),
    (1, 'CSG6', 8),
    (1, 'ILSG7', 9), (1, 'ICSD8', 10), (1, 'ICSD9', 10),(1, 'ILSD10', 9),
    (1, 'CSD11', 8),
    (1, 'PSD12', 7), (1, 'PSD13', 6),
    (1, 'MSD14', 5), (1, 'MSD15', 4), (1, 'MSD16', 3),
    (1, 'MIG21', 3), (1, 'MIG22', 4), (1, 'MIG23', 5),
    (1, 'PIG24', 6), (1, 'PIG25', 7),
    (1, 'CIG26', 8),
    (1, 'ICIG27', 9), (1, 'ICIG28', 10), (1, 'ICID29', 10), (1, 'ILID30', 9),
    (1, 'CID31', 8),
    (1, 'PID32', 7), (1, 'PID33', 6),
    (1, 'MID34', 5), (1, 'MID35', 4), (1, 'MID36', 3);

INSERT INTO importanceDent (IdPriorisation, code, value) VALUES
    (2, 'MSG1', 10), (2, 'MSG2', 9), (2, 'MSG3', 8),
    (2, 'PSG4', 7), (2, 'PSG5', 6),
    (2, 'CSG6', 5),
    (2, 'ILSG7', 4), (2, 'ICSD8', 3), (2, 'ICSD9', 3),(2, 'ILSD10', 4),
    (2, 'CSD11', 5),
    (2, 'PSD12', 6), (2, 'PSD13', 7),
    (2, 'MSD14', 8), (2, 'MSD15', 9), (2, 'MSD16', 10),
    (2, 'MIG21', 10), (2, 'MIG22', 9), (2, 'MIG23', 8),
    (2, 'PIG24', 7), (2, 'PIG25', 6),
    (2, 'CIG26', 5),
    (2, 'ICIG27', 4), (2, 'ICIG28', 3), (2, 'ICID29', 3), (2, 'ILID30', 4),
    (2, 'CID31', 5),
    (2, 'PID32', 6), (2, 'PID33', 7),
    (2, 'MID34', 8), (2, 'MID35', 9), (2, 'MID36', 10);

CREATE TABLE prestation (
    id SERIAL PRIMARY KEY ,
    nom VARCHAR
);
insert into prestation (nom) values
                                 ('Bonne sante');
insert into prestation (nom) values
    ('Remplacer'),
    ('Grand Reparation'),
    ('Reparation'),
    ('Netoyage');

CREATE TABLE echelleDent (
     id SERIAL PRIMARY KEY ,
     min INT CHECK ( min >= 0 AND min<=10 ) ,
     max INT CHECK ( max >= 0 AND max<=10 ) ,
     idprestation INT REFERENCES prestation
);
insert into echelleDent ( "min", "max", idprestation) values
      (0 , 0 , 1),
      (1 , 3 , 2),
      (4 , 7 , 3),
      (8 , 9 , 4),
      (10 , 10 , 5);

CREATE TABLE prixprestation(
    id SERIAL PRIMARY KEY ,
    codedent VARCHAR REFERENCES dent(code) ,
    idprestation INT REFERENCES prestation ,
    prix DOUBLE PRECISION
);
-- Combinaisons pour le codeDent et idPrestation
INSERT INTO prixprestation (codedent, idprestation, prix) VALUES
    ('MSG1', 1, 100000.00), ('MSG1', 2, 5000.00), ('MSG1', 3, 2000.00), ('MSG1', 4, 1000.00),
    ('MSG2', 1, 100000.00), ('MSG2', 2, 5000.00), ('MSG2', 3, 2000.00), ('MSG2', 4, 1000.00),
    ('MSG3', 1, 100000.00), ('MSG3', 2, 5000.00), ('MSG3', 3, 2000.00), ('MSG3', 4, 1000.00),
    ('PSG4', 1, 100000.00), ('PSG4', 2, 5000.00), ('PSG4', 3, 2000.00), ('PSG4', 4, 1000.00),
    ('PSG5', 1, 100000.00), ('PSG5', 2, 5000.00), ('PSG5', 3, 2000.00), ('PSG5', 4, 1000.00),
    ('CSG6', 1, 100000.00), ('CSG6', 2, 5000.00), ('CSG6', 3, 2000.00), ('CSG6', 4, 1000.00),
    ('ILSG7', 1, 100000.00), ('ILSG7', 2, 5000.00), ('ILSG7', 3, 2000.00), ('ILSG7', 4, 1000.00),
    ('ICSD8', 1, 100000.00), ('ICSD8', 2, 5000.00), ('ICSD8', 3, 2000.00), ('ICSD8', 4, 1000.00),
    ('ICSD9', 1, 100000.00), ('ICSD9', 2, 5000.00), ('ICSD9', 3, 2000.00), ('ICSD9', 4, 1000.00),
    ('ILSD10', 1, 100000.00), ('ILSD10', 2, 5000.00), ('ILSD10', 3, 2000.00), ('ILSD10', 4, 1000.00),
    ('CSD11', 1, 100000.00), ('CSD11', 2, 5000.00), ('CSD11', 3, 2000.00), ('CSD11', 4, 1000.00),
    ('PSD12', 1, 100000.00), ('PSD12', 2, 5000.00), ('PSD12', 3, 2000.00), ('PSD12', 4, 1000.00),
    ('PSD13', 1, 100000.00), ('PSD13', 2, 5000.00), ('PSD13', 3, 2000.00), ('PSD13', 4, 1000.00),
    ('MSD14', 1, 100000.00), ('MSD14', 2, 5000.00), ('MSD14', 3, 2000.00), ('MSD14', 4, 1000.00),
    ('MSD15', 1, 100000.00), ('MSD15', 2, 5000.00), ('MSD15', 3, 2000.00), ('MSD15', 4, 1000.00),
    ('MSD16', 1, 100000.00), ('MSD16', 2, 5000.00), ('MSD16', 3, 2000.00), ('MSD16', 4, 1000.00),
    ('MIG21', 1, 100000.00), ('MIG21', 2, 5000.00), ('MIG21', 3, 2000.00), ('MIG21', 4, 1000.00),
    ('MIG22', 1, 100000.00), ('MIG22', 2, 5000.00), ('MIG22', 3, 2000.00), ('MIG22', 4, 1000.00),
    ('MIG23', 1, 100000.00), ('MIG23', 2, 5000.00), ('MIG23', 3, 2000.00), ('MIG23', 4, 1000.00),
    ('PIG24', 1, 100000.00), ('PIG24', 2, 5000.00), ('PIG24', 3, 2000.00), ('PIG24', 4, 1000.00),
    ('PIG25', 1, 100000.00), ('PIG25', 2, 5000.00), ('PIG25', 3, 2000.00), ('PIG25', 4, 1000.00),
    ('CIG26', 1, 100000.00), ('CIG26', 2, 5000.00), ('CIG26', 3, 2000.00), ('CIG26', 4, 1000.00),
    ('ICIG27', 1, 100000.00), ('ICIG27', 2, 5000.00), ('ICIG27', 3, 2000.00), ('ICIG27', 4, 1000.00),
    ('ICIG28', 1, 100000.00), ('ICIG28', 2, 5000.00), ('ICIG28', 3, 2000.00), ('ICIG28', 4, 1000.00),
    ('ICID29', 1, 100000.00), ('ICID29', 2, 5000.00), ('ICID29', 3, 2000.00), ('ICID29', 4, 1000.00),
    ('ILID30', 1, 100000.00), ('ILID30', 2, 5000.00), ('ILID30', 3, 2000.00), ('ILID30', 4, 1000.00),
    ('CID31', 1, 100000.00), ('CID31', 2, 5000.00), ('CID31', 3, 2000.00), ('CID31', 4, 1000.00),
    ('PID32', 1, 100000.00), ('PID32', 2, 5000.00), ('PID32', 3, 2000.00), ('PID32', 4, 1000.00),
    ('PID33', 1, 100000.00), ('PID33', 2, 5000.00), ('PID33', 3, 2000.00), ('PID33', 4, 1000.00),
    ('MID34', 1, 100000.00), ('MID34', 2, 5000.00), ('MID34', 3, 2000.00), ('MID34', 4, 1000.00),
    ('MID35', 1, 100000.00), ('MID35', 2, 5000.00), ('MID35', 3, 2000.00), ('MID35', 4, 1000.00),
    ('MID36', 1, 100000.00), ('MID36', 2, 5000.00), ('MID36', 3, 2000.00), ('MID36', 4, 1000.00);


CREATE TABLE consultation (
    id SERIAL PRIMARY KEY ,
    date DATE NOT NULL,
    numero VARCHAR UNIQUE NOT NULL ,
    idpatient INT REFERENCES patient
);
CREATE TABLE traitement (
    id SERIAL PRIMARY KEY ,
    date DATE NOT NULL,
    numeroConsultation VARCHAR REFERENCES consultation(numero),
    prix DOUBLE PRECISION
);
CREATE TABLE priorisationTraitement (
    id SERIAL PRIMARY KEY ,
    numeroChoix INT NOT NULL,
    idTraitement INT REFERENCES traitement,
    idpriorisation INT REFERENCES prestation
);




