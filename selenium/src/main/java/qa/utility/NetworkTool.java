package qa.utility;

import java.util.ArrayList;
import java.util.HashMap;

import qa.SeleniumTest;

public class NetworkTool {

	static ArrayList<HashMap<String, Long>> networkMaps = new ArrayList<HashMap<String, Long>>();
	private static HashMap<String, Long> netAvg = new HashMap<String, Long>();

	public static ArrayList<HashMap<String, Long>> getNetworkMaps() {
		return networkMaps;
	}

	public static void setNetworkMaps(ArrayList<HashMap<String, Long>> networkMaps) {
		NetworkTool.networkMaps = networkMaps;
	}

	public static HashMap<String, Long> getNetworkMap(int map) {
		return networkMaps.get(map);
	}

	public static void setNetworkMap(HashMap<String, Long> map) {
		NetworkTool.networkMaps.add(map);
	}

	public static void netAnalysis() {
		//Catch Index out of bounds if there are no network maps
		try {

			// for each key value in the keyset
			for (String key : networkMaps.get(0).keySet()) {
				String currentKey = key;
				Long avg = 0L;

				// start iterating through HashMaps, collecting all the values
				// under the same key
				for (int j = 0; j < networkMaps.size(); j++) {
					if (networkMaps.get(j).containsKey(currentKey)) {
						avg += networkMaps.get(j).get(currentKey);
					}
				}
				avg = (avg / networkMaps.size());
				netAvg.put(currentKey, avg);
			}

			SeleniumTest.logger.info("Network Analysis: " + netAvg.toString() + System.lineSeparator());
			// System.out.println(netAvg.toString());
		} catch (IndexOutOfBoundsException ex) {
			SeleniumTest.logger.info("No network results to analyze...");
		}
	}
}
