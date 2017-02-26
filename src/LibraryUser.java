import java.util.ArrayList;
/**
 * LibraryUser.java
 * @author Sevval MEHDER
 */
public class LibraryUser extends AbstractUser {

    /**
     * Number of the received books as Integer
     */
    public Integer numberOfReceivedBooks;


    /**
     * Books list that library user currently hold
     */
    public ArrayList<Book> bookThatUserHold;

    /**
     * The limits of books that library user take as Integer
     */
    private Integer booksLimit;

    /**
     * Constructor of the LibraryUser class
     *
     * @param username, name of the username as String
     * @param userPassword, user password as String
     * @param numberOfReceivedBooks, number of the received book that user have as Integer
    */
    public LibraryUser( String username, String userPassword, Integer numberOfReceivedBooks){
        super(username, userPassword);
        this.numberOfReceivedBooks = numberOfReceivedBooks;
        this.booksLimit = 3;
        bookThatUserHold = new ArrayList<>();
    }

    /**
     * Constructor of the LibraryUser class
     *
     * @param username, name of the username as String
     * @param userPassword, user password as String
     */
    public LibraryUser( String username, String userPassword){
        super(username, userPassword);
        this.numberOfReceivedBooks = 0;
        this.booksLimit = 3;
        bookThatUserHold = new ArrayList<>();
    }

    /**
     * Constructor of the LibraryUser class
     *
     */
    public LibraryUser(){
        this.booksLimit = 3;
        bookThatUserHold = new ArrayList<>();
    }
    /**
     * Add book to library user
     * @param newBook, book
     * @return boolean
     */


    /**
     * Getter for the number of the received books
     * @return Integer, number of the received books as Integer
     */

    public Integer getNumberOfReceivedBooks(){ return numberOfReceivedBooks; }

    /**
     * Setter for the numberOfReceivedBooks
     * @param numberOfReceivedBooks, number of the received books as Integer
     */
    public void setNumberOfReceivedBooks(Integer numberOfReceivedBooks){ this.numberOfReceivedBooks = numberOfReceivedBooks;}

    /**
     * Getter of the books list that library user holds
     *
     * @return book list
     */
    public ArrayList<Book> getBookThatUserHolds() {
        return bookThatUserHold;
    }

    /**
     * Override, implemented for polymorphism
     */
    @Override
    public void showUserInformation() {
        System.out.printf("Your username: %s\nYour password: %s\nYou have currently %d books.\n",
                            getUsername(), getPassword(), getNumberOfReceivedBooks());
    }

}
