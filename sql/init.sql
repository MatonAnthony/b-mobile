-- INIT.SQL

CREATE SCHEMA bMobile;

CREATE TABLE bMobile.programmes
(
  id_programme INTEGER PRIMARY KEY,
  nom_programme VARCHAR(100) NOT NULL,
  description TEXT
);

CREATE TABLE bMobile.departements
(
  id_departement INTEGER PRIMARY KEY,
  libelle VARCHAR(50)
);

CREATE TABLE bMobile.annulations
(
  id_annulation INTEGER PRIMARY KEY,
  motif TEXT NOT NULL,
  responsable VARCHAR(20)
);

CREATE TABLE bMobile.utilisateurs
(
  id_utilisateur INTEGER PRIMARY KEY,
  pseudo VARCHAR(100) NOT NULL,
  mdp VARCHAR(250) NOT NULL,
  sel VARCHAR(100) NOT NULL,
  nom VARCHAR(100) NOT NULL,
  prenom VARCHAR(100) NOT NULL,
  email VARCHAR(150) NOT NULL,
  date_inscription TIMESTAMP NOT NULL,
  droits VARCHAR(20) NOT NULL,
  date_naissance TIMESTAMP,
  rue VARCHAR(100),
  numero VARCHAR(20),
  boite VARCHAR(20),
  code_postal VARCHAR(100),
  ville VARCHAR(100),
  pays VARCHAR(100),
  tel VARCHAR(20),
  sexe VARCHAR(10),
  nb_annee_reussies_ens_sup INTEGER,
  banque_iban VARCHAR(50),
  banque_bic VARCHAR(15),
  banque_titulaire_nom VARCHAR(100),
  banque_nom VARCHAR(100)
);

CREATE TABLE bMobile.partenaires
(
  id_partenaire INTEGER PRIMARY KEY,
  id_utilisateur INTEGER NOT NULL,
  nom_affaires VARCHAR(100),
  nom_complet VARCHAR(100),
  departement VARCHAR(100),
  type VARCHAR(10),
  nb_employes INTEGER,
  rue VARCHAR(100),
  numero VARCHAR(20),
  boite VARCHAR(20),
  code_postal VARCHAR(100),
  ville VARCHAR(100),
  region VARCHAR(100),
  pays VARCHAR(100),
  email VARCHAR(150),
  site_web VARCHAR(150),

  CONSTRAINT foreign_key_utilisateur FOREIGN KEY (id_utilisateur) REFERENCES bMobile.utilisateurs (id_utilisateur)
);

CREATE TABLE bMobile.mobilites
(
  id_mobilite INTEGER PRIMARY KEY,
  id_programme INTEGER NOT NULL,
  type VARCHAR(10) NOT NULL,
  destination VARCHAR(100),
  partenaire INTEGER,

  CONSTRAINT foreign_key_programme FOREIGN KEY (id_programme) REFERENCES bMobile.programmes (id_programme),
  CONSTRAINT foreign_key_partenaire FOREIGN KEY (partenaire) REFERENCES bMobile.partenaires (id_partenaire)
);

CREATE TABLE bMobile.demandes
(
  id_demande INTEGER PRIMARY KEY,
  id_etudiant INTEGER NOT NULL,
  id_mobilite INTEGER NOT NULL,
  num_ordre_pref INTEGER NOT NULL,
  id_departement INTEGER NOT NULL,
  quadrimestre INTEGER,
  etat VARCHAR(50) NOT NULL,
  annulee BOOLEAN NOT NULL,
  depart_contrat_bourse BOOLEAN NOT NULL,
  depart_convention_stage_etudes BOOLEAN NOT NULL,
  depart_charte_etudiant BOOLEAN,
  depart_test_langue_erasmus BOOLEAN,
  depart_doc_engagement BOOLEAN NOT NULL,
  depart_doc_envoye_he BOOLEAN NOT NULL,
  logiciel_proeco BOOLEAN NOT NULL,
  logiciel_mobility_tools BOOLEAN,
  logiciel_mobi BOOLEAN,
  retour_att_sejour BOOLEAN NOT NULL,
  retour_releve_note BOOLEAN,
  retour_cert_stage BOOLEAN,
  retour_rapport_final BOOLEAN NOT NULL,
  retour_test_langue_erasmus BOOLEAN,
  retour_doc_envoye_he BOOLEAN NOT NULL,
  motif_annulation INTEGER,

  CONSTRAINT foreign_key_etudiant FOREIGN KEY (id_etudiant) REFERENCES bMobile.utilisateurs (id_utilisateur),
  CONSTRAINT foreign_key_mobilite FOREIGN KEY (id_mobilite) REFERENCES bMobile.mobilites (id_mobilite),
  CONSTRAINT foreign_key_departement FOREIGN KEY (id_departement) REFERENCES bMobile.departements (id_departement),
  CONSTRAINT foreign_key_annulation FOREIGN KEY  (motif_annulation) REFERENCES bMobile.annulations (id_annulation)
);
