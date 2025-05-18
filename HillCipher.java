import java.util.Scanner;

public class HillCipher {

    static int[][] keyMatrix = new int[2][2];

    // Convert character to integer (A=0, B=1, ..., Z=25)
    public static int charToInt(char c) {
        return c - 'A';
    }

    // Convert integer back to character
    public static char intToChar(int i) {
        return (char) (i + 'A');
    }

    // Encrypt a pair of characters using key matrix
    public static String encrypt(String plaintext, int[][] key) {
        plaintext = plaintext.toUpperCase().replaceAll("[^A-Z]", "");
        if (plaintext.length() % 2 != 0) {
            plaintext += "X"; // padding if not even
        }

        StringBuilder cipherText = new StringBuilder();

        for (int i = 0; i < plaintext.length(); i += 2) {
            int[] pair = new int[2];
            pair[0] = charToInt(plaintext.charAt(i));
            pair[1] = charToInt(plaintext.charAt(i + 1));

            int[] result = new int[2];
            result[0] = (key[0][0] * pair[0] + key[0][1] * pair[1]) % 26;
            result[1] = (key[1][0] * pair[0] + key[1][1] * pair[1]) % 26;

            cipherText.append(intToChar(result[0]));
            cipherText.append(intToChar(result[1]));
        }

        return cipherText.toString();
    }

    // Read 2x2 key matrix from user input
    public static void readKeyMatrix(Scanner scanner) {
        System.out.println("Enter 2x2 key matrix (4 numbers, row-wise):");
        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 2; j++)
                keyMatrix[i][j] = scanner.nextInt();
    }

    // Main method
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        readKeyMatrix(scanner);

        scanner.nextLine(); // consume newline
        System.out.println("Enter plaintext message:");
        String plaintext = scanner.nextLine();

        String encrypted = encrypt(plaintext, keyMatrix);
        System.out.println("Encrypted Text: " + encrypted);
    }
}
