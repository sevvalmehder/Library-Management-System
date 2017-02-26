/**
 * Book.java
 * @author Sevval MEHDER
 */
public class Book {

    /**
     * The unique ID for each book as Integer
     */
    public Integer bookID;

    /**
     * Name of the book as String
     */
    public String bookName;

    /**
     * Name of the book's author as String
     */
    public String authorName;

    /**
     * The number of off the shelf books
     */
    public Integer bookCount;

    /**
     * Constructor
     *
     * @param bookName, name of the book as String
     * @param authorName, name of the author as String
     * @param bookCount, number of off the shelf book as Integer
     * @param bookID, number of book ID
     */
    public Book(String authorName, String bookName, Integer bookCount, Integer bookID) {
        this.bookID = bookID;
        this.bookName = bookName;
        this.authorName = authorName;
        this.bookCount = bookCount;

    }

    /**
     * Constructor
     * This will use just a temporary Book variable
     * @param bookID, as Integer
     */
    public Book(Integer bookID) {
        this.bookID = bookID;
    }

    /**
     * Getter of the book ID
     *
     * @return book ID as a Integer
     */
    public Integer getBookID() {
        return bookID;
    }


    /**
     * Getter of the book name
     *
     * @return String, book name as a String
     */
    public String getBookName() {
        return bookName;
    }

    /**
     * Setter of the book name
     *
     * @param bookName book name as a String
     */
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    /**
     * Getter of the author name
     *
     * @return author name as a String
     */
    public String getAuthorName() {
        return authorName;
    }

    /**
     * Setter of the author name
     *
     * @param authorName, author name as a String
     */
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    /**
     * Getter of the count of book
     *
     * @return book count as a Integer
     */
    public Integer getBookCount() {
        return bookCount;
    }

    /**
     * Setter of the count of book
     *
     * @param bookCount, book count as a Integer
     */
    public void setBookCount(Integer bookCount) {
        this.bookCount = bookCount;
    }

    /**
     * Override toString
     *
     * @return String representation
     */
    @Override
    public String toString() {
        return "Book ID = " + bookID + " --> " + authorName + " , " + bookName;
    }




}