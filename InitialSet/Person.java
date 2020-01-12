/*********************
 * Code from filename: Person.java
 * @author brash
 * 
 * Abstract Person class
 * Defines name, age, and abstract study method
 * 
 * Student class
 * Inherits from Person class
 * Defines graduationYear, and body of the study method
 *
 */
abstract class Person {
  public String fname = "John";
  public int age = 24;
  public abstract void study(); // abstract method
}

class Student extends Person {
	  public int graduationYear = 2018;
	  public void study() { // the body of the abstract method is provided here
	    System.out.println("Studying all day long");
	  }
}