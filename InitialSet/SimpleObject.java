public class SimpleObject {
	int age;
	double income;
	String name;
	
	public SimpleObject() {
		age = 0;
		income = 0.0;
		name = "Default";
	}
	
	public SimpleObject(int age, double income, String name) {
		this.age = age;
		this.income = income;
		this.name = name;
	}	
}