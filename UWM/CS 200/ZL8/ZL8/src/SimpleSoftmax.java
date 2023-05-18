///////////////////////// TOP OF FILE COMMENT BLOCK ////////////////////////////
//
// Title:	Calculating softMax of arrays and accuracies of prediction
// 					labels
// Course:	CS200, Fall 2022
//
// Author:	Abhinandan Saha
// Email:	asaha33@wisc.edu
// Lecturer's Name: Jim Williams
//
///////////////////////////////// CITATIONS ////////////////////////////////////
//
// No help given or received.
//
/////////////////////////////// 80 COLUMNS WIDE ////////////////////////////////

/**
 *
 */
public class SimpleSoftmax {

    /**
     * This method calculates the softmax output of a non-normalized array.
     * Example: double[] nonNormArr = {-3.0, 0.2, 7.8}
     * gives the result {0.00002, 0.00050, 0.99948}
     * Details of how to compute the softmax output can be found at zyBook instructions.
     * Hint: use Math.exp(double a) to calculate the exponential values.
     *
     * @param nonNormArr The non-normalized array to be softmaxed.
     * @return The softmax output of the input array; return null if nonNormArr is null.
     */
    public static double[] softmax(double[] nonNormArr) {

        if (nonNormArr == null) {
            return null;
        }

        double[] softMaxArr = new double[nonNormArr.length];

        double denominator = 0.0;
        for (int i = 0; i < nonNormArr.length; i++) {
            denominator = denominator + Math.exp(nonNormArr[i]);
        }

        for (int j = 0; j < nonNormArr.length; j++) {
            softMaxArr[j] = Math.exp(nonNormArr[j]) / denominator;
        }

        return softMaxArr;
    }

    /**
     * This method gives the predicted label of a normalized array.
     * Algorithm:
     * 1. Find the maximum value in the normalized array.
     * 2. Return the index of the maximum value. If there's a tie, return the leftmost
     * maximum one.
     * Example: double[] normArr = {0.00002, 0.00050, 0.99948},
     * The predicted label is 2 because the largest probability is 0.99948.
     *
     * @param normArr The normalized input array.
     * @return The classification label inferred from the input array.
     * Return -1 if normArr is null or empty.
     */
    public static int predictLabel(double[] normArr) {

        if (normArr == null || normArr.length == 0) {
            return -1;
        }

        double max;
        int maxIndex;

        max = normArr[0];
        maxIndex = 0;
        for (int i = 1; i < normArr.length; i++) {
            if (max < normArr[i]) {
                max = normArr[i];
                maxIndex = i;
            }
        }

        return maxIndex;
    }

    /**
     * This method computes the prediction accuracy of a sequence of non-normalized arrays.
     * Algorithm:
     * 1. For each non-normalized array in the input sequence, call the softmax method
     * to calculate the normalized array. Then call the predictLabel
     * method to find the predicted classification label. All the
     * prediction results in the sequence will form a prediction array.
     * 2. Perform an element-wise comparison between the prediction array and
     * the groundTruthArr. If an element in the prediction array is equal to
     * the corresponding one in the groundTruthArr, it is a correct prediction.
     * 3. The prediction accuracy is calculated as:
     * Number Of Correct Predictions / Number Of Total Predictions.
     * Example: double[][] nonNormArrSeq = {{-3.0, 0.2, 7.8},
     * {1.0, 0.0, 0.0},
     * {1.0, 2.0, 3.0}},
     * and int[] groundTruthArr = {2, 0, 0}.
     * <p>
     * First, the normalized array sequence should be:
     * {{0.00002, 0.00050, 0.99948},
     * {0.576117, 0.21194, 0.21194},
     * {0.090031, 0.24473, 0.66524}}
     * the prediction array should be {2, 0, 2}.
     * The correctness of the 3 predictions are {correct, correct, wrong}.
     * Therefore, the prediction accuracy is 2 / 3 = 0.67.
     *
     * @param nonNormArrSeq  The sequence of non-normalized arrays.
     * @param groundTruthArr The array of ground truth labels.
     * @return The accuracy of all the predictions in the input sequence
     * compared to the ground truth labels.
     * Return -1 if either input is null, either input is empty,
     * or the length of the two input arrays doesn't match.
     */
    public static double calcPredictionAccuracy(double[][] nonNormArrSeq, int[] groundTruthArr) {

        if ((groundTruthArr == null) || (nonNormArrSeq == null)) {
            return -1;
        } else if ((groundTruthArr.length == 0) || (nonNormArrSeq.length == 0)) {
            return -1;
        } else if ((groundTruthArr.length != nonNormArrSeq.length)) {
            return -1;
        } else {
            int[] predictionArr = new int[nonNormArrSeq.length];

            for (int i = 0; i < nonNormArrSeq.length; i++) {
                int prediction = predictLabel(softmax(nonNormArrSeq[i]));
                predictionArr[i] = prediction;
            }

            double score = 0;
            for (int j = 0; j < groundTruthArr.length; j++) {
                if (groundTruthArr[j] == predictionArr[j]) {
                    score += 1.0;
                }
            }
            double accuracy = score / groundTruthArr.length;

            return accuracy;
        }
    }

    /**
     * This method returns the accuracy of the input sequence that has the highest accuracy in
     * an array of input sequences.
     * Example: inputs:
     * double[][][] nonNormArrSeqs = {{{-3.0, 0.2, 7.8},{1.0, 0.0, 0.0},{1.0, 2.0, 3.0}},
     * {{-3.0, 0.2, 7.8},{1.0, 0.0, 0.0},{1.0, 2.0, 3.0}}}
     * int[] groundTruthArrs = {{2, 0, 0},{2, 2, 0}}
     * Calculate the accuracy of each input sequence in nonNormArrSeqs. Get the highest accuracy.
     * The prediction accuracy of nonNormArrSeqs[0] is 2 / 3 = 0.67.
     * The prediction accuracy of nonNormArrSeqs[1] is 1 / 3 = 0.33.
     * Therefore, nonNormArrSeq[0] has higher accuracy, return 0.67.
     *
     * @param nonNormArrSeqs  An array of the input sequences.
     * @param groundTruthArrs The array of ground truth labels for the input sequences,
     *                        corresponding to the order of the input sequences in nonNormArrSeqs.
	 *                        For example, the input sequence in nonNormArrSeqs[i] has its array
	 *                        of ground truth labels in
     *                        groundTruthArrs[i].
     * @return The accuracy of the input sequence that has the highest accuracy. If
     * nonNormArrSeqs is empty, return 0;
     */
    public static double compareAccuracy(double[][][] nonNormArrSeqs, int[][] groundTruthArrs) {

        if (nonNormArrSeqs.length == 0) {
            return 0;
        }

        double[] predAccuracies = new double[nonNormArrSeqs.length];

        for (int i = 0; i < nonNormArrSeqs.length; i++) {
            predAccuracies[i] = calcPredictionAccuracy(nonNormArrSeqs[i], groundTruthArrs[i]);
        }

        double maxAcc = 0.0;
        for (int j = 0; j < predAccuracies.length; j++) {
            if (maxAcc < predAccuracies[j]) {
                maxAcc = predAccuracies[j];
            }
        }

        return maxAcc;
    }
}