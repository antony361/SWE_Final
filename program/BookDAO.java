import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    private Connection conn;

    public BookDAO() throws SQLException {
        conn = DatabaseConnection.getConnection();
    }

    public Connection getConnection() {
        return conn;
    }

    public void addBook(Book book) throws SQLException {
        String sql = "INSERT INTO books (bookId, title, author, ISBN, isBorrowed) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, book.getBookId());
        pstmt.setString(2, book.getTitle());
        pstmt.setString(3, book.getAuthor());
        pstmt.setString(4, book.getISBN());
        pstmt.setBoolean(5, book.isBorrowed());
        pstmt.executeUpdate();
        pstmt.close();
    }

    public List<Book> getAllBooks() throws SQLException {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            Book book = new Book(rs.getString("bookId"), rs.getString("title"), rs.getString("author"), rs.getString("ISBN"));
            book.setBorrowed(rs.getBoolean("isBorrowed"));
            books.add(book);
        }
        rs.close();
        stmt.close();
        return books;
    }

    public void borrowBook(String bookId, String patronId) throws SQLException {
        String sql = "UPDATE books SET isBorrowed = TRUE WHERE bookId = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, bookId);
        pstmt.executeUpdate();
        pstmt.close();

        sql = "INSERT INTO borrowed_books (bookId, patronId, borrowDate, dueDate) VALUES (?, ?, NOW(), DATE_ADD(NOW(), INTERVAL 14 DAY))";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, bookId);
        pstmt.setString(2, patronId);
        pstmt.executeUpdate();
        pstmt.close();
    }

    public void returnBook(String bookId, String patronId) throws SQLException {
        String sql = "UPDATE books SET isBorrowed = FALSE WHERE bookId = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, bookId);
        pstmt.executeUpdate();
        pstmt.close();

        sql = "DELETE FROM borrowed_books WHERE bookId = ? AND patronId = ?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, bookId);
        pstmt.setString(2, patronId);
        pstmt.executeUpdate();
        pstmt.close();
    }

    public void addBooks(List<Book> books) throws SQLException {
        for (Book book : books) {
            addBook(book);
        }
    }
}