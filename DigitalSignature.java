import java.security.*;

public class DigitalSignature {

    public static void main(String[] args) {
        try {
            // 1. Generate RSA key pair (private + public key)
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(2048);  // 2048-bit key for security
            KeyPair keyPair = keyGen.generateKeyPair();
            PrivateKey privateKey = keyPair.getPrivate();
            PublicKey publicKey = keyPair.getPublic();

            // 2. Message to sign
            String message = "This is a secret message.";

            // 3. Create digital signature using private key
            Signature signer = Signature.getInstance("SHA256withRSA");
            signer.initSign(privateKey);
            signer.update(message.getBytes("UTF-8"));
            byte[] digitalSignature = signer.sign();

            System.out.println("Message: " + message);
            System.out.println("Digital Signature (Base64): " + java.util.Base64.getEncoder().encodeToString(digitalSignature));

            // 4. Verify the signature using public key
            Signature verifier = Signature.getInstance("SHA256withRSA");
            verifier.initVerify(publicKey);
            verifier.update(message.getBytes("UTF-8"));
            boolean isVerified = verifier.verify(digitalSignature);

            System.out.println("Signature Verified: " + isVerified);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
