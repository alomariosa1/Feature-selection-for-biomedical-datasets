
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

public class Trizzin_2_5_2020 { 
	 
	
	
	public void  TrizOperators (char Sol [] ){
		// TODO Auto-generated method stub
		
		
		ArrayList<Integer> Boundary= new ArrayList<Integer>();
		ArrayList<String> SubGroups= new ArrayList<String>();
		
		
		//char  Sol[]={'1','1','0','0','1','0','0','0','0','0','1','1','0','1','1','0','0','0','1','0','1','0','1','0','1','0','1','1','1','1','0','0','0','0','0','1','0','1','0','1','1','0','0','0','0','1','1','0','1','0'};

		
		// Dynamization : Determine No of elements in each group
		 	
				String Concatenation="", Single="";
				int NoGenes=50;
				Random r = new Random();
				int Low = 5;
				int High = 20;	
				int GroupLen = r.nextInt(High-Low) + Low;
				//System.out.println(" GroupLen ::: "+GroupLen);
		 
		 	// End of dynamisation concept
		 
		 // Segmentation : detrmine nof of groups
		
				int TotGroups=0;
				int x= NoGenes / GroupLen;
				int y= NoGenes % GroupLen;
		 
				if (y!=0)
					TotGroups=x+1;
				else
					TotGroups=x;
		 
				//System.out.println(" TotGroups "+TotGroups);
				
		 // End of Segmentation
		
				
		// Local Quality		
		
		 
				int  Boundaryidx=0, bound=0,Bindex=0;
				String GroupBlock="";
				    
				    for (int seg =0;seg<(TotGroups-1);seg++ )	{
					  
					  bound= (int)GroupLen * ( seg+1);
					  
					  Boundary.add(Boundaryidx, bound ); 
					  
					  Boundaryidx++;
				     }
		
		
		
				 for ( int d=0;d<50;d++){  // All component's
				    	
						/*  if ( d==49){
							  
							  
							  GroupBlock+=Sol[d];
				              break;
						  }*/
							  
							  
							  GroupBlock+=Sol[d]+",";
						   
						//	  System.out.println("GroupBlock :::: "+GroupBlock);
							  
						    if(Boundary.contains(d+1) || d==49){  // d-1 since if s=6 then 0--->5 6 elements
							   
						    	SubGroups.add(Bindex,GroupBlock );
						    //	System.out.println("GroupNo  ======"+d+"      "+GroupBlock);
						    	
						    	GroupBlock="";
						    //	d=d-1;
						    	Bindex++;
						    	
						    }  
						    
						    	
						  }	// End For--- all component
				
				
				
				// System.out.println("SubGroups.size() ::::  "+SubGroups.get(0).toString());
				       /* for ( int Sub=0;Sub<SubGroups.size();Sub++){
				       	 System.out.println("Original sub  array : " + SubGroups.get(Sub).toString());
				        }*/
				Mutation(SubGroups); // pass string  (conver array ) return string 
				
				//TwoOP();  // pass string  (convert array ) return string 
				
			//	Swap ();
				        
				
				
				        
				        
				        
				        
				        
				        
				        
				        
				        
				      //  System.out.println(" After mutationm ");
				        
				 /*  for ( int Sub=0;Sub<SubGroups.size();Sub++){
					     System.out.println("Original sub  array : "+(Sub+1)+"   " + SubGroups.get(Sub).toString());
					
					
					
				
					        }    
				        
				        System.out.println("       Two_Operation      ");*/
				        
				        TwoOP(SubGroups);
				       
				        
				    /* for ( int Sub=0;Sub<SubGroups.size();Sub++){
					      
				    	 System.out.println("Original sub  array : "+(Sub+1)+"   " + SubGroups.get(Sub).toString());
					
									
					        }     
				        
				        
				     System.out.println("       Swap     ");*/
				        
				     Swap(SubGroups); 
				        
				    
				   /*  for ( int Sub=0;Sub<SubGroups.size();Sub++){
					      
				    	 System.out.println("Swapped  sub  array : "+(Sub+1)+"   " + SubGroups.get(Sub).toString());
					
									
					        }       
				        
				  
				     
				     System.out.println("       Merging      ");*/
				     
				     for ( int Sub=0;Sub<SubGroups.size();Sub++){
				    	 
				    	 Single=SubGroups.get(Sub).toString();
				    	 Concatenation=Concatenation+Single;
				    	
				     }
				        
				    
				//     System.out.println("  Concatenation : " +Concatenation); 
				     char  [] ch = new char[Sol.length];
				     String [] Sch= Concatenation.split(",");
				     //System.out.println("  Concatenation ch : " +Arrays.toString(ch)); 

				//     System.out.println("  orginal solution ch : " +Arrays.toString(Sol));
				     for( int  z=0;z<Sch.length; z++){
				    	 String S=Sch[z];
				    	 Sol[z]=S.charAt(0);
				     
				     }
				     
				//     System.out.println("  Concatenation ch :    " +Arrays.toString(Sol));
				     
		// End  Local Quality		
				      
				     
				     
				     
/*				
		ArrayList<String> SubSol = new ArrayList<String>();
		
		int[] FArray = new int[] {90,91,92,93,94,95,96,97,98,99,100,101,102,103,104};
		
		 for (int i = 0; i< 20; i++) {
		int Result = r.nextInt(High-Low) + Low;
		 System.out.println("reversed int array : " +Result); 
		 }
		
		int[] iArray = new int[] {101,102,103,104};
		
		
		
		 System.out.println("Original int array : " + Arrays.toString(iArray)); 
		 ArrayUtils.reverse(iArray); 
		 System.out.println("reversed int array : " + Arrays.toString(iArray)); 
int x1=5;
		 for (int i = 0; i< iArray.length; i++) {
			 FArray[x1] = iArray[i];
			 x1++;
			}
		
		 System.out.println("Original int array : " + Arrays.toString(FArray));*/

	}
	
	
	
	
	
	
	
	
	
	
	public static  void Mutation(ArrayList Msubset){
		
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
			  for ( int c=0;c<(int)s.length;c++){
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
	
	
	}
	
	
	
	


