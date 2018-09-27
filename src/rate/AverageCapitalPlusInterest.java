package rate;

import java.math.BigDecimal;

public class AverageCapitalPlusInterest {

    public static double round(double var0, int var2) {
        if (var2 < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        } else {
            BigDecimal var3 = new BigDecimal(Double.toString(var0));
            BigDecimal var4 = new BigDecimal("1");
            return var3.divide(var4, var2, 4).doubleValue();
        }
    }
    
    public static double conculateAmt(double amount,int periodno,double rate) {
    	double monthRate = rate/1200;
    	return round(amount*monthRate*Math.pow(1+monthRate, periodno)/(Math.pow(1+monthRate, periodno)-1),2);
    }
    
    public static void main(String[] args) throws Exception {
		System.out.println(run(9000.00, 12,14));
	}
    
    public static double everyInterest(double amount,int periodno,double rate) {
    	return (conculateAmt(amount,periodno,rate)*12)-amount;
    }
    
    public static double run(double amount,int periodno,double rate) {
		double monthRate = rate/1200;
		double evePayAmt = round(amount*monthRate*Math.pow(1+monthRate, periodno)/(Math.pow(1+monthRate, periodno)-1),2);
		double interest = 0.00d;
		for(int i=1;i<=periodno;i++) {
			double curinterest = round(amount*monthRate,2);
			System.out.println(curinterest);
			interest = interest+curinterest;
			amount = round(amount - (evePayAmt-amount*monthRate),2);
		}
		return round(interest,2);
    }

}
