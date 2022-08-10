package porttest;

import com.lkw.java.common.ByteCopy;

public class bytesSubstrTest {
    public static void main(String[] args) {

        byte[] bytes={1,2,3,4,5};
        byte[] bytes1 = ByteCopy.ByteSubstr(bytes, 4, 1);

        for (int i = 0; i < bytes1.length; i++) {
            System.out.println(String.valueOf(bytes1[i]));
        }

    }
}
