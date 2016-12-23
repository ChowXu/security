package messageDigest;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by zhouxi.
 * DATE: 16/12/23.
 * TIME: 上午11:00.
 */
public class MD5 {

    private static String string = "MD5 DEMO MESSAGEDigest";

    public static void main(String[] args) {
        jdkMD5();
        ccMD5();

    }


    /**
     * JDK 实现 MD5
     */
    public static void jdkMD5() {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] md5Bytes = md.digest(string.getBytes());
            System.out.println("bytes:       " + md5Bytes.toString());   //输出数组地址 不是内容
            System.out.println("String:      " + new String(md5Bytes));  // new String 乱码
            System.out.println("JDK:         " + Hex.encodeHexString(md5Bytes)); //转化成16进制
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }


    /**
     * commons crypt 通过jdk 实现 简化操作
     */
    public static void ccMD5() {
        String md5  = DigestUtils.md5Hex(string);
        System.out.println("md5:       " + md5 );
    }
}
