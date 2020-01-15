/***************************
 * Class inheritance example
 * @author brash
 *
 * 
 */

abstract class Animal {
  public int legs = 4;;
  public abstract void animalSound();  
}

class Pig extends Animal {
  public void animalSound() {
    System.out.println("The pig says: wee wee");
  }
}

class Dog extends Animal {
  public void animalSound() {
	  System.out.println("The dog says: woof woof");
  }
}

class Human extends Animal {
	public int legs = 2;
	public void animalSound() {
		System.out.println("The human says: wazzzupppp");
	}
}

class AnimalPolymorphism {
  public static void main(String[] args) {
    Pig myPig = new Pig();  // Create a Pig object
    Dog myDog = new Dog();  // Create a Dog object
    Human myHuman = new Human();  // Create a generic animal object
    myPig.animalSound();
    myDog.animalSound();
    myHuman.animalSound();
    System.out.println("Pigs have " + myPig.legs + " legs.");
    System.out.println("Humans have " + myHuman.legs + " legs.");
  }
}