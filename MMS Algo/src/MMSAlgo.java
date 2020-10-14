import java.util.ArrayList;
import java.util.List;

public class MMSAlgo {
	public void allocate(final List<List<Integer>> agentValues) {
		int agents = agentValues.size();
		int items = agentValues.get(0).size();

		List<Float> aveVals = new ArrayList<>();
		aveVals = getAverages(agentValues, agents);

		List<List<Integer>> allocations = new ArrayList<>();
		List<List<Integer>> unAlItems = new ArrayList<>(); // holds unallocated items

		for (int agentI = 0; agentI < agents; agentI++) {
			allocations.add(new ArrayList<>()); // creates list objects to hold each agent's allocations
			unAlItems.add(new ArrayList<>()); // copies agentValues into unAlItems
			unAlItems.get(agentI).addAll(agentValues.get(agentI));
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

						for (int x = 0; x < unAlItems.size(); x++) {
							unAlItems.get(x).remove(itemIndex);
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

		// EF1 algo for the rest of unallocated items
		while (items > 0) { // check that # of items doesn't reach 0
			for (int i = 0; i < unAlItems.size(); i++) {
				if (items == 0) {
					break;
				}
				int bestIndex = findBestItem(unAlItems.get(i));
				int bestVal = unAlItems.get(i).get(bestIndex);
				allocations.get(i).add(bestVal); // adds best item to allocation list

				for (int x = 0; x < agents; x++) {
					unAlItems.get(x).remove(bestIndex); // removes item from other agent's lists
				}
				--items;
			}

		}

		printAllocations(allocations);
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

	public Integer findBestItem(List<Integer> agentI) { // iterates through agent's list of values to find the best item
		int maxIndex = 0;

		for (int i = 1; i < agentI.size(); i++) {
			if (agentI.get(maxIndex) < agentI.get(i)) {
				maxIndex = i;
			}
		}
		return maxIndex;
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
