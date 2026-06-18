-- Script SQL minimal pour initialiser une base de test

CREATE DATABASE IF NOT EXISTS lego_db;
USE lego_db;

-- Table themes
CREATE TABLE IF NOT EXISTS theme (
  numero VARCHAR(50) PRIMARY KEY,
  nom VARCHAR(255) NOT NULL,
  parent VARCHAR(50) NULL
);

-- Table boites
CREATE TABLE IF NOT EXISTS boite (
  numero VARCHAR(50) PRIMARY KEY,
  nom VARCHAR(255),
  annee INT,
  theme_numero VARCHAR(50),
  nb_piece INT,
  imgUrl VARCHAR(1024),
  FOREIGN KEY (theme_numero) REFERENCES theme(numero)
);

-- Table pieces
CREATE TABLE IF NOT EXISTS piece (
  numPiece VARCHAR(50) PRIMARY KEY,
  nomPiece VARCHAR(255),
  imgUrl VARCHAR(1024)
);

-- Table figurines
CREATE TABLE IF NOT EXISTS figurine (
  idFig VARCHAR(50) PRIMARY KEY,
  nomFig VARCHAR(255),
  nbParties INT,
  imgUrl VARCHAR(1024)
);

-- Table utilisateurs (simple)
CREATE TABLE IF NOT EXISTS utilisateur (
  idUtilisateur INT AUTO_INCREMENT PRIMARY KEY,
  login VARCHAR(100) UNIQUE,
  password VARCHAR(255),
  role VARCHAR(50)
);

-- Données de démonstration
INSERT IGNORE INTO theme (numero, nom) VALUES ('T001', 'Star Wars');
INSERT IGNORE INTO boite (numero, nom, annee, theme_numero, nb_piece, imgUrl) VALUES ('B001', 'Falcon', 2017, 'T001', 7541, NULL);
INSERT IGNORE INTO piece (numPiece, nomPiece) VALUES ('P001', 'Brick 2x4');
INSERT IGNORE INTO figurine (idFig, nomFig, nbParties) VALUES ('F001', 'Stormtrooper', 5);
INSERT IGNORE INTO utilisateur (login, password, role) VALUES ('student', 'password', 'student');
