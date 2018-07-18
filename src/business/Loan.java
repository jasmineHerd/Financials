
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
getPrincipal(){
//return original loan amount
}


getIntRate(){
//return origin annual interest
}

getTerm(){
    //return months of the loan
}

getMoPmt(){
    //returns calculated monthly payment for the loan
}

public double getBegBal(x){
    //returns beginning balance for month(x)
    return 0;
}

public double getIntChg(x){
    //returns the interest charge for month(x)
    return 0;
}

public double getEndBal(x){
    //returns ending balance for month(X)
    return 0;
}
}




