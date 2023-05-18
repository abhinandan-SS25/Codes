import java.lang.Math;

public class TestSimpleSoftmax {
	// Tests for the softmax method.
	// Returns true if tests pass, false otherwise.
	public static boolean testSoftmax() {
		System.out.println("Starting testSoftmax...");
		
		double[] input1 = {-3.0, 0.2, 7.8};
		double[] output1 = {2.0388883841921518E-5,
		                    5.001909085372945E-4,
		                    0.9994794202076208};
		
		if (!equalDoubleArr(SimpleSoftmax.softmax(input1), output1)) {
			System.out.println("\tTest softmax 1 failed!");
			return false;
		}
		
		double[] input2 = {0, 0, 0, 1};
		double[] output2 = {0.17487770452710946,
		                    0.17487770452710946,
		                    0.17487770452710946,
		                    0.4753668864186717};
		
		if (!equalDoubleArr(SimpleSoftmax.softmax(input2), output2)) {
			System.out.println("\tTest softmax 2 failed!");
			return false;
		}
		
		System.out.println("...Done testSoftmax");
		return true;
	}
	
	public static boolean equalDoubleArr(double[] arr1, double[] arr2) {
		double EPSILON = 0.000001;
		if (arr1 == null && arr2 == null)
			return true;
		if (arr1 == null || arr2 == null)
			return false;
		if (arr1.length != arr2.length)
			return false;
		for (int i = 0; i < arr1.length; i++) {
			if (!(Math.abs(arr1[i] - arr2[i]) <= EPSILON))
				return false;
		}
		return true;
	}
	
	// Tests for the predictLabel method.
	// Returns true if tests pass, false otherwise.
	public static boolean testPredictLabel() {
		System.out.println("Starting testPredictLabel...");

		double[] input1 = {2.0388883841921518E-5,
				5.001909085372945E-4,
				0.9994794202076208};

		if (SimpleSoftmax.predictLabel(input1) != 2) {
			System.out.println("\tTest predictLabel 1 failed!");
			return false;
		}

		double[] input2 = {0.17487770452710946,
				0.17487770452710946,
				0.17487770452710946,
				0.4753668864186717};
		if (SimpleSoftmax.predictLabel(input2) != 3) {
			System.out.println("\tTest predictLabel 2 failed!");
			return false;
		}

		System.out.println("...Done testPredictLabel");
		return true;
	}
	
	// Tests for the calcPredictionAccuracy method.
	// Returns true if tests pass, false otherwise.
	public static boolean testCalcPredictionAccuracy() {
		double EPSILON = 0.000001;
		
		System.out.println("Starting testCalcPredictionAccuracy...");
		
		{
			double[][] input = {{-3.0, 0.2, 7.8},
			                    {1, 0, 0},
			                    {10, 30, -3}};
			int[] gtLabel = {2, 0, 0};
			double correctResult = (double) 2 / 3;
			if (!(Math.abs(SimpleSoftmax.calcPredictionAccuracy(input, gtLabel) - correctResult) <= EPSILON)) {
				System.out.println("\tTest calcPredictionAccuracy 1 failed!");
				return false;
			}
		}
		
		{
			double[][] input = {{-3.0, 0.2, 7.8, 2.2},
			                    {0, 0, 0, 1}};
			int[] gtLabel = {2, 3};
			double correctResult = 1.0;
			if (!(Math.abs(SimpleSoftmax.calcPredictionAccuracy(input, gtLabel) - correctResult) <= EPSILON)) {
				System.out.println("\tTest calcPredictionAccuracy 2 failed!");
				return false;
			}
		}
		
		{
			double[][] input = {
			                    {-3.0, 0.2, 7.8, 2.2, 1.3},
			                    {0, 0, 0, 1, 0},
			                    {-3.2, 2.1, 2.1, 0.0005, 1.32},
			                    {5.74, -1.81, 10.94, 0.84, 13.70},
			                    {7.71, 11.34, 0.27, 13.08, -0.95},
			                    {0.97, 2.22, 11.80, 8.76, 2.12},
			                    {-3.15, 6.76, 7.64, 5.64, 6.24},
			                    {13.26, -0.35, 10.26, 6.13, 11.25},
			                    {2.91, -0.92, 3.51, 11.00, 13.57},
			                    {8.60, 13.21, 12.88, 10.89, 12.27}
			                   };
			int[] gtLabel = {2, 3, 2, 4, 3, 2, 2, 0, 4, 2};
			double correctResult = 0.8;
			if (!(Math.abs(SimpleSoftmax.calcPredictionAccuracy(input, gtLabel) - correctResult) <= EPSILON)) {
				System.out.println("\tTest calcPredictionAccuracy 3 failed!");
				return false;
			}
		}
		
		System.out.println("...Done testCalcPredictionAccuracy");
		return true;
	}
	

    // Tests for the compareAccuracy method.
	// Returns true if tests pass, false otherwise.
	public static boolean testCompareAccuracy() {
		double EPSILON = 0.000001;
		
		System.out.println("Starting testCompareAccuracy...");
		
		{
			double[][][] nonNormArrSeqs = {{{-3.0, 0.2, 7.8},{1.0, 0.0, 0.0},{1.0, 2.0, 3.0}},
				{{-3.0, 0.2, 7.8},{1.0, 0.0, 0.0},{1.0, 2.0, 3.0}}};
            int[][] groundTruthArrs = {{2, 0, 0},{2, 2, 0}};
			double correctResult = (double) 2 / 3;
			if (!(Math.abs(SimpleSoftmax.compareAccuracy(nonNormArrSeqs, groundTruthArrs) - correctResult) <= EPSILON)) {
				System.out.println("\tTest compareAccuracy 1 failed!");
				return false;
			}
		}
		
		{
			double[][][] nonNormArrSeqs = {{{2.0, 5.5, 3.8},{2.9, 6.7, 7.6},{3.3, 1.2, 2.1}},
				{{2.0, 5.5, 3.8},{2.9, 6.7, 7.6},{3.3, 1.2, 2.1}}};
            int[][] groundTruthArrs = {{2, 2, 0},{1, 2, 0}}; 
			double correctResult = 1.0;
			if (!(Math.abs(SimpleSoftmax.compareAccuracy(nonNormArrSeqs, groundTruthArrs) - correctResult) <= EPSILON)) {
				System.out.println("\tTest compareAccuracy 2 failed!");
				return false;
			}
		}
		
		System.out.println("...Done testCompareAccuracy");
		return true;
	}



	public static void main(String[] args) {
		testSoftmax();
		testPredictLabel();
		testCalcPredictionAccuracy();
        testCompareAccuracy();
	}

}