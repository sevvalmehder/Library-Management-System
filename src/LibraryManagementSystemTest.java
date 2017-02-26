/**
 * LibraryManagementSystemTest.java
 * @author Sevval MEHDER
 */
public class LibraryManagementSystemTest {
    public static void main(String[] args){
        try{
            AbstractManagementSystem system = new UserManagementSystem();
            system.execute();
        }
        catch(Exception e){
            System.out.println("Exception caught: " + e);
        }
    }

}
