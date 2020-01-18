/***********************8
 * Application: CX5
 * 
 * Demonstrate the use of inherited classes
 * 
 * Class CX5 inherits from class SUV, which in turn inherits from class Vehicle
 * @author brash
 *
 */


class CX5 extends SUV {
	
	public String color = "Steel Blue"; 
	
    public static void main(String[] args) {

        CX5 myCar =  new CX5();

        myCar.honk();

        System.out.println("Vehicle type = " + myCar.vehicleType + 
        		" ... fuel = " + myCar.fuelType + 
        		" ... Number of tires = " + myCar.numTires + 
        		" ... Color = " + myCar.color);
    }
}
