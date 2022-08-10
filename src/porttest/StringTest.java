package porttest;

import com.lkw.java.common.Byte2Object;
import com.lkw.java.common.Object2Byte;

public class StringTest {
    public static void main(String[] args) {
        String s="李可文";
        byte[] bytes = Object2Byte.String2Byte(s);
        String s1 = Byte2Object.Byte2String(bytes);
        System.out.println(bytes.length);
        System.out.println(s1);
        System.out.println(1/1024);
    }

}
