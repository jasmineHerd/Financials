
package business;

/**
 *
 * @author jasmineherd
 */
public class Loan {
     public static final String AMTDESC= "Loan Amount:";
    public static final String  RESULTDESC = "Monthly Payment:";
    
    private double amt,rate,mopmt;//mopmt=monthlh payment 
    private int term;//term = months
    private String errmsg;
    private boolean built;
    private double[] begbal,intchg,endbal;
    
    
public Loan(double a,double r,int t){
this.amt = a;
this.rate = r;
this.term = t;//term=months
this.built = false;
this.mopmt = 0;
this.errmsg= "";
if(isValid()){
    buildLoan();
}
        
}

    private boolean isValid(){
        boolean valuesok = true;
        //this refers to globals in class.This one not That one....
        this.errmsg="";
        if(this.amt<=0){
            this.errmsg= "Bad amount:not a positive value";
            valuesok=false;
        }
        if(this.rate<=0){
            this.errmsg+="Bad Rate: not positive";
            valuesok=false;
        }
        if(this.term<=0){
            this.errmsg+="Bad Term: not positive";
            valuesok= false;
        }
        return valuesok;
    }//isValid
    
    public double getAmt(){
        return this.amt;
    }
    public double getRate(){
        return this.rate;
    }
    public int getTerm(){
        return this.term;
    }
    public String getErrorMsg(){
        return this.errmsg;
    }
    
    private void buildLoan(){
        this.errmsg = "";
        try{
            double morate = this.rate / 12.0; //get monthly rate
            double denominator = Math.pow((1+morate),this.term)-1;
            this.mopmt =(morate + morate/ denominator) * this.amt;
            //calc array values
            
            
            this.built = true;
        }catch(Exception e ){
            this.errmsg = "Build error: "+ e.getMessage();
            this.built = false;
                    
        }
    }//end of buildLoan
    public double getResult(){
        if(!built){
            buildLoan();
            if(!built){
                return -1;
            }
        }
        return this.mopmt;
    }
    
}




