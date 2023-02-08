package GWO;




import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
public class Convert_To_Binary {
	public ArrayList Felement1;
	public char GChGenes [];
	
	 public String BinGenes(int keys[],String value, int dim)
     { 
		 
		Felement1	= new ArrayList();
		
		
		GChGenes  =new char[dim];
        
        for( int i=0 ;i<dim;i++)
       	 
        	GChGenes[i]='0';
        
        
        
		
	  StringTokenizer str = new StringTokenizer(value,",");

      String next, BinCombin="";
      int variableValue;
      int counts=0,index1=0;
       
      
      Felement1.clear();
      
      
    // Insert poistions into Arraylist
       
          while (str.hasMoreTokens())
              {
              //get next token
               next = str.nextToken();
              //convert to double
              variableValue =Integer.valueOf(next).intValue();
             //add double to array
               Felement1.add(counts,variableValue);
               counts++;
            
               }
          
         
             for( int index=0; index<Felement1.size(); index++){
             
            //	 System.out.print(" ,"+Felement1.get(index));
            	   // GChGenes[( (Integer) Felement1.get(index)-1)]='1';
            	 
            	 
            	     index1 = indexOf(keys,(Integer)Felement1.get(index),0);
            	    GChGenes[index1]='1';
             }
             BinCombin=String.valueOf(GChGenes);
          
        //  System.out.println("BinCombin======"+BinCombin);
             
          return BinCombin;
          
	 
  }
	 public  int indexOf(int[] array, int valueToFind, int startIndex) {
			int INDEX_NOT_FOUND = -1;;
			
			
			
			if (array == null) {
		          return INDEX_NOT_FOUND;
		      }
		      if (startIndex < 0) {
		          startIndex = 0;
		      }
		      for (int i = startIndex; i < array.length; i++) {
		          if (valueToFind == array[i]) {
		              return i;
		          }
		      }
		      return INDEX_NOT_FOUND;
		  }
}
