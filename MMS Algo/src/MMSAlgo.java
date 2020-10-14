import java.util.ArrayList;
import java.util.List;

public class MMSAlgo {
	public void allocate(final List<List<Integer>> agentValues) {
		int agents = agentValues.size();
		int items = agentValues.get(0).size();

		List<Float> aveVals = new ArrayList<>();
		aveVals = getAverages(agentValues, agents);
		
		List<List<Integer>> allocations = new ArrayList<>();
		
		for (int i = 0; i < agents; i++) { // creates list objects to hold each agent's allocations
			allocations.add(new ArrayList<>());
		}

		while (items > 0) { // check that # of items doesn't reach 0
			int agentNum = 0;
			for (int i = 0; i < agents; i++) {
				if (items == 0) {
					break;
				}
				
				for (int j = 0; j < items; j++) {
					int tempVal = agentValues.get(i).get(j);
					
					if( tempVal >= aveVals.get(i)/2 ) { //checks if agent valuation is greater than average/2
						allocations.get(agentNum).add(tempVal);
						
						for (int x = 0; x < agents; x++) {
							agentValues.get(x).remove(j); // removes item from other agent's lists
						}
						
						agentValues.remove(i); //removes agent from list
						agents--;
						i--;
						aveVals = getAverages(agentValues, agents);
						break;
					}
				}

				agentNum++;
				--items;
			}

		}
		
		printAllocations( allocations );

		
	}

	public void printAllocations(List<List<Integer>> allocations) {
		for (List<Integer> agentI : allocations) {
			for (int i = 0; i < agentI.size(); i++) {
				int val = agentI.get(i);
				if (i < agentI.size() - 1) {
					System.out.print(val + ", ");
				} else {
					System.out.print(val);
				}
			}
			System.out.println();
		}
	}

	public List<Float> getAverages(List<List<Integer>> agentValues, int agents) {
		//finds sum of all values
		List<Float> aveVal = new ArrayList<>();
		
		for (List<Integer> agentI : agentValues) {
			float sum = 0;
			for (int v : agentI) {
				sum += v;
			}
			aveVal.add(sum/agents);
		}
		
		return aveVal;
	}
}
