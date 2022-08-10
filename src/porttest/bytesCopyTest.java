package porttest;

public class bytesCopyTest {
    public static void main(String[] args) {

        /*将数组1和数组2复制分别拷贝到数组3*/
        byte[] bytes1 = {1,2,3,4};
        byte[] bytes2={5,6,7};
        byte[] bytes3=new byte[bytes1.length+ bytes2.length];
        //将第一个数组
        System.arraycopy(bytes1,0,bytes3,0,bytes1.length);
        System.arraycopy(bytes2,0,bytes3,bytes1.length,bytes2.length);


        for (int i = 0; i < bytes3.length; i++) {
            System.out.printf(String.valueOf(bytes3[i]));
        }


    }


}
