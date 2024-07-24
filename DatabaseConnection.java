import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/project_db";
    private static final String USER = "root";
    private static final String PASSWORD = "12345678"; // Replace with your actual password

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC Driver not found", e);
        }
    }

    public static void main(String[] args) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            System.out.println("Connection successful!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
