/***************************
 * Abstract class example
 * @author brash
 *
 * See file Person.java for definition of Student class
 */

public class AbstractClassExample {
  public static void main(String[] args) {
    // create an object of the Student class (which inherits attributes and methods from Person)
    Student myStudent = new Student();

    System.out.println("Name: " + myStudent.fname);
    System.out.println("Age: " + myStudent.age);
    System.out.println("Graduation Year: " + myStudent.graduationYear);
    myStudent.study(); // call abstract method
  }
}
