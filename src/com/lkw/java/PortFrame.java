package com.lkw.java;

import com.lkw.java.common.Byte2Object;
import com.lkw.java.common.ByteCopy;
import com.lkw.java.common.MyCRC;
import com.lkw.java.common.Object2Byte;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.zip.CRC32;

public class PortFrame {

    /*
     * 0是Byte模式
     * 1是String模式
     * 2是文件模式
     * 3是重发模式
     * 4是文件名模式
     * */
    int mode;
    //帧编号
    long frameNum;//0是只有一帧
    //校验码
    long CRC;

    //输入的字节
    byte[] bytes;


    /**
     * 一般用于file头的编码
     * @param mode
     * @param frameNum
     * @param bytes
     */
    public PortFrame(int mode, long frameNum, byte[] bytes) {//这里是输入的字节
        this.mode = mode;
        this.frameNum = frameNum;
        this.CRC= MyCRC.getMyCRC(bytes);
        this.bytes=bytes;
    }

    /**
     * 用于string编码
     * @param str
     */
    public PortFrame(String str) {
        this.bytes = Object2Byte.String2Byte(str);
        this.mode=1;
        this.frameNum=0;
        this.CRC=MyCRC.getMyCRC(this.bytes);
    }

    /**
     * 用于byte模式编码
     * @param mode
     * @param bytes
     */
    public PortFrame(int mode,byte[] bytes) {
        this.mode=mode;
        this.frameNum=0;
        this.bytes=bytes;
        this.CRC=MyCRC.getMyCRC(this.bytes);
    }


    /**
     * 用于接收解码
     * @param bytes
     */
    public  PortFrame(byte[] bytes) {
        this.mode= Byte2Object.Byte2Int(ByteCopy.ByteSubstr(bytes,0,4));
        this.frameNum=Byte2Object.Byte2Long(ByteCopy.ByteSubstr(bytes,4,8));
        this.CRC=Byte2Object.Byte2Long(ByteCopy.ByteSubstr(bytes,12,8));
        this.bytes=ByteCopy.ByteSubstr(bytes,20,bytes.length-20);
    }

    /**
     * 检查CRC对不对
     * @return
     */
    public Boolean checkMyCRC(){
        long myCRC = MyCRC.getMyCRC(this.bytes);
        return myCRC==this.CRC;
    }

    /**
     * 生成协议byte[]
     * @return
     */
    public byte[] getAllBytes(){
        if(this.mode==0){
            return bytes;
        }else {
            byte[] bytes1 = Object2Byte.Int2Byte(mode);
            byte[] bytes2 = Object2Byte.Long2Byte(frameNum);
            byte[] bytes3 = Object2Byte.Long2Byte(CRC);
            return ByteCopy.ByteMerge(ByteCopy.ByteMerge(bytes1, bytes2), ByteCopy.ByteMerge(bytes3, this.bytes));
        }
    }






    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public long getFrameNum() {
        return frameNum;
    }

    public void setFrameNum(long frameNum) {
        this.frameNum = frameNum;
    }

    public long getCRC() {
        return CRC;
    }

    public void setCRC(long CRC) {
        this.CRC = CRC;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }
}
