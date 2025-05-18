import java.util.Scanner;

public class RailFenceSimple {

    // Encrypt message
    public static String encrypt(String text, int key) {
        StringBuilder[] rails = new StringBuilder[key];
        for (int i = 0; i < key; i++) rails[i] = new StringBuilder();

        int dir = 1, row = 0;
        for (char c : text.replaceAll("\\s+", "").toCharArray()) {
            rails[row].append(c);
            row += dir;
            if (row == 0 || row == key - 1) dir *= -1;
        }

        StringBuilder result = new StringBuilder();
        for (StringBuilder rail : rails) result.append(rail);
        return result.toString();
    }

    // Decrypt message
    public static String decrypt(String cipher, int key) {
        int len = cipher.length();
        boolean[][] mark = new boolean[key][len];
        int dir = 1, row = 0;

        // Mark the pattern
        for (int i = 0; i < len; i++) {
            mark[row][i] = true;
            row += dir;
            if (row == 0 || row == key - 1) dir *= -1;
        }

        // Fill the pattern with characters
        char[][] rails = new char[key][len];
        int index = 0;
        for (int i = 0; i < key; i++)
            for (int j = 0; j < len; j++)
                if (mark[i][j]) rails[i][j] = cipher.charAt(index++);

        // Read in zigzag
        StringBuilder result = new StringBuilder();
        row = 0; dir = 1;
        for (int i = 0; i < len; i++) {
            result.append(rails[row][i]);
            row += dir;
            if (row == 0 || row == key - 1) dir *= -1;
        }

        return result.toString();
    }

    // Main method
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Message: ");
        String msg = sc.nextLine();
        System.out.print("Key (rails): ");
        int key = sc.nextInt();

        String encrypted = encrypt(msg, key);
        String decrypted = decrypt(encrypted, key);

        System.out.println("Encrypted: " + encrypted);
        System.out.println("Decrypted: " + decrypted);

        sc.close();
    }
}
