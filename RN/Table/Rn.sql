CREATE TABLE route(
    id INT PRIMARY KEY,
    pkDebut double precision,
    pkFin double precision
);

-- Données de test pour la table route
INSERT INTO route (id, pkDebut, pkFin) VALUES
   (1, 0, 100),
   (2, 0, 260);

CREATE TABLE DegatRoute(
    id SERIAL PRIMARY KEY,
    idRoute INT REFERENCES route(id),
    pkDebut INT,
    pkFin INT,
    etat double precision
);
INSERT INTO DegatRoute ( idRoute, pkDebut, pkFin, etat) VALUES
    (2 , 10 , 50 , 6),
    (2 , 75 , 100 , 3),
    (2 , 120 , 200 , 5),
    (2 , 225 , 260 , 8),
    (1 , 20 , 50 , 9),
    (1 , 70 , 90 , 8);

CREATE TABLE priorisation(
    idPriorisation SERIAL PRIMARY KEY,
    nom VARCHAR
);
-- Données de test pour la table priorisation
INSERT INTO priorisation (idPriorisation, nom) VALUES
   (1, 'Ville'),
   (2, 'Economie');

CREATE TABLE importance(
    id SERIAL PRIMARY KEY,
    idRoute INT REFERENCES route(id),
    idPriorisation INT REFERENCES priorisation(idPriorisation),
    idPk INT,
    valeur double precision
);
SELECT DISTINCT DegatRoute.idRoute, DegatRoute.pkDebut, DegatRoute.pkFin, DegatRoute.etat, MAX(valeur) AS valeur , COALESCE(importance.idPk, 0)
FROM DegatRoute
         JOIN importance ON DegatRoute.idRoute = importance.idRoute
WHERE DegatRoute.idRoute = 2
  AND importance.idPriorisation = 2
  AND importance.idPk BETWEEN DegatRoute.pkDebut AND DegatRoute.pkFin
GROUP BY DegatRoute.idRoute, DegatRoute.pkDebut, DegatRoute.pkFin, DegatRoute.etat
ORDER BY valeur DESC;

SELECT DISTINCT DegatRoute.idRoute, DegatRoute.pkDebut, DegatRoute.pkFin, DegatRoute.etat, MAX(valeur) AS valeur , COALESCE(importance.idPk, 0)
FROM DegatRoute
         JOIN importance ON DegatRoute.idRoute = importance.idRoute
WHERE DegatRoute.idRoute = 2
  AND importance.idPriorisation = 2
  AND importance.idPk BETWEEN DegatRoute.pkDebut AND DegatRoute.pkFin
    AND importance.idPk IS NULL
GROUP BY DegatRoute.idRoute, DegatRoute.pkDebut, DegatRoute.pkFin, DegatRoute.etat
ORDER BY valeur DESC;
SELECT DISTINCT DegatRoute.idRoute, DegatRoute.pkDebut, DegatRoute.pkFin, DegatRoute.etat
FROM DegatRoute
WHERE DegatRoute.idRoute = 1
  AND NOT EXISTS (
        SELECT 1
        FROM importance
        WHERE importance.idRoute = DegatRoute.idRoute
          AND importance.idPriorisation = 1
          AND importance.idPk BETWEEN DegatRoute.pkDebut AND DegatRoute.pkFin
    )
ORDER BY DegatRoute.idRoute, DegatRoute.pkDebut, DegatRoute.pkFin, DegatRoute.etat;


INSERT INTO importance ( idRoute, idPriorisation, idPk, valeur) VALUES
    (2 , 1 , 0 , 10),
    (2 , 1 , 10 , 9),
    (2 , 1 , 50 , 8),
    (2 , 1 , 100 , 7),
    (2 , 1 , 175 , 6),
    (2 , 1 , 200 , 5),
    (2 , 1 , 260 , 8),
    (2 , 2 , 0 , 8),
    (2 , 2 , 10 , 5),
    (2 , 2 , 50 , 8),
    (2 , 2 , 100 , 10),
    (2 , 2 , 175 , 9),
    (2 , 2 , 200 , 8),
    (2 , 2 , 260 , 10),
    (1 , 1 , 0 , 10),
    (1 , 1 , 20 , 8),
    (1 , 1 , 50 , 7),
    (1 , 1 , 100 , 8),
    (1 , 2 , 0 , 8),
    (1 , 2 , 20 , 6),
    (1 , 2 , 50 , 9),
    (1 , 2 , 100 , 9);

CREATE TABLE prestation (
    id SERIAL PRIMARY KEY,
    nom VARCHAR
);
INSERT INTO prestation (nom) VALUES
     ( 'Complet'),
     ( 'Averina Complet'),
     ( 'Reparable'),
     ( 'TipTop'),
    ('RS');

CREATE TABLE prixPrestationDegat(
    id SERIAL PRIMARY KEY,
    idRoute INT REFERENCES route(id),
    idPrestation INT REFERENCES prestation(id),
    valuer double precision
);
INSERT INTO prixPrestationDegat ( idRoute, idPrestation, valuer) VALUES
    (2 , 1 , 85.00),
    (2 , 2 , 65.00),
    (2 , 3 , 75.00),
    (2 , 4 , 55.00),
    (1 , 1 , 50.00),
    (1 , 2 , 30.00),
    (1 , 3 , 40.00),
    (1 , 4 , 20.00);

CREATE TABLE echelleDegat (
    id SERIAL PRIMARY KEY,
    min INT CHECK (min >= 0 AND min <= 10),
    max INT CHECK (max >= 0 AND max <= 10),
    idprestation INT REFERENCES prestation(id)
);
insert into echelleDegat ( "min", "max", idprestation) values
      (0 , 0 , 1),
      (1 , 3 , 2),
      (4 , 7 , 3),
      (8 , 9 , 4),
      (10 , 10 , 5);









