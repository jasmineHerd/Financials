
package business;

/**
 *
 * @author jasmineherd
 */
public class Annuity {
    //Class Constants-static value
    public static final String AMTDESC= "Monthly Deposit:";//amount description/
                                                         //final-FINAL!
    public static final String  RESULTDESC = "Final Annuity Value:";
    
    private double amt,rate;
    private int term;
    private String errmsg;
    private double[] begbal,intearn,endbal;
    private boolean built;
    
    
    
    // constructor-runs when new keyword is used
    //save these params into global variables for the class
    //forms cannot directly access these private values.They belong to Class
    public Annuity(double a,double r, int t){
        this.amt=a;
        this.rate = r;
        this.term = t;
        this.built=false;
        //more validation
        if(isValid()){
            buildAnnuity();
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
    
    
    
    private void buildAnnuity(){
        
        this.errmsg="";
                
        try{
            this.begbal = new double[this.term];
            this.intearn = new double[this.term];
            this.endbal = new double[this.term];
            
            this.begbal[0]=0.0;
            for(int i =0;i<this.term;i++){
                
                if(i>0){
                    this.begbal[i] = this.endbal[i-1];
                }
                this.intearn[i] = (this.begbal[i]+this.amt)*(this.rate/12);
                this.endbal[i] = begbal[i]+this.intearn[i]+this.amt;
                
            }
            this.built= true;
        }catch(Exception e){
            this.errmsg = "build error: "+e.getMessage();
            this.built=false;
        }
        
    }
    public String getErrorMsg(){
        return this.errmsg;
    }
    public double getResult(){
        if(built){
            return this.endbal[this.term-1];
        }
        return -999;
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
    public double getBegBal(int mo){
        if(!built){
            buildAnnuity();
            if(!built){
                return -1;
            }
        }
        if(mo < 1 || mo > this.term){
            //month is out of range
            return -1;
        }
        return this.begbal[mo-1];//remember *fences* array-1 for last index
    }
    public double getIntEarned(int mo){
         if(!built){
            buildAnnuity();
            if(!built){
                return -1;
            }
        }
        if(mo < 1 || mo > this.term){
            //month is out of range
            return -1;
        }
        return this.intearn[mo-1];
    }
    public double getEndBal(int mo){
         if(!built){
            buildAnnuity();
            if(!built){
                return -1;
            }
        }
        if(mo < 1 || mo > this.term){
            //month is out of range
            return -1;
        }
        return this.endbal[mo-1];
    }
}
