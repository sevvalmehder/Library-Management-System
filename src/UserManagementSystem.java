import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;

/**
 * UserManagementSystem.java
 * @author Sevval MEHDER
 */
public class UserManagementSystem extends AbstractManagementSystem {



    /**
     * Execute the User Management System
     * @throws Exception , wrong input
     */

    public void exec() throws Exception {
        Scanner input = new Scanner(System.in);
        readFile();
        LibraryUser currentUser = loginToTheSystem();

        int choice = 0;
        try{
            while(choice != 5) {
                if (currentUser.getUsername() != "nobody") {
                    showMenu(currentUser);
                    System.out.println("Please make a choice");

                    choice = input.nextInt();
                    switch (choice) {
                        case 1:
                            borrowBook(currentUser);
                            break;
                        case 2:
                            returnBook(currentUser);
                            break;
                        case 3:
                            showBorrowedBook(currentUser);
                            break;
                        case 4:
                            currentUser.showUserInformation();
                            break;
                        case 5:
                            System.out.println("Loging out...\nHave a nice day");
                            choice = 5;
                            break;
                        default:
                            System.out.println("This service is not available.\n");
                            break;
                    }
                }
                else {
                    throw new LoginProblem();
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
     * @throws Exception, file exception
     */
    @Override
    public void readFile() throws Exception {
        try {
            Scanner readBooks = new Scanner(new File("books.csv"));
            Scanner readUsers = new Scanner(new File("libraryUsers.csv"));
            Scanner readRecords = new Scanner(new File("records.csv"));
            while (readBooks.hasNext()) {
                String line = readBooks.nextLine();
                String parse[] = line.split(",");
                Book temp = new Book(Integer.parseInt(parse[0]));
                temp.setAuthorName(parse[1]);
                temp.setBookName(parse[2]);
                temp.setBookCount(Integer.parseInt(parse[3]));

                booksOfTheSystem.add(temp);
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
            while (readRecords.hasNext()) {
                String line = readRecords.nextLine();
                String parse[] = line.split(",");
                for( LibraryUser user : getLibraryUserOfTheSystem() ) {
                    if (user.getUsername().equals(parse[0])) {
                        for (Book book : getBooksOfTheSystem()) {
                            if (book.getBookID() == Integer.parseInt( parse[1] )) {
                                user.bookThatUserHold.add(book);
                            }
                        }
                    }
                }
            }
        }catch(FileNotFoundException e) {
            throw new FileExceptions("The file is not found\n");
        }

    }

    /**
     * Library Management Login System
     * @return LibraryUser, if the user found return user otherwise return someone has "nobody" username
     */
    @Override
    public LibraryUser loginToTheSystem(){
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
            for (LibraryUser user : getLibraryUserOfTheSystem()) {
                if (user.getUsername().equals(username)) {
                    if (user.getPassword().equals(password)) {
                        isLogin = true;
                        return user;
                    } else {
                        System.out.printf("Your password is wrong or ");
                        --wrongChance;
                    }
                }
            }
            System.out.println("this user is not found.");
            --wrongChance;
        }
        return new LibraryUser("nobody","nobody");
    }
    /**
     * Show the menu
     * @param user, the user who did log in
     */
    @Override
    public void showMenu(AbstractUser user){
        System.out.printf("User %s, What do you want? \n", user.getUsername() );
        System.out.println("1.Borrow Book\n2.Return Book\n3.Show borrowed books\n4.Show my information\n5.Exit");

    }

    /**
     * Show the books that user hold
     * @param currentUser, the library user who did login
     */
    private void showBorrowedBook(LibraryUser currentUser){
        for( Book book : currentUser.getBookThatUserHolds()){
            System.out.printf("Book ID: %d, %s, %s\n", book.getBookID(), book.getAuthorName(),
                    book.getBookName());
        }
        if( currentUser.getNumberOfReceivedBooks() == 0)
            System.out.println("There is no borrowed book");
    }
    /**
     * Borrow Book operation
     * @param currentUser, the user who did login
     * @return boolean, the operation is complated or not
     */
    private boolean borrowBook( LibraryUser currentUser) {
        Scanner input = new Scanner(System.in);
        System.out.println("Search book or author name: ");
        String wanted = input.nextLine();
        int count = search(wanted);
        if (count == 0) {
            System.out.printf("There is no book you are looking for '%s' \n", wanted);
        }
        if (count > 0) {

            System.out.printf("%d book found. Please write ID the book you want to take: ", count);
            Integer bookId = input.nextInt();
            for (Book book : getBooksOfTheSystem()) {
                if (book.getBookID().equals(bookId)) {
                    if(book.getBookCount() != 0 ){
                        if(currentUser.getNumberOfReceivedBooks() <3 ){
                            currentUser.bookThatUserHold.add(book);
                            currentUser.setNumberOfReceivedBooks(currentUser.getNumberOfReceivedBooks() + 1);
                            book.setBookCount( book.getBookCount() - 1);
                            System.out.printf("%s added your received books\n\n", wanted);
                            return true;
                        }
                        else
                            System.out.println("You have already 3 books. You cant borrow a new book");
                    }
                    else
                        System.out.println("This book is not on shelf");
                }
                else
                    System.out.printf("There is no book with %d ID number\n", bookId);
            }
        }
        return false;
    }

    /**
     * Return the received book
     * @param user, the current user
     */
    private boolean returnBook(LibraryUser user){
        for( Book book : user.getBookThatUserHolds()){
            System.out.printf("Book ID: %d, %s, %s\n", book.getBookID(), book.getAuthorName(),
                    book.getBookName());
        }
        Scanner input = new Scanner(System.in);
        System.out.println("Which book you want to return. Give it ID: ");
        int bookId = input.nextInt();
        for (Book book : user.getBookThatUserHolds()) {
            if (book.getBookID().equals(bookId)) {
                user.bookThatUserHold.remove(book);
                user.setNumberOfReceivedBooks(user.getNumberOfReceivedBooks() - 1);
                book.setBookCount( book.getBookCount() + 1);
                System.out.printf("%s is returned to library\n\n", book.getBookName());
                return true;
            }
            else
                System.out.printf("There is no book %d ID number in your received books", bookId);
        }
        return false;
    }

    /**
     * Search book
     * @param search, the searching String, search in book name and author name
     * @return int the count of search
     */
    protected int search( String search){
        int count = 0;
        for( Book book: getBooksOfTheSystem() ){
            if(book.getBookName().equalsIgnoreCase(search) || book.getAuthorName().equalsIgnoreCase(search) ){
                System.out.printf("Book ID: %d, %s, %s, %d tane \n", book.getBookID(), book.getAuthorName(),
                        book.getBookName(), book.getBookCount());
                count++;
            }
        }
        return count;
    }
}
