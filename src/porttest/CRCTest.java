package porttest;

import com.lkw.java.common.MyCRC;

public class CRCTest {
    public static void main(String[] args) {

        byte[] b={1,0,0,1};
        long myCRC = MyCRC.getMyCRC(b);
        System.out.println(myCRC);
    }

}
