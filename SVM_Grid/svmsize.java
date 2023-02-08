package SVM_Grid;


public class svmsize {
int Sindex;
Double SValue;


public svmsize(int vFeatureValue, double vFeatureFitness) {

	Sindex = vFeatureValue;
	SValue = vFeatureFitness;
}

public int getSindex() {
	return Sindex;
}
public void setSindex(int sindex) {
	Sindex = sindex;
}
public Double getSValue() {
	return SValue;
}
public void setSValue(Double sValue) {
	SValue = sValue;
}

}
