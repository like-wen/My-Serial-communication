package com.lkw.java;

import com.lkw.java.common.Object2Byte;

import java.io.File;

public class OutPortFrames extends PortFrames{
    long fileOutNum=0;


    public OutPortFrames(File file) {
        //创建fileFrame对象,并且设置模式
        fileFrames=new FileFrames(file);
        this.mode=2;
        //获取byte[][]
        byte[][] bytes = fileFrames.getBytes();
        //创建+1长度的portFrame[]
        portFrames=new PortFrame[fileFrames.getBytes().length+1];
        //portFrame[0]的位置进行预定
        portFrames[0]=new PortFrame(4,0, Object2Byte.String2Byte(fileFrames.getName()));
        //主要的字节流编入portFrame[]
        for (int i = 1; i < bytes.length+1; i++) {
            portFrames[i]=new PortFrame(2,i,bytes[i-1]);
        }
    }

    public OutPortFrames(String str) {
        this.mode=1;
        portFrames[0]=new PortFrame(str);
    }

    public OutPortFrames(byte[] bytes) {
        this.mode=0;
        portFrames[0]=new PortFrame(0,bytes);
    }

    public int getTotalFrameNum(){

        return (int)this.portFrames[0].frameNum;

    }


}
