package IO.encoding;

import java.io.UnsupportedEncodingException;

// 字节序列 转到 字符
/*在文件中 存储的是特定编码的字节序列 */
public class Encoding {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String s = "你好ABC";

        /*UTF-8 中文3字节 英文1个字节*/
        byte[] bytes = s.getBytes(); //转成字节序列 默认用项目编码
        for (byte aByte : bytes) {
            System.out.print(Integer.toHexString(aByte & 0xff) + " ");//字节转成int 再转16进制
        }
        System.out.println();

        /*gbk 中文2字节 英文1个字节*/
        byte[] bytes1 = s.getBytes("gbk");
        for (byte b : bytes1) {
            System.out.print(Integer.toHexString(b & 0xff) + " ");
        }


    }
}
