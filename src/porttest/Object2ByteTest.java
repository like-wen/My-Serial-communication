package porttest;

import com.lkw.java.common.Object2Byte;

import java.io.File;

public class Object2ByteTest {
    public static void main(String[] args) {
/*

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
*/
/*
//文件读取
        File file = new File("D:\\work\\javacode\\port5fromport4\\1.txt");
        byte[] bytes = Object2Byte.File2Byte(file);
        for (int i = 0; i < bytes.length; i++) {
            System.out.print(bytes[i]);
        }
*/

        String strf="f";
        String strt="t";
        byte[] bytes = Object2Byte.String2Byte(strt);
        for (int i = 0; i < bytes.length; i++) {
            System.out.println(bytes[i]);
        }

    }
}
