
package Hybrid_GWO;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.Arrays;
//import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang3.ArrayUtils;
//import MRMR.AvgExcelRslt;

public class Trizzin { 
	 
	public static int Sol_Dim=0;
	public static Convert_To_Gpositions GenPosition;
	public static GrayWolfComputeFit GWOfitness;
	
	public void  TrizOperators (char Sol [], String [] Trizing, int Var , int Allgenes, int GKeys[], int Classes,  SVM_Grid.svm_problem SubData1,SVM_Grid.svm_parameter paramcross1,String  THD) throws IOException{
		// TODO Auto-generated method stub
		
		
		ArrayList<Integer> Boundary= new ArrayList<Integer>();
		ArrayList<String> SubGroups= new ArrayList<String>();
		GenPosition= new Convert_To_Gpositions();
		GWOfitness= new GrayWolfComputeFit();
	
		 	
				String Concatenation="", Single="", Blocks="";
				int NoGenes=50;
				Sol_Dim=Sol.length;
				Random r = new Random();
				int Low = 5;
				int High = 20;	
				int GroupLen = r.nextInt(High-Low) + Low;
		
		
				int TotGroups=0;
				int x= NoGenes / GroupLen;
				int y= NoGenes % GroupLen;
		 
				if (y!=0)
					TotGroups=x+1;
				else
					TotGroups=x;
		 
			//	TotGroups
		
		 
				int  Boundaryidx=0, bound=0,Bindex=0;
				String GroupBlock="", BoundyIdx="";
				    
				    for (int seg =0;seg<(TotGroups-1);seg++ )	{
					  
					  bound= (int)GroupLen * ( seg+1);
					  
					  Boundary.add(Boundaryidx, bound ); 
					  BoundyIdx+=bound+",";
					  Boundaryidx++;
				     }
		
		
				    Blocks="";
				 for ( int d=0;d<Sol_Dim;d++){  // All component's
				    	
					
							  
							  
					 GroupBlock+=Sol[d]+",";
						   
						 Blocks+=	Sol[d]+",";  
						    if(Boundary.contains(d+1) || d==49){  // d-1 since if s=6 then 0--->5 6 elements
							   
						    	SubGroups.add(Bindex,GroupBlock );
						    	
						    	GroupBlock="";
						    	Bindex++;
						      }  
						    
						    
						    
						   
						    	
						    }	// End For--- all component
				
				
				/*
				System.out.println("  THD     "+THD+"    TRIZ   Dim    "+ Bindex + "     Blocks     "+Blocks  );
				for ( int d=0;d<SubGroups.size();d++){ 
			
						       
					 System.out.println( " Block   " +(d+1) + "    "+ SubGroups.get(d).toString()  + " thd"+THD); 
						        
						        
				 }
				 
				 */
				 
				 String Blocking="";
				  for ( int d=0;d<SubGroups.size();d++)
						
				       
					  Blocking+=SubGroups.get(d).toString() ;
				 
//	      System.out.println("  before Mut   Current  THD    :"+THD +"   Sol    "+Blocking);

				 
				 
				  Mutation(SubGroups); 
				  
		//		  System.out.println("     After Mutation   ");
			//	  System.out.println("  THD     "+THD+"    TRIZ   Dim    "+ Bindex + "     Blocks     "+Blocks  );
					 
				Blocking="";
				  for ( int d=0;d<SubGroups.size();d++)
						
				       
					  Blocking+=SubGroups.get(d).toString() ;
							        
	//		      System.out.println("  After Mut   Current  THD    :"+THD +"   Sol    "+Blocking);
			    
				  
				    String[] s = Blocking.split(",");
				  
				   if (s.length > 50 )
					   System.exit(0);
				 // System.out.println( " thd"+THD + "  Size   "+s.length+"  Block   \n" + Blocking
							//	 ); 
						       
					
				  
				  
				  
				  
				  
		           Concat_And_CheckFitness(SubGroups, Trizing, Var, Allgenes, GKeys, Classes,SubData1, paramcross1, THD );
				 
	 
				       TwoOP(SubGroups);
				        
			           Concat_And_CheckFitness(SubGroups, Trizing,Var,  Allgenes, GKeys, Classes,SubData1, paramcross1,THD );
                 //  System.out.println("  Sol boundaries       :"+  BoundyIdx);
				        
				     Swap(SubGroups); 
				        
		              Concat_And_CheckFitness(SubGroups, Trizing, Var,  Allgenes, GKeys, Classes,SubData1, paramcross1, THD );

				     
				   /*  for ( int Sub=0;Sub<SubGroups.size();Sub++){
				    	 
				    	 Single=SubGroups.get(Sub).toString();
				    	 Concatenation=Concatenation+Single;
				    	
				     }
				        
				    
				     char  [] ch = new char[Sol.length];
				     String [] Sch= Concatenation.split(",");
				     for( int  z=0;z<Sch.length; z++){
				    	 String S=Sch[z];
				    	 Sol[z]=S.charAt(0);
				     
				     }
				     */
			
             	}
	
	
	public static  void Concat_And_CheckFitness(ArrayList Msubset, String [] Trizing, int Var, int Allgenes, int GKeys[], int Classes,  SVM_Grid.svm_problem SubData1,SVM_Grid.svm_parameter paramcross1, String THD ) throws IOException{
	
		GenPosition= new Convert_To_Gpositions();
		GWOfitness= new GrayWolfComputeFit();
		
		
		
		String Div_Single="", Concat_Single="", Sol_OneBlock="",  S="", Sol_Variables="", Rslt="";
		double TrizFit=0.0;
		
		
			for ( int Sub=0;Sub<Msubset.size();Sub++){
	    	 
					Div_Single=Msubset.get(Sub).toString();
					Concat_Single=Concat_Single+Div_Single;
	    	
			}
	
		//	System.out.println("   Full Sol  :  "+Concat_Single);
		
		 char  [] ch = new char[Sol_Dim];
	     String [] Sch= Concat_Single.split(",");
	    
			//System.out.println("   No , :  "+Arrays.toString(Sch) );

	     
	     	for( int  z=0;z<Sch.length; z++){
	     			S=Sch[z];
	     			Sol_OneBlock+=S;
	     	}

	     
	     	Rslt+= " --------------------------------------------------\n";
	     	Rslt+= "before pos    Current  :"+THD +"\n"+
	    	  "   sol   "+Sol_OneBlock +"\n";
	  //   Sol_Variables=String.valueOf(	GenPosition.PosGenes(Sol_OneBlock,Allgenes,GKeys) );
			
	     	Sol_Variables=	PosGenes(Sol_OneBlock,Allgenes,GKeys, Rslt);
	     	
	 //     System.out.println("    Current  THD    :"+THD +"   Sol    "+Sol_Variables);
	     
	   //  Sol_Variables="10889,3463,20078,19585,20078,";
	     
	     
	   
	     
	     if (Sol_Variables=="")
	    	   System.exit(0);
	     
	//     Sol_Variables="10889,3463,20078,";
	     
	   ///  System.out.println("  *************************");
	     TrizFit= GWOfitness.FitnessValue(Sol_Variables, Allgenes, Classes,SubData1,paramcross1	);

	    
	     
	     
	   //   System.out.println("  Fit    :"+ TrizFit+  "      sol  :"+ Sol_Variables+"    THD"+THD);
	//    System.out.println("    Current in Triz mut before fitness  :"+Sol_Variables);
	     // System.out.println("  Fit    :"+ TrizFit);
		    
	    
	   //   System.out.println("  *************************");
 

          
	//  
	     
		  if ( Double.valueOf(Trizing[Var-1]) < TrizFit ){
				
				for (int i = 0; i < Allgenes; i++)
					Trizing[i]=String.valueOf(  Sch[i] )  ;
				
				    Trizing[Var-1]= String.valueOf( GWOfitness.Evalbee  );
				    Trizing[Var-2]= String.valueOf( GWOfitness.ACC     );
				    Trizing[Var-3]= String.valueOf(  GWOfitness.SEN );
				    Trizing[Var-4]= String.valueOf(  GWOfitness.SEP );
				    Trizing[Var-5]= String.valueOf(  GWOfitness.Fscore  );

			}
		  
		  
		  
		 
	
	}
	public   void Mutation(ArrayList Msubset){
		
		 Random generator1 ;
		 Random generator2 ;
		 generator1 = new Random(System.currentTimeMillis());
		 generator2 = new Random(System.currentTimeMillis());
		Random x = new Random();
		  
		 double Rnd=0.0;
		String Con="";
		String one="1";
		String zero="0",Elm="";
		
		//  System.out.println(" =----------------");
		//  System.out.println("    Positions     ");
		  for ( int Sub=0;Sub<Msubset.size();Sub++){
			  
			  String sub= (String) Msubset.get(Sub);
			 
			  String[] s = sub.split(",");
			  
			//  System.out.println(" Org ::::: "+Arrays.toString(s));
	for ( int c=0;c<(int)(s.length / 2);c++){
			int Pos= generator2.nextInt(s.length);
		//	System.out.print(" "+Pos);
			  
			Elm=s [Pos].toString();
			
			
			Rnd=generator1.nextDouble();
			
			if (Rnd >=0.5)
				  
				  s [Pos]="1";
			else
				s [Pos]="0";
			
			
			/*if (Elm.equals(one))
				  
				  s [Pos]="0";
			 
			
			  if (Elm.equals(zero) )
				
				  s [Pos]="1";
			 */
			
			  
			  }
			  for( int  z=0;z<s.length; z++)
			   Con=Con+s[z]+",";
			  
		//	  System.out.println(" =----------------");
			  Msubset.set(Sub, Con);
			//  System.out.println(" Mut ::::: "+Msubset.get(Sub).toString()+  "   Position :::  "+Pos);
	
			  
	Con="";	  
		  }
		  
		  
	}
		  
		  public static  void TwoOP(ArrayList Msubset){  
		  
			  Random x = new Random();
				String Con="";
				String one="1";
				String zero="0",Elm="";
				String Copy1 [];
				String Copy2[];
				
			//	  System.out.println(" =----------------");
			//	  System.out.println("    Positions     ");
			  
			  
			  
				  
				  for ( int Sub=0;Sub<Msubset.size();Sub++){
			  
				  
				  String sub= (String) Msubset.get(Sub);
					 
				  String[] s = sub.split(",");
			  
				  int Pos= x.nextInt(s.length);
			//	  System.out.print(" "+Pos);
			  
			      if ((s.length-Pos)>1){
			    	  
			    	  Copy1= new String [Pos];
			    	  Copy2= new String [s.length-Pos];
			    	  
			    	  
			    	  System.arraycopy(s, 0, Copy1, 0, Copy1.length); 
			    	  System.arraycopy(s, Pos, Copy2, 0, Copy2.length); 
			    	  
			  	  ArrayUtils.reverse(Copy2); 
			    	  
			    	  
			    	  for( int  X=0;X<Copy1.length; X++)
						   Con=Con+Copy1[X]+",";  
			    	  
			    	  
			    	  for( int  Y=0;Y<Copy2.length; Y++)
						   Con=Con+Copy2[Y]+",";  
			    	  
			    	  Msubset.set(Sub, Con);
			    	  Con="";
			      }
			  //    System.out.println(" =----------------");
			      
			      
			      
			      
			      
			  
			  
		  
		  }// For subset







		  }
		  
		  
		  public static  void Swap(ArrayList Msubset){    
		  
		  
		  
			  Random X = new Random();
			  Random Y = new Random();
			  int Pos1=0, Pos2=0;
			  boolean Flag=false;
			  
				String Con="";
				String one="1";
				String zero="0",Elm="";
				String Copy1 [];
				String Copy2[];
				
				while(Flag== false){
				
					Pos1= X.nextInt(Msubset.size());
					Pos2= Y.nextInt(Msubset.size());
				 
					if (Pos1!=Pos2)
						Flag=true;
				
		         }
				
				
				
				String sub1= (String) Msubset.get(Pos1);
				String sub2= (String) Msubset.get(Pos2);

				
				 Msubset.set(Pos1, sub2);
				 Msubset.set(Pos2, sub1);

				
				
			//	  System.out.println(" =----------------");
			//	  System.out.println("    Positions     ");
			//  
			//	  System.out.println((Pos1+1)+"        "+(Pos2+1));
		  //
		  
		  
		  
		  
		  
		  }

	private static void foreach(Class<Integer> class1) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
	
	
	
	
	public static String PosGenes(String value, int ALLGENES,int Pkeys[], String insidePos)
    { 
		 char GChGenes [];
		 String GeneRepInPos;
		 
		GeneRepInPos="";
		int Gcount=0, GG=0;
		
        GChGenes  =new char[ALLGENES];
        
        GChGenes=value.toCharArray();
       		 
        insidePos+=Arrays.toString(GChGenes);
       
           while (Gcount<ALLGENES){
       	 
            if(    GChGenes[Gcount]=='1'){

		// System.out.print(Pkeys[Gcount]+",");
           	//  GeneRepInPos=GeneRepInPos+(Gcount+1)+",";
           	 GeneRepInPos=GeneRepInPos+Pkeys[Gcount]+",";
           	 GG++;
              }
  		     Gcount++;


            }
          //		System.out.println("  Genes Len  :: "+GG) ;
           
           
           insidePos+= "\n"+GeneRepInPos;
           insidePos+="\n"+"------------------------------------------------";
  	 //    System.out.println( insidePos);
           
           return GeneRepInPos;
           
           
        
	
    }
	
	
	
	
	
	
	
	
	
	
	
	}
	
	
	
	


