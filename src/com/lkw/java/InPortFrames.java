package com.lkw.java;

import com.lkw.java.common.Byte2Object;
import com.lkw.java.common.ByteCopy;
import com.lkw.java.common.Object2Byte;

import java.io.File;

public class InPortFrames extends PortFrames{
    //正在接收帧数
    long fileInNum;

    /**
     * 接收,并检查CRC
     * @param bytes
     * @return
     */
    public boolean Receive(byte[] bytes){
        PortFrame portFrame = new PortFrame(bytes);
        int mode = portFrame.getMode();
        if (!portFrame.checkMyCRC())
            return false;

        if(mode==2) {
            //检查帧序号
            if (portFrame.getFrameNum() != ++fileInNum)
                return false;
            //收入帧,帧数++
            portFrames[(int)fileInNum]=portFrame;
        }
        //设置正在接收帧数
        if (mode==4) {
            fileInNum = 0;
            //收入帧
            portFrames=new PortFrame[(int)portFrame.getFrameNum()+1];
            portFrames[0] = portFrame;
        }
        if(mode==3||mode==1)
            portFrames[0]=portFrame;
        //更改PortFrames的mode
        SetMode(mode);
        return true;
    }

    /**
     * 重发提示
     * 1是传输正确
     * -1是传输错误
     * @return
     */
    public byte[] RepeatRequest(boolean b)  {
        if(mode==3)
            return null;
        String str = new String();
        if(b==true)
            str="t";
        else
            str="f";

        PortFrame portFrame = new PortFrame(3,fileInNum, Object2Byte.String2Byte(str));

        return portFrame.getAllBytes();
    }

    /**
     * 判满
     * @return
     */
    public boolean CheckFull(){
        //直接判满
        if(mode==1||mode==3)
            return true;
        //判断总帧数对不对
        if(mode==2&&fileInNum==portFrames[0].getFrameNum())
            return true;
        else return false;
    }

    /**
     * 输出对象
     * @return
     */
    public Object OutPut(){
        if(mode==0){
            return portFrames[0].getBytes();
        }
        if(mode==1){
            System.out.println(portFrames[0]);
            System.out.println(portFrames[0].bytes[0]);
            byte[] bytes = portFrames[0].getBytes();
            return Byte2Object.Byte2String(bytes);
        }
        if(mode==2){
            System.out.println("输出文件");
            //获取文件名字
            String fileName = Byte2Object.Byte2String(portFrames[0].getBytes());
            //创建总bytes1
            byte[] bytes1 =null;
            for (int i = 1; i < portFrames.length; i++) {
                bytes1 = ByteCopy.ByteMerge(bytes1, portFrames[i].getBytes());
            }
            for (int i = 0; i < bytes1.length; i++) {
                System.out.print(bytes1[i]);
            }

            //变成file直接输出
            Byte2Object.Byte2File(bytes1,"lkw_"+fileName);
            return null;
        }
        if(mode==3) {
            return true;
        }
        return  null;

    }

    //判断进入的mode==3,是什么类型:
    public boolean checkMode_3(){
        System.out.println(portFrames[0]);
        byte[] bytes = portFrames[0].getBytes();
        String s = Byte2Object.Byte2String(bytes);
        System.out.println("checkMode_3():"+s);
        return s.equals("t");
    }



}
