package test;

import java.io.File;
import java.net.InetAddress;
import java.security.MessageDigest;

public class UniqueIdentifier {

	public static void main(String[] args) {
		try {
			StringBuilder sb = new StringBuilder();
			System.out.println("User name: " + System.getProperty("user.name"));
			sb.append(System.getProperty("user.name"));
			System.out.println("Host name: " + InetAddress.getLocalHost().getHostName());
			sb.append(InetAddress.getLocalHost().getHostName());
			System.out.println("Operating System: " + System.getProperty("os.name"));
			sb.append(System.getProperty("os.name"));
			System.out.println("Disk size: " + new File("/").getTotalSpace());
			sb.append(new File("/").getTotalSpace());

			System.out.println();
			System.out.println("Result: " + sb.toString());
			System.out.println("Hashed result: " + sb.toString().hashCode());
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			messageDigest.update(sb.toString().getBytes());
			String encryptedString = new String(messageDigest.digest());
			System.out.println("Encrypted result: " + encryptedString);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}