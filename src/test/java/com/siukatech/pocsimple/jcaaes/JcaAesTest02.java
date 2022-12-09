package com.siukatech.pocsimple.jcaaes;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;

public class JcaAesTest02 {

    public static void main(String[] args) {
        JcaAesTest02 test = new JcaAesTest02();

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            String message = "This is a test with timestamp: [" + sdf.format(new Date()) + "]";
            String password = "thisisatest";
            int keySize = 16;
            String hashAlgorithm = "SHA-1";

            String cipherText = null;

            cipherText = test.encryptStringUsingAes(message, password, keySize, hashAlgorithm);

            System.out.println("main - message: [" + message + "]");
            System.out.println("main - cipherText: [" + cipherText + "]");

            String output = test.decryptStringUsingAesKey(cipherText, password, keySize, hashAlgorithm);

            System.out.println("main - output: [" + output + "]");
        }
        catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

    public String encryptStringUsingAes(String message, String password, int keySize, String hashAlgorithm)
            throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException {

        byte[] iv = CryptoUtils.generateRandomNonce(12);
        GCMParameterSpec gcmParameterSpec = CryptoUtils.generateGcm(128, iv);

        byte[] key = password.getBytes();
        MessageDigest sha = MessageDigest.getInstance(hashAlgorithm);
        key = sha.digest(key);
        key = Arrays.copyOf(key, keySize); // use only first 128 bit

        SecretKeySpec spec = new SecretKeySpec(key, "AES");
        //Cipher cipher = Cipher.getInstance("AES");    // AIU-456
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");    // AIU-456

        //cipher.init(Cipher.ENCRYPT_MODE, spec);
        cipher.init(Cipher.ENCRYPT_MODE, spec, gcmParameterSpec);

        byte[] encryptData = cipher.doFinal(message.getBytes());

        String encryptedStr = Base64.getEncoder().encodeToString(encryptData);

        String ivStr = Base64.getEncoder().encodeToString(iv);
        String output = ivStr + "#" + encryptedStr;

        System.out.println("encryptStringUsingAes - ivStr: [" + ivStr + "], encryptedStr: [" + encryptedStr
                + "], output: [" + output
                + "]");

        return output;
    }

    public String decryptStringUsingAesKey(String message, String keyStr, int keySize, String hashAlgorithm)
            throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException {

        String ivStr = message.substring(0, message.indexOf("#"));
        String encryptedStr = message.substring(message.indexOf("#") + 1);
        byte[] iv = Base64.getDecoder().decode(ivStr);
        GCMParameterSpec gcmParameterSpec = CryptoUtils.generateGcm(128, iv);

        System.out.println("decryptStringUsingAesKey - ivStr: [" + ivStr + "], encryptedStr: [" + encryptedStr
                + "], message: [" + message
                + "]");

        byte[] key = (keyStr).getBytes();
        MessageDigest sha = MessageDigest.getInstance(hashAlgorithm);
        key = sha.digest(key);
        key = Arrays.copyOf(key, keySize); // use only first 128 bit

        SecretKeySpec spec = new SecretKeySpec(key, "AES");
        //Cipher cipher = Cipher.getInstance("AES");    // AIU-456
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");    // AIU-456

        //cipher.init(Cipher.DECRYPT_MODE, spec);
        cipher.init(Cipher.DECRYPT_MODE, spec, gcmParameterSpec);

        //return new String(cipher.doFinal(Base64.getDecoder().decode(message)));
        return new String(cipher.doFinal(Base64.getDecoder().decode(encryptedStr)));

    }

}
