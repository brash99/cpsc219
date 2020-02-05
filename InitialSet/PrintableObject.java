public class PrintableObject {
	int age;
	double income;
	String name;
	
	public PrintableObject() {
		age = 0;
		income = 0.0;
		name = "Default";
	}
	
	public PrintableObject(int age, double income, String name) {
		this.age = age;
		this.income = income;
		this.name = name;
	}	
	
	// As it turns out, when one calls "System.out.println()", what actually
	// gets called is a method called toString() that is part of the Java system library
	// 
	// When we call System.out.println() for and OBJECT, the default is to just print out
	// ObjectType@address
	// 
	// what we can do, though, is to OVERRIDE that method with one of our own, so that
	// when we call System.out.println() for an object of this type, it will call our own
	// toString method instead.
	//
	public String toString() {
		return ("Age = " + this.age + ", income = " + this.income + ", name = " + this.name);
	}
}