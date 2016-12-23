package messageDigest;

import org.apache.commons.codec.binary.Hex;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by zhouxi.
 * DATE: 16/12/23.
 * TIME: 上午11:31.
 */
public class SHA1Demo {

    private static String string ="SHA-1 DEMO JDK";

    public static void main(String[] args) {
        jdkSHA1();

    }

    /**
     * jdk 实现 SHA1
     */
    public static void jdkSHA1(){

        try {
            MessageDigest md = MessageDigest.getInstance("SHA");
            md.update(string.getBytes());
            System.out.println("jdk SHA-1:" + Hex.encodeHexString(md.digest())) ;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
