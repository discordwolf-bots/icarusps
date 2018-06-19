package test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map.Entry;

import ethos.model.items.ItemDefinition;
import ethos.model.players.skills.herblore.PoisonedWeapon;
import ethos.model.players.skills.herblore.PoisonedWeapon.PoisonLevel;

import java.util.Optional;

public class CopyOfTest {

	public static void main(String[] args) throws IOException {

		ItemDefinition.load();
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

		int[][] dataMatrix = { { 863, 219 }, { 864, 220 }, { 865, 221 }, { 866, 222 }, { 867, 223 }, { 868, 224 }, { 869, 225 },

				// DARTS
				{ 806, 232 }, { 807, 233 }, { 808, 234 }, { 809, 235 }, { 810, 236 }, { 811, 237 },

				// JAVELINS
				{ 825, 206 }, { 826, 207 }, { 827, 208 }, { 828, 209 }, { 829, 210 }, { 830, 211 },

				// AXES
				{ 800, 36 }, { 801, 35 }, { 802, 37 }, { 803, 38 }, { 804, 39 }, { 805, 40 },

				// ARROWS
				{ 882, 19 }, { 884, 18 }, { 886, 20 }, { 888, 21 }, { 890, 22 }, { 892, 24 }, { 11212, 1120 }, };

		for (int[] entry : dataMatrix) {
			// map.put(entry[0], entry[1]);
			for (PoisonLevel poison : PoisonLevel.values()) {
				Optional<Integer> weapon = PoisonedWeapon.getResult(poison.getPoisonId(), entry[0]);
				if (weapon.isPresent()) {
					map.put(weapon.get(), entry[1]);
				}
			}
		}

		ArrayList<int[]> list = new ArrayList<int[]>(Arrays.asList(dataMatrix));

		for (Entry<Integer, Integer> entry : map.entrySet()) {
			int[] element = { entry.getKey(), entry.getValue() };
			list.add(element);
		}

		Collections.sort(list, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				return Integer.compare(o1[0], o2[0]);
			}
		});
		for (int dataArray[] : list) {
			System.out.print("{" + dataArray[0] + ", " + dataArray[1] + "}, ");
		}
	}

}
