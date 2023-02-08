package GWO;


import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.*;

import java.util.Date;

public class mainproject1 implements  Linux_Parameter_Setting1 {
	
	
	public    BufferedWriter out ; 
	public   SVM_Grid.svm_parameter   paramcross1;
	public   SVM_Grid.svm_problem     probcross1;
	public   SVM_Grid.svm_problem     Subproblem;

	public   int AllGenes,Datacounter,SubRun;
	
	//public  A_Genetic_alg Batobj;

	
	public  int elite_Genes[];
	
	public  String  path1;
	public  String  path2;
	public  String  path3;
	
	public  String  Th;
	public  int j;

	

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		   BufferedWriter out ; 
		  SVM_Grid.svm_parameter   paramcross1;
		  SVM_Grid.svm_problem     probcross1;
		  
		   int AllGenes,Datacounter=0,SubRun,j;
		   int elite_Genes[];
		   String  path1,path2,path3,Th;
		 
		 




		

	String DataName="";

	
	paramcross1 = new SVM_Grid.svm_parameter();
	probcross1  = new SVM_Grid.svm_problem();
	SVM_Grid.GeneTraincrossvalid  Gloaddata=new SVM_Grid.GeneTraincrossvalid();
 
 

	double C, Gamma;
	File file=new File("");
	
	
	String path = "";
	path="Results/";
	
	System.out.println( "  path file :: "+path);

	
	

System.out.println( " Data index =="+Datacounter );
 	


// Choose Data


Datacounter=5;

 	while (Datacounter<6){
 		
 		
 		
 	 
		elite_Genes= FilteredGenes (Datacounter);
 	
       	C=Get_C_Value(Datacounter);
 	
 	    Gamma=Get_Gamma_Value(Datacounter);
 	
 	    DataName=  data[Datacounter];
 	   int NoOfG= 50;
 	    int Classes=  Data_Classes [Datacounter]; 


 	   
	  path1="alomariosa1/Feature-selection-for-biomedical-datasets/datasets"+

	       System .out.println(" path ::: "+path);

	       System .out.println(" path 1::: "+path1);


	       Gloaddata.datafilename=path1;

	       path2=path+"GWO/"+ExResults[Datacounter];

	  
	  
	    System .out.println(" before load ::: " +path2);
 
	    Gloaddata.read_problem(NoOfG,Gloaddata.datafilename,elite_Genes);
	
	
	
	    paramcross1=Gloaddata.param;
	    paramcross1.C=C;
	    paramcross1.gamma=Gamma;
	    probcross1=Gloaddata.Subprob;
		
		
		
			
		String Result= Results[Datacounter];
		  	
		String StartDate;
	    	Date date1 = new Date();
	    	StartDate=date1.toString();



try{




Thread t1 = new Thread(new ThreadGray(DataName,path2,elite_Genes,NoOfG, Classes,probcross1,paramcross1, "Thread one",0 ));
      


    Thread t2 = new Thread(new ThreadGray(DataName,path2,elite_Genes,NoOfG, Classes, probcross1,paramcross1, "Thread two",1 ));
    Thread t3 = new Thread(new ThreadGray(DataName,path2,elite_Genes,NoOfG,Classes, probcross1,paramcross1, "Thread three",2));
    Thread t4 = new Thread(new ThreadGray(DataName,path2,elite_Genes,NoOfG,Classes, probcross1,paramcross1, "Thread four",3));
 	Thread t5 = new Thread(new ThreadGray(DataName,path2,elite_Genes,NoOfG,Classes, probcross1,paramcross1, "Thread five",4));
 	Thread t6 = new Thread(new ThreadGray(DataName,path2,elite_Genes,NoOfG,Classes, probcross1,paramcross1, "Thread six",5));
 	Thread t7 = new Thread(new ThreadGray(DataName,path2,elite_Genes,NoOfG,Classes, probcross1,paramcross1, "Thread seven",6));
 	Thread t8 = new Thread(new ThreadGray(DataName,path2,elite_Genes,NoOfG,Classes, probcross1,paramcross1, "Thread eight",7));
 	Thread t9 = new Thread(new ThreadGray(DataName,path2,elite_Genes,NoOfG,Classes, probcross1,paramcross1, "Thread nine",8));
 	Thread t10 = new Thread(new ThreadGray(DataName,path2,elite_Genes,NoOfG,Classes, probcross1,paramcross1, "Thread ten",9));

	
    Thread t11 = new Thread(new ThreadGray(DataName,path2,elite_Genes,NoOfG,Classes, probcross1,paramcross1, "Thread 11",10));
    Thread t12 = new Thread(new ThreadGray(DataName,path2,elite_Genes,NoOfG,Classes, probcross1,paramcross1, "Thread 12",11));
    Thread t13 = new Thread(new ThreadGray(DataName,path2,elite_Genes,NoOfG,Classes, probcross1,paramcross1, "Thread 13",12));
    Thread t14 = new Thread(new ThreadGray(DataName,path2,elite_Genes,NoOfG,Classes, probcross1,paramcross1, "Thread 14",13));
 	Thread t15 = new Thread(new ThreadGray(DataName,path2,elite_Genes,NoOfG,Classes, probcross1,paramcross1, "Thread 15",14));
 	Thread t16 = new Thread(new ThreadGray(DataName,path2,elite_Genes,NoOfG,Classes, probcross1,paramcross1, "Thread 16",15));
 	Thread t17 = new Thread(new ThreadGray(DataName,path2,elite_Genes,NoOfG,Classes, probcross1,paramcross1, "Thread 17",16));
 	Thread t18 = new Thread(new ThreadGray(DataName,path2,elite_Genes,NoOfG,Classes, probcross1,paramcross1, "Thread 18",17));
 	Thread t19 = new Thread(new ThreadGray(DataName,path2,elite_Genes,NoOfG,Classes, probcross1,paramcross1, "Thread 19",18));
 	Thread t20 = new Thread(new ThreadGray(DataName,path2,elite_Genes,NoOfG,Classes, probcross1,paramcross1, "Thread 20",19));


	Thread t21 = new Thread(new ThreadGray(DataName,path2,elite_Genes,NoOfG,Classes, probcross1,paramcross1, "Thread 21",20));
    Thread t22 = new Thread(new ThreadGray(DataName,path2,elite_Genes,NoOfG,Classes, probcross1,paramcross1, "Thread 22",21));
    Thread t23 = new Thread(new ThreadGray(DataName,path2,elite_Genes,NoOfG,Classes, probcross1,paramcross1, "Thread 23",22));
    Thread t24 = new Thread(new ThreadGray(DataName,path2,elite_Genes,NoOfG,Classes, probcross1,paramcross1, "Thread 24",23));

 	Thread t25 = new Thread(new ThreadGray(DataName,path2,elite_Genes,NoOfG,Classes, probcross1,paramcross1, "Thread 25",24));

 	Thread t26 = new Thread(new ThreadGray(DataName,path2,elite_Genes,NoOfG,Classes, probcross1,paramcross1, "Thread 26",25));
 	Thread t27 = new Thread(new ThreadGray(DataName,path2,elite_Genes,NoOfG,Classes, probcross1,paramcross1, "Thread 27",26));
 	Thread t28 = new Thread(new ThreadGray(DataName,path2,elite_Genes,NoOfG,Classes, probcross1,paramcross1, "Thread 28",27));
 	Thread t29 = new Thread(new ThreadGray(DataName,path2,elite_Genes,NoOfG,Classes, probcross1,paramcross1, "Thread 29",28));
 	Thread t30 = new Thread(new ThreadGray(DataName,path2,elite_Genes,NoOfG,Classes, probcross1,paramcross1, "Thread 30",29));










t1.start();
/*
t2.start();

t3.start();

t4.start();
t5.start();

t6.start();
t7.start();
t8.start();
t9.start();
t10.start();

t11.start();
t12.start();
t13.start();
t14.start();
t15.start();
t16.start();
t17.start();
t18.start();
t19.start();
t20.start();	


t21.start();
t22.start();
t23.start();
t24.start();
t25.start();

t26.start();
t27.start();
t28.start();
t29.start();
t30.start();
*/

}catch (Exception e){}

		
	 Date date = new Date();
			 		    String End=date.toString();
			 		 
						long diff = date.getTime() - date1.getTime();
						long diffSeconds = diff / 1000 % 60;
						long diffMinutes = diff / (60 * 1000) % 60;

  System.out.println(" The thread start at :   Start at "+StartDate);	
System.out.println(" The threads finish at :   End   at "+End);

System.out.println(" The differnce in minutes  :"+diffMinutes +"  Seconds : "+diffSeconds);	

Datacounter++;
 	}



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
		   
		//  int  DataGenes [] ={ 10643, 2585,	3697	,5378	,4732,	24107,	20078,	10889,	11536,	20985,	7814,	6541,	20495,	18425,	644,	20342,	79,	3463,	19585,	18575,	20070,	1889,	9420,	17067,	4370	,11356,	20891,	12857,	13800,	10827,	23006,	11848,	9941,	1033,	6305,	13189,	1558	,10527,	10291,	3524,	8776,	24338,	16272,	3541,	1880,	22864,	16734,	149,	21944,	4171};
     
		  // EnsMRMR
		   int  DataGenes [] ={  10889,3463,20078,19585,7814,11536,20342,18575,644,4732,10643,13800,16894,18425,9097,1889,6541,3697,10827,24107,24338,3600,8776,9420,12857,20495,3541,16272,8662,18332,16744,6305,20891,4459,18811,13189,21944,1872,21725,9941,702,79,16223,14696,23100,7790,1831,24108,12572,13917};		   
		   
		   
		   
		   
		   return DataGenes;
	   
	
		 
	   case 1 :
	 	 
		 
		   // MLL
		 //  int DataGenes1 []= {9741,318,8428,11297,1461,5580,10797,8165,1119,9005,6067,3768,7930,8937,10457,7754,6278,1696,11718,3882,5370,3021,2592,11044,2261,8809,7666,10419,1737,5801,12418,8423,5337,7782,6223,11643,3277,4602,4327,9668,8105,6294,10318,8212,7270,5124,3399,1316,3462,6560};

		 // EnsMRMR
		   
		 int DataGenes1 []= {  3634,11297,8428,7754,9741,12418,1176,2592,1119,8937,10797,9005,6067,6278,3768,5370,10419,7930,10457,318,7666,8050,8165,5801,8749,3882,8518,7782,1461,8212,11718,8098,5265,11603,11643,7835,8585,3462,6560,3277,7131,8423,9832,6294,6223,11864,4602,2770,9668,2261};



		   
		   
		   return DataGenes1; 
		   
		   // old CNS
		
	   
	   case 2 :
		   
		   // colon
		  // int DataGenes2 [] ={ 765,1582,1672,513,1671,1325,1381,1972,1423,1412,1772,897,286,1473,1346,249,467,1414,493,1153,1771,1917,317,143,1442,245,1637,1248,1411,1867,780,125,377,1730,1042,267,399,1959,1892,698,1200,1002,1058,105,914,1060,807,415,343,1900};

		 
		 int DataGenes2 [] ={ 249,1772,1972,1423,1671,1582,765,286,681,513,1325,897,1381,186,493,1473,780,245,467,30,1771,377,1412,66,1248,399,119,267,914,625,35,1892,1060,1917,354,1637,1047,43,1411,1058,1455,1867,1668,415,1153,1672,1042,1335,516,143};
		   
		   
		   return DataGenes2;
		   
	   case 3:
		   // leuk
		//  int DataGenes3 [] ={3252,4447,4847,6855,1834,2354,2121,1779,1882,1144,6041,1685,5039,2288,1928,2020,4328,2642,1745,6376,804,4366,7119,1595,2402,2441,758,4438,4196,3433,6281,1829,5171,1725,6201,4951,4389,2363,6797,5593,2833,3847,5254,5833,2546,6539,4680,1909,4373,4211};

		 // EnsMRMR
		   int DataGenes3 [] ={3252,4847,6855,1834,1882,1779,2354,6041,4196,4951,2288,1685,1144,2121,3714,2402,4366,1928,2020,538,6539,4328,2642,760,1745,6376,3320,5772,1829,5107,4389,6201,804,4377,4373,2363,3104,5552,758,6281,6078,6215,2546,3899,6225,1595,1674,461,3847,3183};
		   
		   
		   return DataGenes3;
		   
		   
	   case 4:
		   // leuk 3
		// int DataGenes4 []= {2642,1144,4847,4050,3252,758,1882,1779,2335,6510,1685,1834,5171,6236,6855,6225,2354,2121,5543,6041,4055,4680,1745,1207,2215,2288,4484,4318,6974,6797,2010,4342,4328,4082,2020,6696,1078,4366,804,6373,4438,5688,1725,5300,1928,5039,2833,3469,6803,490};

		  // EnsMRMR
		 int DataGenes4 []= { 2642,4050,3252,4847,6855,6510,1882,2335,6225,1834,6236,1779,758,1207,6041,5543,5171,4196,1685,4055,2354,5300,4318,1144,1745,4484,4342,2288,3469,2833,4017,4680,6376,6606,2121,6974,6696,5466,2215,4328,4366,1078,4951,5552,2020,6228,1928,5542,2010,6803};
		   
		   
		   return DataGenes4;
		   
	   case 5:
		   // leuk 4
	//  int DataGenes5 []= {2642,1779,4847,4050,3252,2335,1882,1207,758,1834,5389,5171,5543,2354,2121,6225,6855,1745,6510,1685,1144,356,6236,5300,6041,4438,4680,3433,4582,1725,6797,4055,2242,4366,3469,7119,4318,4484,2288,2010,6803,6974,804,6126,2020,4082,3594,6376,2215,6497};

		 // EnsMRMR
		   int DataGenes5 []= { 1834,2642,4050,3252,4847,5300,1882,1207,6225,1779,2335,6510,4366,6376,5543,6855,2121,3433,6236,2354,5171,2242,3469,6041,4017,758,1745,4484,1685,356,4196,4318,4291,5956,1144,4342,4951,2288,4055,5389,1829,6974,703,6803,4438,3631,6606,2833,2402,4586};
		   
		   return DataGenes5; 
		 
	   case 6:
		   // lymph
		 
	// int DataGenes6 []= {2751,3595,740,997,757,684,1007,2646,3764,2725,768,2122,3739,760,2762,2922,3754,2736,162,756,678,2858,2805,1289,3763,2804,2683,2908,852,1256,2721,1622,758,2733,2669,754,3594,2793,3880,767,1009,2801,163,2695,2626,2774,690,734,3558,2867};

		   // EnsMRMR
		   
		   int DataGenes6 []= {  2736,2122,3754,2922,2801,2793,757,678,3763,740,2750,684,2733,162,2858,3760,2875,2738,734,2779,2722,3595,3704,2805,1007,997,758,3783,2804,2874,1269,2909,2747,712,2758,2684,690,2721,2806,2762,1289,754,2674,2803,3738,768,2807,276,3733,634};
		   
		   return DataGenes6;  
		   
		 
	   case 7:

                    //  CNS
		 
		// int  DataGenes7 []={5637,6810,1632,3315,5459,2917,844,2196,3843,2342,3127,5884,5529,5434,1320,4293,1568,110,2089,1478,49,4484,4259,5922,3878,2142,5476,3320,5528,6252,5513,2149,3006,4644,6108,2996,4588,2032,782,61,5508,5670,3367,3996,2695,5101,2907,237,1192,1474};

		// EnsMRMR
		  int  DataGenes7 []={  2474,5637,1320,6565,844,3127,5922,1124,2196,2404,1698,2695,6810,3315,1632,3320,4606,1474,3185,5433,2996,4484,2917,237,5528,1478,2149,5068,2089,1788,6252,360,1984,2093,4588,5884,3420,1577,1877,2142,339,2761,2513,654,1697,1352,110,3878,5513,5402};
		   
		   
		   return DataGenes7;
		 





		   // old 3/3/2016 MLL
		  // int DataGenes7 []= {9741,318,8428,11297,1461,5580,10797,8165,1119,9005,6067,3768,7930,8937,10457,7754,6278,1696,11718,3882,5370,3021,2592,11044,2261,8809,7666,10419,1737,5801,12418,8423,5337,7782,6223,11643,3277,4602,4327,9668,8105,6294,10318,8212,7270,5124,3399,1316,3462,6560};

		//   return DataGenes7; 
		   
	   case 8:
		   // Ovarian
	//	 int DataGenes8 []= {1679,2237,182,1737,1682,6782,1677,2235,2193,1680,2238,543,1736,2529,1684,2312,2236,1678,1676,1735,1689,1681,2239,181,1738,2191,544,6783,1601,1674,2240,1687,2313,1683,2234,1675,2192,2310,2194,2241,6792,184,1686,2190,2311,2528,1688,541,9606,7383};

		
		   // ENSMRMR
		  int DataGenes8 []= {  1680,1,2237,1679,1737,1682,1677,181,2238,1681,1678,1736,2236,6782,1683,2530,2192,544,2239,1684,13139,2235,1735,1676,2193,182,2312,13988,1689,1738,1601,2240,2311,7374,1687,1494,2191,543,8841,1686,9597,2241,1675,2340,1685,2310,542,2234,1688,6772};
		   
		   return DataGenes8; 
		   
	   case 9:
		   // SRBCT
		  
		   
		// int DataGenes9 []= {1389,1601,187,153,1613,1955,417,246,2046,742,1194,1645,107,2050,2199,545,1003,951,255,1327,509,335,1207,1662,2303,2162,1626,1319,174,1434,1606,1770,1980,188,2203,1489,236,1708,380,976,1932,1673,1954,1884,1263,365,846,442,229,2144};

		  // ENSMRMR
		   
		 int DataGenes9 []= {  1389,742,187,1601,153,1645,1955,246,417,1194,2162,2050,1003,545,2046,335,1613,509,255,1708,1386,236,2303,1662,1327,1884,107,1980,251,123,1158,2199,1954,1207,1434,1606,174,976,1319,846,256,380,1,1673,1626,1655,1932,867,842,365};
		   
		   
		   return DataGenes9; 
		   
		 
	   case 11:
		   // brain 1
		   /// MRMR 3489,4421,2093,2030,1429,5300,5453,216,1627,1449,675,2913,4146,1773,3834,1251,4852,921,4557,2357,4801,5604,1854,5512,2116,2268,5517,3240,185,4822,3113,3995,2295,1026,2618,2716,2256,964,1955,5043,1710,206,309,467,2532,4893,745,1746,868,5361
		   
		//  int DataGenes11 []={3489,4421,2093,2030,1429,5300,5453,216,1627,1449,675,2913,4146,1773,3834,1251,4852,921,4557,2357,4801,5604,1854,5512,2116,2268,5517,3240,185,4822,3113,3995,2295,1026,2618,2716,2256,964,1955,5043,1710,206,309,467,2532,4893,745,1746,868,5361};

		   
		   
			 //Ens
			 int DataGenes11 []={		2532,2030,1251,1449,1773,2093,5604,3113,3489,206,921,3995,4146,5517,2330,1048,2357,964,3834,1972,52,3767,505,237,4421,3745,4841,4822,675,1854,2116,607,1298,2733,5361,3094,2295,1627,1429,3434,497,467,1746,5799,2913,5512,2268,5300,2507,5853			};
			 
			   return DataGenes11; 
		 
	   case 12:
		   
		   // brain 2
		   
		///   MRMR 326,4613,9043,7169,9711,7926,4061,4649,7581,9524,6832,257,9071,4246,9857,7739,3276,5819,6829,6606,2977,10337,10343,2813,745,8967,2313,6102,10011,6205,8419,7962,10055,969,4884,10341,4701,1188,10160,4858,3966,1795,563,8134,10347,4141,9902,3150,3730,7027
		   
	// int DataGenes12 []={326,4613,9043,7169,9711,7926,4061,4649,7581,9524,6832,257,9071,4246,9857,7739,3276,5819,6829,6606,2977,10337,10343,2813,745,8967,2313,6102,10011,6205,8419,7962,10055,969,4884,10341,4701,1188,10160,4858,3966,1795,563,8134,10347,4141,9902,3150,3730,7027};

		   
		   //Ens
		 int DataGenes12 []={	 8967,9711,326,9043,7169,3743,745,4649,9857,9357,6829,7926,10343,2416,4061,10337,9524,6832,7426,10341,4701,4884,7962,3276,919,9071,969,1810,8131,2977,1377,6753,104,5568,10347,3568,9902,2009,6527,5566,10011,6606,180,8319,6403,2313,2813,257,1245,2496};
		   
		   return DataGenes12; 
			   
			   
			   
	   case 13:
		   // lung
		   
		   // MRMR 1422,12438,12052,11300,10188,7109,9937,5031,8294,10128,9038,9134,9770,8484,8457,10139,6513,9733,6655,3875,8986,12524,5835,10175,9840,8429,7568,5292,8808,6839,5920,8130,7834,4778,4772,5407,8890,9170,7052,8831,8125,7617,4986,8156,4788,9093,6539,11943,6125,10381
		   
		//int DataGenes13[]={1422,12438,12052,11300,10188,7109,9937,5031,8294,10128,9038,9134,9770,8484,8457,10139,6513,9733,6655,3875,8986,12524,5835,10175,9840,8429,7568,5292,8808,6839,5920,8130,7834,4778,4772,5407,8890,9170,7052,8831,8125,7617,4986,8156,4788,9093,6539,11943,6125,10381};

		   //Ens
		 int DataGenes13[]={	  8484,9937,11300,7568,12438,1422,10188,8457,3191,9038,5920,5292,10139,10175,10891,7199,5835,9250,10128,8125,12052,3875,9770,5996,4525,8130,2478,7109,9134,12524,6513,8472,4788,10892,7617,8890,4452,8443,8156,9733,4244,9170,9672,8766,3227,8882,9840,12097,10138,5968};
		   
		   return DataGenes13; 
		   
		   
	  default:
		  
		  // prostate
		  
		  // MRMR  4823,364,7451,7574,10130,8468,9878,8220,9138,3200,7515,3124,5648,2665,7652,9949,9839,4126,2596,2718,8901,8009,3121,4564,8801,8765,1943,6715,5815,8967,8858,6640,893,7346,7538,8545,7654,1881,8426,120,5714,6821,5461,7520,7824,5647,9890,7372,7347,6105
		  
		//  int DataGenes14[]={4823,364,7451,7574,10130,8468,9878,8220,9138,3200,7515,3124,5648,2665,7652,9949,9839,4126,2596,2718,8901,8009,3121,4564,8801,8765,1943,6715,5815,8967,8858,6640,893,7346,7538,8545,7654,1881,8426,120,5714,6821,5461,7520,7824,5647,9890,7372,7347,6105};

		  
		  //Ens
		   int DataGenes14[]={	   4823,7451,5521,10130,9138,8220,8468,7061,7515,3200,3124,7652,6168,8765,7574,5461,4564,9878,9839,8009,5074,8801,5815,6884,5648,2718,7346,5714,2596,8967,120,8545,1943,2665,3089,10125,7520,8858,6640,6821,5014,7567,9949,364,8046,1881,5460,5647,7372,4520};
		   
		   return DataGenes14;  
			   
		   
		   
		   
		   
		   
		   
		   
		   
		   
		   
		 
		 
		 
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

		//   return 1000000.0;  old before 1-2017
		 
		   return   10000.0;
		   
		   
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
		   
	   case 9:
		   // SRBCT

		   return 1000.0; 
		   
		   
		   
		   
		 
		 
	   case 10:
		   //9tumors
	  // return 100000.0; org
		   return 1000.0;
	   
	   case 11:
	// brain1
		   return 1000.0;
		   
	   case 12:
			 // brain2 
			   return 1000;
	   case 13:
		    
		   // lung
		   return 1000.0; 
		 
	   default:
		   // prostate
		   return  100.0;
		   
		   
		   
		   
		   
		   
		   
		   
		   
		 
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

		//    return 0.001;  old one before 1-2017
		   
		  return   1.0E-4;
		   
		//   return   0.001;
		 
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

		 //  return 1.0E-4;   old one before 1-2017
		   
		   return  0.001;
		   
		 
	   case 7:

                     // CNS
		   

		  return 1.0E-5;



		   // old  MLL

		 //  return 0.001; 
		   
	   case 8:
		   // Ovarian

		   return 1.0E-5; 
		   
	   case 9:
		   // SRBCT

		   return 1.0E-4; 
		   
		 
		   
		   
	   case 10:
		   //9-tumors
		//   return 0.001; org
		   return 0.001;
	   case 11:
		   // B1
			//org   return 1.0E-3;
		   return 1.0E-3;
		   
	   case 12:
		// B2
		//org   return 1.0E-3;
		   return 1.0E-2;
		   
	   case 13:
		   // Lung
		 //org   return 1.0E-3;
		   return 1.0E-2;
		default:
			// Prostate
			 //org 	return 1.0E-4;
			return 1.0E-4;
		   
		   
		 
		 
		 
	 }
	  
	  
	  
	  
 }

 
 
 
 
 
 
 

}
