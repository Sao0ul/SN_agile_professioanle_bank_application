-- Crée la base si elle n'existe pas
CREATE DATABASE IF NOT EXISTS bankdb;
USE bankdb;

-- Table des comptes
CREATE TABLE IF NOT EXISTS accounts (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    balance INT DEFAULT 0
);

-- Ajout de quelques comptes
INSERT INTO accounts (name, password, balance) VALUES ('Alice', 'password123', 1000);
INSERT INTO accounts (name, password, balance) VALUES ('Bob', 'securepass', 1500);
INSERT INTO accounts (name, password, balance) VALUES ('Charlie', 'mypassword', 2000);

/*
 donc pour utiliser ce cote backen il faut 
-lancer le serveur mysql avec 
    mysqld.exe --console 
    dans le terminal
-lancer le script init.sql avec 
    mysql -u root -p <C:\Users\Raoul\Desktop\javaFX\ProjetsAppJavaFx\BankSystem\src\main\resources\sql\init.sql
    dans le terminal
*/