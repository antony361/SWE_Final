import static org.junit.jupiter.api.Assertions.*;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BookDAOTest {
    private BookDAO bookDAO;
    private PatronDAO patronDAO;

    @BeforeEach
    public void setUp() throws SQLException {
        bookDAO = new BookDAO();
        patronDAO = new PatronDAO();
        clearDatabase();
        insertTestData();
    }

    @AfterEach
    public void tearDown() throws SQLException {
        clearDatabase();
    }

    private void clearDatabase() throws SQLException {
        Statement stmt = bookDAO.getConnection().createStatement();
        stmt.executeUpdate("DELETE FROM borrowed_books");
        stmt.executeUpdate("DELETE FROM books");
        stmt.executeUpdate("DELETE FROM patrons");
        stmt.close();
    }

    private void insertTestData() throws SQLException {
        List<Patron> patrons = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            String patronId = String.valueOf(random.nextInt(10000));
            String name = "Patron " + i;
            String contactInfo = "contact" + i + "@example.com";
            patrons.add(new Patron(patronId, name, contactInfo));
        }
        patronDAO.addPatrons(patrons);

        List<Book> books = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            String bookId = String.valueOf(random.nextInt(10000));
            String title = "Title " + i;
            String author = "Author " + i;
            String ISBN = "ISBN" + random.nextInt(10000);
            books.add(new Book(bookId, title, author, ISBN));
        }
        bookDAO.addBooks(books);
    }

    @Test
    public void testAddBook() throws SQLException {
        String uniqueId = String.valueOf(System.currentTimeMillis());
        Book book = new Book(uniqueId, "1984", "George Orwell", "1234567890");
        bookDAO.addBook(book);
        List<Book> books = bookDAO.getAllBooks();
        assertTrue(books.stream().anyMatch(b -> b.getBookId().equals(uniqueId)));
    }

    @Test
    public void testGetAllBooks() throws SQLException {
        List<Book> books = bookDAO.getAllBooks();
        assertNotNull(books);
        assertFalse(books.isEmpty());
    }

    @Test
    public void testBorrowBook() throws SQLException {
        List<Book> books = bookDAO.getAllBooks();
        String bookId = books.get(0).getBookId();
        List<Patron> patrons = patronDAO.getAllPatrons();
        String patronId = patrons.get(0).getPatronId();
        bookDAO.borrowBook(bookId, patronId);
        books = bookDAO.getAllBooks();
        assertTrue(books.stream().anyMatch(b -> b.getBookId().equals(bookId) && b.isBorrowed()));
    }

    @Test
    public void testReturnBook() throws SQLException {
        List<Book> books = bookDAO.getAllBooks();
        String bookId = books.get(0).getBookId();
        List<Patron> patrons = patronDAO.getAllPatrons();
        String patronId = patrons.get(0).getPatronId();
        bookDAO.borrowBook(bookId, patronId);
        bookDAO.returnBook(bookId, patronId);
        books = bookDAO.getAllBooks();
        assertTrue(books.stream().anyMatch(b -> b.getBookId().equals(bookId) && !b.isBorrowed()));
    }
}