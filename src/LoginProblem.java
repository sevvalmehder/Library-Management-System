/**
 * LoginProblem.java
 * Exception class
 * @author Sevval MEHDER
 */
public class LoginProblem extends Exception {
    /**
     * Override to string method
     */
    @Override
    public String toString() {
        return "You have 3 wrong account login. Did you forget your password?\n";
    }
}
