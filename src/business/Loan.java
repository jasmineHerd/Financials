
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
    
    public double getBegBal(int mo){
        if(!built){
            buildLoan();
            if(!built){
                return -1;
            }
        }
        if(mo < 1 || mo > this.term){
            return -1;
        }
        return this.begbal[mo-1];
    }
    
    public double getEndBal(int mo){
        if(!built){
            buildLoan();
            if(!built){
                return -1;
            }
        }
        if(mo < 1 || mo > this.term){
            return -1;
        }
        return this.endbal[mo-1];
    }
    public double getMoPmt(int mo){
        if(!built){
            buildLoan();
            if(!built){
                return -1;
            }
        }
        if(mo < 1 || mo > this.term){
            return -1;
        }
        return this.mopmt;
        
    }
    public double getIntChg(int mo){
        if(!built){
            buildLoan();
            if(!built){
                return -1;
            }
        }
        if(mo < 1 || mo > this.term){
            return -1;
        }
        return this.intchg[mo-1];
    }

   
    private void buildLoan(){
        this.errmsg = "";
        try{
            double morate = this.rate/12.0;
            double denom = Math.pow((1.0+morate), this.term)-1.0;
           // this.mopmt = (morate + morate/denom)* this.amt;
            this.mopmt = (morate + morate/denom) * this.amt;
            
            this.begbal = new double[this.term];
            this.intchg = new double [this.term];
            this.endbal = new double[this.term];
            
            this.begbal[0] = this.amt;
            this.intchg[0]=this.begbal[0] * morate;
            this.endbal[0]=(this.begbal[0])-(this.mopmt-this.intchg[0]);
            
            for(int i =0;i<this.term;i++){
                if(i>0){
                    this.begbal[i] = this.begbal[i-1]-this.mopmt;
                    this.intchg[i] = this.endbal[i-1]*morate;
                   // this.endbal[i]=(this.begbal[i-1])-(this.mopmt-this.intchg[i-1]);
                   this.endbal[i]= endbal[i-1]-(this.mopmt-this.intchg[i]) ;
                }
                //ending balance wrong
               // this.endbal[i]=(this.begbal[i]+this.intchg[i])-this.mopmt;
                //this.endbal[i]=(this.begbal[i])-(this.mopmt-this.intchg[i]);
                
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



