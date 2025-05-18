/**
 * How It Works:
 * Encrypt: Shifts each letter forward by shift positions.
 * Decrypt: Shifts each letter backward by shift positions (using 26 - shift).
 * Non-letters (spaces, punctuation) are not changed.
 */

import java.util.Scanner;

public class CaesarCipher {

    // Encrypt method
    public static String encrypt(String text, int shift) {
        StringBuilder result = new StringBuilder();

        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isLowerCase(c) ? 'a' : 'A';
                char shifted = (char) ((c - base + shift) % 26 + base);
                result.append(shifted);
            } else {
                result.append(c); // Non-letter characters stay the same
            }
        }

        return result.toString();
    }

    // Decrypt method
    public static String decrypt(String text, int shift) {
        return encrypt(text, 26 - (shift % 26)); // Reverse the shift
    }

    // Main method to run the program
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the message:");
        String message = scanner.nextLine();

        System.out.println("Enter the shift value (0-25):");
        int shift = scanner.nextInt();

        String encrypted = encrypt(message, shift);
        String decrypted = decrypt(encrypted, shift);

        System.out.println("\nEncrypted message: " + encrypted);
        System.out.println("Decrypted message: " + decrypted);

        scanner.close();
    }
}

