package com.lkw.java.common;

public class ByteCopy {

    /**
     * 将两个字节数组直接拼接成一个字节数组
     * @param bytes1
     * @param bytes2
     * @return
     */
    public static byte[] ByteMerge (byte[] bytes1 , byte[] bytes2){
        if(bytes1==null)
            return bytes2;
        if(bytes2==null)
            return bytes1;
        byte[] bytes3=new byte[bytes1.length+ bytes2.length];
        //将第一个数组
        System.arraycopy(bytes1,0,bytes3,0,bytes1.length);
        System.arraycopy(bytes2,0,bytes3,bytes1.length,bytes2.length);
        return bytes3;
    }

    /**
     * 将字节串按固定大小分割,变成二位二维字节数组
     * @param bytes
     * @param size
     * @return
     */
    public static byte[][] ByteCut(byte[] bytes,int size){

        int num=(bytes.length/size);
        byte[][] bytess = new byte[num][size];

        if(bytes.length%size!=0) {
            bytess= new byte[num+1][size];

            for (int i = 0; i < bytes.length%size; i++) {
                bytess[num][i]=bytes[(num)*size+i];
            }
        }
        for (int i = 0; i < num; i++) {
            for (int j = 0; j < size; j++) {
                bytess[i][j]=bytes[i*size+j];
            }
        }
        return bytess;
    }

    public static byte[] BytesCutNull(byte[] bytes){
        int length = bytes.length-1;

        for (; length > 0; length--) {
            if(bytes[length]!=0)
                break;
        }

        return ByteSubstr(bytes,0,length+1);

    }

    /**
     * 截取一个字节数组的一部分
     * @param bytes
     * @param start 开始位置
     * @param len   截取长度
     * @return
     */
    public static byte[] ByteSubstr(byte[] bytes,int start,int len){
        byte[] endBytes=new byte[len];
        for (int i = 0; i < len; i++) {
            endBytes[i]=bytes[start+i];
        }


        return endBytes;
    }
}
