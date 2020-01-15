/*******************************
 *
 * Application: IncrementAgeNonClass 
 * Purpose: Demonstrate the problems with pass by value vs. pass by reference
 * 
 * Note: When we learn about objects and classes, this will come up again, and there
 *       is a really cool way to make our life easier!!!
 *
 * @author brash
 * Date:  January 5, 2020
 *
 *******************************/

public class IncrementAgeNonClass {

    static void PlusAgeUseless(int age) {
        age = age + 1;
    }
    
    static int PlusAge(int age) {
        age = age + 1;
        return age;
    }

    public static void main(String [] args) {

        int age = 25;
        System.out.println("Current age = " + age);

        PlusAgeUseless(age);
        System.out.println("New age = " + age);
        
        age = PlusAge(age);
        System.out.println("New age = " + age);
    
    }

}
