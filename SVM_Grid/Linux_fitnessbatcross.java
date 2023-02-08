package SVM_Grid;





import java.io.*;
import java.lang.String.*;
import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.ArrayList.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.StringTokenizer;



public class Linux_fitnessbatcross implements  Linux_Parameter_Setting1 {
	
	public double  SEN,SEP,STd,Fscore;
	public double  Evalbee;
	public double ACC;
	public double Fcardinality;
	//public double size=41;
	public  Random generator;
	DecimalFormat formatter ;
	String form;
	public double  FitnessValue(String binbee,int AllGenes, svm_problem probfit,svm_problem SubdataEvl,svm_parameter paramfit, int Cno, int Class) throws IOException  {
		
		
		formatter = new DecimalFormat("#0.000");
		generator = new Random();
		double fraction =generator.nextDouble();
		
		GeneTraincrossvalid crossvalid=new GeneTraincrossvalid();
		
		double NoG=AllGenes;
		
		
		Fcardinality=FeaturesNo(binbee);
		
		
		//System.out.println("   in fitness==== C==  "+paramfit.C + "  Gamma ==="+paramfit.gamma  );
		
		ACC=crossvalid.do_cross_validation(binbee,AllGenes,probfit,SubdataEvl,paramfit, Cno,  Class);
	//	fraction=0.01;
		
		SEN=crossvalid.SENE;
		SEP=crossvalid.SEPE;

		Fscore=crossvalid.FScore;
		//ACC=100;
	 
		Evalbee=  ACC;
		double ACCps=ACC/100.0;
		
		
	//	System.out.println( "ACC   "+"       "+ACC + "  "+(ACC/100.0));
	//	double X1=  (  (ACCps)*0.8);
	//	double X2=0.2 * ( (50.0 -Fcardinality) /   50.0) ;
	//	System.out.println( " X1 :: "+ X1+ "   X2 :: "+X2 +  " (X1+X2) = "+(X1+X2));
		   
		   
	form= formatter.format(   (0.8*ACCps)+    ( (0.2)   * ( (50.0 -Fcardinality) /   50.0)   )     );
	
	Evalbee=Double.valueOf(form);
	
//	System.out.println( "Allcross   "+"       "+Evalbee+"      form :: "+form);
	    return Evalbee;
		
	
	
	
	}
	 
	
	
	
	  public double FeaturesNo(String value)
	      { 
		 
		  StringTokenizer str = new StringTokenizer(value,",");
          String next;
          int variableValue;
          double counts=0;
	        
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
