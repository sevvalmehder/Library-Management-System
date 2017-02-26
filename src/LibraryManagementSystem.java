import java.util.ArrayList;
/**
 * LibraryManagementSystem.java
 * @author Sevval MEHDER
 */
public interface LibraryManagementSystem {
    /**
     * Getter of the book list
     *
     * @return book list
     */
    public ArrayList<Book> getBooksOfTheSystem();

    /**
     * Getter of the library user list
     *
     * @return library user list
     */
    public ArrayList<LibraryUser> getLibraryUserOfTheSystem();
}
