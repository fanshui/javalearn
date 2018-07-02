package test.concurrent;

public class Test {

    public static void main(String[] args) {

        int a = 189;


//        reb((byte) 0b10000000, 1);
//        reb((byte) 0b10000000, 2);
//        reb((byte) 0b10000000, 3);
//        reb((byte) 0b10000000, 4);

    }


    /**
     *  0 =< i <= 7
     * @param b
     * @param i
     * @return
     */
    private static byte reb(byte b, int i) {

        System.out.println(Byte.toUnsignedInt(b));

        int c = 128 >>> i;
        System.out.println(Integer.toBinaryString(c));


        System.out.println(Byte.toUnsignedInt((byte) c));

        byte ret = (byte) (b | c);
        return ret;
    }


}
