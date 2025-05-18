/*
 * Concept of Row and Column Transformation:
 * Steps:
 *
 * 1. Choose a key (number of columns).
 * 2. Write the plaintext into a grid row by row.
 * 3. Read the grid column by column to get the ciphertext.
 *
 * Example:
 * Plaintext: HELLO WORLD
 * Key (columns): 4
 *
 * Step 1 – Fill the matrix row-wise:
 * H E L L
 * O   W O
 * R L D X  ← (X is padding to complete the matrix)
 *
 * Step 2 – Read column-wise to get ciphertext:
 * HOR ELW LOD X → Ciphertext: HOR ELWLOD X → HORELWLODX
 */

import java.util.Scanner;

public class RowColumnCipher {

    // Encrypt by writing row-wise and reading column-wise
    public static String encrypt(String text, int cols) {
        text = text.replaceAll("\\s+", "").toUpperCase();
        int rows = (int) Math.ceil((double) text.length() / cols);
        char[][] grid = new char[rows][cols];
        int idx = 0;

        // Fill matrix row-wise
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                grid[i][j] = (idx < text.length()) ? text.charAt(idx++) : 'X'; // padding

        // Read matrix column-wise
        StringBuilder result = new StringBuilder();
        for (int j = 0; j < cols; j++)
            for (int i = 0; i < rows; i++)
                result.append(grid[i][j]);

        return result.toString();
    }

    // Decrypt by reversing the transformation
    public static String decrypt(String cipher, int cols) {
        int rows = (int) Math.ceil((double) cipher.length() / cols);
        char[][] grid = new char[rows][cols];
        int idx = 0;

        // Fill matrix column-wise
        for (int j = 0; j < cols; j++)
            for (int i = 0; i < rows; i++)
                grid[i][j] = cipher.charAt(idx++);

        // Read matrix row-wise
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                result.append(grid[i][j]);

        return result.toString();
    }

    // Main method
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter message: ");
        String message = sc.nextLine();

        System.out.print("Enter number of columns (key): ");
        int cols = sc.nextInt();

        String encrypted = encrypt(message, cols);
        String decrypted = decrypt(encrypted, cols);

        System.out.println("\nEncrypted: " + encrypted);
        System.out.println("Decrypted: " + decrypted);

        sc.close();
    }
}
