package security;


import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public abstract class Coder {

    public static final String KEY_SHA = "SHA";
    public static final String KEY_MD5 = "MD5";

    /**
     * MAC算法可选以下多种算法
     * <p>
     * <pre>
     * HmacMD5
     * HmacSHA1
     * HmacSHA256
     * HmacSHA384
     * HmacSHA512
     * </pre>
     */
    public static final String KEY_MAC = "HmacMD5";



    /**
     * BASE64加密
     * @param keybytes byte数组
     * @return String
     * @throws Exception
     */
    public static String encryptBASE64(byte[] keybytes) {
        return (new BASE64Encoder()).encodeBuffer(keybytes);
    }

    /**
     * BASE64加密
     * @param keyString string
     * @return String
     * @throws Exception
     */
    public static String encryptBASE64(String keyString) {
        return (new BASE64Encoder()).encodeBuffer(keyString.getBytes());
    }


    public static String ccEncryptBASE64(String keyString) {
        return new String(Base64.encodeBase64(keyString.getBytes()));
    }

    public static String ccEncryptBASE64(byte[] keybytes) {
        return new String(Base64.encodeBase64(keybytes));
    }

    /**
     * BASE64解密
     *
     * @param keyString
     * @return byte[] byte数组
     * @throws Exception
     */
    public static byte[] decryptBASE64toBytes(String keyString) throws Exception {
        return (new BASE64Decoder()).decodeBuffer(keyString);
    }

    public static String decryptBASE64toString(String keyString) throws Exception {
        return new String((new BASE64Decoder()).decodeBuffer(keyString));
    }

    public static byte[] ccdecryptBASE64toBytes(String keyString) throws Exception {
        return Base64.decodeBase64(keyString.getBytes());
    }

    public static String ccdecryptBASE64toString(String keyString) throws Exception {
        return new String(Base64.decodeBase64(keyString.getBytes()));
    }

    public static byte[] ccdecryptBASE64toBytes(byte[] keybytes) throws Exception {
        return Base64.decodeBase64(keybytes);
    }

    public static String ccdecryptBASE64toString(byte[] keybytes) throws Exception {
        return new String(Base64.decodeBase64(keybytes));
    }





    /**
     * MD5加密
     *
     * @param data
     * @return
     * @throws Exception
     */
    public static byte[] encryptMD5(byte[] data) throws Exception {

        MessageDigest md5 = MessageDigest.getInstance(KEY_MD5);
        md5.update(data);

        return md5.digest();

    }

    public static String md5(String plainText) {
        byte[] secretBytes = null;
        try {
            secretBytes = MessageDigest.getInstance("md5").digest(
                    plainText.getBytes());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("没有md5这个算法！");
        }
        String md5code = new BigInteger(1, secretBytes).toString(16);// 16进制数字
        // 如果生成数字未满32位，需要前面补0
        for (int i = 0; i < 32 - md5code.length(); i++) {
            md5code = "0" + md5code;
        }
        return md5code;
    }

    /**
     * SHA加密
     *
     * @param data
     * @return
     * @throws Exception
     */
    public static byte[] encryptSHA(byte[] data) throws Exception {

        MessageDigest sha = MessageDigest.getInstance(KEY_SHA);
        sha.update(data);

        return sha.digest();

    }

    /**
     * 初始化HMAC密钥
     *
     * @return
     * @throws Exception
     */
    public static String initMacKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_MAC);

        SecretKey secretKey = keyGenerator.generateKey();
        return encryptBASE64(secretKey.getEncoded());
    }

    /**
     * HMAC加密
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] encryptHMAC(byte[] data, String key) throws Exception {

        SecretKey secretKey = new SecretKeySpec(decryptBASE64toBytes(key), KEY_MAC);
        Mac mac = Mac.getInstance(secretKey.getAlgorithm());
        mac.init(secretKey);

        return mac.doFinal(data);

    }

}







