package com.lkw.java;
import com.lkw.java.common.Byte2Object;
import com.lkw.java.common.ByteCopy;
import com.lkw.java.common.Object2Byte;

import java.io.File;

public class PortFrames {
    /*
     * 0是Byte模式
     * 1是String模式
     * 2是文件模式
     * 3是重发模式
     * */
    int mode;
    PortFrame[] portFrames=new PortFrame[1];
    FileFrames fileFrames;

    public void SetMode(int mode){
        if(mode==4)
            this.mode=2;
        else
            this.mode=mode;
    }

}
















