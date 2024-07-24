import java.sql.SQLException;  // This line is crucial to resolve SQLException
/*
public class LibraryManagementSystem {
    public static void main(String[] args) {
        try {
            Library library = new Library(); // Assuming the Library constructor may throw SQLException
            // You might want to add other operations related to the library here.
            System.out.println("Library system is running...");
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
*/
public class LibraryManagementSystem {
    public static void main(String[] args) {
        try {
            UserInterface ui = new UserInterface();
            ui.start();
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
