/*
 * How It Works
 * User A generates a Diffie-Hellman key pair.
 * User B generates their key pair using A’s parameters.
 * Both parties compute the shared secret using each other's public keys and their own private keys.
 * The shared secret should be the same on both sides – proving the exchange succeeded.
 */

// import javax.crypto.KeyAgreement;
// import javax.crypto.interfaces.DHPublicKey;
// import javax.crypto.spec.DHParameterSpec;
// import java.security.*;

// public class DiffieHellman {

//     public static void main(String[] args) {
//         try {
//             // 1. Generate key pair for User A
//             KeyPairGenerator keyPairGenA = KeyPairGenerator.getInstance("DH");
//             keyPairGenA.initialize(2048);  // Generate with 2048-bit key size
//             KeyPair keyPairA = keyPairGenA.generateKeyPair();

//             // 2. Generate key pair for User B using A's public key parameters
//             DHParameterSpec dhParamSpec = ((DHPublicKey) keyPairA.getPublic()).getParams();
//             KeyPairGenerator keyPairGenB = KeyPairGenerator.getInstance("DH");
//             keyPairGenB.initialize(dhParamSpec);
//             KeyPair keyPairB = keyPairGenB.generateKeyPair();

//             // 3. Each party generates the shared secret

//             // User A creates the shared secret
//             KeyAgreement keyAgreeA = KeyAgreement.getInstance("DH");
//             keyAgreeA.init(keyPairA.getPrivate());
//             keyAgreeA.doPhase(keyPairB.getPublic(), true);
//             byte[] sharedSecretA = keyAgreeA.generateSecret();

//             // User B creates the shared secret
//             KeyAgreement keyAgreeB = KeyAgreement.getInstance("DH");
//             keyAgreeB.init(keyPairB.getPrivate());
//             keyAgreeB.doPhase(keyPairA.getPublic(), true);
//             byte[] sharedSecretB = keyAgreeB.generateSecret();

//             // 4. Compare the shared secrets
//             System.out.println("Shared Secret A: " + bytesToHex(sharedSecretA));
//             System.out.println("Shared Secret B: " + bytesToHex(sharedSecretB));

//             if (MessageDigest.isEqual(sharedSecretA, sharedSecretB)) {
//                 System.out.println("✅ Shared secrets match. Secure key exchange successful.");
//             } else {
//                 System.out.println("❌ Shared secrets do NOT match.");
//             }

//         } catch (Exception e) {
//             e.printStackTrace();
//         }
//     }

//     // Helper method to convert bytes to hex
//     private static String bytesToHex(byte[] bytes) {
//         StringBuilder hex = new StringBuilder();
//         for (byte b : bytes) {
//             hex.append(String.format("%02X", b));
//         }
//         return hex.toString();
//     }
// }

class DiffieHellman {
    public static void main(String args[]) {
        int p = 23;/* publicly known (prime number) */
        int g = 5; /* publicly known (primitive root) */
        int x = 4; /* only Alice knows this secret */
        int y = 3; /* only Bob knows this secret */
        double aliceSends = (Math.pow(g, x)) % p;
        double bobComputes = (Math.pow(aliceSends, y)) % p;
        double bobSends = (Math.pow(g, y)) % p;
        double aliceComputes = (Math.pow(bobSends, x)) % p;
        double sharedSecret = (Math.pow(g, (x * y))) % p;
        System.out.println("Simulation of Diffie-Hellman key exchange algorithm\n");
        System.out.println("Alice Sends : " + aliceSends);
        System.out.println("Bob Computes : " + bobComputes);
        System.out.println("Bob Sends : " + bobSends);
        System.out.println("Alice Computes : " + aliceComputes);
        System.out.println("Shared Secret : " + sharedSecret);
        /* shared secrets should match and equality is transitive */
        if ((aliceComputes == sharedSecret) && (aliceComputes == bobComputes))
            System.out.println("Success: Shared Secrets Matches! " + sharedSecret);
        else
            System.out.println("Error: Shared Secrets does not Match");
    }
}