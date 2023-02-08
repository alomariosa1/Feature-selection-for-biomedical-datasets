package SVM_Grid;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


import java.text.DecimalFormat;
import java.util.*;
import java.io.IOException;
import java.lang.String.*;
import java.lang.reflect.Array;
import java.util.ArrayList.*;
/*
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
*/




public class Parameter_selection implements Linux_Parameter_Setting1 {
	
	
	
	
//	public  static  BufferedWriter out ; 
	public  static svm_parameter   paramcross1;
	public  static svm_problem     probcross1;
	public  static RunGridSearch   GridObj;
	//public   int AllGenes;
	
	DecimalFormat formatter = new DecimalFormat("#0.000");
	   static String [] DataCu         ={"Breast",  "MLL",  "Colon"   ,  "Leukemia" , "Leukemia3c" ,  "Leukemia4c" ,  "Lymphoma" ,"CNS", "Ovarian" , "SRBCT",          "9_Tumors","Brain_Tumor1", "Brain_Tumor2", "Lung_Cancer", "Prostate_Tumor"};

	
	   static int    [] GenesCounting1={24481  , 12582  , 2000,   7129,  7129, 7129 , 4026 , 7129 , 15154, 2308,      5726,5920,10367,12600,10509 };


	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

				
		//int ALLGenes=  ???? Data set ( breast , CNS, Leukemia, SRBCT)
			
		  paramcross1 = new svm_parameter();
	         paramcross1.svm_type=0;
	         paramcross1.kernel_type=2;
	 	      probcross1  = new svm_problem();
	 	     GridObj = new RunGridSearch();
	 	    
	 	     GeneTraincrossvalid  Gloaddata=new GeneTraincrossvalid();
		
		
		
	 	    int Datai=10;
		while (Datai<11) {
		
		
		
		
		int ALLGenes=GenesCounting1[Datai];
				
				
		
		
		
		
		int Gcount=0;
		
		String GeneRepInPos="";
		
		int[] WholeGenes= new int [ALLGenes];
         while (Gcount<ALLGenes){
        	 
            
             GeneRepInPos=GeneRepInPos+(Gcount+1)+",";
             WholeGenes[Gcount]=(Gcount+1);
            		 
   		      Gcount++;
   		 
             }
         
            
       
// load data
      //   Gloaddata.datafilename= "C:\\Users\\alomari\\workspace\\GeneSelection2017\\Bat\\src\\Bat2017\\DataScale2017\\New Data\\"+DataCu[Datai]+"\\"+DataCu[Datai]+"Scale.txt";
 	  //  Gloaddata.datafilename= "C:\\Users\\User\\Desktop\\MRMR check\\Best C_Gamma To each dataset\\"+DataCu[Datai];
 	 

/* File file=new File("");

String path = file.getAbsolutePath();

	System.out.print( " File path "+ path);

	path+="/Data/AR_5AllData.txt"; */


Gloaddata.datafilename= "/gclst/alomariosa/SVM_Grid/Data/Task_12_CrossValidation/T-FDF_12Sub_64ChLibsvmTreated.txt";


	// Gloaddata.datafilename="C:\\Users\\alomari\\Dropbox\\Zaid & Osama\\FPA Java Code\\TestSVM_AR5\\SVM-Format\\AR_5AllData.txt";

 	 //	Gloaddata.datafilename=path;
		
		
		String WholeGenes1="";
 	 	int ALLG=64;
		int Class=12;
 	 	 int[] elite_Genes = new int [ALLG];
 		for (int i=0;i<ALLG;i++){
 	  		elite_Genes [i]=(i+1);
 	  		WholeGenes1+=(i+1)+",";
 		}
 		System.out.println(" Genes "+WholeGenes1);
 	 	
 	 	
 	     
 	     Gloaddata.read_problem(ALLG,Gloaddata.datafilename,elite_Genes);
 		
 	
 		
 		 paramcross1=(svm_parameter)Gloaddata.param.clone();
 		
 		
 		 probcross1=Gloaddata.prob;
 		
 		GridObj.RunGrid(ALLG, probcross1, paramcross1, WholeGenes1,Datai, Class);
 		
 		System.out.print( "\n"+"  positions  " +GeneRepInPos+"\n" );
 		
 		
 		
 		
            
    
		
		
		
		
 		//GridObj.Output(Datai);
		
		
		
 	//	System.out.print( "       END              END   "+" Data Name =="+data[ Datai]+"    No of Genes === "+Gcount );
 		Datai++;
		}
		
		System.out.print( "\n"+"  Data  " +Gloaddata.datafilename+"\n" );
	}

}
