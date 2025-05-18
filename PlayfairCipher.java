/*
 * Create a 5x5 matrix using a keyword (excluding 'J')
 * Break plaintext into digraphs (pairs of letters)
 * Insert X between duplicate letters
 * Add X at the end if odd length
 * Apply rules:
 *   Same row: replace each letter with the letter to its right
 *   Same column: replace with the letter below
 *   Rectangle: swap column indices
 */

import java.util.*;

public class PlayfairCipher {

    static char[][] matrix = new char[5][5];
    static String key = "";

    // Function to prepare key matrix
    public static void generateMatrix(String keyInput) {
        keyInput = keyInput.toUpperCase().replaceAll("[^A-Z]", "").replace("J", "I");
        StringBuilder keyBuilder = new StringBuilder();

        Set<Character> used = new HashSet<>();

        for (char c : keyInput.toCharArray()) {
            if (!used.contains(c)) {
                keyBuilder.append(c);
                used.add(c);
            }
        }

        for (char c = 'A'; c <= 'Z'; c++) {
            if (c != 'J' && !used.contains(c)) {
                keyBuilder.append(c);
                used.add(c);
            }
        }

        key = keyBuilder.toString();
        for (int i = 0; i < 25; i++) {
            matrix[i / 5][i % 5] = key.charAt(i);
        }
    }

    // Function to find position of a letter in the matrix
    public static int[] findPosition(char c) {
        if (c == 'J') c = 'I';
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++)
                if (matrix[i][j] == c)
                    return new int[]{i, j};
        return null;
    }

    // Function to format plaintext
    public static String formatPlainText(String input) {
        input = input.toUpperCase().replaceAll("[^A-Z]", "").replace("J", "I");
        StringBuilder formatted = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            char first = input.charAt(i);
            if (i + 1 < input.length()) {
                char second = input.charAt(i + 1);
                if (first == second) {
                    formatted.append(first).append('X');
                } else {
                    formatted.append(first).append(second);
                    i++;
                }
            } else {
                formatted.append(first).append('X');
            }
        }

        return formatted.toString();
    }

    // Encrypt digraphs using Playfair rules
    public static String encrypt(String plaintext) {
        StringBuilder ciphertext = new StringBuilder();

        for (int i = 0; i < plaintext.length(); i += 2) {
            char a = plaintext.charAt(i);
            char b = plaintext.charAt(i + 1);

            int[] posA = findPosition(a);
            int[] posB = findPosition(b);

            if (posA[0] == posB[0]) { // Same row
                ciphertext.append(matrix[posA[0]][(posA[1] + 1) % 5]);
                ciphertext.append(matrix[posB[0]][(posB[1] + 1) % 5]);
            } else if (posA[1] == posB[1]) { // Same column
                ciphertext.append(matrix[(posA[0] + 1) % 5][posA[1]]);
                ciphertext.append(matrix[(posB[0] + 1) % 5][posB[1]]);
            } else { // Rectangle swap
                ciphertext.append(matrix[posA[0]][posB[1]]);
                ciphertext.append(matrix[posB[0]][posA[1]]);
            }
        }

        return ciphertext.toString();
    }

    // Main program
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter key:");
        String inputKey = scanner.nextLine();
        generateMatrix(inputKey);

        System.out.println("Enter message to encrypt:");
        String message = scanner.nextLine();

        String formattedMessage = formatPlainText(message);
        String encryptedMessage = encrypt(formattedMessage);

        System.out.println("\nFormatted Message: " + formattedMessage);
        System.out.println("Encrypted Message: " + encryptedMessage);

        scanner.close();
    }
}
