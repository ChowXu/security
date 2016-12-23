package base64;

import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;

/**
 * Created by zhouxi.
 * DATE: 16/12/22.
 * TIME: 下午10:15.
 */


public class Base64Demo {

    private static String string = "java base64 Demo";

    public static void main(String[] args) throws IOException {
//        jdkBase64();
//        commonsCodesBase64();
        bouncyCastleBase64();
    }

    /**
     * jdkBase64 的实现
     * @throws IOException
     */
    public static void jdkBase64() throws IOException {
        // Base64Encoder
        BASE64Encoder encoder = new BASE64Encoder();
        String encode = encoder.encode(string.getBytes());
        System.out.println("decode:=====" + encode);

        //Base64Decoder
        BASE64Decoder decoder = new BASE64Decoder();
        String decode = new String(decoder.decodeBuffer(encode));
        System.out.println("decode:     " + decode);

    }

    /**
     * commonsCodes 的实现
     */
    public static void commonsCodesBase64(){

        String encode =new String(Base64.encodeBase64(string.getBytes()));
        System.out.println(encode);

        String decode =new String(Base64.decodeBase64(encode));
        System.out.println(decode);

    }

    /**
     * bouncy Castle 的实现
     */
    public static void bouncyCastleBase64(){
        byte[] encodeBytes = org.bouncycastle.util.encoders.Base64.encode(string.getBytes());
        System.out.println("encode:" + new String(encodeBytes));

        byte[] decodeBytes = org.bouncycastle.util.encoders.Base64.decode(encodeBytes);
        System.out.println("decode:" + new String(decodeBytes));
    }


}
