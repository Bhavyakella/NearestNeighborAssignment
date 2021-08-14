/*Bhavya kella 
 Programming Fundamentals
PROGRAMMING ASSIGNMENT 3*/
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class NearestNeighbor {

	public static void main(String[] args) throws FileNotFoundException {
		heading();

		// initilize the variables
		String trainClassLabel[] = new String[75];
		String testClassLabel[] = new String[75];
		String predictedClassLabel[] = new String[75];
		double[][] test_attr_Val = new double[75][4];
		double[][] train_attr_Val = new double[75][4];
		
		// User inputs for file names
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the name of the training file: ");
		// store iris-training-data into training variable
		String training = sc.nextLine(); 
		System.out.print("Enter the name of the testing file: ");
		// store iris-testing-data into training variable
		String testing = sc.nextLine(); 

		// let's make testing values
		// store testing data into testingdata
		File testingdata = new File(testing); 
		// create a scanner to read the testing data
		Scanner fileScanTestingdata = new Scanner(testingdata); 
		for (int i = 0; fileScanTestingdata.hasNextLine(); i++) {
			String str = fileScanTestingdata.nextLine();
			String[] numbers = str.split(",", 5);
			testClassLabel[i] = numbers[4];
			for (int j = 0; j < 4; j++) {
				test_attr_Val[i][j] = Double.parseDouble(numbers[j]);
			}
		}

		// let's make traning values
		File trainingdata = new File(training);
		// store traninig data into trainingdata
		Scanner fileScanTrainingdata = new Scanner(trainingdata);
		// create a scanner to read the training data
		for (int i = 0; fileScanTrainingdata.hasNextLine(); i++) {
			String str = fileScanTrainingdata.nextLine();
			String[] num = str.split(",", 5);
			trainClassLabel[i] = num[4];
			for (int j = 0; j < 4; j++) {
				train_attr_Val[i][j] = Double.parseDouble(num[j]);
			}
		}

		// print accuracy
		accuracy(testClassLabel, prediction(test_attr_Val, train_attr_Val, trainClassLabel, predictedClassLabel));

		// close scanners
		fileScanTrainingdata.close();
		fileScanTestingdata.close();
		sc.close();
	}

	// Uses our distance formula to find the shortest distance between our value and
	// test values.

	public static int leastdistance(double sly, double swy, double ply, double pwy, double[][] trainVal) {

		int Num = 0;
		double slTotal = Math.pow((trainVal[0][0] - sly), 2);
		double swTotal = Math.pow((trainVal[0][1] - swy), 2);
		double plTotal = Math.pow((trainVal[0][2] - ply), 2);
		double pwTotal = Math.pow((trainVal[0][3] - pwy), 2);
		double distance = Math.sqrt(slTotal + swTotal + plTotal + pwTotal);
		for (int i = 0; i < 75; i++) {
			double slTest = Math.pow((trainVal[i][0] - sly), 2);
			double swTest = Math.pow((trainVal[i][1] - swy), 2);
			double plTest = Math.pow((trainVal[i][2] - ply), 2);
			double pwTest = Math.pow((trainVal[i][3] - pwy), 2);
			double testDistance = Math.sqrt(slTest + swTest + plTest + pwTest);
			if (testDistance < distance) {
				Num = i;
				distance = testDistance;

			}
		}

		return Num;
	}

	private static String[] prediction(double testingVal[][], double trainingVal[][], String[] trainingClassLabel,
			String[] predictedClassLabel) {
		for (int i = 0; i < 75; i++) {
			int closestPrediction = leastdistance(testingVal[i][0], testingVal[i][1], testingVal[i][2],
					testingVal[i][3], trainingVal);
			predictedClassLabel[i] = trainingClassLabel[closestPrediction];
		}
		return predictedClassLabel;
	}

	public static void accuracy(String[] trueLabel, String[] predictedLabel) {
		int count = 0;
		double correctVal = 0;
		double accurate = 0;
		System.out.println("\nEX#: TRUE LABEL, PREDICTED LABEL");
		for (int i = 0; i < 75; i++) {
			count++;
			System.out.println(count + ": " + trueLabel[i] + " " + predictedLabel[i]);
			if (trueLabel[i].equals(predictedLabel[i])) {
				correctVal++;
				accurate = (correctVal / 75);
			}
		}
		System.out.print("ACCURACY: " + accurate);
	}

	public static String heading() {
		String output = ("");
		System.out.println(output);
		return output;
	}
}
