package com.siukatech.pocsimple.jcaaes;

import javax.crypto.*;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.IvParameterSpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

/**
 * Reference:
 * https://mkyong.com/java/java-aes-encryption-and-decryption/
 *
 * Section: 2. AES encryption and decryption
 */
public class JcaAesTest01 {

    public static void main(String[] args) {
        JcaAesTest01 test = new JcaAesTest01();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
        String input01 = "This is a test with timestamp: [" + sdf.format(new Date()) + "]";
        String output01 = "";
        String cipher01 = null;

        try {
            // encrypt and decrypt need the same key.
            // get AES 256 bits (32 bytes) key
            SecretKey secretKey = CryptoUtils.generateKey(256);

            // encrypt and decrypt need the same IV.
            // AES-GCM needs IV 96-bit (12 bytes)
            byte[] iv = CryptoUtils.generateRandomNonce(12);

            // must be one of {128, 120, 112, 104, 96}
            GCMParameterSpec gcmParameterSpec = CryptoUtils.generateGcm(128, iv);

            Thread.sleep(1000);
            cipher01 = test.encryptByAESGCM(secretKey, gcmParameterSpec, input01);
            System.out.println("main - now: [" + sdf.format(new Date()) + "], input01: [" + input01 + "], cipher01: [" + cipher01 + "]");

            Thread.sleep(1000);
            output01 = test.decryptByAESGCM(secretKey, gcmParameterSpec, cipher01);
            System.out.println("main - now: [" + sdf.format(new Date()) + "], output01: [" + output01 + "]");
        }
        catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

    public static Cipher getCipher(SecretKey secretKey, GCMParameterSpec gcmParameterSpec, int mode)
            throws NoSuchPaddingException, NoSuchAlgorithmException
            , InvalidAlgorithmParameterException, InvalidKeyException
            , IllegalBlockSizeException, BadPaddingException {
        //SecretKey secretKey = CryptoUtils.generateKey(256);
        //IvParameterSpec ivParameterSpec = CryptoUtils.generateIv();
        Cipher cipher = CryptoUtils.getCipher();
        ////cipher.init(mode, secretKey, ivParameterSpec);
        //cipher.init(mode, secretKey);
        cipher.init(mode, secretKey, gcmParameterSpec);
        return cipher;
    }

    public String encryptByAESGCM(SecretKey secretKey, GCMParameterSpec gcmParameterSpec, String input) throws Exception {
        Cipher cipher = getCipher(secretKey, gcmParameterSpec, Cipher.ENCRYPT_MODE);
        byte[] cipherText = cipher.doFinal(input.getBytes());
        System.out.println("encryptByAESGCM - cipherText.length: [" + cipherText.length + "]");
        String output = Base64.getEncoder().encodeToString(cipherText);
        return output;
    }

    public String decryptByAESGCM(SecretKey secretKey, GCMParameterSpec gcmParameterSpec, String input) throws Exception {
        byte[] cipherText = Base64.getDecoder().decode(input);
        System.out.println("decryptByAESGCM - cipherText.length: [" + cipherText.length + "]");
        Cipher cipher = getCipher(secretKey, gcmParameterSpec, Cipher.DECRYPT_MODE);
        String output = new String(cipher.doFinal(cipherText));
        return output;
    }
}
