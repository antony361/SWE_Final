import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private Scanner scanner;
    private BookDAO bookDAO;
    private PatronDAO patronDAO;

    public UserInterface() throws SQLException {
        scanner = new Scanner(System.in);
        bookDAO = new BookDAO();
        patronDAO = new PatronDAO();
    }

    public void start() {
        boolean running = true;
        while (running) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Add Book");
            System.out.println("2. List All Books");
            System.out.println("3. Add Patron");
            System.out.println("4. List All Patrons");
            System.out.println("5. Borrow Book");
            System.out.println("6. Return Book");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline left-over

            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    listAllBooks();
                    break;
                case 3:
                    addPatron();
                    break;
                case 4:
                    listAllPatrons();
                    break;
                case 5:
                    borrowBook();
                    break;
                case 6:
                    returnBook();
                    break;
                case 7:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
        scanner.close();
    }

    private void addBook() {
        System.out.print("Enter Book ID: ");
        String bookId = scanner.nextLine();
        System.out.print("Enter Title: ");
        String title = scanner.nextLine();
        System.out.print("Enter Author: ");
        String author = scanner.nextLine();
        System.out.print("Enter ISBN: ");
        String ISBN = scanner.nextLine();
        Book book = new Book(bookId, title, author, ISBN);
        try {
            bookDAO.addBook(book);
            System.out.println("Book added successfully.");
        } catch (SQLException e) {
            System.out.println("Error adding book: " + e.getMessage());
        }
    }

    private void listAllBooks() {
        try {
            List<Book> books = bookDAO.getAllBooks();
            for (Book book : books) {
                System.out.println(book.getDetails());
            }
        } catch (SQLException e) {
            System.out.println("Error listing books: " + e.getMessage());
        }
    }

    private void addPatron() {
        System.out.print("Enter Patron ID: ");
        String patronId = scanner.nextLine();
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Contact Info: ");
        String contactInfo = scanner.nextLine();
        Patron patron = new Patron(patronId, name, contactInfo);
        try {
            patronDAO.addPatron(patron);
            System.out.println("Patron added successfully.");
        } catch (SQLException e) {
            System.out.println("Error adding patron: " + e.getMessage());
        }
    }

    private void listAllPatrons() {
        try {
            List<Patron> patrons = patronDAO.getAllPatrons();
            for (Patron patron : patrons) {
                System.out.println(patron.getDetails());
            }
        } catch (SQLException e) {
            System.out.println("Error listing patrons: " + e.getMessage());
        }
    }

    private void borrowBook() {
        System.out.print("Enter Book ID: ");
        String bookId = scanner.nextLine();
        System.out.print("Enter Patron ID: ");
        String patronId = scanner.nextLine();
        try {
            bookDAO.borrowBook(bookId, patronId);
            System.out.println("Book borrowed successfully.");
        } catch (SQLException e) {
            System.out.println("Error borrowing book: " + e.getMessage());
        }
    }

    private void returnBook() {
        System.out.print("Enter Book ID: ");
        String bookId = scanner.nextLine();
        System.out.print("Enter Patron ID: ");
        String patronId = scanner.nextLine();
        try {
            bookDAO.returnBook(bookId, patronId);
            System.out.println("Book returned successfully.");
        } catch (SQLException e) {
            System.out.println("Error returning book: " + e.getMessage());
        }
    }
}
