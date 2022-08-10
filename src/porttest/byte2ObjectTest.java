package porttest;

import com.lkw.java.common.Byte2Object;

public class byte2ObjectTest {

    public static void main(String[] args) {

        byte[] bytes1 = {0,0,1,1};
        int i = Byte2Object.Byte2Int(bytes1);
        System.out.println(i);


        byte[] bytes2 = {0,0,0,0,0,0,1,1};
        Long j = Byte2Object.Byte2Long(bytes2);
        System.out.println(j);




    }
}
