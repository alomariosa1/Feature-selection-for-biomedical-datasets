package GWO;


import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



import java.util.Arrays;
import java.util.Comparator;



import java.util.Date;
import java.util.Formatter;
import java.util.Random;
import java.util.StringTokenizer;
import java.lang.Integer;
import java.text.DecimalFormat;
import java.text.NumberFormat;


import java.util.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



public class Hybrid_GWO implements  Linux_Parameter_Setting1{

	
	
//	public GrayWolfComputeFit GWOfitness;
	
	public GrayWolfComputeFit GWOfitness;
	
	public Convert_To_Gpositions GenPosition;
	
	
	public Gamma Gammaobj;
	public String GenePositions,Max;
	
	public Formatter formatterprint;

	public int NoGen;
	
	public List <ExcelRslt>  ExSaving;
	

	public int Len;
	public double ExAcc;
	public String ExPos;
	
	
	
	
	
	     public int GlobalGeneLength;
	
	     static int GpopSize = 100;
	
	     static int GrayWolfLength = 50;                
	
	     int    MaxIter = 100;
	 
	     static int var=GrayWolfLength+6;
		 double br=0.1;
		 double B_BW=0.05;

	
	
	 public  String [][] Wolfs;// = new double[GpopSize][var];
	 String [] Best;
	 String [] Current;
	 
	
	 public  char [] Loc ;  //= new double[2][var];
	 public  char [] Loc2 ; 
	
	 public  String [] Best1; //= new double[1][var];
	 public  String [] BetaSolution;
	 public  String [] TransfSolTostring;

	 
	 double Prob=0.5, Flowering=0.0,RndBit;
	 Random generator1; 
	 Random generator2;
	 Random generator3; 
	 Random generator4;
	 Random generator5; 
	 Random generator6;
	 
	 Random Floweridx1 ;
	 Random Floweridx2 ;
	 Random eps = new Random();
	 String bits="";
	 
	 double Fit1=0.0,Fit2=0.0, Fit3=0.0, Fit4=0.0, Fit5=0.0 , ACC1,ACC2=0;
	 
	 
	 
	 // GrayWolf
	 double r1;
	 double r2;
	 double r3;
	 double r4;
	 double r5;
	 double r6;
	 
	 
	 
	 double Rnd;
	 double Frnd;
	 
	 int maxiter;
	 double alfa[];
	 double beta[];
	 double delta[];
	 double XX[][];
	 double X1;
	 double X2;
	 double X3;
	 double X4;
	 double fitness[];
	 double BESTVAL[];
	 double iterdep[];
	 double a;
	 double A1;
	 double C1;
	 double A2;
	 double C2;
	 double A3;
	 double C3;
	 double D_alpha=0.0, D_beta=0.0 , D_delta=0.0;
	 double V1,V2,V3;
	 double ACC;
	 double Fit;
	 int NumIter=100;
	 int Classes;
	 double TrizFit;
	 String Genes_Pos="", bins="";
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
		
	public void RunGray(String DATA, String Path,int GKeys[],int Allgenes,int classes,SVM_Grid.svm_problem SubData,SVM_Grid.svm_parameter paramcross , String THD) throws IOException{
	
		
		 
		
		
		
	
		Wolfs = new String[GpopSize][var];
		Best= new String [var];
		Current= new String [var];
		BetaSolution = new String [var];
		TransfSolTostring= new String [GrayWolfLength];
		
		
		Allgenes=50;
		GlobalGeneLength=Allgenes;
		int BinLen= GKeys.length;
	    GWOfitness=new  GrayWolfComputeFit();   
	    GenPosition= new Convert_To_Gpositions();
	    Gammaobj= new Gamma();
	    GenePositions="";
	    NoGen=Allgenes;
	    String Gene_Pos="";
	    double LEN = 0;
	     Classes=classes;
	    
	    
	    // GrayWolf
	    maxiter=MaxIter;  
	    
	    XX=new double[GpopSize][GrayWolfLength];
	    alfa=new double[GrayWolfLength];
	    beta=new double[GrayWolfLength];
	    delta=new double[GrayWolfLength];
	   
	    BESTVAL=new double[maxiter];  
	    iterdep=new double[maxiter]; 
	    TrizFit=0;
	    
	    
	    
	    String NEW_LINE = System.getProperty("line.separator");	
        ArrayList key= new ArrayList();
        ExSaving= new ArrayList<ExcelRslt>();
	
	    ExSaving.add(new ExcelRslt( "Iteration", 
			    "fitness",
			    "Accuracy", 
			    "Sensitivity", 
			    "Specificity",
		
			    "F1score", 
			   "MCC",
			    "No.of channels",
			    " Channel Pos "));
	    
			System.out.println("          initPopulation  ()" ); 
		
			
			initPopulation();
			ComputeFitness(Allgenes,GKeys,BinLen,Classes,SubData,paramcross);
			
			
			System.out.println( " All Pop -------------------------------------");
			for(int indx=0;indx<GpopSize; indx++) {
				
				String GrayWolfVariables="";
			       for (int i = 0; i < GrayWolfLength; i++)
			
			    	   GrayWolfVariables+=Wolfs[indx][i];
				
		     
			System.out.println( " Pop     "+ (indx+1)  ) ;
			System.out.println( GrayWolfVariables );
			System.out.println("ACC  "+ Wolfs[indx][var-2]);
			System.out.println("Fittness  "+ Wolfs[indx][var-1] );
			System.out.println( "+++++++++++++++++++++++++++++++++++++++"); 
			}
			
			
			
			
			
			Arrays.sort(Wolfs, new Comparator<String[]>() {
			    public int compare(String[] o1, String[] o2) {
			        return Double.compare(Double.parseDouble(o2[var-1]), 
			                              Double.parseDouble(o1[var-1]));
			    }
			});
			
			
			
			
			
			/*
			System.out.println( "\n\n ------------------------------------------------------------------");
			
			
			for(int indx=0;indx<GpopSize; indx++) {
				
				String GrayWolfVariables="";
			       for (int i = 0; i < GrayWolfLength; i++)
			
			    	   GrayWolfVariables+=Wolfs[indx][i];
				
		     
			System.out.println( " Pop     "+ (indx+1)  ) ;
			System.out.println( GrayWolfVariables );
			System.out.println("ACC  "+ Wolfs[indx][var-1]);
			System.out.println("Fittness  "+ Wolfs[indx][var-2] );
			System.out.println( "+++++++++++++++++++++++++++++++++++++++"); 
			}
			*/
			
			System.out.println( " Best 1                   "+Wolfs[0][var-1]);
			System.out.println( " Best 2                   "+Wolfs[1][var-1]);
			System.out.println( " Best 3                   "+Wolfs[2][var-1]);

			
		
			for (int i = 0; i < GrayWolfLength; i++)
				Best[i]=String.valueOf(Integer.valueOf(Wolfs[0][i]) );
			
			Best[var-1]=String.valueOf(Double.valueOf(Wolfs[0][var-1]) );
			Best[var-2]=String.valueOf(Double.valueOf(Wolfs[0][var-2]) );
			Best[var-3]=String.valueOf(Double.valueOf(Wolfs[0][var-3]) );
			Best[var-4]=String.valueOf(Double.valueOf(Wolfs[0][var-4]) );
			Best[var-5]=String.valueOf(Double.valueOf(Wolfs[0][var-5]) );
			Best[var-6]=String.valueOf(Double.valueOf(Wolfs[0][var-6]) );

			

			// Selecting Alpha,Beta, Delta
			
			//          Alpha
			
			for (int i = 0; i < GrayWolfLength; i++)
				alfa[i]=Double.valueOf(Wolfs[0][i]);
		
			for (int i = 0; i < GrayWolfLength; i++)
				beta[i]=Double.valueOf(Wolfs[1][i]);
			
			for (int i = 0; i < GrayWolfLength; i++)
				delta[i]=Double.valueOf(Wolfs[2][i]);
			
			
			 
	       
		
			//start Iterations	
			
		for (int Iter = 0; Iter < MaxIter; Iter++)
		{
				
			int count_So_Impv1=0;
			int count_So_Impv2=0;

			
				for(int j=0;j<GrayWolfLength;j++)
				a=2.0-((double)Iter*(2.0/(double)maxiter));
				
				System.out.println("  Iteration  "+Iter);
				

			for (int GrayIdx = 0; GrayIdx < GpopSize; GrayIdx++){

				
				Current=Copy(GrayIdx);
				
				
			
				int count0=0;
				int count1=0;
				
					for (int i = 0; i < GrayWolfLength; i++){

				    generator1 = new Random(System.currentTimeMillis());
				    generator2 = new Random(System.currentTimeMillis());
				    generator3 = new Random(System.currentTimeMillis());
				    generator4 = new Random(System.currentTimeMillis());
				    generator5 = new Random(System.currentTimeMillis());
				    generator6 = new Random(System.currentTimeMillis());
				    
				    Floweridx1 = new Random(System.currentTimeMillis());
					Floweridx2 = new Random(System.currentTimeMillis());
					
					r1=  generator1.nextDouble();
					r2=  generator2.nextDouble();
					r3=  generator3.nextDouble();
					r4=  generator4.nextDouble();
					r5=  generator5.nextDouble();
					r6=  generator6.nextDouble();
					
					
					
					//-------------------------Alpha------------------------------------ 
					
					Rnd=  generator1.nextDouble();
					A1=2.0*a*r1-a;
					C1=2.0*r2;
					
					D_alpha= (Math.abs(C1*alfa[i]- Double.valueOf(Wolfs[GrayIdx][i])));   

				
				    X1= alfa[i] -A1*D_alpha;
					
   				    
					//-------------------------Beta------------------------------------ 
    
				    
					 Rnd=  generator1.nextDouble();
					
   				      
   				      A2=2.0*a*r3-a;
				      C2=2.0*r4;
   				      

					D_beta= (Math.abs(C2*beta[i]- Double.valueOf( Wolfs[GrayIdx][i] )));   
					
				    X2= beta[i] -A2*D_beta;

					//-------------------------delta------------------------------------ 
 
				    
				    
				    Rnd=  generator1.nextDouble();

   				     
   				    A3=2.0*a*r5-a;
				    C3=2.0*r6;
   				      
					D_delta= (Math.abs(C3*delta[i]- Double.valueOf( Wolfs[GrayIdx][i] )));   
				    X3= delta[i] -A3*D_delta;
 
				  
					//------------------------- Update decision variables------------------------------------ 
   
				    
				    Frnd=  generator1.nextDouble();
 				      
 		        	 double S= 1/ ( 1+Math.exp( -(X1+X2+X3)/3    ));
 		        	 
//				      if ( S>=Frnd)
//				    	  Wolfs[GrayIdx][i]="1";
//				      
//				      else
//				    	  Wolfs[GrayIdx][i]="0";
 		        	 
 		        	 
 		        	 
 		        	if ( S>=Frnd)
			    	  Current[i]="1";
		      
			       else
			    	  Current[i]="0";
		        	 
				    	  
				       
					} // Single Gray Variables
					
					
					
					
					
					
					
					
					
					
					
					// copy	
					
					ComputeWOLFfitness(Current,Allgenes, GKeys, BinLen ,Classes,SubData,paramcross );

					for (int i = 0; i < GrayWolfLength; i++)
						Wolfs[GrayIdx][i]=String.valueOf(  Current[i] )  ;
					
					    Wolfs[GrayIdx][var-1]= String.valueOf(  Current  [ var-1]  );
					    Wolfs[GrayIdx][var-2]= String.valueOf(  Current [ var-2]  );
					    Wolfs[GrayIdx][var-3]= String.valueOf(  Current [ var-3]  );
					    Wolfs[GrayIdx][var-4]= String.valueOf(  Current [ var-4]  );
					    Wolfs[GrayIdx][var-5]= String.valueOf(  Current [ var-5]  );
					    Wolfs[GrayIdx][var-6]= String.valueOf(  Current [ var-6]  );

/* original code 24-5-2020
					if ( Double.valueOf(Current[var-1]) > Double.valueOf(Wolfs[GrayIdx][var-1]) ){
						count_So_Impv1++;

						for (int i = 0; i < GrayWolfLength; i++)
							Wolfs[GrayIdx][i]=String.valueOf(  Current[i] )  ;
						
						    Wolfs[GrayIdx][var-1]= String.valueOf(  Current  [ var-1]  );
						    Wolfs[GrayIdx][var-2]= String.valueOf(  Current [ var-2]  );
						    Wolfs[GrayIdx][var-3]= String.valueOf(  Current [ var-3]  );
						    Wolfs[GrayIdx][var-4]= String.valueOf(  Current [ var-4]  );
						    Wolfs[GrayIdx][var-5]= String.valueOf(  Current [ var-5]  );
 
					}
					
					*/
					
					
				// Beta Hill climbing	    
					    
						Run_BHillClimbing	(GrayIdx,  GKeys,BinLen,SubData,paramcross);
    
					    
				// End Beta Hill Climbing	    
					    
			
						
						
			
						
	// Begin  Triz	
			
	/*
					Current=Copy(GrayIdx);
							
					double X;
		            
			//		TRIZ (Current, Allgenes,GKeys,BinLen,Classes,SubData,paramcross, THD);
					
                   if ( Double.valueOf(Current[var-1]) > Double.valueOf(Wolfs[GrayIdx][var-1]) ){
                	   count_So_Impv2++;
                //	   System.out.println(" -----   Changed  ------  old   " +Wolfs[GrayIdx][var-1] + "    "+Current[var-1]);
	
						for (int i = 0; i < GrayWolfLength; i++)
							Wolfs[GrayIdx][i]=String.valueOf(  Current[i] )  ;
						
						    Wolfs[GrayIdx][var-1]= String.valueOf(  Current  [ var-1]  );
						    Wolfs[GrayIdx][var-2]= String.valueOf(  Current [ var-2]  );
						    Wolfs[GrayIdx][var-3]= String.valueOf(  Current [ var-3]  );
						    Wolfs[GrayIdx][var-4]= String.valueOf(  Current [ var-4]  );
						    Wolfs[GrayIdx][var-5]= String.valueOf(  Current [ var-5]  );
						    Wolfs[GrayIdx][var-6]= String.valueOf(  Current [ var-6]  );

					}
	*/
// END  Triz	
		
				
					
					
                   
                   
                   
                   
                   
                   
                   
                   
                   
                   
                   
                   
                   
                   
					
			}// Gray wolfes
			
			
			ComputeFitness(Allgenes,GKeys,BinLen,Classes,SubData,paramcross);
			
		

			

			Arrays.sort(Wolfs, new Comparator<String[]>() {
			    public int compare(String[] o1, String[] o2) {
			        return Double.compare(Double.parseDouble(o2[var-1]), 
			                              Double.parseDouble(o1[var-1]));
			    }
			});
			
			
		
				
			
			
			
			System.out.println("Best  "+Double.valueOf(Best[var-1]) +"   Current      "+Double.valueOf(Wolfs[0][var-1]) );

			if ( Double.valueOf(Best[var-1])< Double.valueOf(Wolfs[0][var-1]) ){
				for (int i = 0; i < GrayWolfLength; i++)
					Best[i]=String.valueOf( Double.valueOf(  Wolfs[0][i] )  );
				
				    Best[var-1]= String.valueOf( Double.valueOf(  Wolfs[0][var-1] ));
				    Best[var-2]=String.valueOf(  Double.valueOf(  Wolfs[0][var-2]  ));
				    Best[var-3]=String.valueOf(  Double.valueOf(  Wolfs[0][var-3]  ));
				    Best[var-4]=String.valueOf(  Double.valueOf(  Wolfs[0][var-4]  ));
				    Best[var-5]=String.valueOf(  Double.valueOf(  Wolfs[0][var-5]  ));
				    Best[var-6]=String.valueOf(  Double.valueOf(  Wolfs[0][var-6]  ));

				    
			}
			
			
			
			bits= "";		
			
			for (int i = 0; i < GrayWolfLength; i++){
				
				double element=  Double.valueOf( Best[i] );
				int Element = (int) element;
				bits+= String.valueOf( Element);
			
			}
			
			
			 Gene_Pos=String.valueOf(	GenPosition.PosGenes(bits,Allgenes,GKeys) );
			 LEN=GWOfitness.FeaturesNo(Gene_Pos);
			 Fit1= GWOfitness.FitnessValue(Gene_Pos, BinLen, Classes,SubData,paramcross	);
		     Fit2=GWOfitness.ACC;
			
		     System.out.println(" ******************************************");
				System.out.println(" The best Solution length::");
				System.out.println(" Solution  "+	bits );
				System.out.println( LEN );	
				System.out.println("  Feature Representation       ");
				System.out.println(Gene_Pos);		
				System.out.println("  binary Representation       ");
				System.out.println(bits);
				
				System.out.println("  Accuracy       ");
				System.out.println( Fit2);
				System.out.println("  Fittness       ");
				System.out.println( Fit1 );
				System.out.println(" ******************************************");
			
			
				ExSaving.add(new ExcelRslt(String.valueOf(Iter+1),  String.valueOf(Best[var-1])   ,   String.valueOf(Best[var-2]), String.valueOf(Best[var-3]), String.valueOf(Best[var-4]),String.valueOf(Best[var-5]),String.valueOf(Best[var-6]), String.valueOf(LEN) ,   String.valueOf(Gene_Pos) ));

			
			
			
            //sort(Wolfs);
            
            
            
            
            
            
          //  TRIZ here
				
	/*
				double X;
            
				bins=TRIZ (0, Allgenes,GKeys,BinLen,Classes,SubData,paramcross);
				 X= Double.valueOf(Wolfs[0][var-1] );
     			  if (  X <  TrizFit ) {
					for (int i = 0; i < GrayWolfLength; i++)
						alfa[i]=Double.valueOf(bins.charAt(i));
					   System.out.println(" -----   Changed  ------  old  " +Wolfs[0][var-1] + "   new "+TrizFit);
     			  }
				
				bins=TRIZ (1, Allgenes,GKeys,BinLen,Classes,SubData,paramcross);
				   X= Double.valueOf(Wolfs[1][var-1] );
				if (X<  TrizFit ){
			      	for (int i = 0; i < GrayWolfLength; i++)
					    beta[i]=Double.valueOf(bins.charAt(i));
					   System.out.println(" -----   Changed  ------  old  " +X + "   new "+TrizFit);
				   }

            
				   
				   bins=TRIZ (2, Allgenes,GKeys,BinLen,Classes,SubData,paramcross);
				   X= Double.valueOf(Wolfs[2][var-1] );
				   if (X <  TrizFit )  {
			      	for (int i = 0; i < GrayWolfLength; i++)
			      		delta[i]=Double.valueOf(bins.charAt(i));
					   System.out.println(" -----   Changed  ------  old  " +X + "   new "+TrizFit);
				   }
          
				*/   
				   
				   
				   
				   
            //      Update    Alpha, Beta, Delta
			
			for (int i = 0; i < GrayWolfLength; i++)
				alfa[i]=Double.valueOf(Wolfs[0][i]);
		
			for (int i = 0; i < GrayWolfLength; i++)
				beta[i]=Double.valueOf(Wolfs[1][i]);
			
			for (int i = 0; i < GrayWolfLength; i++)
				delta[i]=Double.valueOf(Wolfs[2][i]);
			
		
			System.out.println("--------------------------------- Iteration   # "+(Iter+1)+"  -------------------"  );    
			System.out.println("");       	      
			System.out.println("                 Improvents in first replacment : "+count_So_Impv1);
			System.out.println("                 Improvents in Triz replacment  : "+count_So_Impv2);		
			System.out.println(""); 
			System.out.println("----------------------------------------------------------------------------------");		
	
			System.out.println("               Thread ::              "+   THD  );
		
		} // EndMaxIterations		      
 				      //
		
		
		
		
		
		writeToExcel(ExSaving,Path);
		
		
		
	}// End of function
	
  
	private void  Run_BHillClimbing(int GYidx, int GKeys[], int BinLen,SVM_Grid.svm_problem SubData,SVM_Grid.svm_parameter paramcross) throws IOException{
		

		
		int Iter=0;
		
		
		for (int i = 0; i < GrayWolfLength; i++){
			BetaSolution[i]=Wolfs[GYidx][i];
			
		}
		/*
		ComputeWOLFfitness (BetaSolution, BinLen, GKeys, BinLen, probcross, SubData, paramcross   );
		
		BetaSolution[var-1]=String.valueOf(Fit2);
		BetaSolution[var-2]=String.valueOf(Fit1);
		*/
		
		double Fitness=Double.valueOf(Wolfs[GYidx][var-2]  );
		double NewFitness=0;
		
		
		
		 /* int Iter=0;
		  
		  double XBetaSolution []= new double [FlowerLength];
		  char Sol [] = new char [FlowerLength];
		  double NewACC;
		  
		  XBetaSolution=GetCopyOf(BetaSolution);*/
		  
		  
		  
			 while ( Iter < NumIter) {

				 
				int  z=generator1.nextInt(50);
					
		      	  	if(   BetaSolution [z]=="1")
		      	  		
		      	  	      BetaSolution [z]="0";
		      	  	else
		      	  		  BetaSolution [z]= "1";
				 
				 
				 
		      	  for( int bit=0; bit< BinLen; bit++){
		      			 
		       			 
		       		 
		     	  		if ( Math.random() <= B_BW ){
		     	  			
		     	  			if(Math.random() >=0.5)
		      			 
		     	  			//if(   BetaSol.BatSol [bit]=='1')
		      					 
		     	  				BetaSolution [bit]= "1";
		      					
		     	  			else
		     	  				BetaSolution [bit]= "0";
		      					 
		     	  				}
		      				 
		      			   
		      				 
		      		 
		     			}   // End Bit for	 
		      	  	
		      	  	
		  	       ComputeWOLFfitness (BetaSolution, BinLen, GKeys, BinLen,Classes, SubData, paramcross   );
                     
		  	       NewFitness= Fit1;
		  	       


				     if( Fitness  <  NewFitness){

		      	  	 
				    	 for (int i = 0; i < GrayWolfLength; i++)
				 		//	BetaSolution[i]=String.valueOf(Integer.valueOf(Wolfs[GYidx][i]) );
		      	  	
				    	 Wolfs [GYidx][i] = BetaSolution[i];
		      	  	
				    	 /*  old code 07-09-2021
				    	    Wolfs[GYidx][var-2]= String.valueOf(Fit1);
							Wolfs[GYidx][var-1]=String.valueOf(Fit2);		      	  	
							Fitness=NewFitness;
							*/
							
							
							
							 Wolfs[GYidx][var-1]= String.valueOf(Fit1);
						     Wolfs[GYidx][var-2]=String.valueOf(Fit2);
						     Wolfs[GYidx][var-3]=String.valueOf(GWOfitness.SEN);
						     Wolfs[GYidx][var-4]=String.valueOf(GWOfitness.SEP);
						     Wolfs[GYidx][var-5]=String.valueOf(GWOfitness.Fscore);
						     Wolfs[GYidx][var-6]=String.valueOf(GWOfitness.MCC_1);	
							
							 Fitness=NewFitness;

							
							
							
							
							
							
				     }
				    
				     Iter++;
			  // End Iteration 
				     
			 }
	}		 
				 
		
		
	
		
		// Compute Fitness for Each individual Wolf
	public void ComputeWOLFfitness(String GrayWolfSol[], int Allgenes, int GKeys[],int BinLen, int Classes,SVM_Grid.svm_problem SubData,SVM_Grid.svm_parameter paramcross  ) throws IOException {
		// TODO Auto-generated method stub
		
	
	// for(int indx=0;indx<GpopSize; indx++) {
			
			String GrayWolfVariables="";
		       for (int i = 0; i < GrayWolfLength; i++)
				{
		    	   GrayWolfVariables+=GrayWolfSol[i];
				}
		     
			String Gene_Pos1=String.valueOf( GenPosition.PosGenes(GrayWolfVariables,Allgenes,GKeys) );
			
			
			GrayWolfSol[var-1]   = String.valueOf (  GWOfitness.FitnessValue(Gene_Pos1, BinLen,Classes, SubData,paramcross	) );
			GrayWolfSol[var-2]   =  String.valueOf (   GWOfitness.ACC );
			GrayWolfSol[var-3]   = String.valueOf (  GWOfitness.SEN  );
			GrayWolfSol[var-4]   = String.valueOf (  GWOfitness.SEP  );
			GrayWolfSol[var-5]   = String.valueOf (  GWOfitness.Fscore  );
			GrayWolfSol[var-6]   = String.valueOf (  GWOfitness.MCC_1  );

			
	
	  }
		
		
	
	
	
	
	// Compute fitness for all Wolfs
public void ComputeFitness(int Allgenes, int GKeys[],int BinLen, int Classes, SVM_Grid.svm_problem SubData,SVM_Grid.svm_parameter paramcross ) throws IOException {
		// TODO Auto-generated method stub
		
	
	 for(int indx=0;indx<GpopSize; indx++) {
			
			String GrayWolfVariables="";
		       for (int i = 0; i < GrayWolfLength; i++)
				{
		    	   GrayWolfVariables+=Wolfs[indx][i];
				}
		     
			String Gene_Pos1=String.valueOf(	GenPosition.PosGenes(GrayWolfVariables,Allgenes,GKeys) );
			
			
			 Fit1= GWOfitness.FitnessValue(Gene_Pos1, BinLen, Classes,SubData,paramcross	);
					
		     Fit2=GWOfitness.ACC;
			
			
			
	
			
		//	GenPosition.PosGenes(String.valueOf( GenePoplist.Batpoplist.get(0).BatSol),BinLen,GKeys),  BinLen,probcross,SubData,paramcross	
			
		//	String.valueOf(Convert_To_Pos (indx,GKeys))
	      //   Fit1= GWOfitness.FitnessValue(String.valueOf(Convert_To_Pos (indx,GKeys)), BinLen,probcross,SubData,paramcross	);
	
			
			
		//	Fit2=GWOfitness.ACC;
			
			
			     Wolfs[indx][var-1]= String.valueOf(Fit1);
			     Wolfs[indx][var-2]=String.valueOf(Fit2);
			     Wolfs[indx][var-3]=String.valueOf(GWOfitness.SEN);
			     Wolfs[indx][var-4]=String.valueOf(GWOfitness.SEP);
			     Wolfs[indx][var-5]=String.valueOf(GWOfitness.Fscore);
			     Wolfs[indx][var-6]=String.valueOf(GWOfitness.MCC_1);
				
			
		
		}
	
	
	  }



public  void writeToExcel(List<ExcelRslt> ExcelSave, String EndPath){
	
	
	
	Workbook workbook = new XSSFWorkbook();

    Sheet studentsSheet = workbook.createSheet("Students");

    int rowIndex = 0;
    for(ExcelRslt RS : ExcelSave){
        Row row = studentsSheet.createRow(rowIndex++);
        int cellIndex = 0;
        //first place in row is name
        row.createCell(cellIndex++).setCellValue(RS.getIter());

        //second place in row is marks in maths
        row.createCell(cellIndex++).setCellValue(RS.getFit());

        //third place in row is marks in Science
        row.createCell(cellIndex++).setCellValue(RS.getACC());

        //fourth place in row is marks in English
        
        row.createCell(cellIndex++).setCellValue(RS.getSEN()  );
        row.createCell(cellIndex++).setCellValue(RS.getSEP()  );
        row.createCell(cellIndex++).setCellValue(RS.getf1score()  );
        row.createCell(cellIndex++).setCellValue(RS.getMCC() );

        
        row.createCell(cellIndex++).setCellValue(RS.getLength());

        row.createCell(cellIndex++).setCellValue(RS.getPositions());

        
    }

    //write this workbook in excel file.
    try {
        FileOutputStream fos = new FileOutputStream(EndPath);
        workbook.write(fos);
        fos.close();

        System.out.println(EndPath + " is successfully written");
        workbook.close();
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }


}
	

	
	
public   double Levy(int Flowering)
{
	
	
	double beta=3/2,sigma;
	
	
	sigma=Math.pow(   Gammaobj.gamma(1+beta)* Math.sin(Math.PI*beta/2) /  (Gammaobj.gamma((1+beta)/2)   * Math.pow(beta*2, (beta-1)/2 )       ) ,  (1/beta) );
  
double u=	generator1.nextInt(GrayWolfLength) * sigma;
double v=   generator1.nextInt(GrayWolfLength);
double Step= Math.pow(  u/ Math.abs(v) ,(1/beta) );

double L=0.01*Step;

return L;
	
	
}
	
	
	
	
	public String  FeaturesNo(String value)
    { 
	 
	  StringTokenizer str = new StringTokenizer(value,",");
    String next;
    int variableValue;
    int counts=0,Gcount=0;
      
   // Insert poistions into Arraylist
      
         while (str.hasMoreTokens())
            {
             //get next token
              next = str.nextToken();
             //convert to double
              variableValue =Integer.valueOf(next).intValue();
            //add double to array
              
              if(variableValue==1)
            	  Gcount++;
              counts++;
             }
         
         next=String.valueOf(counts);
         return next;
         
	 
 }
	 
	
	
	 public void print(String searchPoints[][], int Gk[], int Allgenes){
	
		 double tmp,tmp1=0;
		 DecimalFormat  formatter = new DecimalFormat("#0");
		 DecimalFormat  formatter1 = new DecimalFormat("#0.00");
		
	  for ( int i=0;i<GpopSize;i++){
		  tmp=Double.valueOf(searchPoints[i][var-2]);
		  tmp1=Double.valueOf(searchPoints[i][var-1]);
		  System.out.println();
		  String Bin="";
		  for ( int j=0;j<GrayWolfLength;j++){
		  
		//  System.out.print(formatter.format(searchPoints[i][j])+",");
			  Bin=Bin+String.valueOf(searchPoints[i][j]);
	  }
			String Gene_Pos=String.valueOf(	GenPosition.PosGenes(Bin,Allgenes,Gk) );
		//	System.out.print( Gene_Pos);
			double LEN=GWOfitness.FeaturesNo(Gene_Pos);
			//formatter1.format(tmp)	FeaturesNo (String.valueOf(Convert_Chld_To_Pos (0,Best1,GKeys)))
/*		  System.out.print( "Genes  :\n"+  Bin        +"\n fits== "+tmp+"    ACC= "+tmp1 +"   Feature Len : "+ FeaturesNo (String.valueOf(Convert_Chld_To_Pos (i,searchPoints,Gk)))                             );
*/
		 		  System.out.print( "Genes  :\n"+  Bin        +"\n fits== "+tmp+"    ACC= "+tmp1 +"   Feature Len : "+ LEN                      );
	  
		  
		  
		  System.out.println();
	  }
	
	
	 }
	
	private static String  Convert_Chld_To_Pos ( int index,String ch[][],int key2[]){
		
		String S="";
		for ( int j=0;j<GrayWolfLength;j++){
		
		if(ch[index][j]=="1")
		   
			S=S+key2[j]+",";
			
		}
		
		return S;
		
	}
	
	
	
	
	
private static String  Convert_Chld_To_LOCPos ( String ch[],int key2[]){
		
		String S="";
		for ( int j=0;j<GrayWolfLength;j++){
		
		if(ch[j]=="1")
		   
			S=S+key2[j]+",";
			
		}
		
		return S;
		
	}
	
	
	
	
	
	private  String  Convert_To_Pos(int index, int key[])
	{
	
	 String  Positions="";
	
	for ( int i=0;i<GrayWolfLength;i++)
	
	        if (Wolfs[index][i]=="1")
	        	Positions+=key[i]+",";
	     
	
	return Positions;
	
	}
	
	
	
	
	
	private  String  Convert_To_LOCpos(double Loc [], int key[])
	{
	
	 String  Positions="";
	
	for ( int i=0;i<GrayWolfLength;i++)
	
	        if (Loc[i]==1)
	        	Positions+=key[i]+",";
	     
	
	return Positions;
	
	}
	
	
	
	
	
	
	private  void initPopulation()
	{
		
		for (int i = 0; i < GpopSize; i++)
			for (int j = 0; j < GrayWolfLength; j++)
				Wolfs[i][j] = "0";
		
		Random randomGenerator = new Random(System.currentTimeMillis());
		
		//for entire population
		
		for (int i = 0; i < GpopSize; i++)
		{
			int count=0;
			int RGno= randomGenerator.nextInt(15)+1;
			
		//	System.out.println(RGno);
			//for all chromosomes
			for (int j = 0; j < RGno; j++)
			{
				//randomly create chromosome values
				int Poss= randomGenerator.nextInt(50);
				Wolfs[i][Poss] = "1";
				count++;
				
					
			}
						
		}
		
		
		
		
		
		
	}


	/**
	 * this method returns a random number n such that
	 * 0.0 <= n <= 1.0
	 */
	static double randomDouble()
	{
		Random r = new Random();
		return r.nextInt(1000) / 1000.0;
	}
	
	
	 public void sort(String searchPoints[][]){
	        Arrays.sort(searchPoints, new Comparator() {

	          /*  public int compare(double[] o1, double[] o2) {
	                if (o1[var-2] < o2[var-2]) {
	                    return 1; // -1 for descending order
	                } else if (o1[var-2] > o2[var-2]) {
	                    return -1; // 1 for descending order
	                } else {
	                    return 0;
	                }
	            }*/

	            public int compare(Object o1, Object o2) {
	                double[] O1 = (double[]) o1;
	                double[] O2 = (double[]) o2;
	                if (O1[var-1] < O2[var-1]) {
	                    return 1; // -1 for descending order
	                } else if (O1[var-1] > O2[var-1]) {
	                    return -1; // 1 for descending order
	                } else {
	                    return 0;
	                }
	            }
	        });
	    }
	
	 
	 
	 
	 
	 public class StrinArrayComparator implements Comparator<String[]> {
		    @Override
		    public int compare(String[] array1, String[] array2) {
		        // get the second element of each array, andtransform it into a Double
		        Double d1 = Double.valueOf(array1[var-1]);
		        Double d2 = Double.valueOf(array2[var-1]);
		        // since you want a descending order, you need to negate the 
		        // comparison of the double
		        return -d1.compareTo(d2);
		        // or : return d2.compareTo(d1);
		    }
		} 
	 
	 
	 
	 
	 class ColumnComparator implements Comparator {
			int columnToSort;
			ColumnComparator(int columnToSort) {
				this.columnToSort = columnToSort;
			}
			//overriding compare method
			public int compare(Object o1, Object o2) {
				Double[] row1 = (Double[]) o1;
				Double[] row2 = (Double[]) o2;
				//compare the columns to sort
				return row1[columnToSort].compareTo(row2[columnToSort]);
			}
	 
	 
	 }
	 
	 
	 private  String [] Copy(int  Idx){
		
		 
		 for (int i = 0; i < GrayWolfLength; i++)
				Current[i]=String.valueOf(Integer.valueOf(Wolfs[Idx][i]) );
			
		 Current[var-1]=Wolfs[Idx][var-1] ;
		 Current[var-2]= Wolfs[Idx][var-2] ;
		 Current[var-3]=Wolfs[Idx][var-3];
		 Current[var-4]=Wolfs[Idx][var-4] ;
		 Current[var-5]=Wolfs[Idx][var-5] ;
		 Current[var-6]=Wolfs[Idx][var-6] ;

		 
		 return Current;
	 
	 }
	 
	 
	 
	 
	 
	 
	 
	 
		private  void  TRIZ(String [] TrizingSol,  int Allgenes, int GKeys[],int BinLen, int Classes, SVM_Grid.svm_problem SubData,SVM_Grid.svm_parameter paramcross, String THD) throws IOException{
			
			
			Trizzin Triz_obj= new Trizzin();
			char[] Sol = new char [GrayWolfLength];
			String single="";
			// Convert to binary characters
			
		
				for (int j = 0; j < GrayWolfLength; j++){
					
					single=TrizingSol[j];
				    Sol[j]=   single.charAt(0);
					}
			
			single="";
			Triz_obj.TrizOperators(Sol, TrizingSol, var, Allgenes,  GKeys, Classes, SubData, paramcross, THD );
				
				
				
				
			/*	
			single+=String.valueOf(Sol);
				

			Genes_Pos=String.valueOf(	GenPosition.PosGenes(single,Allgenes,GKeys) );
				
				
			TrizFit= GWOfitness.FitnessValue(Genes_Pos, BinLen, Classes,SubData,paramcross	);
				*/	
	
		

			
			
		}

	 
	 
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	 
	 
	 
	 
	
}
