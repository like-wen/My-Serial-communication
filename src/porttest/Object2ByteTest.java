package porttest;

import com.lkw.java.common.Object2Byte;

public class Object2ByteTest {
    public static void main(String[] args) {

        int a=257;
        byte[] bytes1 = Object2Byte.Int2Byte(a);

        for (int i = 0; i < bytes1.length; i++) {
            System.out.println(bytes1[i]);
        }

        long s=258;
        byte[] bytes2 = Object2Byte.Long2Byte(s);

        for (int i = 0; i < bytes2.length; i++) {
            System.out.println(bytes2[i]);
        }




    }
}
