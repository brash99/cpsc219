public class Money {
	
		    private double amount;
            private String currency;

            public Money(double amountValue, String currencyValue) {
                        amount = amountValue;
                        currency = currencyValue;
            }

            public boolean equals (Money other) {
                        if (null == other) return false;
                        if (! (other instanceof Money)) return false;
                        if (currency != other.currency) return false;
                        if (amount != other.amount) return false;
                        return true;
            }

            public int hashCode() {
                        return this.currency.hashCode();
            }
            
            public String toString() {
            	return "The amount of money is " + currency + " " + amount;
            }

            public Money plus(Money other) {                                                
                        if (null == other) return null;
                        if (other.currency != currency) {
                           throw new IllegalArgumentException("cannot add $other.currency to $currency");
                        }

                        return new Money(amount + other.amount, currency);
            }
            
            public static void main(String[] args) {
            	
            	Money buck = new Money(1,"USD");
            	Money loonie =  new Money(1,"CDN");
            	Money toonie = new Money(2,"CDN");
            	
            	System.out.println(buck);
            	System.out.println(loonie);
            	System.out.println(toonie);
            	
            	Money twodollarbill = buck.plus(buck);
            	Money toonie_check = loonie.plus(loonie);
            	Money toonie_check2 = toonie;
            	
            	System.out.println(twodollarbill);  
            	System.out.println(toonie_check);
            	
            	//
            	// Call the equals method above, to actually check if the amount and currency of each object are
            	// equal to one another.
            	//
            	if (toonie_check.equals(toonie)) {
            		System.out.println("Success!! :)");
            	}else {
            		System.out.println("Failure!! :(");
            	}
            	
            	//
            	// Use instead the == operator ... for objects, "==" will only be true if the two objects point
            	// to the SAME LOCATION in memory ... so we expect failure of the test in this case.
            	//
            	if (toonie_check == toonie) {
            		System.out.println("Success!! :)");
            	}else {
            		System.out.println("Failure!! :(");
            	}
            	
            	//
            	// Use instead the == operator ... for objects, "==" will only be true if the two objects point
            	// to the SAME LOCATION in memory ... so we expect success of the test in this case.
            	//
            	if (toonie_check2 == toonie) {
            		System.out.println("Success!! :)");
            	}else {
            		System.out.println("Failure!! :(");
            	}
            	
            	//
            	// Compare two US dollars to two Canadian dollars ... we expect failure of the test as we
            	// require both the amount AND the currency to be the same.
            	//
            	if (toonie_check.equals(twodollarbill)) {
            		System.out.println("Success!! :)");
            	}else {
            		System.out.println("Failure!! :(");
            	}
            	
            }
}