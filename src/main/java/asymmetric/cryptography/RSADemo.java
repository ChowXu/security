package asymmetric.cryptography;

import org.apache.commons.codec.binary.Base64;

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

    }

    public static void jdkRSA() {
        try {
            // 1.初始化密钥
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(512);
            KeyPair keypair = keyPairGenerator.generateKeyPair();

            RSAPublicKey rsapublicKey = (RSAPublicKey) keypair.getPublic();
            RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keypair.getPrivate();
            System.out.println("rsaPublicKey:" + rsaPrivateKey);
            System.out.println("rsaPrivateKey:" + rsaPrivateKey);

            //2.私钥加密 公钥解密 -- 加密
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(rsaPrivateKey.getEncoded());
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE,privateKey);
            byte[] result = cipher.doFinal(string.getBytes());
            System.out.println("私钥加密 公钥解密 -- 加密:" + Base64.encodeBase64String(result));

            //3.  解密
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(rsapublicKey.getEncoded());
            keyFactory = KeyFactory.getInstance("RSA");
            PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);




        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
