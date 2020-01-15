/*******************************
 *
 * Application: IncrementAgeSeparate
 * Purpose: Define a basic external class in an external
 * file (PersonalInfoBasic.java)
 * 
 * @author brash
 * Date:  January 5, 2020
 *
 *******************************/

public class IncrementAgeSeparate {

    public static void main(String [] args) {

        // Create an object of type PersonalInfoBasic called Olivia
        // This object will have two member variables - age and name.
        //
        // The PersonalInfo class is defined in a separate file, PersonalInfo.java
        //
        // The procedure now is as follows:
        //
        // 1.  javac PersonalInfoBasic.java   (This creates PersonalInfo.class)
        // 2.  javac IncrementAgeSeparate.java (This creates IncrementAgeSeparate.class)
        // 3.  java IncrementAgeSeparate (This runs the app)
        //
        PersonalInfoBasic Olivia = new PersonalInfoBasic();

        Olivia.age = 25;
        Olivia.name = "Olivia";

        System.out.println("Name, age = " + Olivia.name + ", " + Olivia.age);
        
        // Create a second object called Dad
        PersonalInfoBasic Dad = new PersonalInfoBasic();

        Dad.age = 53;
        Dad.name = "Dad";

        System.out.println("Name, age = " + Dad.name + ", " + Dad.age);

    }

}
