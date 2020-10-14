import java.io.File; // Import the File class
import java.io.FileNotFoundException; // Import this class to handle errors
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner; // Import the Scanner class to read text files

public class Main {
	private static final String COMMA_DELIMITER = ", ";

	public static void main(String[] args) {
		File data = new File("data.txt"); // file must have each agents valuations on different lines and separated by commas
		
		List<List<Integer>> agentValues = new ArrayList<>();
		try {
			Scanner dataReader = new Scanner(data);
			while (dataReader.hasNextLine()) {
				String agentData = dataReader.nextLine(); // reads data from file
				String[] stringValues = agentData.split(COMMA_DELIMITER);
				ArrayList<Integer> iValues = convert2Int(stringValues); // converts data from file to array list
				agentValues.add(iValues);
			}
		} catch (FileNotFoundException fnfe) {
			System.out.print("Error: " + fnfe.getMessage());
		}

		MMSAlgo algo = new MMSAlgo(); // creates algo object
		algo.allocate(agentValues);
	}

	public static ArrayList<Integer> convert2Int(String[] sArray) { // converts string Array to Integer ArrayList
		ArrayList<Integer> iList = new ArrayList<>(sArray.length);
		for (String s : sArray) {
			iList.add(Integer.parseInt(s));
		}
		return iList;
	}
}
