/*******************************
 *
 * Application: IncrementAgeSetGet
 * Purpose:  Add Set and Get Methods - see PersonalInfoSetGet.java
 *
 * @author brash
 * Date:  January 5, 2020
 *
 *******************************/

public class IncrementAgeSetGet {

    public static void main(String [] args) {

        // Create an object of type PersonalInfoSetGet called Olivia
        // This object will have two member variables - age and name.
        //
        PersonalInfoSetGet Olivia = new PersonalInfoSetGet(25,"Olivia");

        System.out.println("Name, age = " + Olivia.GetName() + ", " + Olivia.GetAge());
        
        // Increment the age
        Olivia.IncrementAge();
        
        System.out.println("Name, age = " + Olivia.GetName() + ", " + Olivia.GetAge());

    }

}
