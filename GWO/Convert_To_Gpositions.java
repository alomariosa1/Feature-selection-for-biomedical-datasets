package GWO;



import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Random;
import java.util.StringTokenizer;
public class Convert_To_Gpositions {
	public ArrayList Felement2;
	public char GChGenes [];
	public String GeneRepInPos;
	
	 public String PosGenes(String value, int ALLGENES,int Pkeys[])
     { 
		 
			Random randomGenerator = new Random(System.currentTimeMillis());

		GeneRepInPos="";
		Felement2	= new ArrayList();
		int Gcount=0, GG=0;
		
         GChGenes  =new char[ALLGENES];
         
         GChGenes=value.toCharArray();
        		 
       
        
            while (Gcount<ALLGENES){
        	 
             if(    GChGenes[Gcount]=='1'){

		// System.out.print(Pkeys[Gcount]+",");
            	//  GeneRepInPos=GeneRepInPos+(Gcount+1)+",";
            	 GeneRepInPos=GeneRepInPos+Pkeys[Gcount]+",";
            	 GG++;
               }
   
   		     Gcount++;


             }
            
            if (GG==0)
            	GeneRepInPos=GeneRepInPos+Pkeys[randomGenerator.nextInt(50)]+",";
            
           //		System.out.println("  Genes Len  :: "+GG) ;
            
            
            return GeneRepInPos;
            
            
         
	
     }
	
}
