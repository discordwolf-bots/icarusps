package test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class CheckForMatches {

	public static void main(String[] args) throws Exception {
		List<String> ips = Arrays.asList("188.221.95.86", "204.93.58.101", "204.93.58.107", "209.73.137.103", "209.73.137.172", "50.118.172.23", "66.171.228.54", "90.198.52.117",
				"90.198.52.33", "90.200.21.142", "90.200.98.243", "90.206.66.12");

		Path path = Paths.get("ethos.online");

		try (BufferedReader reader = new BufferedReader(new FileReader(path.toFile()))) {
			String line = null;

			while ((line = reader.readLine()) != null) {
				line = line.toLowerCase().trim();
				// if (!line.contains("logs/")) {
				// continue;
				// }

				for (String ip : ips) {
					if (line.contains(ip)) {
						System.out.println("IP: " + ip + " Data: " + line);
					}
				}
			}

		}

		System.out.println("Finished.");
	}

}
