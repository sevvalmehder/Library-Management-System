/**
 * LibraryStaff.java
 * @author Sevval MEHDER
 */
public class LibraryStaff extends AbstractUser {


    /**
     * Construcotr of the LibraryUSer class
     *
     * @param username, name of the username as String
     * @param userPassword, user password as String
     */
    public LibraryStaff( String username, String userPassword){
        super(username, userPassword);
    }

    /**
     * Override, implemented for polymorphism
     */
    @Override
    public void showUserInformation() {
        System.out.printf("Your username: %s\nYour password: %s\n",
                getUsername(), getPassword());
    }

    /**
     * No parameter constructor
     *
     */
    public LibraryStaff(){}

}
