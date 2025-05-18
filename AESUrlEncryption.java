/**
 * Explanation
 * AES (Advanced Encryption Standard) is more secure and modern than DES.
 * URL Encoding is used to make the encrypted string safe for URLs.
 * We use Base64 for readable encrypted output and compatibility with URLEncoder.
 */


import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
// import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Scanner;

public class AESUrlEncryption {

    // Encrypt method
    public static String encrypt(String plainText, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encrypted = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encrypted);  // Encode to Base64
    }

    // Decrypt method
    public static String decrypt(String cipherText, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decoded = Base64.getDecoder().decode(cipherText);
        byte[] decrypted = cipher.doFinal(decoded);
        return new String(decrypted, StandardCharsets.UTF_8);
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {

            // Get user input (a URL or text)
            System.out.print("Enter URL to encrypt: ");
            String originalUrl = scanner.nextLine();

            // Generate AES key
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(128); // AES supports 128, 192, 256 bits
            SecretKey secretKey = keyGen.generateKey();

            // Encrypt the URL
            String encrypted = encrypt(originalUrl, secretKey);

            // Encode encrypted string for safe use in URLs
            String urlEncoded = URLEncoder.encode(encrypted, StandardCharsets.UTF_8.toString());
            System.out.println("Encrypted & URL-safe: " + urlEncoded);

            // Decrypt back
            String urlDecoded = URLDecoder.decode(urlEncoded, StandardCharsets.UTF_8.toString());
            String decrypted = decrypt(urlDecoded, secretKey);
            System.out.println("Decrypted URL: " + decrypted);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
