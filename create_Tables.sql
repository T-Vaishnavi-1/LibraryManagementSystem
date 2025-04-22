
--for creating table storing all shelf details
CREATE TABLE shelf(shelfId INT PRIMARY KEY NOT NULL AUTO_INCREMENT,location VARCHAR(30),
capacity INT,shelfName VARCHAR(100),isEmpty TINYINT(1) DEFAULT 1,booksCount INT DEFAULT 1);

--for creating table for storing all users
CREATE TABLE user(userId INT AUTO_INCREMENT PRIMARY KEY NOT NULL,userName VARCHAR(30) UNIQUE,
password VARCHAR(30),borrowedBooks INT DEFAULT 0 );

---for creating table for storing books data
CREATE TABLE books(bookId INT PRIMARY KEY NOT NULL AUTO_INCREMENT,title VARCHAR(100),author VARCHAR(100),shelfId INT,
isAvailable TINYINT(1) DEFAULT 0,category VARCHAR(30),userId INT,FOREIGN KEY(shelfId) REFERENCES shelf(shelfId));

