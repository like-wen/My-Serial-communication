package com.lkw.java;

import com.lkw.java.common.Object2Byte;

import java.io.File;

public class OutPortFrames extends PortFrames{
    int fileOutNum=-1;


    /**
     * 用file生产的输出帧
     * @param file
     */
    public OutPortFrames(File file) {
        //创建fileFrame对象,并且设置模式
        fileFrames=new FileFrames(file);
        this.mode=2;
        //获取byte[][]
        byte[][] bytes = fileFrames.getBytes();
        //创建+1长度的portFrame[]
        portFrames=new PortFrame[fileFrames.getBytes().length+1];
        //portFrame[0]的位置进行预定
        portFrames[0]=new PortFrame(4, portFrames.length-1, Object2Byte.String2Byte(fileFrames.getName()));
        //主要的字节流编入portFrame[]
        for (int i = 0; i < bytes.length; i++) {
            portFrames[i+1]=new PortFrame(2,i+1,bytes[i]);
        }
    }

    /**
     * 用String生产的输出帧
     * @param str
     */
    public OutPortFrames(String str) {
        this.mode=1;
        portFrames[0]=new PortFrame(str);
    }

    /**
     * 用byte生产的输出帧(废弃中)
     * @param bytes
     */
    public OutPortFrames(byte[] bytes) {
        this.mode=0;
        portFrames[0]=new PortFrame(0,bytes);
    }

    /**
     *
     * @return
     */
    public int getTotalFrameNum(){

        return (int)this.portFrames[0].frameNum;

    }


}
