
package business;

/**
 *
 * @author jasmineherd
 */
public class Loan {
     public static final String AMTDESC= "Loan Amount:";
    public static final String  RESULTDESC = "Monthly Payment:";
    
    private double p,i;
    private int t;//term = months
    
public Loan(double p,double i,int t){
this.t = t;//term=months
this.i = i;
this.p = p;

}

}




