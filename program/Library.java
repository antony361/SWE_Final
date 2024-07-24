import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Library {
    private Connection conn;

    public Library() throws SQLException {
        // Change the database URL, username, and password as per your MySQL setup
        String url = "jdbc:mysql://localhost:3306/project_db";
        String user = "root";
        String password = "12345678";
        conn = DriverManager.getConnection(url, user, password);
        createTables();
    }

    private void createTables() throws SQLException {
        Statement stmt = conn.createStatement();
        String createBooksTable = "CREATE TABLE IF NOT EXISTS books (" +
                "bookId VARCHAR(255) PRIMARY KEY, " +
                "title VARCHAR(255), " +
                "author VARCHAR(255), " +
                "ISBN VARCHAR(255), " +
                "isBorrowed BOOLEAN)";
        stmt.execute(createBooksTable);

        String createPatronsTable = "CREATE TABLE IF NOT EXISTS patrons (" +
                "patronId VARCHAR(255) PRIMARY KEY, " +
                "name VARCHAR(255), " +
                "contactInfo VARCHAR(255))";
        stmt.execute(createPatronsTable);

        String createBorrowedBooksTable = "CREATE TABLE IF NOT EXISTS borrowed_books (" +
                "bookId VARCHAR(255), " +
                "patronId VARCHAR(255), " +
                "borrowDate DATE, " +
                "dueDate DATE, " +
                "FOREIGN KEY (bookId) REFERENCES books(bookId), " +
                "FOREIGN KEY (patronId) REFERENCES patrons(patronId))";
        stmt.execute(createBorrowedBooksTable);
        stmt.close();
    }

    // Other methods similar to SQLite implementation, adjusting SQL syntax as needed for MySQL
}
