package porttest;

import com.lkw.java.common.Byte2Object;
import com.lkw.java.common.Object2Byte;

import java.io.File;

public class FileTest {
    public static void main(String[] args) {
        File file = new File("D:\\work\\javacode\\port5fromport4\\src\\porttest\\StringTest.java");
        boolean exists = file.exists();
        System.out.println(exists);


        byte[] bytes = Object2Byte.File2Byte(file);
        Byte2Object.Byte2File(bytes,"myfiletest");

    }


}
