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
    public static void main(String[] args) {

        CX5 myCar =  new CX5();

        myCar.honk();

        System.out.println(myCar.vehicleType + " " + myCar.fuelType + " " + myCar.numTires);
    }
}
