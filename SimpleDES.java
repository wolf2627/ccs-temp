/*
 * How it Works
 * KeyGenerator creates a random DES key.
 * Cipher is used for both encryption and decryption using the DES algorithm.
 * Base64 is used to safely display encrypted binary data as a string.
 */




import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
// import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Scanner;

public class SimpleDES {

    // Encrypt method
    public static String encrypt(String message, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedBytes = cipher.doFinal(message.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes); // Convert to string for easy display
    }

    // Decrypt method
    public static String decrypt(String encryptedMessage, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedMessage));
        return new String(decryptedBytes);
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            // 1. Get user message
            System.out.print("Enter a message to encrypt: ");
            String message = scanner.nextLine();

            // 2. Generate DES key
            KeyGenerator keyGen = KeyGenerator.getInstance("DES");
            SecretKey secretKey = keyGen.generateKey();

            // 3. Encrypt the message
            String encrypted = encrypt(message, secretKey);
            System.out.println("Encrypted message: " + encrypted);

            // 4. Decrypt the message
            String decrypted = decrypt(encrypted, secretKey);
            System.out.println("Decrypted message: " + decrypted);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
