-- INIT.SQL

CREATE SCHEMA bMobile;

CREATE TABLE bMobile.programs
(
  id SERIAL PRIMARY KEY,
  name VARCHAR(100),
  description TEXT,
  nr_ver INTEGER
);

CREATE TABLE bMobile.countries
(
  iso CHAR(2) PRIMARY KEY ,
  name_en VARCHAR(150),
  name_fr VARCHAR(150),
  nr_ver INTEGER,
  id_program INTEGER,

  CONSTRAINT foreign_key_program FOREIGN KEY (id_program) REFERENCES bMobile.programs (id)
);

CREATE TABLE bMobile.departments
(
  id SERIAL PRIMARY KEY,
  label VARCHAR(50),
  nr_ver INTEGER
);

CREATE TABLE bMobile.cancelations
(
  id SERIAL PRIMARY KEY,
  reason TEXT,
  responsible VARCHAR(20),
  nr_ver INTEGER
);

CREATE TABLE bMobile.users
(
  id SERIAL PRIMARY KEY,
  pseudo VARCHAR(100),
  password VARCHAR(250) ,
  name VARCHAR(100) ,
  firstname VARCHAR(100) ,
  email VARCHAR(150) ,
  registration_date TIMESTAMP ,
  permissions VARCHAR(20) ,
  birth_date TIMESTAMP,
  street VARCHAR(100),
  house_number VARCHAR(20),
  mailbox VARCHAR(20),
  zip VARCHAR(100),
  city VARCHAR(100),
  country CHAR(2),
  tel VARCHAR(20),
  gender VARCHAR(10),
  successfull_year_in_college INTEGER,
  iban VARCHAR(50),
  bic VARCHAR(15),
  account_holder VARCHAR(100),
  bank_name VARCHAR(100),
  nr_ver INTEGER,

  CONSTRAINT foreign_key_country FOREIGN KEY (country) REFERENCES bMobile.countries (iso)
);

CREATE TABLE bMobile.partners
(
  id SERIAL PRIMARY KEY,
  id_user INTEGER ,
  legal_name VARCHAR(100),
  business_name VARCHAR(100),
  full_name VARCHAR(100),
  department VARCHAR(100),
  type VARCHAR(10),
  nb_employees INTEGER,
  street VARCHAR(100),
  number VARCHAR(20),
  mailbox VARCHAR(20),
  zip VARCHAR(100),
  city VARCHAR(100),
  state VARCHAR(100),
  country CHAR(2),
  email VARCHAR(150),
  website VARCHAR(150),
  exists BOOLEAN,
  nr_ver INTEGER,

  CONSTRAINT foreign_key_user FOREIGN KEY (id_user) REFERENCES bMobile.users (id),
  CONSTRAINT foreign_key_country FOREIGN KEY (country) REFERENCES bMobile.countries (iso)
);

CREATE TABLE bMobile.mobilities
(
  id SERIAL PRIMARY KEY,
  id_student INTEGER ,
  id_program INTEGER ,
  id_partner INTEGER,
  type VARCHAR(10),
  preference_order INTEGER ,
  country CHAR(2),
  id_department INTEGER ,
  quadrimester INTEGER,
  status VARCHAR(50) ,
  canceled BOOLEAN ,
  departure_grant_contract BOOLEAN ,
  departure_convention_internship_schoolarship BOOLEAN ,
  departure_student_convention BOOLEAN,
  departure_erasmus_language_test BOOLEAN,
  departure_doc_aggreement BOOLEAN ,
  depart_doc_sent_highschool BOOLEAN ,
  software_proeco BOOLEAN ,
  software_mobility_tools BOOLEAN,
  software_mobi BOOLEAN,
  return_residence_cert BOOLEAN ,
  return_transcript BOOLEAN,
  return_internship_cert BOOLEAN,
  return_final_report BOOLEAN ,
  return_erasmus_language_test BOOLEAN,
  return_doc_sent_highschool BOOLEAN ,
  cancelation_reason INTEGER,
  academic_year VARCHAR(30),
  nr_ver INTEGER,

  CONSTRAINT foreign_key_student FOREIGN KEY (id_student) REFERENCES bMobile.users (id),
  CONSTRAINT foreign_key_program FOREIGN KEY (id_program) REFERENCES bMobile.programs (id),
  CONSTRAINT foreign_key_partner FOREIGN KEY (id_partner) REFERENCES bMobile.partners (id),
  CONSTRAINT foreign_key_country FOREIGN KEY (country) REFERENCES bMobile.countries (iso),
  CONSTRAINT foreign_key_department FOREIGN KEY (id_department) REFERENCES bMobile.departments (id),
  CONSTRAINT foreign_key_cancel FOREIGN KEY  (cancelation_reason) REFERENCES bMobile.cancelations (id)
);

CREATE TABLE bMobile.partners_departments
(
  id_partner INTEGER,
  id_department INTEGER,
  nr_ver INTEGER,

  CONSTRAINT pk_partner_department PRIMARY KEY (id_partner,id_department),
  CONSTRAINT foreign_key_partner FOREIGN KEY (id_partner) REFERENCES bMobile.partners (id),
  CONSTRAINT foreign_key_department FOREIGN KEY (id_department) REFERENCES bMobile.departments (id)


);

