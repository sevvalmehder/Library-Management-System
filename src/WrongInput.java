/**
 * WrongInput.java
 * Exception class
 * @author Sevval MEHDER
 */
public class WrongInput extends Exception {

    /**
     * Override toString method
     * @return String
     */
    @Override
    public String toString() {
        return "You must give an integer. Your input is not available.";
    }
}
