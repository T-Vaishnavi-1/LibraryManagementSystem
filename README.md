# LibraryManagementSystem

Roll no: CS24B047 and CS24B048

  Group no:24


Library Management System - Java Swing + MySQL

This is a **Library Management System** built using **Java Swing** for the GUI and **MySQL** for data storage. It allows users to sign up, log in, add books, borrow/return books, and search the collection. It’s designed as a desktop application with a clean, functional interface.

---

##  Features

-  User Sign Up and Login  
-  Add Books to Library  
-  Search for Books  
-  Borrow and Return Books  
-  View Borrowed Books  
-  User Authentication  
-  MySQL Database Integration  

---

##  Technologies Used

- **Java Swing** – GUI components  
- **JDBC** – Database connectivity  
- **MySQL** – Backend database  

---

##  Folder Structure

. ├── addBook.java 
  ├── borrowBook.java 
  ├── returnBook.java 
  ├── Search.java 
  ├── Book.java 
  ├── DatabaseConn.java
  ├── LoginPage.java
  ├── SignUp.java
  ├── MainDb.java
  ├── WelcomePage.java
  |---create_Tables.java
  └── BorrowedBooks.java
  
---

##  Setup Instructions

### 1. Prerequisites

- Java JDK (17 or compatible)
- VS Code 
- MySQL Server  
- JDBC  

### 2.  MySQL Database Setup

1. Create a database:

2.For creating tables,data is mentioned in create_Tables.sql file.

3. Update DatabaseConn.java with your MySQL credentials:

   Connection conn =  DriverManager.getConnection("jdbc:mysql://localhost:3306/library_db", "root", "your_password");

-----
▶ Running the App
Compile the Java files:

javac *.java
Run the main file (Library.java):

java Library(main class)

----------
 * Notes
All data is stored and retrieved from MySQL using JDBC.

This project is intended for learning core Java, OOP concepts, and simple database integration.

No external frameworks are used—keeping it purely Java Swing and JDBC.
