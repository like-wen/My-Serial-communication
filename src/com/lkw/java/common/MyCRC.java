package com.lkw.java.common;

import java.util.zip.CRC32;

public class MyCRC {

    static public long getMyCRC(byte[] bytes){
        CRC32 crc32 = new CRC32();
        crc32.update(bytes);
        return crc32.getValue();
    }
}
