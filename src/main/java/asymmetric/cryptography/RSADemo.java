package asymmetric.cryptography;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * Created by zhouxi.
 * DATE: 16/12/23.
 * TIME: 下午2:27.
 */
public class RSADemo {

    public static String string = "JAVA Security RSA";

    public static void main(String[] args) {

        jdkRSA();

    }

    public static void jdkRSA() {
        try {
            // 1.初始化密钥   生成公钥 和 私钥
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(512);
            KeyPair keypair = keyPairGenerator.generateKeyPair();

            RSAPublicKey rsapublicKey = (RSAPublicKey) keypair.getPublic();
            RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keypair.getPrivate();
            System.out.println("rsaPublicKey:" + Base64.encodeBase64String(rsaPrivateKey.getEncoded()));
            System.out.println("rsaPrivateKey:" + Hex.encodeHexString(rsaPrivateKey.getEncoded()));

            /**
             *  生成私钥
             */
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(rsaPrivateKey.getEncoded());
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);

            /*
            生成公钥
             */
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(rsapublicKey.getEncoded());
            keyFactory = KeyFactory.getInstance("RSA");
            PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);

            //2.私钥加密 公钥解密 -- 加密
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE,privateKey);
            byte[] result = cipher.doFinal(string.getBytes());
            System.out.println("私钥加密 公钥解密 -- 加密:" + Base64.encodeBase64String(result));

            //3.解密
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
            result = cipher.doFinal(result);
            System.out.println("私钥加密 公钥解密 -- 解密" + new String(result));

            //4. 公钥加密  私钥解密 --加密
            cipher.init(Cipher.ENCRYPT_MODE,publicKey);
            result = cipher.doFinal(result);
            System.out.println("公钥加密 私钥解密-- 加密" + Base64.encodeBase64String(result));

            //5. 公钥加密 私钥解密 -- 解密
            cipher.init(Cipher.DECRYPT_MODE,privateKey);
            result = cipher.doFinal(result);
            System.out.println("公钥加密 私钥解密-- 解密" + new String(result));



        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
