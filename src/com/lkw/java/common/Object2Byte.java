package com.lkw.java.common;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class Object2Byte {

    public static byte[] Long2Byte(long object){
        ByteBuffer buffer = ByteBuffer.allocate(8);
        buffer.putLong(0, object);
        return buffer.array();
    }


    public static byte[] Int2Byte(int object){
        ByteBuffer buffer = ByteBuffer.allocate(4);
        buffer.putInt(0, object);
        return buffer.array();
    }

    public static byte[] File2Byte(File file){
        byte[] data = null;

        try {
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            int len;
            byte[] buffer = new byte[1024];
            while ((len = fis.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }

            data = baos.toByteArray();

            fis.close();
            baos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }


    public static byte[] String2Byte(String str){
        byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
        return bytes;
    }

    public static byte[][] File2Bytess(File file){
        byte[] bytes1 = Object2Byte.File2Byte(file);
        return ByteCopy.ByteCut(bytes1, 1024);
    }



}
