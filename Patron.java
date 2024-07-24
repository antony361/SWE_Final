import java.util.ArrayList;
import java.util.List;

public class Patron {
    private String patronId;
    private String name;
    private String contactInfo;
    private List<Book> borrowedBooks;

    public Patron(String patronId, String name, String contactInfo) {
        this.patronId = patronId;
        this.name = name;
        this.contactInfo = contactInfo;
        this.borrowedBooks = new ArrayList<>();
    }

    public String getPatronId() {
        return patronId;
    }

    public String getName() {
        return name;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void borrowBook(Book book) {
        borrowedBooks.add(book);
        book.setBorrowed(true);
    }

    public void returnBook(Book book) {
        borrowedBooks.remove(book);
        book.setBorrowed(false);
    }

    public String getDetails() {
        return "Patron ID: " + patronId + ", Name: " + name + ", Contact Info: " + contactInfo + ", Borrowed Books: " + borrowedBooks;
    }
}
