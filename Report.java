import java.util.List;

public class Report {
    private List<Book> borrowedBooks;
    private List<Book> overdueBooks;

    public Report(List<Book> borrowedBooks, List<Book> overdueBooks) {
        this.borrowedBooks = borrowedBooks;
        this.overdueBooks = overdueBooks;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public List<Book> getOverdueBooks() {
        return overdueBooks;
    }
}
