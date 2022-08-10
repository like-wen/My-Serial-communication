package com.lkw.java;

import com.lkw.java.common.Byte2Object;
import com.lkw.java.common.ByteCopy;
import com.lkw.java.common.Object2Byte;

import java.io.File;

public class FileFrames {
    byte[][] bytes;
    String name;


    public FileFrames(File file) {
        this.bytes = Object2Byte.File2Bytess(file);
        this.name=file.getName();
    }

    public byte[][] getBytes() {
        return bytes;
    }

    public String getName() {
        return name;
    }




    /**
     * 直接写出file
     * @param bytes
     * @param name
     */
    public static void createFile(byte[][] bytes,String name){
        byte[] bytes1 ={};
        for (int i = 0; i < bytes.length; i++) {
            ByteCopy.ByteMerge(bytes1, bytes[i]);

        }
        Byte2Object.Byte2File(bytes1,name);

    }

}
