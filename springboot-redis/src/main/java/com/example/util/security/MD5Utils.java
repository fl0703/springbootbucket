package com.example.util.security;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 对密码进行加密和验证的程序
 * Created by luoxiang on 2018-4-27.
 */
public class MD5Utils {

    private static Logger logger = LoggerFactory.getLogger(MD5Utils.class);

    /** 十六进制下数字到字符的映射数组 */
    private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

    /**
     * 把inputString加密。
     *
     * @param inputString
     *            待加密的字符串
     * @return
     */
    public static String createPassword(String inputString) {
        return encodeByMD5(inputString);
    }

    /**
     * 验证输入的密码是否正确
     *
     * @param password
     *            真正的密码（加密后的真密码）
     * @param inputString
     *            输入的字符串
     * @return
     */
    public static boolean authenticatePassword(String password,
                                               String inputString) {
        if (password.equals(encodeByMD5(inputString))) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 对字符串进行MD5编码
     *
     * @param originString
     * @return
     */
    private static String encodeByMD5(String originString) {
        if (originString != null) {
            try {
                MessageDigest md = MessageDigest.getInstance("MD5");
                byte[] results = md.digest(originString.getBytes());
                String resultString = byteArrayToHexString(results);
                return resultString.toUpperCase();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 转换字节数组为16进制字串
     *
     * @param b
     *            字节数组
     * @return 十六进制字串
     */
    private static String byteArrayToHexString(byte[] b) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }

    /**
     * 将一个字节转化成16进制形式的字符串
     *
     * @param b
     * @return
     */
    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n = 256 + n;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    /**
     * md5-32位大写加密
     * @param text
     * @return
     */
    public static String encode(String text){
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update((text).getBytes("UTF-8"));
            byte b[] = md5.digest();

            int i;
            StringBuffer buf = new StringBuffer("");

            for(int offset=0; offset<b.length; offset++){
                i = b[offset];
                if(i<0){
                    i+=256;
                }
                if(i<16){
                    buf.append("0");
                }
                buf.append(Integer.toHexString(i));
            }
            return buf.toString().toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "" ;
    }

    /**
     * MD5加码 生成32位md5码(不可逆的)
     *
     * @param inStr 加密的字符串
     * @return 一个加密的32位密钥
     */
    public static String string2MD5(String inStr) throws NoSuchAlgorithmException{
        MessageDigest md5;
        String string="";
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            logger.error("MD5加密实现的错误日志-->>"+e.getMessage(), e);
            return string;
        }
        char[] charArray = inStr.toCharArray();
        byte[] byteArray = new byte[charArray.length];
        for (int i = 0; i < charArray.length; i++) {
            byteArray[i] = (byte) charArray[i];
        }
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        string=hexValue.toString();
        logger.debug("MD5加密的32位密钥的调试日志-->>" + string);
        return string;
    }

    /**
     * 加密解密算法 执行一次加密，两次解密
     *
     * @param inStr 编译的字符串
     * @return 返回一个二次加密的字符串
     */
    public static String convertMD5(String inStr) throws Exception{
        char[] a = inStr.toCharArray();
        for (int i = 0; i < a.length; i++) {
            a[i] = (char) (a[i] ^ 't');
        }
        String string = new String(a);
        logger.debug("MD5加密的二次加密的字符串的调试日志-->>" + string);
        return string;
    }

//    public static void main(String[] args) {
//        String password = MD5Util.createPassword("888888");
//        System.out.println("对888888用MD5摘要后的字符串：" + password);
//        String inputString = "8888";
//        System.out.println("8888与密码匹配？"
//                + MD5Util.authenticatePassword(password, inputString));
//        inputString = "888888";
//        System.out.println("888888与密码匹配？"
//                + MD5Util.authenticatePassword(password, inputString));
//    }
}
