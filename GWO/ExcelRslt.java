package GWO;



public class ExcelRslt {

    private String Iter;
    private String Fit;
    private String ACC;
    private String SEN;
    private String SEP;
    private String F1score;
    private String MCC;
    private String Length;
    private String Positions;


    public ExcelRslt(String Iter, String Fit, String ACC, String sen, String  sep, String f1score, String MCC,  String Length, String Positions) {
        this.Iter = Iter;
        this.Fit = Fit;
        this.ACC = ACC;
        this.SEN=sen;
        this.SEP=sep;
        this.F1score=f1score;
        this.MCC=MCC;
        
        
        this.Length = Length;
        this.Positions=Positions;
    }

    public String getIter() {
        return Iter;
    }

    public void setIter(String Iter) {
        this.Iter = Iter;
    }

    public String getFit() {
        return Fit;
    }

    public void setFit(String Fit) {
        this.Fit = Fit;
    }

    public String getACC() {
        return ACC;
    }

    public void setACC(String ACC) {
        this.ACC = ACC;
    }

    
    
    public String getSEN() {
        return SEN;
    }

    public void setSEN(String Sen) {
        this.SEN = Sen;
    }
    
    
    public String getf1score() {
        return F1score;
    }

    public void setf1score(String f1score) {
        this.F1score = f1score;
    } 
    
    
    public String getMCC() {
        return MCC;
    }

    public void setMCC(String MCC) {
        this.MCC = MCC;
    } 
    
    
    
    
    
    
    public String getSEP() {
        return SEP;
    }

    public void setSEP(String Sep) {
        this.SEP = Sep;
    } 
    
    
    
    
    
    
    
    public String getLength() {
        return Length;
    }

    public void setLength(String Length) {
        this.Length = Length;
    }

    
    public String getPositions() {
        return Positions;
    }

    public void setPositions(String Positions) {
        this.Positions = Positions;
    }
    
    
    
    @Override
    public String toString() {
        return Iter+ ": Fit "+Fit+ " ACC "+ACC+" Length "+Length;
    }
}