import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * AbstractManagementSystem.java
 * @author Sevval MEHDER
 *
 */

public abstract class AbstractManagementSystem implements LibraryManagementSystem{
    /**
     * Book list of the system
     */
    protected ArrayList<Book> booksOfTheSystem;
    /**
     * Library User list of the system
     */
    protected ArrayList<LibraryUser> libraryUserOfTheSystem;

    /**
     * Constructor of the Abstract Management System
     */
    public AbstractManagementSystem() {
        booksOfTheSystem = new ArrayList<>();
        libraryUserOfTheSystem = new ArrayList<>();
    }

    /**
     * Execute the system
     * @throws Exception, wrong input
     */

    public void execute() throws Exception{
        System.out.println("Welcome to library management system.\n 1. Login as library user \n " +
                            "2. Login as library staff\n 3.Exit");
        Scanner input = new Scanner(System.in);
        Integer choice = 0;
        try{
            while(choice != 3){

                choice = input.nextInt();

                switch (choice) {
                    case 1:
                        UserManagementSystem userSystem = new UserManagementSystem();
                        userSystem.exec();
                        choice = 3;
                        break;
                    case 2:
                        StaffManagementSystem staffSystem = new StaffManagementSystem();
                        staffSystem.exec();
                        choice = 3;
                        break;
                    case 3:
                        System.out.println("Have a nice day\n");
                        choice = 3;
                        break;
                    default:
                        System.out.println("This service is not available.\n 1. Login as library user \n " +
                                            "2. Login as library staff\n 3.Exit");
                        break;
                }

            }
        }
        catch(InputMismatchException e) {
            throw new WrongInput();
        }
    }


    /**
     * Write to file
     * @throws Exception, file exceptions
     */
    public void writeToFile() throws Exception{
        try {
            FileWriter file = new FileWriter("books.csv", false);
            FileWriter file2 = new FileWriter("libraryUsers.csv", false);
            FileWriter file3 = new FileWriter("records.csv", false);

            PrintWriter writer = new PrintWriter(file);
            PrintWriter writer2 = new PrintWriter(file2);
            PrintWriter writer3 = new PrintWriter(file3);

            for(Book book : getBooksOfTheSystem() ){
                writer.printf("%d,%s,%s,%d\n", book.getBookID(), book.getAuthorName(),
                                                book.getBookName(), book.getBookCount() );
            }
            for(LibraryUser user: getLibraryUserOfTheSystem() ){
                writer2.printf("%s,%s,%d\n", user.getUsername(), user.getPassword(), user.numberOfReceivedBooks );
            }
            writer.close();
            writer2.close();

            for(LibraryUser user: getLibraryUserOfTheSystem() ){
                for(Book book : user.getBookThatUserHolds())
                    writer3.printf("%s,%d\n", user.getUsername(), book.getBookID() );
            }

            writer3.close();
        }
        catch(FileNotFoundException e) {
            throw new FileExceptions("The file is not found\n");
        }
        catch(IOException e) {
            throw new FileExceptions("The file cannot open\n");
        }
    }


    /**
     * Show the menu
     * Polymorphism
     * @param user, user who did log in
     */
    public abstract void showMenu( AbstractUser user);

    /**
     * Getter of the book list
     *
     * @return book list
     */
    @Override
    public ArrayList<Book> getBooksOfTheSystem() {
        return booksOfTheSystem;
    }

    /**
     * Getter of the library user list
     *
     * @return library user list
     */
    @Override
    public ArrayList<LibraryUser> getLibraryUserOfTheSystem() {
        return libraryUserOfTheSystem;
    }

    /**
     * Library Management Login System
     * @return AbstractUser, if the user found return user otherwise return someone has "nobody" username
     */
    public abstract AbstractUser loginToTheSystem();
    /**
     * Read File
     * @throws Exception
     * Polymorphism
     */
    public abstract void readFile() throws Exception;
}
