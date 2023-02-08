package GWO;





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
import java.lang.Math; 



//package libsvm;
public class GeneTraincrossvalid{

	    //
	public SVM_Grid.svm_model model;
	public String input_file_name;     // set by parse_command_line
	public String model_file_name;     // set by parse_command_line
	public String error_msg;
	public int cross_validation;
	public int nr_fold=10;
    public SVM_Grid.svm_parameter param ; // parameter definition
    public SVM_Grid.svm_problem prob ;      // contains the data set
    public static String datafilename="";
    public String modelname="newmodel";
    //svm_train train1;
    public  ArrayList Felement ;
    public   char ChFeature[];
    
    public   char GChFeature[];
    
    public SVM_Grid.svm_model myModel ;
    public double Accuracy;
    
    public SVM_Grid.svm_problem Subprob ;   // NEW SUB
    public  ArrayList        SubFelement;// NEW SUB

   

    public double SENE=0.0;
    public  double SEPE=0.0;
    public double FScore=0.0 ,FScore1=0.0,FScore2=0.0,FScore3=0.0,FScore4=0.0,FScore5=0.0;
    public double MCC=0.0, MCC1=0.0, MCC2=0.0, MCC3=0.0, MCC4=0.0, MCC5=0.0;
    public  double STD=0;
    
    
    
    
    
    public GeneTraincrossvalid() {


         
            }
   

  
    
    public  void read_problem(int NoOfGenes, String datafilename ,int  Features []) throws IOException
    {
    	
    	param = new SVM_Grid.svm_parameter();
        prob  = new SVM_Grid.svm_problem();
		Subprob = new SVM_Grid.svm_problem();

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
         
         
         
         
         
         
         
         
         
         
         
         
         

    	
    	 
    	 
    	 
    	 
    	
    	param.svm_type = SVM_Grid.svm_parameter.C_SVC;
         param.kernel_type = SVM_Grid.svm_parameter.RBF;
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
         
         
        
           try {
        	   
        	// read data by fp file. 
        	   
         BufferedReader fp = new BufferedReader(new FileReader(datafilename));
         Vector<Double> vy = new Vector<Double>();
         Vector<SVM_Grid.svm_node[]> vx = new Vector<SVM_Grid.svm_node[]>();  // index and the value
         Vector<SVM_Grid.svm_node[]> Subvx= new Vector<SVM_Grid.svm_node[]>();
         
         ArrayList<SVM_Grid.svmsize>  SetInstances=new ArrayList<SVM_Grid.svmsize>();  // featurevalue and fitness
         
         
         
         SVM_Grid.svmsize S1= new SVM_Grid.svmsize(1,0.3);

         
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
            
        			
        			
        			
        			
        			SVM_Grid.svm_node[] x = new SVM_Grid.svm_node[m];
        			SVM_Grid.svm_node[] Subx = new SVM_Grid.svm_node[Features.length];
                     int elite_Feature=0;
            
        				for(int j=0;j<m;j++)  {
        						Findex=Integer.valueOf(st.nextToken()).intValue(); // I think t discard null value
            	
            
        						instvalue= atof(st.nextToken());
        						
        			//			x[j] = new svm_node();
        						
        					//	System.out.println("index == "+Findex+ " ,Value  ="+ instvalue);
        					
        						x[j]= new SVM_Grid.svm_node(); 
        						x[j].index = Findex;
                            	x[j].value =instvalue; 
   	
                            	
                            	
                            	if(SubFelement.contains(Findex)){
                            		
                            		Subx[elite_Feature]= new SVM_Grid.svm_node(); 
                            		
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
        		Subprob.x = new SVM_Grid.svm_node[Subprob.l][];  // Node determine the No of instances(static)  and the other value for rows
        		for(int i=0;i<Subprob.l;i++)
        			Subprob.x[i] = Subvx.elementAt(i); 
     //   
        			Subprob.y = new double[Subprob.l]; // Array size of classes 
        
        			for(int i=0;i<Subprob.l;i++)
        				Subprob.y[i] = vy.elementAt(i);    //
        
       
        
        
        
        

        	prob.l = vy.size();   // No of instances
           	prob.x = new SVM_Grid.svm_node[prob.l][];  // Node determine the No of instances(static)  and the other value for rows
        	for(int i=0;i<prob.l;i++)
        		prob.x[i] = vx.elementAt(i);
        	
    
        	prob.y = new double[prob.l]; // Array size of classes 
        
        	for(int i=0;i<prob.l;i++)
        		prob.y[i] = vy.elementAt(i);    //
        
        
        
        
        
        	if(param.gamma == 0 && max_index > 0)
        		param.gamma = 1.0/max_index;

        	if(param.kernel_type == SVM_Grid.svm_parameter.PRECOMPUTED)
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
    
    
    

    
    public double do_cross_validation(String ChannPositions,int All_Fea, int Classes, SVM_Grid.svm_problem Training,SVM_Grid.svm_parameter ParamValid)
	{
		
    	
    	     int i;
		
	        int total = 0;
	        ArrayList SelectedChann =new ArrayList() ;

	      
	        /*
	        int ChannBit=0;
	        String ChannPositions="";
	    
	     	   for (int ChIdx=0; ChIdx<All_Fea; ChIdx++ ){
	       	 
	     	   if(    SelectedChannels[ChIdx]=='1'){
	 		    
	     		  ChannPositions= ChannPositions + (ChIdx+1)+",";
	           	
	              }    
	  	
	            }*/
		
		
		
		
		int total_correct = 0;
		double total_error = 0;
		double sumv = 0, sumy = 0, sumvv = 0, sumyy = 0, sumvy = 0;
		
		
		
		//SVMCROSS.SVM_Train_Test2(Training,Testing,ParamValid,nr_fold,SelectedChannels,FlowerLength);
		
	 
	   // public double do_cross_validation(char [] SelectedChannels,int All_Fea,svm_problem Training,svm_parameter ParamValid)
		double[] target = new double[Training.l];
		
		//System.out.println(" nr_fold  "+nr_fold + " ChannPositions="+ChannPositions);

		
		SVM_Grid.SVMCROSS.svm_cross_validation(Training,Training, ParamValid, nr_fold, target,ChannPositions, All_Fea);
	
		//System.out.println("  sol before pass crossvalid    "+ChannPositions);
		
		/*String  F ="1,0,1,1,0,0";
		double Y []=  null;
		
		
		SVMCROSSFlower.svm_cross_validation(Training, ParamValid, nr_fold,Y, F, All_Fea);	*/
		
		
		
		
		if(ParamValid.svm_type == SVM_Grid.svm_parameter.EPSILON_SVR ||
				ParamValid.svm_type == SVM_Grid.svm_parameter.NU_SVR)
		{
			for(i=0;i<Training.l;i++)
			{
				double y = Training.y[i];
				double v = target[i];
				total_error += (v-y)*(v-y);
				sumv += v;
				sumy += y;
				sumvv += v*v;
				sumyy += y*y;
				sumvy += v*y;
			}
			System.out.print("Cross Validation Mean squared error = "+total_error/Training.l+"\n");
			System.out.print("Cross Validation Squared correlation coefficient = "+
				((Training.l*sumvy-sumv*sumy)*(Training.l*sumvy-sumv*sumy))/
				((Training.l*sumvv-sumv*sumv)*(Training.l*sumyy-sumy*sumy))+"\n"
				);
			return -0.444;
		}
		
		
		
		else
		{
			
	
			
			//	for(i=0;i<target.length ;i++){
				
				        
			 
				
			              
				
				
			Accuracy=100.0* ( sensitivity (target, Training, Classes  )) /target.length;
			
		
			return Accuracy;
		}
	}


    
    

    public  int  sensitivity (double [] predict,   SVM_Grid.svm_problem  problems, int Classes ){

    	
    	  
    	  
    	// CNS 1 cancer ... 0 normal.
    	// Breast 1 replace 0 nonreplace
    	// Colon 1 tumor   0 normal
    	 // Leukemia ALL 0 AML 1
    	    	 int total_correct = 0;

    	switch (Classes) {
    	 
    	 case 2: 
       // System.out.println("      Binary Classes          ");
        double TP=0,P=0,TN=0,N=0,FP=0,FN=0;
		

        
        for(int i=0;i<problems.l;i++){
        	
        	 if(predict[i] == problems.y[i]){
					++total_correct;
			
				
			}
        	
        	
        	
        	
        	
        	
        	
    	        	        	
        	 if (problems.y[i] == 0) {
 	        		P++;

                 if (predict[i] == problems.y[i]) 
                 	TP++;
                 }
        	
        	//         Specifity
                 
              if (problems.y[i] == 1) {
  	        		N++;

                  if (predict[i] == problems.y[i]) 
                  	TN++;
                  }
        	
       
        
        	/*if(problems.y[i]==0 && predict[i]==problems.y[i] )
        		 TP++;
        
        	if(problems.y[i]==0 && predict[i]!=problems.y[i] )
        		FN++;
        	
        
        	if(problems.y[i]==1 && predict[i]==problems.y[i] )
        		FP++;
       
        	if(problems.y[i]==1 && predict[i]!=problems.y[i] )
       			TN++;*/
        
        }
        
        SENE=(TP/P) ;
        SEPE= (TN/N);
        FN= (P-TP);
        FP= (N-TN);
        FScore= ( 2*TP ) / ( 2*TP+ FN +FP  );
        MCC= (TP*TN-FP*FN) / Math.sqrt ( (TP+FP) * (TP+FN) * (TN+FP) * (TN+FN)   ) ;
        
        /*System.out.println(" Overall Sens  "+    (TP/P)   ) ;
        System.out.println(" Overall Spec  "+     (TN/N)    )  ;
	    System.out.println(" Overall accuracy  "+   (  (TP+TN) /(P+N) )       );
	    System.out.println(" Overall FScore  "+   (  FScore   )    );    	        
                        
        System.out.println("		Confusion Matrix			\n\n");
		
		System.out.println("      0                1     ");
		System.out.println("---------------------------  ");
		System.out.println(" 0 |"+TP+"          "+(P-TP)   );
		System.out.println(" 1 |"+(N-TN)+"      "+(TN)   );
		System.out.println("---------------------------  ");*/
		
        
        
        
        
         break;
    	 
    	 
    	 case 3: 
    	    //    System.out.println("      multiclass         ");
    	        
    	        
    	        double Ap=0,Atp=0,An=0,Atn=0, Fp111=0, TN111=0 ;
    	        double Bp=0,Btp=0,Bn=0,Btn=0, Fp222=0,TN222=0;
    	        double Cp=0,Ctp=0,Cn=0,Ctn=0, Fp333=0,TN333=0;
    	        
    	        
    	        for(int i=0;i<problems.l;i++){
    	        
    	        	
    	        	 
    	    
    	            	
    	            	 if(predict[i] == problems.y[i]){
    	    					++total_correct;
    	    			
    	    				
    	    			}
    	            	
    	        	
    	        	
    	        	
    	        	
    	        	if (problems.y[i] == 0) {
     	 	        	Ap++;

     	                 if (predict[i] == problems.y[i]) 
     	                 	Atp++;
     	             
     	 	        
     	 	        if (predict[i] != problems.y[i]) 
     	                     Atn++; 
     	 	        //SP
     	 	        
     	 	                        
     	             }
     	 	        
     	 	       if (predict[i] == 0 && problems.y[i] !=0 ) {
     	                  Fp111++; 

     	 	       }
     	 	        
     	 	       
     	 	       
     	 	      // ##############################################3 
     	 	      if (problems.y[i] == 1) {
     		        	Bp++;

     		        	 if (predict[i] == problems.y[i]) 
     		                 	Btp++;
     		             
     		 	        
     		 	        if (predict[i] != problems.y[i]) 
     		                     Btn++; 
     		 	        
     	 	      }
     		        //SP
     		        
     		                        
     	          
     		        
     		       if (predict[i] == 1 && problems.y[i] !=1 ) {
     	                Fp222++; 

     		       }
     	 	       
     	 	       
     	 	       
     	 	       
     	 	      //*************************************************** 
     		       
     		       if (problems.y[i] == 2) {
     		        	Cp++;

     		        	 if (predict[i] == problems.y[i]) 
     		                 	Ctp++;
     		             
     		 	        
     		 	        if (predict[i] != problems.y[i]) 
     		                     Ctn++; 
     		 	        
     		      }
     		        //SP
     		        
     		                        
     	         
     		        
     		       if (predict[i] == 2 && problems.y[i] !=2 ) {
     	               Fp333++; 

     		       } 
     		       
     		       
     	 	      
    	        	
    	        	
    	          
    	        
    	        
    	        }// For    
    	        
    	            TN111= problems.l - (Atp+Atn+Fp111);
    	 	       TN222= problems.l - (Btp+Btn+Fp222);
    	 	       TN333= problems.l - (Ctp+Ctn+Fp333);
    	        
    	        
    	        
    	   
     	        SENE= (  (Atp/Ap)+ (Btp/Bp)+(Ctp/Cp) ) /3   ;
     	        SEPE= (   (TN111/ (TN111+Fp111))+  (TN222/ (TN222+Fp222))+ (TN333/ (TN333+Fp333)) ) /3    ;
     	        FN= (Ap-Atp);
     	        FP= Fp111;
     	        FScore1= ( 2*Atp ) / ( 2*Atp+ FN +FP  );
        	    MCC1= (Atp*TN111-FP*FN) / Math.sqrt ( (Atp+FP) * (Atp+FN) * (TN111+FP) * (TN111+FN)   ) ;

     	        
     	        FN= (Bp-Btp);
    	        FP= Fp222;
     	        FScore2=( 2*Btp ) / ( 2*Btp+ FN +FP  );
        	    MCC2= (Btp*TN222-FP*FN) / Math.sqrt ( (Btp+FP) * (Btp+FN) * (TN222+FP) * (TN222+FN)   ) ;

     	        
     	       FN= (Cp-Ctp);
   	           FP= Fp333;
     	       FScore3= ( 2*Ctp ) / ( 2*Ctp+ FN +FP  );
       	       MCC3= (Ctp*TN333-FP*FN) / Math.sqrt ( (Ctp+FP) * (Ctp+FN) * (TN333+FP) * (TN333+FN)   ) ;

     	       MCC=  ( (MCC1+MCC2+MCC3 ) /3 );
     	       FScore= (FScore1+FScore2+FScore3)/3;
     	    //   System.out.println(" Total FScore  "+   (  FScore )    );

     	     
     	     

    	         break;
    	 
    	         
    	         
    	 case 4:
    		 
    	//	 System.out.println("      multiclass   4      ");
 	        
 	        
 	        double App=0,Atpp=0,Ann=0,Atnn=0, Fp11=0,  TN11 ;
 	        double Bpp=0,Btpp=0,Bnn=0,Btnn=0, Fp22=0,  TN22 ;
 	        double Cpp=0,Ctpp=0,Cnn=0,Ctnn=0, Fp33=0,  TN33 ;
 	       double  Dpp=0,Dtpp=0,Dnn=0,Dtnn=0, Fp44=0,  TN44 ;
 	        
 	        for(int i=0;i<problems.l;i++){
 	        	
 	        	
 	        	 if(predict[i] == problems.y[i]){
 					++total_correct;
 			
 				
 			}
 	        	
 	        	
 	        	
 	        
 	        	if (problems.y[i] == 0) {
 	 	        	App++;

 	                 if (predict[i] == problems.y[i]) 
 	                 	Atpp++;
 	             
 	 	        
 	 	        if (predict[i] != problems.y[i]) 
 	                     Atnn++; 
 	 	        //SP
 	 	        
 	 	                        
 	             }
 	 	        
 	 	       if (predict[i] == 0 && problems.y[i] !=0 ) {
 	                  Fp11++; 

 	 	       }
 	 	        
 	 	       
 	 	      // ##############################################3 
 	 	      if (problems.y[i] == 1) {
 		        	Bpp++;

 		        	 if (predict[i] == problems.y[i]) 
 		                 	Btpp++;
 		             
 		 	        
 		 	        if (predict[i] != problems.y[i]) 
 		                     Btnn++; 
 		 	        
 	 	      }
 		        //SP
 		        
 		                        
 	          
 		        
 		       if (predict[i] == 1 && problems.y[i] !=1 ) {
 	                Fp22++; 

 		       }
 	 	       
 	 	       
 	 	       
 	 	       
 	 	      //*************************************************** 
 		       
 		       if (problems.y[i] == 2) {
 		        	Cpp++;

 		        	 if (predict[i] == problems.y[i]) 
 		                 	Ctpp++;
 		             
 		 	        
 		 	        if (predict[i] != problems.y[i]) 
 		                     Ctnn++; 
 		 	        
 		      }
 		        //SP
 		        
 		                        
 	         
 		        
 		       if (predict[i] == 2 && problems.y[i] !=2 ) {
 	               Fp33++; 

 		       } 
 		       
 		       
 	 	       
 	 	       
 	 	       //***************************************************************
 		       
 		       
 		       
 		       if (problems.y[i] == 3) {
 		        	Dpp++;

 		        	 if (predict[i] == problems.y[i]) 
 		                 	Dtpp++;
 		             
 		 	        
 		 	        if (predict[i] != problems.y[i]) 
 		                     Dtnn++; 
 		 	        
 		      }
 		        //SP
 		        
 		                        
 	        
 		        
 		       if (predict[i] == 3 && problems.y[i] !=3 ) {
 	              Fp44++; 

 		       } 
 		       
 		       
 		       
 		       //******************************
 		       
 	        	
 	        	
 	        
 	       
 	        
 	        
 	        
 	        
 	        }// For    
 	        
 	        
 	        
 	        
 	        
 	   
 	        
 	        
 	       TN11= problems.l - (Atpp+Atnn+Fp11);
 	       TN22= problems.l - (Btpp+Btnn+Fp22);
 	       TN33= problems.l - (Ctpp+Ctnn+Fp33);
 	       TN44= problems.l - (Dtpp+Dtnn+Fp44);
 	       
 	        
 	        
 	        
 	        
 	        
 	        
 	        
 	        
 	   
 	        
 	       SENE= (  (Atpp/App)+ (Btpp/Bpp)+(Ctpp/Cpp)+(Dtpp/Dpp) ) /4       ;
 	        SEPE= (  (TN11/ (TN11+Fp11))+ (TN22/ (TN22+Fp22))+(TN33/ (TN33+Fp33))+(TN44/ (TN44+Fp44)) ) /4     ;
 	        
 	        
 	       FN= (App-Atpp);
	        FP= Fp11;
	        FScore1= ( 2*Atpp ) / ( 2*Atpp+ FN +FP  );
    	    MCC1= (Atpp*TN11-FP*FN) / Math.sqrt ( (Atpp+FP) * (Atpp+FN) * (TN11+FP) * (TN11+FN)   ) ;
 	        
	        FN= (Bpp-Btpp);
	        FP= Fp22;
	        FScore2= ( 2*Btpp ) / ( 2*Btpp+ FN +FP  );
    	    MCC2= (Btpp*TN22-FP*FN) / Math.sqrt ( (Btpp+FP) * (Btpp+FN) * (TN22+FP) * (TN22+FN)   ) ;

 	        
	        FN= (Cpp-Ctpp);
	        FP= Fp33;
	        FScore3= ( 2*Ctpp ) / ( 2*Ctpp+ FN +FP  );
    	    MCC3= (Ctpp*TN33-FP*FN) / Math.sqrt ( (Ctpp+FP) * (Ctpp+FN) * (TN33+FP) * (TN33+FN)   ) ;
	        
	        FN= (Dpp-Dtpp);
	        FP= Fp44;
	        FScore4= ( 2*Dtpp ) / ( 2*Dtpp+ FN +FP  );
    	    MCC4= (Dtpp*TN44-FP*FN) / Math.sqrt ( (Dtpp+FP) * (Dtpp+FN) * (TN44+FP) * (TN44+FN)   ) ;

	        FScore=(FScore1+FScore2+FScore3+FScore4)/4	; 
	        MCC=   ( (MCC1+MCC2+MCC3 +MCC4 ) /4 );
 	    	
 	    //    System.out.println(" Overall FSCORE  "+   (  FScore )       );

 	         break;
 	 
    		 
 	         
 	         
 	         
 	         
 	    
 	         
    	 case 5:    
 	         
 	         
// System.out.println("      multiclass   5      ");
 	        
 	        
 	        double Appp=0,Atppp=0,Annn=0,Atnnn=0 , Fp1=0, TN1=0;
 	        double Bppp=0,Btppp=0,Bnnn=0,Btnnn=0, Fp2=0, TN2=0;
 	        double Cppp=0,Ctppp=0,Cnnn=0,Ctnnn=0, Fp3=0, TN3=0;
 	       double  Dppp=0,Dtppp=0,Dnnn=0,Dtnnn=0, Fp4=0, TN4=0;
 	      double  Eppp=0,Etppp=0,Ennn=0,Etnnn=0, Fp5=0, TN5=0;
 	      
 	      
 	        for(int i=0;i<problems.l;i++){
 	        
 	        
 	        	 if(predict[i] == problems.y[i]){
  					++total_correct;
  			
  				
  			}  	
 	        	
 	        	
 	        	
 	        // SN	CLass 0
 	        if (problems.y[i] == 0) {
 	        	Appp++;

                 if (predict[i] == problems.y[i]) 
                 	Atppp++;
             
 	        
 	        if (predict[i] != problems.y[i]) 
                     Atnnn++; 
 	        //SP
 	        
 	                        
             }
 	        
 	       if (predict[i] == 0 && problems.y[i] !=0 ) {
                  Fp1++; 

 	       }
 	        
 	       
 	      // ##############################################3 
 	      if (problems.y[i] == 1) {
	        	Bppp++;

	        	 if (predict[i] == problems.y[i]) 
	                 	Btppp++;
	             
	 	        
	 	        if (predict[i] != problems.y[i]) 
	                     Btnnn++; 
	 	        
 	      }
	        //SP
	        
	                        
          
	        
	       if (predict[i] == 1 && problems.y[i] !=1 ) {
                Fp2++; 

	       }
 	       
 	       
 	       
 	       
 	      //*************************************************** 
	       
	       if (problems.y[i] == 2) {
	        	Cppp++;

	        	 if (predict[i] == problems.y[i]) 
	                 	Ctppp++;
	             
	 	        
	 	        if (predict[i] != problems.y[i]) 
	                     Ctnnn++; 
	 	        
	      }
	        //SP
	        
	                        
         
	        
	       if (predict[i] == 2 && problems.y[i] !=2 ) {
               Fp3++; 

	       } 
	       
	       
 	       
 	       
 	       //***************************************************************
	       
	       
	       
	       if (problems.y[i] == 3) {
	        	Dppp++;

	        	 if (predict[i] == problems.y[i]) 
	                 	Dtppp++;
	             
	 	        
	 	        if (predict[i] != problems.y[i]) 
	                     Dtnnn++; 
	 	        
	      }
	        //SP
	        
	                        
        
	        
	       if (predict[i] == 3 && problems.y[i] !=3 ) {
              Fp4++; 

	       } 
	       
	       
	       
	   
	       
	       //******************************
	       
	       if (problems.y[i] == 4) {
	        	Eppp++;

	        	 if (predict[i] == problems.y[i]) 
	                 	Etppp++;
	             
	 	        
	 	        if (predict[i] != problems.y[i]) 
	                    Etnnn++; 
	 	        
	      }
	        //SP
	        
	                        
        
	        
	       if (predict[i] == 4 && problems.y[i] !=4 ) {
              Fp5++; 

	       } 
	       
	       
 	        
 	        //**********************************

 	        
 	        
 	        }// For    
 	        
 	        
 	        
 	       TN1= problems.l - (Atppp+Atnnn+Fp1);
 	       TN2= problems.l - (Btppp+Btnnn+Fp2);
 	       TN3= problems.l - (Ctppp+Ctnnn+Fp3);
 	       TN4= problems.l - (Dtppp+Dtnnn+Fp4);
 	       TN5= problems.l - (Etppp+Etnnn+Fp5);

 	        
 	        
 	    
 	    		 
 	        SENE=   (  (Atppp/Appp)+ (Btppp/Bppp)+(Ctppp/Cppp)+(Dtppp/Dppp)+ (Etppp/Eppp)) /5        ;
 	        SEPE=  (  (TN1/(TN1+Fp1) )+ (TN2/(TN2+Fp2) )+(TN3/(TN3+Fp3) )+(TN4/(TN4+Fp4) )+  (TN5/(TN5+Fp5) ) ) /5      ;
 	        
 	        
 	        FN= (Appp-Atppp);
	        FP= Fp1;
//	        System.out.println("TP  "+Atppp+"  FN 1 ="+ FN + " FP1  : "+FP);
	        FScore1= ( 2*Atppp ) / ( 2*Atppp+ FN +FP  );
    	    MCC1= (Atppp*TN1-FP*FN) / Math.sqrt ( (Atppp+FP) * (Atppp+FN) * (TN1+FP) * (TN1+FN)   ) ;

	        
	        FN= (Bppp-Btppp);
	        FP= Fp2;
	        //	        System.out.println("TP  "+Btppp+"  FN 2 ="+ FN + " FP2  : "+FP);

	        FScore2= ( 2*Btppp ) / ( 2*Btppp+ FN +FP  );
    	    MCC2= (Btppp*TN2-FP*FN) / Math.sqrt ( (Btppp+FP) * (Btppp+FN) * (TN2+FP) * (TN2+FN)   ) ;

	        
	        FN= (Cppp-Ctppp);
	        FP= Fp3;
	        //	        System.out.println("TP  "+Ctppp+"  FN 3 ="+ FN + " FP3  : "+FP);

	        FScore3= ( 2*Ctppp ) / ( 2*Ctppp+ FN +FP  );
    	    MCC3= (Ctppp*TN3-FP*FN) / Math.sqrt ( (Ctppp+FP) * (Ctppp+FN) * (TN3+FP) * (TN3+FN)   ) ;

	        
	        FN= (Dppp-Dtppp);
	        FP= Fp4;
	        //        System.out.println("TP  "+Dtppp+"  FN 4 ="+ FN + " FP4  : "+FP);

	        FScore4= ( 2*Dtppp ) / ( 2*Dtppp+ FN +FP  );
    	    MCC4= (Dtppp*TN4-FP*FN) / Math.sqrt ( (Dtppp+FP) * (Dtppp+FN) * (TN4+FP) * (TN4+FN)   ) ;

	        
	        FN= (Eppp-Etppp);
	        FP= Fp5;
	        //      System.out.println("TP  "+Etppp+"  FN 5 ="+ FN + " FP5  : "+FP);

	        FScore5= ( 2*Etppp ) / ( 2*Etppp+ FN +FP  );
    	    MCC5= (Etppp*TN5-FP*FN) / Math.sqrt ( (Etppp+FP) * (Etppp+FN) * (TN5+FP) * (TN5+FN)   ) ;

	        
	        FScore=(FScore1+FScore2+FScore3+FScore4+FScore5)/5	; 
	        MCC= (MCC1 + MCC2 +MCC3+ MCC4+MCC5)/5;
	        
	        //      System.out.println(" Fscore1 :"+ FScore1 +"  Fscore2 :"+ FScore2  +"  Fscore3 :"+ FScore3 +"  Fscore4 :"+ FScore4  + " Fscore5 :"+ FScore5 );
	        //      System.out.println(" Overall FScore  "+   ( FScore )      );
    
 	        
 	        
 	        
	     /*   FN= (App-Atpp);
	        FP= (Ann-Atnn);
	        FScore1= ( 2*Atpp ) / ( 2*Atpp+ FN +FP  );*/
 	        
 	        
 	        
 	        
 	        
 	        
 	         break;
 	 
 	         
 	         
 	         
 	         
 	         
 	         
 	         
 	         
    	 case 9:    
 	         
 	         
    	//	 System.out.println("      multiclass   9      ");
    		 	        
    		 	        
    		 	        double Apppp=0,Atpppp=0,Annnn=0,Atnnnn=0;
    		 	        double Bpppp=0,Btpppp=0,Bnnnn=0,Btnnnn=0;
    		 	        double Cpppp=0,Ctpppp=0,Cnnnn=0,Ctnnnn=0;
    		 	        double Dpppp=0,Dtpppp=0,Dnnnn=0,Dtnnnn=0;
    		 	        double Epppp=0,Etpppp=0,Ennnn=0,Etnnnn=0;
    		 	        
    		 	        double Fpppp=0,Ftpppp=0,Fnnnn=0,Ftnnnn=0;
    		 	        double Gpppp=0,Gtpppp=0,Gnnnn=0,Gtnnnn=0;
    		 	        double Hpppp=0,Htpppp=0,Hnnnn=0,Htnnnn=0;
    		 	        double Ipppp=0,Itpppp=0,Innnn=0,Itnnnn=0;
    		 	        
    		 	        
    		 	      
    		 	      
    		 	        for(int i=0;i<problems.l;i++){
    		 	        
    		 	        
    		 	        // SN	CLass 0
    		 	        if (problems.y[i] == 0) {
    		 	        	Apppp++;

    		                 if (predict[i] == problems.y[i]) 
    		                 	Atpppp++;
    		              }
    		 	        
    		 	        
    		 	        //SP
    		 	        
    		 	        if (problems.y[i] != 0) {
    		                 Annnn++;

    		                 if (predict[i] == problems.y[i]) 
    		                     Atnnnn++; 
    		             }
    		 	        
    		 	        
    		 	        //**********************************

    		 	        
    		 	        
    		 	        // SN	CLass 1
    		 	        if (problems.y[i] == 1) {
    		 	        	Bpppp++;

    		                 if (predict[i] == problems.y[i]) 
    		                 	Btpppp++;
    		              }
    		 	            	        
    		 	        //SP
    		 	        
    		 	        if (problems.y[i] != 1) {
    		                 Bnnnn++;

    		                 if (predict[i] == problems.y[i]) 
    		                     Btnnnn++; 
    		             }
    		 	        
    		 	        
    		 	        //**********************************
    		 	        
    		 	        
    		 	        // SN	CLass 2
    		 	        if (problems.y[i] == 2) {
    		 	        	Cpppp++;

    		                 if (predict[i] == problems.y[i]) 
    		                 	Ctpppp++;
    		              }
    		 	        
    		 	        
    		 	        //SP
    		 	        
    		 	        if (problems.y[i] != 2) {
    		                 Cnnnn++;

    		                 if (predict[i] == problems.y[i]) 
    		                     Ctnnnn++; 
    		             }
    		 	        
    		 	       //**********************************
    		 	        
    		 	    	        
    		 	       // SN	CLass 3
    		 	        
    		 	        if (problems.y[i] == 3) {
    		 	        		Dpppp++;

    		                 if (predict[i] == problems.y[i]) 
    		                 	Dtpppp++;
    		              }
    		 	        
    		 	        
    		 	        
    		 	       if (problems.y[i] != 3) {
    		               Dnnnn++;

    		               if (predict[i] == problems.y[i]) 
    		                   Dtnnnn++; 
    		           }
    		 	        
    		 	        
    		 	        
    		 	        
    		 	       
    		 	    //**********************************
    			        
    			        
    		 	       // SN	CLass 4
    		 	        
    		 	        if (problems.y[i] == 4) {
    		 	        		Epppp++;

    		                 if (predict[i] == problems.y[i]) 
    		                 	Etpppp++;
    		              }
    		 	        
    		 	    //   System.out.println(" EPPP : "+Eppp +"  Etppp : "+Etppp);
    		 	        // SP
    		 	       if (problems.y[i] != 4) {
    		               Ennnn++;

    		               if (predict[i] == problems.y[i]) 
    		                   Etnnnn++; 
    		           }
    		 	        
    		 	        
    		 	        
    		 	      //**********************************
   			        
   			        
    		 	       // SN	CLass 5
    		 	        
    		 	        if (problems.y[i] == 5) {
    		 	        		Fpppp++;

    		                 if (predict[i] == problems.y[i]) 
    		                 	Ftpppp++;
    		              }
    		 	        
    		 	    //   System.out.println(" EPPP : "+Eppp +"  Etppp : "+Etppp);
    		 	        // SP
    		 	       if (problems.y[i] != 5) {
    		               Fnnnn++;

    		               if (predict[i] == problems.y[i]) 
    		                   Ftnnnn++; 
    		           }
    		 	        
    		 	        
    		 	       
    		 	        
    		 	       
    		 	    //**********************************
      			        
      			        
    		 	       // SN	CLass 6
    		 	        
    		 	        if (problems.y[i] == 6) {
    		 	        		Gpppp++;

    		                 if (predict[i] == problems.y[i]) 
    		                 	Gtpppp++;
    		              }
    		 	        
    		 	    //   System.out.println(" EPPP : "+Eppp +"  Etppp : "+Etppp);
    		 	        // SP
    		 	       if (problems.y[i] != 6) {
    		               Gnnnn++;

    		               if (predict[i] == problems.y[i]) 
    		                   Gtnnnn++; 
    		           }
    		 	       
    		 	       
    		 	       
    		 	       
    		 	      //**********************************
     			        
     			        
    		 	       // SN	CLass 7
    		 	        
    		 	        if (problems.y[i] == 7) {
    		 	        		Hpppp++;

    		                 if (predict[i] == problems.y[i]) 
    		                 	Htpppp++;
    		              }
    		 	        
    		 	    //   System.out.println(" EPPP : "+Eppp +"  Etppp : "+Etppp);
    		 	        // SP
    		 	       if (problems.y[i] != 7) {
    		               Hnnnn++;

    		               if (predict[i] == problems.y[i]) 
    		                   Htnnnn++; 
    		           }
    		 	            
    		 	       
    		 	       
    		 	       
    		 	       
    		 	       
    		 	       
    		 	      //**********************************
    			        
    			        
    		 	       // SN	CLass 8
    		 	        
    		 	        if (problems.y[i] == 8) {
    		 	        		Ipppp++;

    		                 if (predict[i] == problems.y[i]) 
    		                 	Itpppp++;
    		              }
    		 	        
    		 	    //   System.out.println(" EPPP : "+Eppp +"  Etppp : "+Etppp);
    		 	        // SP
    		 	       if (problems.y[i] != 8) {
    		              Innnn++;

    		               if (predict[i] == problems.y[i]) 
    		                   Itnnnn++; 
    		           }
    		 	       
    		 	       
    		 	       
    		 	       
    		 	       
    		 	       
    		 	       
    		 	        
    		 	        }// For    
    		 	        
    		 	        
    		 	        
    		 	        
    		 	        
    		 	    /*    for(int i=0;i<problems.l;i++){
    		 	        
    		 	        	if(problems.y[i]==0 && predict[i]==problems.y[i] )
    		 	        		 TP++;
    		 	        
    		 	        	if(problems.y[i]==0 && predict[i]!=problems.y[i] )
    		 	        		FN++;
    		 	        	
    		 	        
    		 	        	if( ( problems.y[i]==1 || ) && predict[i]==problems.y[i] )
    		 	        		FP++;
    		 	       
    		 	        	if(problems.y[i]==1 && predict[i]!=problems.y[i] )
    		 	       			TN++;
    		 	        
    		 	        }*/
    		 	        
    		 	        System.out.println(" Sens :: class 0 "+ (Atpppp/Apppp) );
    		 	        System.out.println(" Spec :: class 0 "+  (Atnnnn/Annnn) );
    		 	       System.out.println("-------------------------- " );
    		 	        System.out.println(" Sens :: class 1 "+ (Btpppp/Bpppp) );
    		 	        System.out.println(" Spec :: class 1 "+  (Btnnnn/Bnnnn) );
    		 	       System.out.println("-------------------------- " );
    		 	        
    		 	        System.out.println(" Sens :: class 2 "+ (Ctpppp/Cpppp) );
    		 	        System.out.println(" Spec :: class 2 "+  (Ctnnnn/Cnnnn) );
    		 	       System.out.println("-------------------------- " );
    		 	       System.out.println(" Sens :: class 3 "+ (Dtpppp/Dpppp) );
    			        System.out.println(" Spec :: class 3 "+  (Dtnnnn/Dnnnn) );
    			        System.out.println("-------------------------- " );
    			        System.out.println(" Sens :: class 4 "+ (Etpppp/Epppp) );
    			        System.out.println(" Spec :: class 4 "+  (Etnnnn/Ennnn) );
    			        System.out.println("-------------------------- " );
    			        System.out.println(" Sens :: class 5 "+ (Ftpppp/Fpppp) );
    			        System.out.println(" Spec :: class 5 "+  (Ftnnnn/Fnnnn) );
    			        System.out.println("-------------------------- " );
    			        System.out.println(" Sens :: class 6 "+ (Gtpppp/Gpppp) );
    			        System.out.println(" Spec :: class 6 "+  (Gtnnnn/Gnnnn) );
    			        System.out.println("-------------------------- " );
    			        System.out.println(" Sens :: class 7 "+ (Htpppp/Hpppp) );
    			        System.out.println(" Spec :: class 7 "+  (Htnnnn/Hnnnn) );
    			        System.out.println("-------------------------- " );
    			        System.out.println(" Sens :: class 8 "+ (Itpppp/Ipppp) );
    			        System.out.println(" Spec :: class 8 "+  (Itnnnn/Innnn) );
    			        System.out.println("-------------------------- " );
    			        System.out.println("-------------------------- " );
    			        System.out.println(" Overall Sens  "+ (   (  (Atpppp/Apppp)+ (Btpppp/Bpppp)+(Ctpppp/Cpppp)+(Dtpppp/Dpppp)+ (Etpppp/Epppp)+(Ftpppp/Fpppp) +(Gtpppp/Gpppp)+ (Htpppp/Hpppp) +(Itpppp/Ipppp)  ) /9    )         );
    			        System.out.println(" Overall Spec  "+ (   (  (Atnnnn/Annnn)+ (Btnnnn/Bnnnn)+(Ctnnnn/Cnnnn)+(Dtnnnn/Dnnnn)+  (Etnnnn/Ennnn) +(Ftnnnn/Fnnnn) + (Gtnnnn/Gnnnn) +(Htnnnn/Hnnnn) + (Itnnnn/Innnn)  ) /9    )   		  );
    		 	        System.out.println(" Overall accuracy  "+   (  (Atpppp+Btpppp+Ctpppp+Dtpppp +Etpppp+Ftpppp+Gtpppp+Htpppp+Itpppp) /(Apppp+Bpppp+Cpppp+Dpppp+Epppp+Fpppp+ Gpppp + Hpppp+Ipppp) )       );
    		 	        
    		 	    		 
    		 	         break;
 	         
 	         
 	         
 	         
 	         
 	         
 	         
 	         
 	         
 	         
 	         
 	         
 	         
 	         
 	         
 	         
 	         
 	         
 	         
 	         
 	         
 	         
 	         
 	         
 	         
 	         
 	         
 	         
 	         
 	         
 	         
 	         
 	         
    		 
    		 
    		 
    	 
    	 
    	 } // Switch
    	 
    	 
    	 
    	 
    	 
    	 
    	 
    	 
    	 
    	 
    	 
    	 return total_correct;
    	 
    	 
    	 
    	 
    	 
    }
    
    
    
    
    
    
    
    
    }


    
    
    