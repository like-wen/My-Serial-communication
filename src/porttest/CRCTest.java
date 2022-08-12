package porttest;

import com.lkw.java.common.MyCRC;
import com.lkw.java.common.Object2Byte;

public class CRCTest {
    public static void main(String[] args) {

        byte[] b= Object2Byte.String2Byte( "123");
        long myCRC = MyCRC.getMyCRC(b);
        System.out.println(myCRC);
        byte[] bytes = Object2Byte.Long2Byte(myCRC);
        for (int i = 0; i < bytes.length; i++) {
            System.out.print(bytes[i]);
        }
    }

}
