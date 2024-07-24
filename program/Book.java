public class Book {
    private String bookId;
    private String title;
    private String author;
    private String ISBN;
    private boolean isBorrowed;

    public Book(String bookId, String title, String author, String ISBN) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.isBorrowed = false; // By default, the book is not borrowed
    }

    public String getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getISBN() {
        return ISBN;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public void setBorrowed(boolean borrowed) {
        isBorrowed = borrowed;
    }

    public String getDetails() {
        return "Book ID: " + bookId + ", Title: " + title + ", Author: " + author + ", ISBN: " + ISBN + ", Borrowed: " + isBorrowed;
    }
}
