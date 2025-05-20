/*
 * How it Works
 * KeyPairGenerator creates a pair of RSA keys (public and private).
 * Cipher is used for both encryption and decryption using the RSA algorithm.
 * Base64 is used to safely display encrypted binary data as a string.
 */

 import javax.crypto.Cipher;
 import java.security.*;
 import java.util.Base64;
 import java.util.Scanner;
 
 public class SimpleRSA {
 
     // Encrypt method
     public static String encrypt(String message, PublicKey publicKey) throws Exception {
         Cipher cipher = Cipher.getInstance("RSA");
         cipher.init(Cipher.ENCRYPT_MODE, publicKey);
         byte[] encryptedBytes = cipher.doFinal(message.getBytes());
         return Base64.getEncoder().encodeToString(encryptedBytes); // Convert to string for easy display
     }
 
     // Decrypt method
     public static String decrypt(String encryptedMessage, PrivateKey privateKey) throws Exception {
         Cipher cipher = Cipher.getInstance("RSA");
         cipher.init(Cipher.DECRYPT_MODE, privateKey);
         byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedMessage));
         return new String(decryptedBytes);
     }
 
     public static void main(String[] args) {
         try (Scanner scanner = new Scanner(System.in)) {
             // 1. Get user message
             System.out.print("Enter a message to encrypt: ");
             String message = scanner.nextLine();
 
             // 2. Generate RSA key pair
             KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
             keyGen.initialize(2048);  // RSA key size (2048 bits is typical)
             KeyPair keyPair = keyGen.generateKeyPair();
 
             PublicKey publicKey = keyPair.getPublic();
             PrivateKey privateKey = keyPair.getPrivate();
 
             // 3. Encrypt the message
             String encrypted = encrypt(message, publicKey);
             System.out.println("Encrypted message: " + encrypted);
 
             // 4. Decrypt the message
             String decrypted = decrypt(encrypted, privateKey);
             System.out.println("Decrypted message: " + decrypted);
 
         } catch (Exception e) {
             e.printStackTrace();
         }
     }
 }
 