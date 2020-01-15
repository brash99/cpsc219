/***************************
 * Multiple inheritance example
 * 
 * @author brash
 *
 */


// First Parent class 
interface Parent1 { 
    default void fun() { 
        System.out.println("Parent1 fun()"); 
    } 
    
    default void parent1function() {
    	System.out.println("Parent 1 unique function");
    }
} 
  
// Second Parent Class 
interface Parent2 { 
    default void fun() { 
        System.out.println("Parent2 fun()"); 
    } 
    
    default void parent2function() {
    	System.out.println("Parent 2 unique function");
    }
} 

//
// The child class, MultipleInheritance,
// is inheriting from multiple parent classes
//
class MultipleInheritance implements Parent1, Parent2 {
	
   public void fun() {
	   //
	   // Since both parent classes have a fun() method
	   // we can call either one or both of them as the fun()
	   // method of our child class - we use the 'super'
	   // keyword to make the connection with the parent class
	   //
	   int ichoice = 1;
	   
	   if (ichoice == 1) {
		   Parent1.super.fun();
	   } else {
		   Parent2.super.fun();
	   }
   }
	
   public static void main(String args[]) { 
       MultipleInheritance t = new MultipleInheritance();
       t.fun();
       t.parent1function();
       t.parent2function();
   } 
} 