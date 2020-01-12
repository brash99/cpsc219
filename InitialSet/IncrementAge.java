/*******************************
 *
 * Application: IncrementAge
 * Purpose:
 * 
 * Demonstrate Set and Get Methods of the PersonalInfo class
 *
 * Author:  E.J. Brash
 * Date:  January 5, 2020
 *
 *******************************/

public class IncrementAge {

    public static void main(String [] args) {

        // Create an object of type PersonalInfo called Olivia
        // This object will have two member variables - age and name.
        //
        PersonalInfo Olivia = new PersonalInfo(25,"Olivia");

        System.out.println("Name, age = " + Olivia.GetName() + ", " + Olivia.GetAge());
        
        // Increment the age
        Olivia.IncrementAge();
        
        System.out.println("Name, age = " + Olivia.GetName() + ", " + Olivia.GetAge());
        
        // Set a new name and age
        Olivia.SetName("Dad");
        Olivia.SetAge(53);
        
        System.out.println("Name, age = " + Olivia.GetName() + ", " + Olivia.GetAge());

        //Olivia.age = 25;  // Can't do this!!! age is private member

    }

}
