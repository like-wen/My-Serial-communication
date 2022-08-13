package porttest;

import com.lkw.java.common.ByteCopy;

public class bytesCutTest {
    public static void main(String[] args) {
        byte[] bytes={1,2,3,4,5,6,7,8,9,0,0,0,0};
/*

        byte[][] bytes1 = ByteCopy.ByteCut(bytes, 3);
        for (int i = 0; i < bytes1.length; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.printf(String.valueOf(bytes1[i][j]));
            }
            System.out.println();
        }
*/

        byte[] bytes1 = ByteCopy.BytesCutNull(bytes);

        for (int i = bytes1.length - 1; i >= 0; i--) {
            System.out.println(bytes1[i]);
        }


    }
}
