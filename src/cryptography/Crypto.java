/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptography;

import java.security.Key;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Marko
 */
public class Crypto {
    public static String encrypt(String message, String keyString) throws Exception {
        byte[] messageByte = message.getBytes();
        byte[] keyByte = keyString.getBytes();
        Key key = new SecretKeySpec(keyByte, "AES");

        Cipher c = Cipher.getInstance("AES");
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedMessageByte = c.doFinal(messageByte);
        String encryptedMessageString1 = new String(Base64.getEncoder().encode(encryptedMessageByte));

        System.out.println(encryptedMessageString1);

        return encryptedMessageString1;
    }

    public static String decrypt(String message, String keyString) throws Exception {
        byte[] messageByte = message.getBytes();
        byte[] keyByte = keyString.getBytes();
        Key key = new SecretKeySpec(keyByte, "AES");

        Cipher c = Cipher.getInstance("AES");
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] decryptedMessageByte = c.doFinal(Base64.getDecoder().decode(messageByte));

        String decryptedMessageString = new String(decryptedMessageByte);
        System.out.println(decryptedMessageString);

        return decryptedMessageString;
    }
}
