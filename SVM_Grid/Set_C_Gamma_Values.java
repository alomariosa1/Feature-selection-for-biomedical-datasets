package SVM_Grid;


public class Set_C_Gamma_Values {

	

		
		public double  CValue;
		public double  GmValue;
		public double  CrossFitness;
		
			public double  SEN;
			public double  SEP;
			public double  Fscore;
		
		
		public Set_C_Gamma_Values() {
			
		}
		
		public Set_C_Gamma_Values(double cValue,double gmValue, double crossFitness) {
			super();
			this.CValue = cValue;
			this.GmValue= gmValue;
			this.CrossFitness=crossFitness;
			
			
		}


		public double getC_Value() {
			return CValue;
		}
		
		public double getGM_Value() {
			return GmValue;
		}
		
		public double getCrossFitness() {
			return CrossFitness;
		}
		
		public double getSEN() {
			return SEN;
		}
		
		public double getSEP() {
			return SEP;
		}
		
			public double getFscore() {
			return Fscore;
		}
		
		
			
		public void set_CValue(double cValue) {
			CValue = cValue;
		}
		
		public void set_GmValue(double gmValue) {
			GmValue = gmValue;
		}
		
		
		public void setCrossFitness(double crossFitness) {
			CrossFitness = crossFitness;
		}

	
	
	    public void setSEN(double S) {
			SEN = S;
		}
	
		
		public void setSEP(double X) {
			SEP = X;
		}

		public void setFscore(double Y) {
			Fscore = Y;
		}

		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	

