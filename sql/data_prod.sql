-- DATA_PROD.SQL

TRUNCATE TABLE bmobile.utilisateurs CASCADE ;
TRUNCATE TABLE bmobile.demandes CASCADE ;
TRUNCATE TABLE bmobile.annulations CASCADE ;
TRUNCATE TABLE bmobile.departements CASCADE ;
TRUNCATE TABLE bmobile.mobilites CASCADE ;
TRUNCATE TABLE bmobile.partenaires CASCADE ;
TRUNCATE TABLE bmobile.programmes CASCADE ;

INSERT INTO bmobile.utilisateurs (id_utilisateur, pseudo, mdp, nom, prenom, email, droits)
VALUES (1, 'kamil', 'kamil123', 'Arszagi vel Harszagi', 'Kamil', 'kamil.arszagi@student.vinci.be','ETUDIANT');

INSERT INTO bmobile.utilisateurs (id_utilisateur, pseudo, mdp, nom, prenom, email, droits)
VALUES (2, 'martin', 'martin123', 'Techy', 'Martin', 'martin.techy@student.vinci.be','ETUDIANT');

INSERT INTO bmobile.utilisateurs (pseudo, mdp, nom, prenom, email, droits)
VALUES ('prof', 'prof123', 'ProfNom', 'ProfPrenom', 'prof@vinci.be','PROF');