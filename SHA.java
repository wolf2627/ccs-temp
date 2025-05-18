import java.security.MessageDigest;
import java.util.Scanner;

public class SHA {

    // Method to convert byte array to hexadecimal string
    public static String bytesToHex(byte[] bytes) {
        StringBuilder hex = new StringBuilder();
        for (byte b : bytes) {
            hex.append(String.format("%02x", b));
        }
        return hex.toString();
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            // Get user input
            System.out.print("Enter message to hash using SHA-1: ");
            String message = scanner.nextLine();

            // Create SHA-1 MessageDigest instance
            MessageDigest sha1Digest = MessageDigest.getInstance("SHA-1");

            // Compute the hash
            byte[] hashBytes = sha1Digest.digest(message.getBytes("UTF-8"));

            // Convert to hexadecimal string
            String hashHex = bytesToHex(hashBytes);

            // Print the result
            System.out.println("SHA-1 Hash: " + hashHex);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
