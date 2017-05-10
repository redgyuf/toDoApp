DROP DATABASE toDo;

CREATE SCHEMA `toDo` ;
USE `toDo`;

CREATE TABLE `Users` (
	userID INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	Email VARCHAR(50) NOT NULL,
	Password VARCHAR(30) NOT NULL
);

CREATE TABLE `Tasks` (
	taskID INT UNSIGNED AUTO_INCREMENT,
	Name LONGTEXT,
	Status varchar(100),
    userID INT UNSIGNED,
    primary key (taskID),
    foreign key (userID) references toDo.Users(userID)    
);

insert into toDo.Users (Email, Password) values ("admin@gmail.com", "admin");
insert into toDo.Tasks (Name, Status, userID) values ("Add a toDo to your list!","ACTIVE",1);
