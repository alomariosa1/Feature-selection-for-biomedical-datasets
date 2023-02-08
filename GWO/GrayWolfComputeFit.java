package GWO;



import java.io.*;
import java.lang.String.*;
import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.ArrayList.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.StringTokenizer;



public class GrayWolfComputeFit implements  Linux_Parameter_Setting1 {
	
	public double  Evalbee;
	public double ACC;
	public double Fcardinality;
	//public double size=41;
	public  Random generator;
	DecimalFormat formatter ;
	String form,CheckLength;
	double SEN, SEP, Fscore, MCC_1;

	public double  FitnessValue(String binbee, int NoChann, int Classes,  SVM_Grid.svm_problem probfit,SVM_Grid.svm_parameter paramfit) throws IOException  {
		
		
		formatter = new DecimalFormat("#0.0000");
		

		
		generator = new Random();
		double fraction =generator.nextDouble();
		
		GeneTraincrossvalid crossvalid=new GeneTraincrossvalid();

		
		
		
		binbee=Check_Zeros(binbee,NoChann);
		Fcardinality=FeaturesNo(binbee);
		
		if (  binbee == null)
			  System.exit(0);
		
		//System.out.println("   in fitness==== C==  "+paramfit.C + "  Gamma ==="+paramfit.gamma  );
		
		ACC=crossvalid.do_cross_validation(binbee,NoChann,Classes,probfit,paramfit);
	//	fraction=0.01;
		
		SEN= crossvalid.SENE;
		SEP= crossvalid.SEPE;
		Fscore=crossvalid.FScore;
		MCC_1=crossvalid.MCC;
		
		
		
		//ACC=100;
	 
		Evalbee=  ACC;
		
	//	System.out.println( " Accuracy ::"+ACC);
 	ACC=ACC;
		
/*ACC=ACC/100;
*/	/*	
		//double ACCps=ACC/100.0;
	System.out.println( " ********************************");
	System.out.println( " ********************************");
	System.out.println(" binbeee "+binbee);
		System.out.println(" AllGenes  "+AllGenes);
		System.out.println( " Accuracy ::"+ACC);
	    System.out.println( "  Length  "+Fcardinality);
	    double X1=  (  (ACC)*0.8);
		double X2=0.2 * ( (50.0 -Fcardinality) /   50.0) ;
		System.out.println( " X1 :: "+ X1+ "   X2 :: "+X2 +  " (X1+X2) = "+(X1+X2));

				
		System.out.println( " X1 :: "+ X1+ "   X2 :: "+X2 +  " (X1+X2) = "+(X1+X2));*/
		   
		   
//	form= formatter.format(   (0.8*ACC)+    ( (0.001)   * ( (50.0 -Fcardinality) /  50.0)   )     );
 	
 	if (  Fcardinality == 0){
		  
 		System.out.println( " bin      "+binbee);
 		System.exit(0);
		  
 	}
 	//double Rslt=  1.0*ACC  +    (    0.001* (1.0-(Fcardinality / 50.0))  )    ;
 	
 //	ACC=ACC/100;

 	
	form= formatter.format(   (1.0*ACC)+    ( (0.001)   * ( (50.0 -Fcardinality) /  50.0)   )     );

 	
 	
 	
 	
 	
 	
 //System.out.println( "--                                               --"+Rslt);
	//form= formatter.format( Rslt);
	
//	ACC=ACC*100;
	Evalbee = Double.valueOf(form); 
	
//	System.out.println( "Allcross   "+"       "+Evalbee+"      form :: "+form);
	    return Evalbee;
		
	
	
	
	}
	 
	private  String  Check_Zeros(String value, int NoChann )
	{
	
	
	
		
		int count  =0;
	 
		Random randomGenerator = new Random(System.currentTimeMillis());
		
		
		char Chann[]  =new char[NoChann];
		  Chann=value.toCharArray();
	
	for ( int i=0;i<Chann.length;i++){
	
	        if (Chann[i]=='0')
	        	 count++;
	        	
	}
	
	if (count ==(NoChann-1)){
		
		int Rndpos=randomGenerator.nextInt(50);
		Chann[Rndpos]='1';
		
	}
	String Bits = String.copyValueOf(Chann);

	return Bits;
		
	
	}
	
	
	
	  public double FeaturesNo(String value)
	      { 
		 
		  StringTokenizer str = new StringTokenizer(value,",");
          String next;
          int variableValue;
          double counts=0;
          
     //     System.out.println(" Value == "+value);
	        
	     // Insert poistions into Arraylist
	        
	           while (str.hasMoreTokens())
	              {
	               //get next token
	                next = str.nextToken();
	               //convert to double
	                variableValue =Integer.valueOf(next).intValue();
	              //add double to array
	       
	                counts++;
	               }
	           
	           
	           return counts;
	           
		 
	   }
		 
		 

}
