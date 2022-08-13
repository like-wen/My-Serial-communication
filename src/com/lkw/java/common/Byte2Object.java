package com.lkw.java.common;

import java.io.*;
import java.nio.ByteBuffer;

public class Byte2Object {

    public static int Byte2Int(byte[] bytes){

        ByteBuffer buffer = ByteBuffer.allocate(4);
        buffer.put(bytes, 0, bytes.length);
        //flip方法将Buffer从写模式切换到读模式，调用flip()方法会将position设回0，从头读起
        buffer.flip();
        return buffer.getInt();
    }
    public static long Byte2Long(byte[] bytes){

        ByteBuffer buffer = ByteBuffer.allocate(8);
        buffer.put(bytes, 0, bytes.length);
        //flip方法将Buffer从写模式切换到读模式，调用flip()方法会将position设回0，从头读起
        buffer.flip();
        return buffer.getLong();
    }

    /**
     * 直接写出
     * @param bytes
     * @param fileName
     */
    public static void Byte2File(byte[] bytes,String fileName){
        bytes = ByteCopy.BytesCutNull(bytes);
        String filePath=System.getProperty("user.dir");
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try {
            File dir = new File(filePath);
            if (!dir.exists() && dir.isDirectory()) {// 判断文件目录是否存在
                dir.mkdirs();
            }
            file = new File(filePath + "\\" + fileName);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String Byte2String(byte[] bytes){
        String s = null;
        try {
            s = new String(bytes, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        return s;
    }

}
