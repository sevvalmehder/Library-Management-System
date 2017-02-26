/**
 * FileExceptions.java
 * This exception class can handle two different exception
 * @author Sevval MEHDER
 */
public class FileExceptions extends Exception{

    /**
     * The message that users can this and see their mistakes
     */
    private String message;

    /**
     * Constructor
     * @param message, give information about the type of exception
     */
    public FileExceptions(String message){
        this.message = message;
    }

    /**
     * Override toString method
     */
    @Override
    public String toString() {
        return message;
    }
}
