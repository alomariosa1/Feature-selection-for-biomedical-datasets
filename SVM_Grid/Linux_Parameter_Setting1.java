package SVM_Grid;


public interface Linux_Parameter_Setting1 {
	
	
	// galpha  changed 23\\1\\2016
	//bw 0.7 changed to 0.9
	
	
	
	
	int SWARM_SIZE = 100;
	int MAX_ITERATION = 100;
	int DIMENSION = 50;
	int Runs=30;


	int B_NI=50;
	double B_B=0;
	double B_BW=0.3;
double Rthreshold=0.3;



	
   //pls take care all order of data and genes and c,gammal       ALL Changed

	
	String [] data         ={"Breast\\Scale.txt",  "MLL\\Scale.txt",  "Colon\\Scale.txt"   ,  "Leukemia\\Scale.txt" , "Leukemia_3c\\Scale.txt" ,  "Leukemia_4c\\Scale.txt" ,  "Lymphoma\\Scale.txt", "CNS\\Scale.txt" , "Ovarian\\Scale.txt" , "SRBCT\\Scale.txt"};
 
	String [] Results      ={"Results\\Breast.txt", "Results\\MLL.txt",  "Results\\Colon.txt"   ,  "Results\\Leukemia.txt" , "Results\\Leukemia_3c.txt" ,  "Results\\Leukemia_4c.txt" ,  "Results\\Lymphoma.txt" ,"Results\\CNS.txt",  "Results\\Ovarian.txt" , "Results\\SRBCT.txt"};

	String [] ExResults         ={"Breast\\Runs ",  "MLL\\Runs ",  "Colon\\Runs "   ,  "Leukemia\\Runs " , "Leukemia_3c\\Runs " ,  "Leukemia_4c\\Runs " ,  "Lymphoma\\Runs " ,"CNS\\Runs " , "Ovarian\\Runs " , "SRBCT\\Runs "};

	int    [] GenesCounting={24481  , 12582  , 2000,   7129,  7129, 7129 , 4026 , 7129, 15154,    2308 };
	
		String [] DataSource         ={ "Breast","MLL",  "Colon"   ,  "Leukemia" , "Leukemia_3c" ,  "Leukemia_4c" ,  "Lymphoma", "CNS" , "Ovarian" , "SRBCT"};
	
	double Beta_min=0.0;
	double Beta_max=1;
	
	
	
	double Freq_min=0.3;
	double Freq_max=1;
	
	double Vel_min=-6;
	double Vel_max=6;
	
		
	
	double EPS_min=-1;
	double EPS_max=1;
	
	double Pulse_m=0;
	double Pulse_mx=1;
	
	double Loudness_min=1;
	double Loudness_max=2;
	
	double IntR=0.5;
	
	double GAlpha=0.5;//org 0.9
	
	double Alpha= 0.9;
	double Gamma= 0.9; 

// inside decrease r

	
	double Bw=0.8;
	double MutPercent=0.5;
	
	double C1 = 2.0;
	double C2 = 2.0;
	double W_UPPERBOUND = 1.0;
	double W_LOWERBOUND = 0.0;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
