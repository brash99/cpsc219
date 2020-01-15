// First Parent class 
interface Parent1 { 
    default void fun() { 
        System.out.println("Parent1"); 
    } 
} 
  
// Second Parent Class 
interface Parent2 { 
    default void fun() { 
        System.out.println("Parent2"); 
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
   } 
} 