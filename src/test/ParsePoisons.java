package test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map.Entry;

public class ParsePoisons {

	private static HashMap<String, int[]> map = new HashMap<String, int[]>();
	private static ArrayList<String> results = new ArrayList<String>();

	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new FileReader("./Data/test/poisons.txt"));
			String line;
			while ((line = br.readLine()) != null) {
				line = line.toLowerCase();
				String[] parts = line.split(" ", 2);
				Item item = new Item(Integer.parseInt(parts[0]), parts[1]);
				add(item);
			}
			br.close();
			for (Entry<String, int[]> entry : map.entrySet()) {
				// System.out.println(entry.getKey());
				for (int i = 1; i < entry.getValue().length; i++) {
					StringBuilder sb = new StringBuilder();
					sb.append(entry.getKey().toUpperCase().replaceAll(" ", "_") + "_P");
					switch (i) {

					case 1:
						sb.append("(PoisonLevel.NORMAL, ");
						break;
					case 2:
						sb.append("_PLUS(PoisonLevel.PLUS, ");
						break;
					case 3:
						sb.append("_PLUS_PLUS(PoisonLevel.PLUSPLUS, ");
						break;
					}
					sb.append(entry.getValue()[0] + ", " + entry.getValue()[i] + "),");
					results.add(sb.toString());
				}
			}
			Collections.sort(results, new Comparator<String>() {

				@Override
				public int compare(String o1, String o2) {
					return o1.compareTo(o2);
				}
			});
			for (String value : results) {
				System.out.println(value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void add(Item item) throws IllegalArgumentException {
		String name = item.getName();
		int id = item.getId();
		if (name.contains("(") && name.contains(")")) {
			String key = name.substring(0, name.indexOf("("));
			if (!map.containsKey(key)) {
				throw new IllegalArgumentException("Did not contain key");
			} else {
				int[] poisons = map.get(key);
				if (name.contains("(p++)")) {
					poisons[3] = id;
				} else if (name.contains("(p+)")) {
					poisons[2] = id;
				} else if (name.contains("(p)")) {
					poisons[1] = id;
				}
				map.remove(key);
				map.put(key, poisons);
			}
		} else {
			int[] array = new int[4];
			array[0] = item.getId();
			map.put(name, array);
		}
	}

}
