package encryption;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.*;
import javax.crypto.spec.DESedeKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

/**
 * Created by zhouxi.
 * DATE: 16/12/22.
 * TIME: 下午11:47.
 */
public class ThreeDESDemo {

    private static String string = "3DES DEMO";

    public static void main(String[] args) throws NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, InvalidKeySpecException {
        jdk3DES();
    }


    public static void jdk3DES() throws NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException, NoSuchPaddingException, BadPaddingException, IllegalBlockSizeException {

        /**
         *
         */
        KeyGenerator keyGenerator = KeyGenerator.getInstance("DESede");
        keyGenerator.init(new SecureRandom());
        SecretKey secretKey = keyGenerator.generateKey();
        byte[] bytesKey = secretKey.getEncoded();

        //key 转换
        DESedeKeySpec desKeySpec = new DESedeKeySpec(bytesKey);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("DESede");
        Key convertSecretKey = factory.generateSecret(desKeySpec);

        //encryption
        Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, convertSecretKey);
        byte[] result = cipher.doFinal(string.getBytes());
        System.out.println("jdk des encrypt:" + Hex.encodeHexString(result));

        //decrypt
        cipher.init(Cipher.DECRYPT_MODE, convertSecretKey);
        result = cipher.doFinal(result);
        System.out.println("jdk DES decryption: ++++" + new String(result));


    }
}
