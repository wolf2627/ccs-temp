/*
 * It's a polyalphabetic cipher.
 * Each letter in the plaintext is shifted by a value derived from the keyword.
 * More secure than Caesar Cipher because it uses multiple Caesar shifts.
 * 
 * How it works:
 *      Each letter of the message is shifted by the corresponding letter in the keyword.
 *      'A' means shift by 0, 'B' by 1, ..., 'Z' by 25.
 *      It wraps around the alphabet using modulo 26. 
 */

import java.util.Scanner;

public class VigenereCipher {

    // Helper function to repeat the keyword to match the message length
    public static String generateKey(String message, String keyword) {
        StringBuilder key = new StringBuilder();
        keyword = keyword.toUpperCase();
        int keywordIndex = 0;

        for (int i = 0; i < message.length(); i++) {
            char current = message.charAt(i);
            if (Character.isLetter(current)) {
                key.append(keyword.charAt(keywordIndex));
                keywordIndex = (keywordIndex + 1) % keyword.length();
            } else {
                key.append(current); // non-letters stay as is
            }
        }

        return key.toString();
    }

    // Encrypt the message
    public static String encrypt(String message, String keyword) {
        message = message.toUpperCase();
        keyword = generateKey(message, keyword);
        StringBuilder encrypted = new StringBuilder();

        for (int i = 0; i < message.length(); i++) {
            char m = message.charAt(i);
            char k = keyword.charAt(i);

            if (Character.isLetter(m)) {
                char e = (char) ((m + k - 2 * 'A') % 26 + 'A');
                encrypted.append(e);
            } else {
                encrypted.append(m);
            }
        }

        return encrypted.toString();
    }

    // Decrypt the message
    public static String decrypt(String encryptedMessage, String keyword) {
        encryptedMessage = encryptedMessage.toUpperCase();
        keyword = generateKey(encryptedMessage, keyword);
        StringBuilder decrypted = new StringBuilder();

        for (int i = 0; i < encryptedMessage.length(); i++) {
            char e = encryptedMessage.charAt(i);
            char k = keyword.charAt(i);

            if (Character.isLetter(e)) {
                char d = (char) ((e - k + 26) % 26 + 'A');
                decrypted.append(d);
            } else {
                decrypted.append(e);
            }
        }

        return decrypted.toString();
    }

    // Main method
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter message:");
        String message = scanner.nextLine();

        System.out.println("Enter keyword:");
        String keyword = scanner.nextLine();

        String encrypted = encrypt(message, keyword);
        String decrypted = decrypt(encrypted, keyword);

        System.out.println("\nEncrypted Message: " + encrypted);
        System.out.println("Decrypted Message: " + decrypted);

        scanner.close();
    }
}
