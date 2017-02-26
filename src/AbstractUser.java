/**
 * AbstractUser.java
 * @author Sevval MEHDER
 */
public abstract class AbstractUser implements InterfaceUser {

    /**
     * Username of the user as String
     */
    public String username;

    /**
     * Password of the user as String
     */
    private String userPassword;

    /**
     * Constructor
     *
     * @param newUsername, name of the username as String
     * @param newUserPassword, user password as String
     */
    public AbstractUser(String newUsername, String newUserPassword) {
        this.username = newUsername;
        this.userPassword = newUserPassword;
    }

    /**
     * Constructor
     *  No parameter constructor
     */
    public AbstractUser() {}


    /**
     * Getter for the username
     * @return String, username as String
     */
    @Override
    public String getUsername(){ return username; }

    /**
     * Setter for the username
     * @param newUsername, username of the user as String
     */
    @Override
    public void setUsername(String newUsername){ username = newUsername;}

    /**
     * Getter for the password
     * @return String, username as String
     */
    @Override
    public String getPassword(){ return userPassword; }

    /**
     * Setter for the password
     * @param newPassword, password of the user as String
     */
    @Override
    public void setPassword(String newPassword){userPassword = newPassword; }

    /**
     * Abstract class that management system users prints their username and password
     * For Polymorphism
     */
    public abstract void showUserInformation();
}
