# SWE_Final
Library Management System

Description
The Library Management System is a Java application that allows users to manage a library of books and patrons. The system supports functionalities such as adding books and patrons, listing all books and patrons, borrowing books, and returning books.

Installation Instructions
1. Clone the repository:
   git clone https://github.com/yourusername/library-management-system.git

2. Navigate to the project directory:
   cd library-management-system

3. Download the MySQL JDBC driver:
   Download the MySQL Connector/J from the MySQL website: https://dev.mysql.com/downloads/connector/j/
   Place the mysql-connector-java-x.x.xx-bin.jar file in the lib directory.

4. Compile the project:
   javac -cp "lib/mysql-connector-java-x.x.xx-bin.jar:src/main/java" src/main/java/your/package/*.java

5. Run the project:
   java -cp "lib/mysql-connector-java-x.x.xx-bin.jar:src/main/java" your.package.LibraryManagementSystem

Database Setup
1. Connect to MySQL:
   mysql -u root -p

2. Create the project_db database:
   CREATE DATABASE project_db;
   USE project_db;

3. Create necessary tables:
   CREATE TABLE books (
       bookId VARCHAR(50) PRIMARY KEY,
       title VARCHAR(255) NOT NULL,
       author VARCHAR(255) NOT NULL,
       ISBN VARCHAR(20) NOT NULL,
       isBorrowed BOOLEAN NOT NULL DEFAULT FALSE
   );

   CREATE TABLE patrons (
       patronId VARCHAR(50) PRIMARY KEY,
       name VARCHAR(255) NOT NULL,
       contactInfo VARCHAR(255)
   );

   CREATE TABLE borrowed_books (
       bookId VARCHAR(50),
       patronId VARCHAR(50),
       borrowDate DATE,
       dueDate DATE,
       PRIMARY KEY (bookId, patronId),
       FOREIGN KEY (bookId) REFERENCES books(bookId),
       FOREIGN KEY (patronId) REFERENCES patrons(patronId)
   );

Usage Instructions
1. Start the application:
   Run the main class LibraryManagementSystem.

2. Choose an option from the menu:
   - 1: Add Book
   - 2: List All Books
   - 3: Add Patron
   - 4: List All Patrons
   - 5: Borrow Book
   - 6: Return Book
   - 7: Exit

3. Follow the prompts to perform the desired action.
