import java.io.*; // Import the File class
import java.util.Scanner;

public class Datasets {

	public Datasets() {
		Scanner input = new Scanner(System.in);
		System.out.print("Enter max number of agents: ");
		int agents = input.nextInt();
		System.out.print("Enter max number of items: ");
		int items = input.nextInt();
		System.out.print("Enter number of instances: ");
		int instances = input.nextInt();
		System.out.print("Enter min agent value: ");
		int min_agentVal = input.nextInt();
		System.out.print("Enter max agent value: ");
		int max_agentVal = input.nextInt();
		createDataSet( agents, items, instances, min_agentVal, max_agentVal);
	}

	public void createDataSet(int agents, int items, int instances, int min_agentVal, int max_agentVal) {

		for (int inst_index = 0; inst_index < instances; inst_index++) {

			// chooses random number of agents and items
			int rand_agents = (int) (Math.random() * (agents + 1) + 1); //[1, agents]
			int rand_items = (int) (Math.random() * (items + 1) + 1); //[1, items]

			try {
				// creates new file for dataset
				File myObj = new File("dataset" + inst_index + ".txt");
				
				if (myObj.createNewFile()) {
					System.out.println("File created: " + myObj.getName());
				} else {
					System.out.println("File already exists.");
				}
			} catch (IOException e) {
				System.out.println("An error occurred.");
				e.printStackTrace();
			}
			
			try {
				//writes into dataset file
				BufferedWriter out = new BufferedWriter(new FileWriter("dataset" + inst_index + ".txt"));
				for (int agent_index = 0; agent_index < rand_agents; agent_index++) {
					for (int item_index = 0; item_index < rand_items; item_index++) {
						int randVal = (int)(Math.random() * (max_agentVal - min_agentVal + 1) + min_agentVal); // chooses random agent values
						if( item_index < rand_items - 1 )
							out.write( randVal + ", " );
						else
							out.write( randVal + "" );
					}
					out.write("\r\n");
				}
				out.close();
				System.out.println("Successfully wrote to the file.");
				
			} catch (IOException e) {
				System.out.println("An error occurred.");
				e.printStackTrace();
			}

		}

	}
}
