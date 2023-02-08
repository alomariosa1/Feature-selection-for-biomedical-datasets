package SVM_Grid;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;




import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;



/* import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook; */

public class RunGridSearch  implements Linux_Parameter_Setting1 {

	
	Set_C_Gamma_Values best; 
	Set_C_Gamma_Values trail; 
	
	Linux_fitnessbatcross GCGmFitness ; 
	ArrayList<Double> Clist;
	
	ArrayList<Double> Glist;
	
	
	public  BufferedWriter  out ;
	public  StringBuilder Sout;
	
	public int Output=0;
	
	
	   String [] DataCu         ={"Breast",  "MLL",  "Colon"   ,  "Leukemia" , "Leukemia3c" ,  "Leukemia4c" ,  "Lymphoma" ,"CNS", "Ovarian" , "SRBCT",          "9_Tumors","Brain_Tumor1", "Brain_Tumor2", "Lung_Cancer", "Prostate_Tumor"};

		
	    int    [] GenesCounting1={24481  , 12582  , 2000,   7129,  7129, 7129 , 4026 , 7129 , 15154, 2308,      5726,5920,10367,12600,10509 };

	File file=new File("");
	//public void beesrun(int Allgenes,svm_problem probcross,svm_parameter paramcross) throws IOException {

	public void  RunGrid (int Allgenes,svm_problem probcross2,svm_parameter paramcross2, String TrainAllGenes,int Dno, int Class) throws IOException{
		
		
		best= new Set_C_Gamma_Values();
		trail= new Set_C_Gamma_Values();
		GCGmFitness = new Linux_fitnessbatcross();
		Clist=new ArrayList();
		Glist=new ArrayList();
		
		
		
		double baseno=10;
	//	double C,gamma;
		

		Date start = new Date();
		String S= start.toString();
		
	//	for ( int Rst=0; Rst< Results.length; Rst++) {
		
		
		
	//	best.set_CValue(0.01);
	//	best.set_GmValue(0.003);
		
		
		String NEW_LINE = System.getProperty("line.separator");
		
		Sout=new StringBuilder();

		
		
		
		
		
		// Binary   c [1 ..... 10]   BaseNo=10
		// Binary   g [-5..... 5 ]	 BaseNo=10
		
		/* References
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 */
		
				
		// Decimal  c [          ]   BaseNo=2
		// Decimal  g [          ]   BaseNo=2
		
		
		/* References
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 */
		
		
		
		
		for ( double i=0;i<=15;i+=1) //0-15  for ( double i=0;i<=7;i+=1)
			
			Clist.add(Math.pow(baseno,i));
		
		      
		
		
		for ( double j=-5;j<=14;j+=1) // -5 - 15  for ( double j=-5;j<=7;j+=1)
		
			Glist.add(Math.pow(baseno,j));
		
		
		//   best.CValue=10;     //Clist.get(0);
			
		//    best.GmValue=0.01 ;     //Glist.get(0);
		    
		//    paramcross2.C=best.getC_Value();
        //	paramcross2.gamma=best.getGM_Value();
        // 	best.setCrossFitness(GCGmFitness.FitnessValue(TrainAllGenes, Allgenes, probcross2, paramcross2));
		
            paramcross2.C=Clist.get(0);
        	paramcross2.gamma=Glist.get(0);
        	
        	double F=GCGmFitness.FitnessValue(TrainAllGenes, Allgenes, probcross2,probcross2, paramcross2,Dno, Class);
        	best.setCrossFitness(GCGmFitness.ACC);
			best.setSEN(GCGmFitness.SEN);
			best.setSEP(GCGmFitness.SEP);
			best.setFscore(GCGmFitness.Fscore);
		

         	  for (int Cval=0;Cval<Clist.size();Cval++){
      			
		    	  trail.CValue= Clist.get(Cval);
			 
		    	 for (int Gval=0;Gval<Glist.size();Gval++){
				     
		    		 trail.GmValue=Glist.get(Gval);
		    	 
		    	 
		           	paramcross2.C=trail.getC_Value();
		           	paramcross2.gamma=trail.getGM_Value();
		         
		           	
		           			double acc=GCGmFitness.FitnessValue(TrainAllGenes, Allgenes, probcross2,probcross2, paramcross2,Dno, Class);

				           	trail.setCrossFitness(GCGmFitness.ACC);
							trail.setSEN(GCGmFitness.SEN);
							trail.setSEP(GCGmFitness.SEP);
							trail.setFscore(GCGmFitness.Fscore);
							
							
		        	System.out.println("  trail C  ===  "+paramcross2.C+"   Gamma===  "+paramcross2.gamma);
		    		System.out.println("\n  Accuracy === "+GCGmFitness.ACC+"\n"  +" Sen  "+GCGmFitness.SEN+ " SEP  "+GCGmFitness.SEP +"  Fscore  " +GCGmFitness.Fscore);
		    		
		         	
		         	
		         	
		         	Sout.append(NEW_LINE);
		         	Sout.append(NEW_LINE);
		         	 Sout.append("trail.getC_Value()  "+ trail.CValue +"     trail.GmValue"+ trail.GmValue+"  fitness==="+trail.getCrossFitness() +" Sen  "+trail.getSEN()+ " SEP  "+trail.getSEP() +"  Fscore  " +trail.getFscore() );
		         	Sout.append(NEW_LINE);
		         	Sout.append(NEW_LINE);
		         	
			      if( trail.getCrossFitness()>best.getCrossFitness()){
			    	  
			    	  best.CValue=trail.CValue;
			    	  best.GmValue=trail.GmValue;
			    	
					   best.CrossFitness=trail.getCrossFitness();
			    	    best.SEN=trail.getSEN();
						 best.SEP=trail.getSEP();
						 best.Fscore=trail.getFscore();


						
			      }
		         	
		    	//	System.out.println( "       Iteration  "+Cval + " SubIter  "+Gval + " Best fittness ==  "+best.getCrossFitness() +"  C  "+ paramcross2.C+"   G  "+paramcross2.gamma);   

		    	 
		    	 
			 Sout.append(NEW_LINE);
		      Sout.append(NEW_LINE);
		      Sout.append("C===  "+best.getC_Value() +"   Gamma=== "+best.getGM_Value()+" Fitness=== "+best.getCrossFitness()   +" Sen  "+best.getSEN()+ " SEP  "+best.getSEP() +"  Fscore  " +best.getFscore() );
		System.out.print("C===  "+best.getC_Value() +"   Gamma=== "+best.getGM_Value()+" Fitness=== "+best.getCrossFitness()  +" Sen  "+best.getSEN()+ " SEP  "+best.getSEP() +"  Fscore  " +best.getFscore()     );
		      Sout.append(NEW_LINE);
		      Sout.append(NEW_LINE);
         	
         	
		  	Date End = new Date();
			String E= End.toString();
       
			System.out.print("C== start "+ S +"\n"+ "    End   "+E);
		
		

 		System.out.print( "       END              END   ");
		
		
 		Output ();
		
		}
			 }
		      
		      /*
		      Sout.append(NEW_LINE);
		      Sout.append(NEW_LINE);
		      Sout.append("C===  "+best.getC_Value() +"   Gamma=== "+best.getGM_Value()+" Fitness=== "+best.getCrossFitness()   +" Sen  "+best.getSEN()+ " SEP  "+best.getSEP() +"  Fscore  " +best.getFscore() );
		System.out.print("C===  "+best.getC_Value() +"   Gamma=== "+best.getGM_Value()+" Fitness=== "+best.getCrossFitness()  +" Sen  "+best.getSEN()+ " SEP  "+best.getSEP() +"  Fscore  " +best.getFscore()     );
		      Sout.append(NEW_LINE);
		      Sout.append(NEW_LINE);
         	
         	
		  	Date End = new Date();
			String E= End.toString();
       
			System.out.print("C== start "+ S +"\n"+ "    End   "+E);
		
		

 		System.out.print( "       END              END   ");
		
		
 		Output ();
		
		*/
	}
	
	
	
	public void  Output (){
		
	
		
		String path = file.getAbsolutePath();

	System.out.print( " File path "+ path);

	path+="/Results/SvmParameter.txt";
		
		
try {
			
	// output
	// 	Gloaddata.datafilename="C:\\Users\\alomari\\Dropbox\\Zaid & Osama\\FPA Java Code\\TestSVM_AR5\\SVM-Format\\Scale.txt";

	  FileWriter fstream = new FileWriter(path);
		  //  FileWriter fstream = new FileWriter("C:\\Users\\User\\Desktop\\MRMR check\\Best C_Gamma To each dataset\\All Best C and Gamma base 10\\"+Sdata[Output]);
		    //"C:\\Users\\User\\Desktop\\Gene Work\\hill\\newacurracy.txt"


		    out= new BufferedWriter(fstream);
			out.write("Writing line one to file");
			out.newLine();
			out.write("Writing line two to file");
			
			
		
		
				
		    
			 
			 
			 
			 
		out.newLine();
		
		out.write(Sout.toString());
		out.newLine();
		out.newLine();
		out.write("----------------------------------------------------------------");
		out.write("-----------------------Iteration: -------------------------");
		out.write("----------------------------------------------------------------");

     

        out.write("----------------------------------------------------------------");
        out.write("-----------------------    END     -----------------------------");

		} catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            //Close the BufferedWriter
            try {
                if (out != null) {
                	out.flush();
                	out.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }




		Output++;
		
	}
	
		
	
	
}
