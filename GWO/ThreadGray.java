package GWO;







import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.*;

//********** please check this and change   for(int i=0;i<Runs;i++){

public class ThreadGray implements  Linux_Parameter_Setting1, Runnable  {
	
	
//	private final String[]  = null;
	

	
	
	

	
	
    
    String data;
    String StoreFile;
    int ReducedGenes []; 
    int TotalGenes;
    SVM_Grid.svm_problem AllData ;
    SVM_Grid.svm_problem ReduceData;
    SVM_Grid.svm_parameter MainParma;
    String threading;
    int cycle;
    int Classes;
    


public ThreadGray(String Dataset, String StoreFile, int ReducedGenes [],  int TotalGenes, int Classes, SVM_Grid.svm_problem ReduceData, SVM_Grid.svm_parameter MainParma, String threading, int cycle)                {

	this.data=Dataset;
	this.StoreFile=StoreFile;
	this.ReducedGenes=ReducedGenes;
	this.TotalGenes=TotalGenes;
	this.AllData=AllData;
	this.ReduceData=ReduceData;
	this.MainParma=MainParma;
	this.threading=threading;
	this.cycle=cycle;
	
	this.Classes=Classes;
}




	public  void run ( )  {
		// TODO Auto-generated method stub

		
//		try{
//		
		
















 			String StartDate;

            	        Date date1 = new Date();

         	       StartDate=date1.toString();


		String DataName="";


		  Hybrid_GWO GrayObj=new Hybrid_GWO();
	
		
		

			    	


					
					
				int i=0;
				String path3="";
System .out.println(" before i loop ");
			       for( i=0;i<1;i++){   // No of Runs of Bats
			    	   System .out.println(" #######################################" );	    	   
			    	   System .out.println(" Run  " + (cycle+1) + "     Thread "+threading);
			    	   System .out.println(" #######################################" );	    	   

			    	
				 	
				 			path3=StoreFile+"\\Run"+(cycle+1)+".xlsx";

                                       System .out.println("Path3 :: "+path3);
			    	System .out.println(" the number of genes ===+ "+TotalGenes);
			    	    
			    	try {
			    		GrayObj.RunGray(data,path3,ReducedGenes,TotalGenes,Classes, ReduceData,MainParma,threading);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			 	     
			    	System .out.println(" call " );
			    	     
			    	
			    


		
			       }// No of runs
			    
			    
			     
		
		
		    
		    
		    
		    		    
		    
		    
		    
		
		
	
	 


		          Date date = new Date();
		          System.out.println( "\n Start Date : \n"+StartDate +"\n End Date:\n"+date.toString());
	    
	       

               System.out.println("\n ************************* Parameters ***********************************");
		
	      
               System.out.println( " DataName      ===    "+DataName);

	       System.out.println( " No Of Runs    ===    "+i);
               System.out.println( " SWARM_SIZE    ===    "+SWARM_SIZE);
               System.out.println( " Max_Iteration ===    "+MAX_ITERATION);
               System.out.println( " Freq_min      ===    "+Freq_min);
	       System.out.println( " Freq_max      ===    "+Freq_max);   
               System.out.println( " IntR          ===    "+IntR); 
               System.out.println( " GAlpha (loud) ===    "+GAlpha); 
	       System.out.println( " Alpha         ===    "+Alpha);
	     //   System.out.println( " Gamma         ===    "+Gamma);


               System.out.println("\n ************************************************************************");



//		 }catch(Exception e){}
//
//
//
//
//
//
//		
//	}
//	
	}
	  public static int[] FilteredGenes(int Data){
		  
		
		
		  switch (Data){
		 
		  /*
                        case 0 : 
			
			 // Breast
			   int  DataGenes0 [] ={ 1, 2,	3	,4	,5,	6,	7,	8,	9,	10,	11,	12,	13,	14,	15,	16,	17,	18,	19,	20,	21,	22,23,	24,	25	,26,	27,	28,	29,	30,	31,	32,	33,	34,	35,	36,	37	,38,	39,	40,	41};
          
			   return DataGenes0;

*/

		 
		   case 0 : 
			
			 // Breast
			   int  DataGenes [] ={ 10643, 2585,	3697	,5378	,4732,	24107,	20078,	10889,	11536,	20985,	7814,	6541,	20495,	18425,	644,	20342,	79,	3463,	19585,	18575,	20070,	1889,	9420,	17067,	4370	,11356,	20891,	12857,	13800,	10827,	23006,	11848,	9941,	1033,	6305,	13189,	1558	,10527,	10291,	3524,	8776,	24338,	16272,	3541,	1880,	22864,	16734,	149,	21944,	4171};
          
			   return DataGenes;
		   
		
			 
		   case 1 :
		 	 
			 
			   // MLL
			   int DataGenes1 []= {9741,318,8428,11297,1461,5580,10797,8165,1119,9005,6067,3768,7930,8937,10457,7754,6278,1696,11718,3882,5370,3021,2592,11044,2261,8809,7666,10419,1737,5801,12418,8423,5337,7782,6223,11643,3277,4602,4327,9668,8105,6294,10318,8212,7270,5124,3399,1316,3462,6560};

			   return DataGenes1; 
			   
			   // old CNS
			
		   
		   case 2 :
			   
			   // colon
			   int DataGenes2 [] ={ 765,1582,1672,513,1671,1325,1381,1972,1423,1412,1772,897,286,1473,1346,249,467,1414,493,1153,1771,1917,317,143,1442,245,1637,1248,1411,1867,780,125,377,1730,1042,267,399,1959,1892,698,1200,1002,1058,105,914,1060,807,415,343,1900};

			   return DataGenes2;
			   
		   case 3:
			   // leuk
			   int DataGenes3 [] ={3252,4447,4847,6855,1834,2354,2121,1779,1882,1144,6041,1685,5039,2288,1928,2020,4328,2642,1745,6376,804,4366,7119,1595,2402,2441,758,4438,4196,3433,6281,1829,5171,1725,6201,4951,4389,2363,6797,5593,2833,3847,5254,5833,2546,6539,4680,1909,4373,4211};

			   return DataGenes3;
			   
			   
		   case 4:
			   // leuk 3
			   int DataGenes4 []= {2642,1144,4847,4050,3252,758,1882,1779,2335,6510,1685,1834,5171,6236,6855,6225,2354,2121,5543,6041,4055,4680,1745,1207,2215,2288,4484,4318,6974,6797,2010,4342,4328,4082,2020,6696,1078,4366,804,6373,4438,5688,1725,5300,1928,5039,2833,3469,6803,490};

			   return DataGenes4;
			   
		   case 5:
			   // leuk 4
			   int DataGenes5 []= {2642,1779,4847,4050,3252,2335,1882,1207,758,1834,5389,5171,5543,2354,2121,6225,6855,1745,6510,1685,1144,356,6236,5300,6041,4438,4680,3433,4582,1725,6797,4055,2242,4366,3469,7119,4318,4484,2288,2010,6803,6974,804,6126,2020,4082,3594,6376,2215,6497};

			   return DataGenes5; 
			 
		   case 6:
			   // lymph
			   int DataGenes6 []= {2751,3595,740,997,757,684,1007,2646,3764,2725,768,2122,3739,760,2762,2922,3754,2736,162,756,678,2858,2805,1289,3763,2804,2683,2908,852,1256,2721,1622,758,2733,2669,754,3594,2793,3880,767,1009,2801,163,2695,2626,2774,690,734,3558,2867};

			   return DataGenes6;  
			   
			 
		   case 7:

                         //  CNS
			   int  DataGenes7 []={5637,6810,1632,3315,5459,2917,844,2196,3843,2342,3127,5884,5529,5434,1320,4293,1568,110,2089,1478,49,4484,4259,5922,3878,2142,5476,3320,5528,6252,5513,2149,3006,4644,6108,2996,4588,2032,782,61,5508,5670,3367,3996,2695,5101,2907,237,1192,1474};

			   return DataGenes7;
			 





			   // old 3/3/2016 MLL
			  // int DataGenes7 []= {9741,318,8428,11297,1461,5580,10797,8165,1119,9005,6067,3768,7930,8937,10457,7754,6278,1696,11718,3882,5370,3021,2592,11044,2261,8809,7666,10419,1737,5801,12418,8423,5337,7782,6223,11643,3277,4602,4327,9668,8105,6294,10318,8212,7270,5124,3399,1316,3462,6560};

			//   return DataGenes7; 
			   
		   case 8:
			   // Ovarian
			   int DataGenes8 []= {1679,2237,182,1737,1682,6782,1677,2235,2193,1680,2238,543,1736,2529,1684,2312,2236,1678,1676,1735,1689,1681,2239,181,1738,2191,544,6783,1601,1674,2240,1687,2313,1683,2234,1675,2192,2310,2194,2241,6792,184,1686,2190,2311,2528,1688,541,9606,7383};

			   return DataGenes8; 
			   
		   default:
			   // SRBCT
			   int DataGenes9 []= {1389,1601,187,153,1613,1955,417,246,2046,742,1194,1645,107,2050,2199,545,1003,951,255,1327,509,335,1207,1662,2303,2162,1626,1319,174,1434,1606,1770,1980,188,2203,1489,236,1708,380,976,1932,1673,1954,1884,1263,365,846,442,229,2144};

			   return DataGenes9; 
			   
			 
			 
			 
			 
		 }
		  
		  
		  
		  
		  
		  
		  
		  
	  }
	
	  public static double Get_C_Value (int Data){
		  
		  switch (Data){
			 
			 
		   case 0 : 
			
			 // Breast
         
			   return 10000;
		   
		
			 
		   case 1 :
		 	 
			  // old  CNS
			   

			//   return 1.0E10;
			   
			   
			   
			   //  MLL

			   return 1000000.0; 
			 
		   
		   case 2 :
			   
			   // colon

			   return 1.0E12;
			   
		   case 3:
			   // leuk

			   return 10000.0;
			   
			   
		   case 4:
			   // leuk 3

			   return 1000000.0;
			   
		   case 5:
			   // leuk 4

			   return 1.0E8; 
			 
		   case 6:
			   // lymph

			   return 100.0;  
			   
			 
		   case 7:


                         //  CNS
			   

			   return 1.0E10;



			   // old MLL

			//   return 1000000.0; 
			   
		   case 8:
			   // Ovarian

			   return 10000; 
			   
		   default:
			   // SRBCT

			   return 1000.0; 
			   
			 
			 
			 
			 
		 }
		  
		  
		  
		  
	  }
	  
	  
	  
	  
  public static double Get_Gamma_Value (int Data){
		  
		  switch (Data){
			 
			 
		   case 0 : 
			
			 // Breast
         
			   return 0.00001;
		   
		
			 
		   case 1 :
		 	 
			  // old CNS
			   

			//   return 1.0E-5;
			   
			   
			   //  MLL

			   return 0.001; 
			 
		   
		   case 2 :
			   
			   // colon

			   return 1.0E-5;
			   
		   case 3:
			   // leuk

			   return 1.0E-4;
			   
			   
		   case 4:
			   // leuk 3

			   return 1.0E-4;
			   
		   case 5:
			   // leuk 4

			   return 1.0E-4; 
			 
		   case 6:
			   // lymph

			   return 1.0E-4;  
			   
			 
		   case 7:

                          // CNS
			   

			  return 1.0E-5;



			   // old  MLL

			 //  return 0.001; 
			   
		   case 8:
			   // Ovarian

			   return 1.0E-5; 
			   
		   default:
			   // SRBCT

			   return 1.0E-4; 
			   
			 
			 
			 
			 
		 }
		  
		  
		  
		  
	  }



	
}

