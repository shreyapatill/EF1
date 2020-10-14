import java.util.ArrayList;
import java.util.List;

public class MMSAlgo {
	public void allocate(final List<List<Integer>> agentValues) {
		int agents = agentValues.size();
		int items = agentValues.get(0).size();

		List<Float> aveVals = new ArrayList<>();
		aveVals = getAverages(agentValues, agents);

		List<List<Integer>> allocations = new ArrayList<>();

		for (int agentI = 0; agentI < agents; agentI++) { // creates list objects to hold each agent's allocations
			allocations.add(new ArrayList<>());
		}

		while (items > 0) { // check that # of items doesn't reach 0
			int agentNum = 0;
			for (int agentIndex = 0; agentIndex < agents; agentIndex++) {
				if (items == 0) {
					break;
				}

				for (int itemIndex = 0; itemIndex < items; itemIndex++) {
					int tempVal = agentValues.get(agentIndex).get(itemIndex);

					if (tempVal >= aveVals.get(agentIndex) / 2) { // checks if agent valuation is greater than average/2
						allocations.get(agentNum).add(tempVal);

						for (int x = 0; x < agents; x++) {
							agentValues.get(x).remove(itemIndex); // removes item from other agent's lists
						}

						agentValues.remove(agentIndex); // removes agent from list
						aveVals.remove(agentIndex);
						agents--;

						agentIndex--;
						aveVals = getAverages(agentValues, agents);
						break;
					}
				}
				agentNum++;
				--items;
			}
			break;
		}
		printAllocations( allocations );		
	}

	public List<Float> getAverages(List<List<Integer>> agentValues, int agents) {
		// finds sum of all values
		List<Float> aveVal = new ArrayList<>();

		for (List<Integer> agentI : agentValues) {
			float sum = 0;
			for (int v : agentI) {
				sum += v;
			}
			aveVal.add(sum / agents);
		}

		return aveVal;
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

}
