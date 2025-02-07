CREATE DATABASE HW3;
USE HW3;

-- Question 1
CREATE TABLE Departments(name VARCHAR(4), campus VARCHAR(5), PRIMARY KEY(name));
CREATE TABLE Students(first_name VARCHAR(30), last_name VARCHAR(30), id INT, PRIMARY KEY(id));
CREATE TABLE Classes(name VARCHAR(50), credits INT, PRIMARY KEY(name));
CREATE TABLE Majors(sid INT, dname VARCHAR(4), FOREIGN KEY(sid) REFERENCES Students(id), FOREIGN KEY(dname) REFERENCES Departments(name));
CREATE TABLE Minors(sid INT, dname VARCHAR(4), FOREIGN KEY(sid) REFERENCES Students(id), FOREIGN KEY(dname) REFERENCES Departments(name));
CREATE TABLE IsTaking(sid INT, name VARCHAR(50), FOREIGN KEY(sid) REFERENCES Students(id), FOREIGN KEY(name) REFERENCES Classes(name));
CREATE TABLE HasTaken(sid INT, name VARCHAR(50), grade CHAR(1), FOREIGN KEY(sid) REFERENCES Students(id), FOREIGN KEY(name) REFERENCES Classes(name));

-- Question 2
INSERT INTO Departments VALUES('Bio','Busch');
INSERT INTO Departments VALUES('Chem','CAC');
INSERT INTO Departments VALUES('CS','Livi');
INSERT INTO Departments VALUES('Eng','CD');
INSERT INTO Departments VALUES('Math','Busch');
INSERT INTO Departments VALUES('Phys','CAC');
