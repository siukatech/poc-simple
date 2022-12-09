package com.siukatech.pocsimple.jcaaes;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.IvParameterSpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class CryptoUtils {

    public static String SECRET_KEY_ALGORITHM_AES = "AES";

    public static String CIPHER_ALGORITHM_AES_GCM_NO_PADDING = "AES/GCM/NoPadding";

    public static byte[] generateRandomNonce(int numBytes) {
        byte[] nonce = new byte[numBytes];
        new SecureRandom().nextBytes(nonce);
        return nonce;
    }
    public static SecretKey generateKey(int n) throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(SECRET_KEY_ALGORITHM_AES);
        keyGenerator.init(n);
        SecretKey key = keyGenerator.generateKey();
        return key;
    }

    public static IvParameterSpec generateIv() {
        byte[] iv = new byte[16];
        new SecureRandom().nextBytes(iv);
        return new IvParameterSpec(iv);
    }

    public static GCMParameterSpec generateGcm(int tagLengthBit, byte[] iv) {
        GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(tagLengthBit, iv);
        return gcmParameterSpec;
    }

    public static Cipher getCipher() throws NoSuchPaddingException, NoSuchAlgorithmException {
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM_AES_GCM_NO_PADDING);
        return cipher;
    }
}
