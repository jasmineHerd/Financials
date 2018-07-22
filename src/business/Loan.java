
package business;

/**
 *
 * @author jasmineherd
 */
public class Loan {
    public static final String AMTDESC = "Loan Amount: ";
    public static final String RESULTDESC = "Monthly Deposit" ;
    
    private double amt,rate,mopmt;  
    private int term;
    private String errmsg;
    private boolean built;
    private double[] begbal,intchg,endbal;
    
    public Loan(double a, double r, int t){
        this.amt = a;
        this.rate = r;
        this.term = t;
        this.built= false;
        this.mopmt = 0.0;
        this.errmsg = ""; 
        if(isValid()){
            buildLoan();
        }
        
    }
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
            double morate = this.rate/12.0;
            double denom = Math.pow((1+morate), this.term)-1;
            this.mopmt = (morate + morate/denom)* this.amt;
            
            this.begbal = new double[this.term];
            this.intchg = new double [this.term];
            this.endbal = new double[this.term];
            
            this.begbal[0] = this.amt;
            for(int i =0;i<this.term;i++){
                if(i>0){
                    this.begbal[i] = this.begbal[i-1];
                }
                this.intchg[i]=this.begbal[i]*morate;
                this.endbal[i]=(this.begbal[i]+this.intchg[i])-this.mopmt;
            }
            
            
            this.built =true;
        }catch(Exception e){
            this.errmsg = "Build error: "+ e.getMessage();
            this.built = false;
            
        }
    }
    public double getResult(){
        if(!built){
            buildLoan();
            if(!built){
                return -1;
            }
        }
        return this.mopmt;
    }
    private boolean isValid(){
        boolean valuesok = true;
        this.errmsg = "";
        if(this.amt <=0){
            this.errmsg = "Bad amount: not a positive value";
            valuesok = false;
        }
        if(this.rate <= 0 ){
            this.errmsg += " Bad Rate: not positive";
            valuesok = false;
        }
        if(this.term <= 0){
            this.errmsg += " Bad Term: not positive";
            valuesok = false;
        }
        return valuesok;
    }
    
}



