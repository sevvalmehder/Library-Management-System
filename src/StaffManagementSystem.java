import com.sun.org.apache.xpath.internal.SourceTree;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * StaffManagementSystem.java
 * @author Sevval MEHDER
 */

public class StaffManagementSystem extends AbstractManagementSystem {

    /**
     * Library staff list of the system
     */
    private ArrayList<LibraryStaff> libraryStaffOfTheSystem;

    /**
     * Constructor of the Staff Management System
     */
    public StaffManagementSystem() {
        libraryStaffOfTheSystem = new ArrayList<>();
    }

    /**
     * Execute the Staff Management System
     * @throws Exception, wrong input
     */

    public void exec() throws Exception{
        Scanner input = new Scanner(System.in);
        readFile();
        LibraryStaff currentStaff = loginToTheSystem();

        int choice = 0;
        try{
            while(choice != 5) {
                if (currentStaff.getUsername() != "nobody") {
                    showMenu(currentStaff);
                    System.out.println("Please make a choice");

                    choice = input.nextInt();
                    switch (choice) {
                        case 1:
                            addBook();
                            break;
                        case 2:
                            removeBook();
                            break;
                        case 3:
                            registerNewLibraryUser();
                            break;
                        case 4:
                            currentStaff.showUserInformation();
                            break;
                        case 5:
                            System.out.println("Log out...\nHave a nice day");
                            choice = 5;
                            break;
                        default:
                            System.out.println("This service is not available.\n");
                            break;
                    }
                }

            }
        }
        catch(InputMismatchException e) {
            throw new WrongInput();
        }
        writeToFile();
    }

    /**
     * Read csv file
     * @throws  Exception, file exception
     */
    @Override
    public void readFile() throws Exception {
        try {
            Scanner readBooks = new Scanner(new File("books.csv"));
            Scanner readUsers = new Scanner(new File("libraryUsers.csv"));
            Scanner readStaff = new Scanner(new File("libraryStaff.csv"));
            Scanner readRecords = new Scanner(new File("records.csv"));

            while (readBooks.hasNext()) {
                String line = readBooks.nextLine();
                String parse[] = line.split(",");
                Book temp = new Book(Integer.parseInt(parse[0]));
                temp.setAuthorName(parse[1]);
                temp.setBookName(parse[2]);
                temp.setBookCount(Integer.parseInt(parse[3]));

                booksOfTheSystem.add(temp);
                //System.out.println(readBooks.next());
            }
            while (readUsers.hasNext()) {
                String line = readUsers.nextLine();
                String parse[] = line.split(",");
                LibraryUser temp2 = new LibraryUser();
                temp2.setUsername(parse[0]);
                temp2.setPassword(parse[1]);
                temp2.setNumberOfReceivedBooks(Integer.parseInt(parse[2]));

                libraryUserOfTheSystem.add(temp2);
            }
            while (readStaff.hasNext() ) {
                String line = readStaff.nextLine();
                String parse[] = line.split(",");
                LibraryStaff temp3 = new LibraryStaff();
                temp3.setUsername(parse[0]);
                temp3.setPassword(parse[1]);

                libraryStaffOfTheSystem.add(temp3);
            }
            while (readRecords.hasNext()) {
                String line = readRecords.nextLine();
                String parse[] = line.split(",");
                for( LibraryUser user : getLibraryUserOfTheSystem() ) {
                    if (user.getUsername().equals(parse[0])) {
                        for (Book book : getBooksOfTheSystem()) {
                            if (book.getBookID().equals(parse[1])) {
                                user.bookThatUserHold.add(book);
                            }
                        }
                    }
                }
            }
        } catch(FileNotFoundException e) {
            throw new FileExceptions("The file is not found\n");
        }

    }

    /**
     * Library Management Login System
     * @return LibraryStaff, if the staff found return the staff otherwise return someone has "nobody" username
     */
    public LibraryStaff loginToTheSystem(){
        Scanner input = new Scanner(System.in);
        int wrongChance = 3;
        boolean isLogin = false;

        while(!isLogin && wrongChance > 0 ) {
            if(wrongChance != 3)
                System.out.println("Try again");

            System.out.println("Username: ");
            String username = input.nextLine();
            System.out.println("Password: ");
            String password = input.nextLine();
            for (LibraryStaff staff : getLibraryStaffOfTheSystem()) {
                if (staff.getUsername().equals(username)) {
                    if (staff.getPassword().equals(password)) {
                        isLogin = true;
                        return staff;
                    } else {
                        System.out.printf("Your password is wrong or ");
                        --wrongChance;
                    }
                }
            }
            System.out.println("this staff is not found.");
            --wrongChance;
        }
        return new LibraryStaff("nobody","nobody");
    }

    /**
     * Add Book operation
     */
    private void addBook() {
        Scanner input = new Scanner( System.in );
        String authorName, bookName;
        boolean found = false;
        System.out.println("Please give author name of the book will added: ");
        authorName = input.nextLine();
        System.out.println("Please give name of the book will added: ");
        bookName = input.nextLine();

        for( Book book : getBooksOfTheSystem() ){
            if(book.getAuthorName().equalsIgnoreCase(authorName)
                    && book.getBookName().equalsIgnoreCase(bookName)) {
                found = true;
                book.setBookCount(book.getBookCount() + 1);
                System.out.println("This book is already exist and you add one more for library");
            }
        }
        if(found == false) {
            booksOfTheSystem.add(new Book(authorName, bookName, 1, getBooksOfTheSystem().size()+1));
            System.out.println("Adding this book is succeed");
        }
    }

    /**
     * Remove Book operation
     */
    private void removeBook() {
        Scanner input = new Scanner(System.in);
        System.out.println("Give book ID for remove: ");
        Integer wanted = input.nextInt();
        Book bookForRemove= search(wanted);
        if ( bookForRemove.getBookID() == -1 ) {
            System.out.printf("There is no book you are looking for ID number %d \n", wanted);
        }
        else{
            System.out.printf("Do you want to remove all '%s' from '%s' has %d ID number?[Y/N]\n",
                                bookForRemove.getBookName(), bookForRemove.getAuthorName(), bookForRemove.getBookID() );
            String answer = input.next();
            if( answer.equalsIgnoreCase("y") ){
                getBooksOfTheSystem().remove(bookForRemove);
                System.out.printf("Removing %s is succeed\n",bookForRemove.getBookName() );
            }
        }
    }
    /**
     * Register operation to new library user
     */
    private void registerNewLibraryUser(){
        Scanner input = new Scanner( System.in );
        String username, password;
        System.out.println("Please give username for register new library user: ");
        username = input.nextLine();
        System.out.printf("Password for new user %s\n", username);
        password = input.nextLine();

        getLibraryUserOfTheSystem().add( new LibraryUser(username, password));

    }
    /**
     * Search book
     * @param search, the searching String, search in book name and author name
     * @return Book, the book has the given ID
     */
    protected Book search( Integer search){
        for( Book book: getBooksOfTheSystem()){
            if( book.getBookID().equals(search) ){
                return book;
            }
        }
        return new Book( -1 );
    }
    /**
     * Show the menu
     * @param staff, the user who did log in
     */
    @Override
    public void showMenu(AbstractUser staff){
        System.out.printf("Hello Library Staff %s, What do you want? \n", staff.getUsername() );
        System.out.println("1.Add New Book\n2.Remove a Book\n3.Register Library User\n4.Show my information\n5.Exit\n");

    }

    /**
     * Getter of the library staff list
     *
     * @return library ustaff list
     */
    public ArrayList<LibraryStaff> getLibraryStaffOfTheSystem() {
        return libraryStaffOfTheSystem;
    }

}
