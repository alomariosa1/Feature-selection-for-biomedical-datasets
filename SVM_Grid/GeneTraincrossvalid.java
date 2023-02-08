package SVM_Grid;






import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Vector;



//package libsvm;
public class GeneTraincrossvalid{

	    //
	public svm_model model;
	public String input_file_name;     // set by parse_command_line
	public String model_file_name;     // set by parse_command_line
	public String error_msg;
	public int cross_validation;
	public int nr_fold=10;
    public svm_parameter param ; // parameter definition
    public svm_problem prob ;      // contains the data set
    public static String datafilename="";
    public String modelname="newmodel";
    //svm_train train1;
    public  ArrayList Felement ;
    public   char ChFeature[];
    
    public   char GChFeature[];
    
    public svm_model myModel ;
    public double Accuracy;
    
    public svm_problem Subprob ;   // NEW SUB
    public  ArrayList        SubFelement;// NEW SUB

     double SENE=0.0;
	 double SEPE=0.0;
	 double FScore,FScore1=0.0,FScore2=0.0,FScore3=0.0,FScore4=0.0,FScore5=0.0,FScore6=0.0 , FScore7=0.0 ,FScore8=0.0 ,FScore9=0.0 ,FScore10=0.0, FScore11=0.0, FScore12=0.0  ;
	 double FP1,FP2,FP3,FP4,FP5, FP6, FP7, FP8,FP9,FP10, FP11, FP12;
	 double TN1,TN2,TN3,TN4,TN5, TN6, TN7, TN8,TN9,TN10, TN11, TN12;
	
	
	
	
	
	
    public GeneTraincrossvalid() {


         
            }
   

  
    
    public  void read_problem(int NoOfGenes, String datafilename ,int  Features []) throws IOException
    {
    	
    	param = new svm_parameter();
        prob  = new svm_problem();
		Subprob = new svm_problem();

        Felement =new  ArrayList();
   	 SubFelement =new  ArrayList();
  	     int Fcount=0;
        int  variableValue,Findex;
        String next="";
        double instvalue=0;
        String lastfeature="";
    	
    	
    	
    	
    	
    	
    	

    	
        
         
         
         // Specific genes
         
         String FSelect="";
     	
     	for(int sel=0;sel<Features.length;sel++){        // This is for all genes.

            
     		FSelect=FSelect+Features[sel]+",";
                  
               }


     	StringTokenizer str1 = new StringTokenizer(FSelect,",");

         
     			// Insert poistions into Arraylist
            
               while (str1.hasMoreTokens())
                  {
                   //get next token
                    next = str1.nextToken();
                   //convert to double
                    variableValue =Integer.valueOf(next).intValue();
                  //add double to array
                    SubFelement.add(Fcount,variableValue);
                    Fcount++;
                   }
         
         
         
         
               param.svm_type = svm_parameter.C_SVC;
               param.kernel_type = svm_parameter.RBF;
               param.degree = 3;
          //     param.gamma =0.05;    // australian
               param.gamma =0.001 ; 
             //param.gamma = 2; 
               param.coef0 = 0; //0
               param.nu = 0.2;
               param.cache_size = 512;
              
               //  default values for c and gamma is 1,0.01, repectively. // please make sure of this
               
              param.C = 100 ;   //for australian data
            //   param.C = 8; // found by grid.py;
               param.eps = 1e-3;
               param.p = 0.1;
               param.shrinking = 1;
               param.probability = 0;
               param.nr_weight = 0;
               param.weight_label = new int[0];
               param.weight = new double[0];
               
         
         
         
         
         
         
         
         

    	
    	 
    	 
    	 
    	 /*          Old Code 30-4-2018
    	
    	param.svm_type = svm_parameter.C_SVC;
         param.kernel_type = svm_parameter.RBF;
         param.degree = 3;
    //     param.gamma =0.05;    // australian
         param.gamma =0.0001; 
       //param.gamma = 2; 
         param.coef0 = 0; //0
         param.nu = 0.2;
         param.cache_size = 512;
        
         //  default values for c and gamma is 1,0.01, repectively. // please make sure of this
         
        param.C = 1000;   //for australian data
      //   param.C = 8; // found by grid.py;
         param.eps = 1e-3;
         param.p = 0.1;
         param.shrinking = 1;
         param.probability = 0;
         param.nr_weight = 0;
         param.weight_label = new int[0];
         param.weight = new double[0];
         
         */
        
           try {
        	   
        	// read data by fp file. 
        	   
         BufferedReader fp = new BufferedReader(new FileReader(datafilename));
         Vector<Double> vy = new Vector<Double>();
         Vector<svm_node[]> vx = new Vector<svm_node[]>();  // index and the value
         Vector<svm_node[]> Subvx= new Vector<svm_node[]>();
         
         ArrayList<svmsize>  SetInstances=new ArrayList<svmsize>();  // featurevalue and fitness
         
         
         
                  svmsize S1= new svmsize(1,0.3);

         
                  int countinstance=0;
        
      //  ChFeature=new char[size];
     //   ChFeature=Fselected.toCharArray();
        
                  int count=0;
                  Felement.clear();
                  SetInstances.clear();
       
        
         //  Gene selected values for each solution 
        // we set b+1 due to the svm format start from 1:22 etc
        
                 
      
      
      
      
      
      
        int max_index = 0;
        
        while(true)
        {
        		String line = fp.readLine();



        		if(line == null) break;

        			StringTokenizer st = new StringTokenizer(line," \t\n\r\f:");
            
        			vy.addElement(atof(st.nextToken()));  // vy class
        			int m = st.countTokens()/2;           // divided on two since tokenizer spilit 1:0.5 into two tokens and consider one token divided into for..m block
        			int Snodesize= Felement.size();       
           
        			SetInstances.clear();
        			countinstance=0;
            
        			
        			
        			
        			
        			svm_node[] x = new svm_node[m];
        			svm_node[] Subx = new svm_node[Features.length];
                     int elite_Feature=0;
            
        				for(int j=0;j<m;j++)  {
        						Findex=Integer.valueOf(st.nextToken()).intValue(); // I think t discard null value
            	
            
        						instvalue= atof(st.nextToken());
        						
        			//			x[j] = new svm_node();
        						
        					//	System.out.println("index == "+Findex+ " ,Value  ="+ instvalue);
        					
        						x[j]= new svm_node(); 
        						x[j].index = Findex;
                            	x[j].value =instvalue; 
   	
                            	
                            	
                            	if(SubFelement.contains(Findex)){
                            		
                            		Subx[elite_Feature]= new svm_node(); 
                            		
            						Subx[elite_Feature].index = Findex;
                                	Subx[elite_Feature].value =instvalue; 
                                	elite_Feature++;
                            	}
        					}//end for
            
            
            
      
            
            
            
            
            
            
            
            
            
            
         if(m>0) 
        	  	max_index = Math.max(max_index, x[m-1].index);
            
            vx.addElement(x); // add index and the value
            Subvx.addElement(Subx);
            
        }//end while
        
        
       
        
        
        ///        Sub data
        
        	
        		Subprob.l = vy.size();               // No of instances
        		Subprob.x = new svm_node[Subprob.l][];  // Node determine the No of instances(static)  and the other value for rows
        		for(int i=0;i<Subprob.l;i++)
        			Subprob.x[i] = Subvx.elementAt(i); 
     //   
        			Subprob.y = new double[Subprob.l]; // Array size of classes 
        
        			for(int i=0;i<Subprob.l;i++)
        				Subprob.y[i] = vy.elementAt(i);    //
        
       
        
        
        
        

        	prob.l = vy.size();   // No of instances
           	prob.x = new svm_node[prob.l][];  // Node determine the No of instances(static)  and the other value for rows
        	for(int i=0;i<prob.l;i++)
        		prob.x[i] = vx.elementAt(i);
        	
    
        	prob.y = new double[prob.l]; // Array size of classes 
        
        	for(int i=0;i<prob.l;i++)
        		prob.y[i] = vy.elementAt(i);    //
        
        
        
        
        
        	if(param.gamma == 0 && max_index > 0)
        		param.gamma = 1.0/max_index;

        	if(param.kernel_type == svm_parameter.PRECOMPUTED)
        		for(int i=0;i<prob.l;i++)
        		{
                if (prob.x[i][0].index != 0)
                {
                    System.err.print("Wrong kernel matrix: first column must be 0:sample_serial_number\n");
                    System.exit(1);
                }
                if ((int)prob.x[i][0].value <= 0 || (int)prob.x[i][0].value > max_index)
                {
                    System.err.print("Wrong input format: sample_serial_number out of range\n");
                    System.exit(1);
                }
            }

        fp.close();
        
        
          
           
           
    } catch (FileNotFoundException ex) {
        ex.printStackTrace();
    } catch (IOException ex) {
        ex.printStackTrace();
    } finally {
        //Close the BufferedWriter
        
       

    }   
           
           
           
           
           
           
           
           
           
           
    }
    
    
    private  double atof(String s)
    {
        double d = Double.valueOf(s).doubleValue();
        if (Double.isNaN(d) || Double.isInfinite(d))
        {
            System.err.print("NaN or Infinity in input\n");
            System.exit(1);
        }
        return(d);
    }

    private int atoi(String s)
    {
        return Integer.parseInt(s);
    }
    
    
    
    
    
    public double do_cross_validation(String FeatureReduct,int All_Genes,svm_problem ProbValid,svm_problem SubDataCross,svm_parameter ParamValid, int D, int Class)
	{
		int i;
		
		
	        int total = 0;
	  
		
		
		
		
		
		int total_correct = 0;
		double total_error = 0;
		double sumv = 0, sumy = 0, sumvv = 0, sumyy = 0, sumvy = 0;
		
		
		double[] target = new double[ProbValid.l];

	//	SVMCROSS.svm_cross_validation(prob,param,nr_fold,target,FeatureReduct);
		
		SVMCROSS.svm_cross_validation(ProbValid,SubDataCross,ParamValid,nr_fold,target,FeatureReduct,All_Genes);
		
		
		
		
		
		
		if(ParamValid.svm_type == svm_parameter.EPSILON_SVR ||
				ParamValid.svm_type == svm_parameter.NU_SVR)
		{
			for(i=0;i<ProbValid.l;i++)
			{
				double y = ProbValid.y[i];
				double v = target[i];
				total_error += (v-y)*(v-y);
				sumv += v;
				sumy += y;
				sumvv += v*v;
				sumyy += y*y;
				sumvy += v*y;
			}
			System.out.print("Cross Validation Mean squared error = "+total_error/ProbValid.l+"\n");
			System.out.print("Cross Validation Squared correlation coefficient = "+
				((ProbValid.l*sumvy-sumv*sumy)*(ProbValid.l*sumvy-sumv*sumy))/
				((ProbValid.l*sumvv-sumv*sumv)*(ProbValid.l*sumyy-sumy*sumy))+"\n"
				);
			return -0.444;
		}
		
		
		
		else
		{
			for(i=0;i<ProbValid.l;i++){
				
				        
			            
			   //********************  End   compute  Detection  Rate   
			            
			            
			   //********************  Start   compute  False Alarm Rate       
			      
			                if(target[i] == ProbValid.y[i])
								++total_correct;
						
							
						}
			
			
						sensitivity (target, ProbValid,Class  );

			
			             //   DR=Ccorrectattack /CRawattack;
			           //     FAR=Ccorrectnormal /CRawnormal ;
			         	
			                
			      //           System.out.println("Detection  Rate  (  DR  )= "+ DR+"  correctDR "+Ccorrectattack+"all RDR" +CRawattack   +"  \n");
			                 
			      //           System.out.println("False Alram  Rate  (FAR  )  = "+ FAR  +" correct FAR  "+Ccorrectnormal+"  all RFAR  "+CRawnormal+  "\n");

			 //                System.out.println("all   correct classification  "+correct+"  sum of DR+FR  "+(Ccorrectattack+Ccorrectnormal) );

				         	//	System.out.println("########################################\n");

				
				
				
				
				
				
				
				
				         //		System.out.println("########################################\n");
				         //		System.out.println("########################################\n");

				
			//	System.out.print("Cross Validation Accuracy = "+100.0*total_correct/ProbValid.l+"%\n");
			//	System.out.print("total_correct    "+total_correct);
				
			Accuracy=100.0*total_correct/ProbValid.l;
			
			return Accuracy;
		}
	}


	
	
	
	
	
	
	
	
	
	
		    public   void sensitivity (double [] predict,   svm_problem  problems, int Classes ){

	    	
	    	  
	    	  
	    	// CNS 1 cancer ... 0 normal.
	    	// Breast 1 replace 0 nonreplace
	    	// Colon 1 tumor   0 normal
	    	 // Leukemia ALL 0 AML 1
	    	
	    	switch (Classes) {
	    	 
	    	   
	 	         
	    	 case 12:    
	 	         
	 	         
	    		 System.out.println("      multiclass   10      ");
	    		 	        
	    		 	        
	    		 	    //    double Ap=0,Atp=0,An=0,Atn=0;
	    		 	        double Bp=0,Btp=0,Bn=0,Btn=0;
	    		 	        double Cp=0,Ctp=0,Cn=0,Ctn=0;
	    		 	        double Dp=0,Dtp=0,Dn=0,Dtn=0;
	    		 	        double Ep=0,Etp=0,En=0,Etn=0;
	    		 	        
	    		 	        double Fp=0,Ftp=0,Fn=0,Ftn=0;
	    		 	        double Gp=0,Gtp=0,Gn=0,Gtn=0;
	    		 	        double Hp=0,Htp=0,Hn=0,Htn=0;
	    		 	        double Ip=0,Itp=0,In=0,Itn=0;
	    		 	        double Wp=0,Wtp=0,Wn=0,Wtn=0;
	    		 	        double Zp=0,Ztp=0,Zn=0,Ztn=0;

	    		 	        double ZIp=0,ZItp=0,ZIn=0,ZItn=0;
	    		 	        double ZIIp=0,ZIItp=0,ZIIn=0,ZIItn=0;
	    		 	      
	    		 	        for(int i=0;i<problems.l;i++){
	    		 	        
	    		 	        
	    		 	        //             CLASS 1
	    		 	        	
	    		 	        	if (problems.y[i] == 0) {
	    		 		        	Bp++;

	    		 		        	 if (predict[i] == problems.y[i]) 
	    		 		                 	Btp++;
	    		 		             
	    		 		 	        
	    		 		 	        if (predict[i] != problems.y[i]) 
	    		 		                     Btn++; 
	    		 		 	        
	    		 	 	      }
	    		 		        //SP
	    		 		      
	    		 		        
	    		 		       if (predict[i] == 0 && problems.y[i] !=0 ) {
	    		 	                FP1++; 

	    		 		       }
	    		 	        
	    		 	       	
	    		 	     // *****************************************   	
	    		 	        	
	    		 	      //****************************************

	    		 		      //             CLASS 2
	    		 	        	
	    		 	        	if (problems.y[i] == 1) {
	    		 		        	Cp++;

	    		 		        	 if (predict[i] == problems.y[i]) 
	    		 		                 	Ctp++;
	    		 		             
	    		 		 	        
	    		 		 	        if (predict[i] != problems.y[i]) 
	    		 		                     Ctn++; 
	    		 		 	        
	    		 	 	      }
	    		 		        //SP
	    		 		      
	    		 		        
	    		 		       if (predict[i] == 1 && problems.y[i] !=1 ) {
	    		 	                FP2++; 

	    		 		       }
	    		 	        
	    		 	       	
	    		 	     // *****************************************   	
	    		 	        	
	    		 	      //**************************************** 
	    		 	        
	    		 		      //             CLASS 3
	    		 	        	
	    		 	        	if (problems.y[i] == 2) {
	    		 		        	Dp++;

	    		 		        	 if (predict[i] == problems.y[i]) 
	    		 		                 	Dtp++;
	    		 		             
	    		 		 	        
	    		 		 	        if (predict[i] != problems.y[i]) 
	    		 		                     Dtn++; 
	    		 		 	        
	    		 	 	      }
	    		 		        //SP
	    		 		      
	    		 		        
	    		 		       if (predict[i] == 2 && problems.y[i] !=2 ) {
	    		 	                FP3++; 

	    		 		       }
	    		 	        
	    		 	       	
	    		 	     // *****************************************   	
	    		 	        	
	    		 	      //**************************************** 
	    		 	         	        
	    		 		       
	    		 		      //             CLASS 4
	    		 	        	
	    		 	        	if (problems.y[i] == 3) {
	    		 		        	Ep++;

	    		 		        	 if (predict[i] == problems.y[i]) 
	    		 		                 	Etp++;
	    		 		             
	    		 		 	        
	    		 		 	        if (predict[i] != problems.y[i]) 
	    		 		                     Etn++; 
	    		 		 	        
	    		 	 	      }
	    		 		        //SP
	    		 		      
	    		 		        
	    		 		       if (predict[i] == 3 && problems.y[i] !=3 ) {
	    		 	                FP4++; 

	    		 		       }
	    		 	        
	    		 	       	
	    		 	     // *****************************************   	
	    		 	        	
	    		 	      //****************************************        
	    		 		       
	    		 		       
	    		 		      //             CLASS 5
	    		 	        	
	    		 	        	if (problems.y[i] == 4) {
	    		 		        	Fp++;

	    		 		        	 if (predict[i] == problems.y[i]) 
	    		 		                 	Ftp++;
	    		 		             
	    		 		 	        
	    		 		 	        if (predict[i] != problems.y[i]) 
	    		 		                     Ftn++; 
	    		 		 	        
	    		 	 	      }
	    		 		        //SP
	    		 		      
	    		 		        
	    		 		       if (predict[i] == 4 && problems.y[i] !=4 ) {
	    		 	                FP5++; 

	    		 		       }
	    		 	        
	    		 	       	
	    		 	     // *****************************************   	
	    		 	        	
	    		 	      //****************************************             
	    		 		       
	    		 		
	    		 		      //             CLASS 6
	    		 	        	
	    		 	        	if (problems.y[i] == 5) {
	    		 		        	Gp++;

	    		 		        	 if (predict[i] == problems.y[i]) 
	    		 		                 	Gtp++;
	    		 		             
	    		 		 	        
	    		 		 	        if (predict[i] != problems.y[i]) 
	    		 		                     Gtn++; 
	    		 		 	        
	    		 	 	      }
	    		 		        //SP
	    		 		      
	    		 		        
	    		 		       if (predict[i] == 5 && problems.y[i] !=5 ) {
	    		 	                FP6++; 

	    		 		       }
	    		 	        
	    		 	       	
	    		 	     // *****************************************   	
	    		 	        	
	    		 	      //****************************************             
	    		 		       
	    		 		       
	    		 		      //             CLASS 7
	    		 	        	
	    		 	        	if (problems.y[i] == 6) {
	    		 		        	Hp++;

	    		 		        	 if (predict[i] == problems.y[i]) 
	    		 		                 	Htp++;
	    		 		             
	    		 		 	        
	    		 		 	        if (predict[i] != problems.y[i]) 
	    		 		                     Htn++; 
	    		 		 	        
	    		 	 	      }
	    		 		        //SP
	    		 		      
	    		 		        
	    		 		       if (predict[i] == 6 && problems.y[i] !=6 ) {
	    		 	                FP7++; 

	    		 		       }
	    		 	        
	    		 	       	
	    		 	     // *****************************************   	
	    		 	        	
	    		 	      //****************************************             
	    		 		             
	    		 		       
	    		 		      //             CLASS 8
	    		 	        	
	    		 	        	if (problems.y[i] == 7) {
	    		 		        	Ip++;

	    		 		        	 if (predict[i] == problems.y[i]) 
	    		 		                 	Itp++;
	    		 		             
	    		 		 	        
	    		 		 	        if (predict[i] != problems.y[i]) 
	    		 		                     Itn++; 
	    		 		 	        
	    		 	 	      }
	    		 		        //SP
	    		 		      
	    		 		        
	    		 		       if (predict[i] == 7 && problems.y[i] !=7 ) {
	    		 	                FP8++; 

	    		 		       }
	    		 	        
	    		 	       	
	    		 	     // *****************************************   	
	    		 	        	
	    		 	      //****************************************                   
	    		 		       
	    		 		       
	    		 		      //             CLASS 9
	    		 	        	
	    		 	        	if (problems.y[i] == 8) {
	    		 		        	Wp++;

	    		 		        	 if (predict[i] == problems.y[i]) 
	    		 		                 	Wtp++;
	    		 		             
	    		 		 	        
	    		 		 	        if (predict[i] != problems.y[i]) 
	    		 		                     Wtn++; 
	    		 		 	        
	    		 	 	      }
	    		 		        //SP
	    		 		      
	    		 		        
	    		 		       if (predict[i] == 8 && problems.y[i] !=8 ) {
	    		 	                FP9++; 

	    		 		       }
	    		 	        
	    		 	       	
	    		 	     // *****************************************   	
	    		 	        	
	    		 	      //****************************************        
	    		 		       
	    		 		       
	    		 		       
		    		 		      //             CLASS 10
		    		 	        	
		    		 	        	if (problems.y[i] == 9) {
		    		 		        	Zp++;

		    		 		        	 if (predict[i] == problems.y[i]) 
		    		 		                 	Ztp++;
		    		 		             
		    		 		 	        
		    		 		 	        if (predict[i] != problems.y[i]) 
		    		 		                     Ztn++; 
		    		 		 	        
		    		 	 	      }
		    		 		        //SP
		    		 		      
		    		 		        
		    		 		       if (predict[i] == 9 && problems.y[i] !=9 ) {
		    		 	                FP10++; 

		    		 		       }
		    		 	        
		    		 	       	
		    		 	     // *****************************************   	
		    		 	        	
									
									
								   //             CLASS 11
		    		 	        	
		    		 	        	if (problems.y[i] == 10) {
		    		 		        	ZIp++;

		    		 		        	 if (predict[i] == problems.y[i]) 
		    		 		                 	ZItp++;
		    		 		             
		    		 		 	        
		    		 		 	        if (predict[i] != problems.y[i]) 
		    		 		                     ZItn++; 
		    		 		 	        
		    		 	 	      }
		    		 		        //SP
		    		 		      
		    		 		        
		    		 		       if (predict[i] == 10 && problems.y[i] !=10 ) {
		    		 	                FP11++; 

		    		 		       }
		    		 	        
		    		 	       	
		    		 	     // *****************************************   		
									
									 //             CLASS 12
									
									if (problems.y[i] == 11) {
		    		 		        	ZIIp++;

		    		 		        	 if (predict[i] == problems.y[i]) 
		    		 		                 	ZIItp++;
		    		 		             
		    		 		 	        
		    		 		 	        if (predict[i] != problems.y[i]) 
		    		 		                     ZIItn++; 
		    		 		 	        
		    		 	 	      }
		    		 		        //SP
		    		 		      
		    		 		        
		    		 		       if (predict[i] == 11 && problems.y[i] !=11 ) {
		    		 	                FP12++; 

		    		 		       }
		    		 	        
									
									
									
									
									
									
									
									
									
									
									
									
		    		 	      //****************************************               
	    		 		       
	    		 		       
	    		 		       
	    		 		       
	    		 		       
	    		 		       
		    		 	 	    
		    		 	 	       
		    		 	 	       
		    		 	 	       
		    		 	 	       
		    		 	 	 
	    		 		       
	    		 		       
	    		 		       
	    		 		       
	    		 		       
	    		 		       
	    		 		       
	    		 	        
	    		 	        
	    		 	 
	    		 	       
	    		 	       
	    		 	       
	    		 	       
	    		 	       
	    		 	       
	    		 	        
	    		 	        }// For    
	    		 	        
	    		 	        
	    		 	        
	    		 	       TN1= problems.l - (Btp+Btn+FP1);
    		 	 	       TN2= problems.l - (Ctp+Ctn+FP2);
    		 	 	       TN3= problems.l - (Dtp+Dtn+FP3);
    		 	 	       TN4= problems.l - (Etp+Etn+FP4);
    		 	 	       TN5= problems.l - (Ftp+Ftn+FP5);
    		 	 	       TN6= problems.l - (Gtp+Gtn+FP6);
    		 	 	       TN7= problems.l - (Htp+Htn+FP7);
    		 	 	       TN8= problems.l - (Itp+Itn+FP8);
    		 	 	       TN9= problems.l - (Wtp+Wtn+FP9);
    		 	 	       TN10= problems.l - (Ztp+Ztn+FP10);
							TN11= problems.l - (ZItp+ZItn+FP11);
	    		 	        
							TN12= problems.l - (ZIItp+ZIItn+FP12);     
	    		 	        
	    		 	        
	    		 	        
	    		 	        
	    		 	        
	    		 	        
	    		 	        
	    		 	        
	    		 	        
	    		 	        
	    		 	        /*
	    		 	    
	    		 	       System.out.println("-------------------------- " );
	    		 	        System.out.println(" Sens :: class 1 "+ (Btp/Bp) );
	    		 	        System.out.println(" Spec :: class 1 "+  (Btn/Bn) );
	    		 	       System.out.println("-------------------------- " );
	    		 	        
	    		 	        System.out.println(" Sens :: class 2 "+ (Ctp/Cp) );
	    		 	        System.out.println(" Spec :: class 2 "+  (Ctn/Cn) );
	    		 	       System.out.println("-------------------------- " );
	    		 	       System.out.println(" Sens :: class 3 "+ (Dtp/Dp) );
	    			        System.out.println(" Spec :: class 3 "+  (Dtn/Dn) );
	    			        System.out.println("-------------------------- " );
	    			        System.out.println(" Sens :: class 4 "+ (Etp/Ep) );
	    			        System.out.println(" Spec :: class 4 "+  (Etn/En) );
	    			        System.out.println("-------------------------- " );
	    			        System.out.println(" Sens :: class 5 "+ (Ftp/Fp) );
	    			        System.out.println(" Spec :: class 5 "+  (Ftn/Fn) );
	    			        System.out.println("-------------------------- " );
	    			        System.out.println(" Sens :: class 6 "+ (Gtp/Gp) );
	    			        System.out.println(" Spec :: class 6 "+  (Gtn/Gn) );
	    			        System.out.println("-------------------------- " );
	    			        System.out.println(" Sens :: class 7 "+ (Htp/Hp) );
	    			        System.out.println(" Spec :: class 7 "+  (Htn/Hn) );
	    			        System.out.println("-------------------------- " );
	    			        System.out.println(" Sens :: class 8 "+ (Itp/Ip) );
	    			        System.out.println(" Spec :: class 8 "+  (Itn/In) );
	    			        System.out.println("-------------------------- " );
	    			        System.out.println("-------------------------- " );
	    			        
	    			        /*
	    			        System.out.println(" Overall Sens  "+ (   (  (Atp/Ap)+ (Btp/Bp)+(Ctp/Cp)+(Dtp/Dp)+ (Etp/Ep)+(Ftp/Fp) +(Gtp/Gp)+ (Htp/Hp) +(Itp/Ip)  ) /9    )         );
	    			        System.out.println(" Overall Spec  "+ (   (  (Atn/An)+ (Btn/Bn)+(Ctn/Cn)+(Dtn/Dn)+  (Etn/En) +(Ftn/Fn) + (Gtn/Gn) +(Htn/Hn) + (Itn/In)  ) /9    )   		  );
	    		 	        System.out.println(" Overall accuracy  "+   (  (Atp+Btp+Ctp+Dtp +Etp+Ftp+Gtp+Htp+Itp) /(Ap+Bp+Cp+Dp+Ep+Fp+ Gp + Hp+Ip) )       );
	    		 	        */
	    		 	    		 
	    			        System.out.println(" Overall Sens  "+ (   (   (Btp/Bp)+(Ctp/Cp)+(Dtp/Dp)+ (Etp/Ep)+(Ftp/Fp) +(Gtp/Gp)+ (Htp/Hp) +(Itp/Ip) +(Wtp/Wp)+(Ztp/Zp)  + (ZItp/ZIp) + (ZIItp/ZIIp)  )   /12    )         );
	    			        System.out.println(" Overall Spec  "+ ( (   (TN1/(TN1+FP1) )+ (TN2/ (TN2+FP2)  ) +(TN3/ (TN3+FP3))+(TN4/ (TN4+FP4))+  (TN5/ (TN5+FP5)) + (TN6/ (TN6+FP6)) + (TN7/ (TN7+FP7))  + (TN8/ (TN8+FP8)) + (TN9/ (TN9+FP9))	+ (TN10/ (TN10+FP10)) + (TN11/(TN11+FP11)) + (TN12/(TN12+FP12))   	)/12  )           );
	    		 	    //    System.out.println(" Overall accuracy  "+   (  (Btp+Ctp+Dtp +Etp+Ftp+Gtp+Htp+Itp) /(Bp+Cp+Dp+Ep+Fp+ Gp + Hp+Ip) )       );
	    		 	      
	    		 	        
	    		 	       SENE= (   (   (Btp/Bp)+(Ctp/Cp)+(Dtp/Dp)+ (Etp/Ep)+(Ftp/Fp) +(Gtp/Gp)+ (Htp/Hp) +(Itp/Ip) +(Wtp/Wp)+(Ztp/Zp)  + (ZItp/ZIp) + (ZIItp/ZIIp)  )    /12    )  ; 
	    		 	       SEPE= ( (   (TN1/(TN1+FP1) )+ (TN2/ (TN2+FP2)  ) +(TN3/ (TN3+FP3))+(TN4/ (TN4+FP4))+  (TN5/ (TN5+FP5)) + (TN6/ (TN6+FP6)) + (TN7/ (TN7+FP7))  + (TN8/ (TN8+FP8)) + (TN9/ (TN9+FP9))	+ (TN10/ (TN10+FP10))  + (TN11/(TN11+FP11)) + (TN12/(TN12+FP12))   	)/12  )     ;  	   
	    		 	      
	    		 	       double FN,FP; 
	    		 	       
	    		 	       
	    		 	        FN= (Bp-Btp);
	    			        FP= FP1;
	    			    //    System.out.println("TP  "+Btp+"  FN 1 ="+ FN + " FP1  : "+FP);
	    			        FScore1= ( 2*Btp ) / ( 2*Btp+ FN +FP  );
	    		 	        
	    		 	        
	    			        
	    			        FN= (Cp-Ctp);
	    			        FP= FP2;
	    			        System.out.println("TP  "+Ctp+"  FN 1 ="+ FN + " FP1  : "+FP);
	    			        FScore2= ( 2*Ctp ) / ( 2*Ctp+ FN +FP  );
	    		 	        
	    			        
	    			        FN= (Dp-Dtp);
	    			        FP= FP3;
	    			      //  System.out.println("TP  "+Dtp+"  FN 1 ="+ FN + " FP1  : "+FP);
	    			        FScore3= ( 2*Dtp ) / ( 2*Dtp+ FN +FP  );
	    			        
	    			        
	    			        FN= (Ep-Etp);
	    			        FP= FP4;
	    			      //  System.out.println("TP  "+Etp+"  FN 1 ="+ FN + " FP1  : "+FP);
	    			        FScore4= ( 2*Etp ) / ( 2*Etp+ FN +FP  );
	    			        
	    			        
	    			        FN= (Fp-Ftp);
	    			        FP= FP5;
	    			     //   System.out.println("TP  "+Ftp+"  FN 1 ="+ FN + " FP1  : "+FP);
	    			        FScore5= ( 2*Ftp ) / ( 2*Ftp+ FN +FP  );
	    			        
	    			        
	    			        FN= (Gp-Gtp);
	    			        FP= FP6;
	    			     //   System.out.println("TP  "+Gtp+"  FN 1 ="+ FN + " FP1  : "+FP);
	    			        FScore6= ( 2*Gtp ) / ( 2*Gtp+ FN +FP  );
	    			        
	    			        
	    			        FN= (Hp-Htp);
	    			        FP= FP7;
	    			   //     System.out.println("TP  "+Htp+"  FN 1 ="+ FN + " FP1  : "+FP);
	    			        FScore7= ( 2*Htp ) / ( 2*Htp+ FN +FP  );
	    			        
	    			        
	    			        FN= (Ip-Itp);
	    			        FP= FP8;
	    			     //   System.out.println("TP  "+Itp+"  FN 1 ="+ FN + " FP1  : "+FP);
	    			        FScore8= ( 2*Itp ) / ( 2*Itp+ FN +FP  );
	    			        
	    			        
	    			        
	    			        FN= (Wp-Wtp);
	    			        FP= FP9;
	    			  //      System.out.println("TP  "+Wtp+"  FN 1 ="+ FN + " FP1  : "+FP);
	    			        FScore9= ( 2*Wtp ) / ( 2*Wtp+ FN +FP  );
	    			        
	    			        
	    			        
	    			        FN= (Zp-Ztp);
	    			        FP= FP10;
	    			   //     System.out.println("TP  "+Ztp+"  FN 1 ="+ FN + " FP1  : "+FP);
	    			        FScore10= ( 2*Ztp ) / ( 2*Ztp+ FN +FP  );
	    			        
	    			        
							
							FN= (ZIp-ZItp);
	    			        FP= FP11;
	    			   //     System.out.println("TP  "+Ztp+"  FN 1 ="+ FN + " FP1  : "+FP);
	    			        FScore11= ( 2*ZItp ) / ( 2*ZItp+ FN +FP  );
	    			        
							
							
							FN= (ZIIp-ZIItp);
	    			        FP= FP12;
	    			   //     System.out.println("TP  "+Ztp+"  FN 1 ="+ FN + " FP1  : "+FP);
	    			        FScore12= ( 2*ZIItp ) / ( 2*ZIItp+ FN +FP  );
							
							
							
							
	    			        
	    			        FScore=(FScore1+FScore2+FScore3+FScore4+FScore5+ FScore6+ FScore7+ FScore8+ FScore9+  FScore10+ FScore11+ FScore12  )/12	; 
	    			        
	    		 	         break;
	 	         
	 	         
	 	         
	 	         
	 	         
	 	         
	 	         
	 	         
	 	         
	 	         
	 	         
	 	         
	 	         
	 	         
	 	         
	 	        case 7:    
	 	         
	 	         
	    		 System.out.println("      multiclass   7      ");
	    		 	        
	    		 	        
	    		 	    //    double Ap=0,Atp=0,An=0,Atn=0;
	    		 	      

						   Bp=0;
						   Btp=0;
						    Bn=0;
						    Btn=0;
	    		 	        
							Cp=0;
							 Ctp=0;
							Cn=0;
							Ctn=0;
	    		 	       
						   Dp=0;
							 Dtp=0;
							 Dn=0;
							 Dtn=0;
	    		 	       
						   Ep=0;
							 Etp=0;
							 En=0;
							 Etn=0;
	    		 	        
	    		 	         Fp=0;
							 Ftp=0;
							 Fn=0;
							 Ftn=0;
	    		 	        
							Gp=0;
							 Gtp=0;
							 Gn=0;
							 Gtn=0;
	    		 	       
						   Hp=0;
							 Htp=0;
							 Hn=0;
							 Htn=0;
	    		 
	    		 	      
	    		 	        for(int i=0;i<problems.l;i++){
	    		 	        
	    		 	        
	    		 	        //             CLASS 1
	    		 	        	
	    		 	        	if (problems.y[i] == 0) {
	    		 		        	Bp++;

	    		 		        	 if (predict[i] == problems.y[i]) 
	    		 		                 	Btp++;
	    		 		             
	    		 		 	        
	    		 		 	        if (predict[i] != problems.y[i]) 
	    		 		                     Btn++; 
	    		 		 	        
	    		 	 	      }
	    		 		        //SP
	    		 		      
	    		 		        
	    		 		       if (predict[i] == 0 && problems.y[i] !=0 ) {
	    		 	                FP1++; 

	    		 		       }
	    		 	        
	    		 	       	
	    		 	     // *****************************************   	
	    		 	        	
	    		 	      //****************************************

	    		 		      //             CLASS 2
	    		 	        	
	    		 	        	if (problems.y[i] == 1) {
	    		 		        	Cp++;

	    		 		        	 if (predict[i] == problems.y[i]) 
	    		 		                 	Ctp++;
	    		 		             
	    		 		 	        
	    		 		 	        if (predict[i] != problems.y[i]) 
	    		 		                     Ctn++; 
	    		 		 	        
	    		 	 	      }
	    		 		        //SP
	    		 		      
	    		 		        
	    		 		       if (predict[i] == 1 && problems.y[i] !=1 ) {
	    		 	                FP2++; 

	    		 		       }
	    		 	        
	    		 	       	
	    		 	     // *****************************************   	
	    		 	        	
	    		 	      //**************************************** 
	    		 	        
	    		 		      //             CLASS 3
	    		 	        	
	    		 	        	if (problems.y[i] == 2) {
	    		 		        	Dp++;

	    		 		        	 if (predict[i] == problems.y[i]) 
	    		 		                 	Dtp++;
	    		 		             
	    		 		 	        
	    		 		 	        if (predict[i] != problems.y[i]) 
	    		 		                     Dtn++; 
	    		 		 	        
	    		 	 	      }
	    		 		        //SP
	    		 		      
	    		 		        
	    		 		       if (predict[i] == 2 && problems.y[i] !=2 ) {
	    		 	                FP3++; 

	    		 		       }
	    		 	        
	    		 	       	
	    		 	     // *****************************************   	
	    		 	        	
	    		 	      //**************************************** 
	    		 	         	        
	    		 		       
	    		 		      //             CLASS 4
	    		 	        	
	    		 	        	if (problems.y[i] == 3) {
	    		 		        	Ep++;

	    		 		        	 if (predict[i] == problems.y[i]) 
	    		 		                 	Etp++;
	    		 		             
	    		 		 	        
	    		 		 	        if (predict[i] != problems.y[i]) 
	    		 		                     Etn++; 
	    		 		 	        
	    		 	 	      }
	    		 		        //SP
	    		 		      
	    		 		        
	    		 		       if (predict[i] == 3 && problems.y[i] !=3 ) {
	    		 	                FP4++; 

	    		 		       }
	    		 	        
	    		 	       	
	    		 	     // *****************************************   	
	    		 	        	
	    		 	      //****************************************        
	    		 		       
	    		 		       
	    		 		      //             CLASS 5
	    		 	        	
	    		 	        	if (problems.y[i] == 4) {
	    		 		        	Fp++;

	    		 		        	 if (predict[i] == problems.y[i]) 
	    		 		                 	Ftp++;
	    		 		             
	    		 		 	        
	    		 		 	        if (predict[i] != problems.y[i]) 
	    		 		                     Ftn++; 
	    		 		 	        
	    		 	 	      }
	    		 		        //SP
	    		 		      
	    		 		        
	    		 		       if (predict[i] == 4 && problems.y[i] !=4 ) {
	    		 	                FP5++; 

	    		 		       }
	    		 	        
	    		 	       	
	    		 	     // *****************************************   	
	    		 	        	
	    		 	      //****************************************             
	    		 		       
	    		 		
	    		 		      //             CLASS 6
	    		 	        	
	    		 	        	if (problems.y[i] == 5) {
	    		 		        	Gp++;

	    		 		        	 if (predict[i] == problems.y[i]) 
	    		 		                 	Gtp++;
	    		 		             
	    		 		 	        
	    		 		 	        if (predict[i] != problems.y[i]) 
	    		 		                     Gtn++; 
	    		 		 	        
	    		 	 	      }
	    		 		        //SP
	    		 		      
	    		 		        
	    		 		       if (predict[i] == 5 && problems.y[i] !=5 ) {
	    		 	                FP6++; 

	    		 		       }
	    		 	        
	    		 	       	
	    		 	     // *****************************************   	
	    		 	        	
	    		 	      //****************************************             
	    		 		       
	    		 		       
	    		 		      //             CLASS 7
	    		 	        	
	    		 	        	if (problems.y[i] == 6) {
	    		 		        	Hp++;

	    		 		        	 if (predict[i] == problems.y[i]) 
	    		 		                 	Htp++;
	    		 		             
	    		 		 	        
	    		 		 	        if (predict[i] != problems.y[i]) 
	    		 		                     Htn++; 
	    		 		 	        
	    		 	 	      }
	    		 		        //SP
	    		 		      
	    		 		        
	    		 		       if (predict[i] == 6 && problems.y[i] !=6 ) {
	    		 	                FP7++; 

	    		 		       }
	    		 	        
	    		 	       	
	    		 	     // *****************************************   	
	    		 	        	
	    		 	      //****************************************             
	    		 		             
	    		 		       
	    		 		    
									
									
									
									
									
									
									
									
									
									
		    		 	      //****************************************               
	    		 		       
	    		 		       
	    		 		       
	    		 		       
	    		 		       
	    		 		       
		    		 	 	    
		    		 	 	       
		    		 	 	       
		    		 	 	       
		    		 	 	       
		    		 	 	 
	    		 		       
	    		 		       
	    		 		       
	    		 		       
	    		 		       
	    		 		       
	    		 		       
	    		 	        
	    		 	        
	    		 	 
	    		 	       
	    		 	       
	    		 	       
	    		 	       
	    		 	       
	    		 	       
	    		 	        
	    		 	        }// For    
	    		 	        
	    		 	        
	    		 	        
	    		 	       TN1= problems.l - (Btp+Btn+FP1);
    		 	 	       TN2= problems.l - (Ctp+Ctn+FP2);
    		 	 	       TN3= problems.l - (Dtp+Dtn+FP3);
    		 	 	       TN4= problems.l - (Etp+Etn+FP4);
    		 	 	       TN5= problems.l - (Ftp+Ftn+FP5);
    		 	 	       TN6= problems.l - (Gtp+Gtn+FP6);
    		 	 	       TN7= problems.l - (Htp+Htn+FP7);
    		 	    
	    		 	        
	    		 	        
	    		 	        
	    		 	        
	    		 	        
	    		 	        
	    		 	        
	    		 	        
	    		 	        
	    		 	        
	    		 	        /*
	    		 	    
	    		 	       System.out.println("-------------------------- " );
	    		 	        System.out.println(" Sens :: class 1 "+ (Btp/Bp) );
	    		 	        System.out.println(" Spec :: class 1 "+  (Btn/Bn) );
	    		 	       System.out.println("-------------------------- " );
	    		 	        
	    		 	        System.out.println(" Sens :: class 2 "+ (Ctp/Cp) );
	    		 	        System.out.println(" Spec :: class 2 "+  (Ctn/Cn) );
	    		 	       System.out.println("-------------------------- " );
	    		 	       System.out.println(" Sens :: class 3 "+ (Dtp/Dp) );
	    			        System.out.println(" Spec :: class 3 "+  (Dtn/Dn) );
	    			        System.out.println("-------------------------- " );
	    			        System.out.println(" Sens :: class 4 "+ (Etp/Ep) );
	    			        System.out.println(" Spec :: class 4 "+  (Etn/En) );
	    			        System.out.println("-------------------------- " );
	    			        System.out.println(" Sens :: class 5 "+ (Ftp/Fp) );
	    			        System.out.println(" Spec :: class 5 "+  (Ftn/Fn) );
	    			        System.out.println("-------------------------- " );
	    			        System.out.println(" Sens :: class 6 "+ (Gtp/Gp) );
	    			        System.out.println(" Spec :: class 6 "+  (Gtn/Gn) );
	    			        System.out.println("-------------------------- " );
	    			        System.out.println(" Sens :: class 7 "+ (Htp/Hp) );
	    			        System.out.println(" Spec :: class 7 "+  (Htn/Hn) );
	    			        System.out.println("-------------------------- " );
	    			        System.out.println(" Sens :: class 8 "+ (Itp/Ip) );
	    			        System.out.println(" Spec :: class 8 "+  (Itn/In) );
	    			        System.out.println("-------------------------- " );
	    			        System.out.println("-------------------------- " );
	    			        
	    			        /*
	    			        System.out.println(" Overall Sens  "+ (   (  (Atp/Ap)+ (Btp/Bp)+(Ctp/Cp)+(Dtp/Dp)+ (Etp/Ep)+(Ftp/Fp) +(Gtp/Gp)+ (Htp/Hp) +(Itp/Ip)  ) /9    )         );
	    			        System.out.println(" Overall Spec  "+ (   (  (Atn/An)+ (Btn/Bn)+(Ctn/Cn)+(Dtn/Dn)+  (Etn/En) +(Ftn/Fn) + (Gtn/Gn) +(Htn/Hn) + (Itn/In)  ) /9    )   		  );
	    		 	        System.out.println(" Overall accuracy  "+   (  (Atp+Btp+Ctp+Dtp +Etp+Ftp+Gtp+Htp+Itp) /(Ap+Bp+Cp+Dp+Ep+Fp+ Gp + Hp+Ip) )       );
	    		 	        */
	    		 	    		 
	    			        System.out.println(" Overall Sens  "+ (   (   (Btp/Bp)+(Ctp/Cp)+(Dtp/Dp)+ (Etp/Ep)+(Ftp/Fp) +(Gtp/Gp)+ (Htp/Hp)  )   /7    )         );
	    			        System.out.println(" Overall Spec  "+ ( (   (TN1/(TN1+FP1) )+ (TN2/ (TN2+FP2)  ) +(TN3/ (TN3+FP3))+(TN4/ (TN4+FP4))+  (TN5/ (TN5+FP5)) + (TN6/ (TN6+FP6)) + (TN7/ (TN7+FP7))    	)/7  )           );
	    		 	    //    System.out.println(" Overall accuracy  "+   (  (Btp+Ctp+Dtp +Etp+Ftp+Gtp+Htp+Itp) /(Bp+Cp+Dp+Ep+Fp+ Gp + Hp+Ip) )       );
	    		 	      
	    		 	        
	    		 	       SENE= (   (   (Btp/Bp)+(Ctp/Cp)+(Dtp/Dp)+ (Etp/Ep)+(Ftp/Fp) +(Gtp/Gp)+ (Htp/Hp)   )    /7    )  ; 
	    		 	       SEPE= ( (   (TN1/(TN1+FP1) )+ (TN2/ (TN2+FP2)  ) +(TN3/ (TN3+FP3))+(TN4/ (TN4+FP4))+  (TN5/ (TN5+FP5)) + (TN6/ (TN6+FP6)) + (TN7/ (TN7+FP7))   	)/7  )     ;  	   
	    		 	      
	    		 	     //  double FN,FP; 
	    		 	       
	    		 	       
	    		 	        FN= (Bp-Btp);
	    			        FP= FP1;
	    			    //    System.out.println("TP  "+Btp+"  FN 1 ="+ FN + " FP1  : "+FP);
	    			        FScore1= ( 2*Btp ) / ( 2*Btp+ FN +FP  );
	    		 	        
	    		 	        
	    			        
	    			        FN= (Cp-Ctp);
	    			        FP= FP2;
	    			        System.out.println("TP  "+Ctp+"  FN 1 ="+ FN + " FP1  : "+FP);
	    			        FScore2= ( 2*Ctp ) / ( 2*Ctp+ FN +FP  );
	    		 	        
	    			        
	    			        FN= (Dp-Dtp);
	    			        FP= FP3;
	    			      //  System.out.println("TP  "+Dtp+"  FN 1 ="+ FN + " FP1  : "+FP);
	    			        FScore3= ( 2*Dtp ) / ( 2*Dtp+ FN +FP  );
	    			        
	    			        
	    			        FN= (Ep-Etp);
	    			        FP= FP4;
	    			      //  System.out.println("TP  "+Etp+"  FN 1 ="+ FN + " FP1  : "+FP);
	    			        FScore4= ( 2*Etp ) / ( 2*Etp+ FN +FP  );
	    			        
	    			        
	    			        FN= (Fp-Ftp);
	    			        FP= FP5;
	    			     //   System.out.println("TP  "+Ftp+"  FN 1 ="+ FN + " FP1  : "+FP);
	    			        FScore5= ( 2*Ftp ) / ( 2*Ftp+ FN +FP  );
	    			        
	    			        
	    			        FN= (Gp-Gtp);
	    			        FP= FP6;
	    			     //   System.out.println("TP  "+Gtp+"  FN 1 ="+ FN + " FP1  : "+FP);
	    			        FScore6= ( 2*Gtp ) / ( 2*Gtp+ FN +FP  );
	    			        
	    			        
	    			        FN= (Hp-Htp);
	    			        FP= FP7;
	    			   //     System.out.println("TP  "+Htp+"  FN 1 ="+ FN + " FP1  : "+FP);
	    			        FScore7= ( 2*Htp ) / ( 2*Htp+ FN +FP  );
	    			        
	    			        
	    			      
							
							
							
	    			        
	    			        FScore=(FScore1+FScore2+FScore3+FScore4+FScore5+ FScore6+ FScore7 )/7	; 
	    			        
	    		 	         break;
	 	         
	 	           
	 	         
	 	         
	 	         
	 	         
	 	         
	 	         
	 	         
	 	         
	 	         
	 	         
	 	         
	 	         
	 	         
	 	         
	 	         
	 	         
	 	         
	    		 
	    		 
	    		 
	    	 
	    	 
	    	 } // Switch
	    	 
	    	 
	    	 
	    	 
	    	 
	    	 
	    	 
	    	 
	    	 
	    	 
	    	 
	    	 
	    	 
	    	 
	    	 
	    	 
	    	 
	    }
	    
	     
	  
	  
	  
	  
	  
	  
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
    
}








