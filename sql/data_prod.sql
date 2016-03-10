-- DATA_PROD.SQL

TRUNCATE TABLE bmobile.users CASCADE ;
TRUNCATE TABLE bmobile.requests CASCADE ;
TRUNCATE TABLE bmobile.cancelations CASCADE ;
TRUNCATE TABLE bmobile.departments CASCADE ;
TRUNCATE TABLE bmobile.mobilities CASCADE ;
TRUNCATE TABLE bmobile.partners CASCADE ;
TRUNCATE TABLE bmobile.programs CASCADE ;

INSERT INTO bmobile.users (id, pseudo, password, name, firstname, email, permissions)
VALUES (DEFAULT, 'kamil', 'kamil123', 'Arszagi vel Harszagi', 'Kamil', 'kamil.arszagi@student.vinci.be','ETUDIANT');

INSERT INTO bmobile.users (id, pseudo, password, name, firstname, email, permissions)
VALUES (DEFAULT, 'martin', 'martin123', 'Techy', 'Martin', 'martin.techy@student.vinci.be','ETUDIANT');

INSERT INTO bmobile.users (id, pseudo, password, name, firstname, email, permissions)
VALUES (DEFAULT, 'prof', 'prof123', 'ProfNom', 'ProfPrenom', 'prof@vinci.be','PROF');
