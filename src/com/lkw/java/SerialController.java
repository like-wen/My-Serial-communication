package com.lkw.java;

import com.lkw.java.common.Byte2Object;
import gnu.io.*;

import java.io.*;
import java.nio.file.Files;
import java.util.*;

public class SerialController {

    private SerialPort serialPort;


    public OutPortFrames outPortFrames=new OutPortFrames("");

    public InPortFrames inPortFrames = new InPortFrames();

    /**
     * 获得系统可用端口的列表
     *
     * @return 可用的端口名称列表
     */
    @SuppressWarnings("unchecked")
    public static List<String> getSystemPort() {
        List<String> systemPorts = new ArrayList<>();
        //获得系统可用的端口
        Enumeration<CommPortIdentifier> portList = CommPortIdentifier.getPortIdentifiers();
        while (portList.hasMoreElements()) {
            String portName = portList.nextElement().getName();
            systemPorts.add(portName);
        }
        System.out.println("系统可用端口列表： " + systemPorts);
        return systemPorts;
    }

    /**
     * 开启串口
     *
     * @param name     串口名称
     * @param baudRate 波特率
     * @return 串口对象
     */
    public boolean open(String name, int baudRate, String parity_str) {

        try {
            // 通过端口名称得到端口
            CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(name);
            // 打开端口  自定义名字，打开超时时间
            CommPort commPort = portIdentifier.open(name, 1000);
            // 判断是不是串口
            if (commPort instanceof SerialPort) {
                serialPort = (SerialPort) commPort;
                //设置串口参数（波特率，数据位8， 停止位1， 校验位无）
                serialPort.setSerialPortParams(baudRate, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, JudgeParity(parity_str));
                return true;
            } else {
                //是其他类型端口
                throw new NoSuchPortException();
            }
        } catch (UnsupportedCommOperationException | NoSuchPortException | PortInUseException e) {
            serialPort.close();
            e.printStackTrace();
        }
        return false;
    }


    /**
     * 通过String判断奇偶校验位
     *
     * @param parity_str
     * @return
     */
    private int JudgeParity(String parity_str) {
        int parity = SerialPort.PARITY_NONE;
        if (parity_str.equals("无校验"))
            parity = SerialPort.PARITY_NONE;
        else if (parity_str.equals("奇校验"))
            parity = SerialPort.PARITY_ODD;
        else if (parity_str.equals("偶校验"))
            parity = SerialPort.PARITY_EVEN;
        else if (parity_str.equals("校验位为1"))
            parity = SerialPort.PARITY_MARK;
        else if (parity_str.equals("校验位为0"))
            parity = SerialPort.PARITY_SPACE;
        return parity;
    }

    /**
     * 关闭串口
     */
    public void close() {
        if (serialPort != null) {
            serialPort.close();
            serialPort = null;
        }
    }

    /**
     * 发送bytes
     * @param bytes
     */
    public void sendByte(byte[] bytes) {
        outPortFrames = new OutPortFrames(bytes);
        byte[] allBytes = outPortFrames.portFrames[0].getAllBytes();
        sendData(allBytes);
    }

    /**
     * 发送String
     * @param str
     */
    public void sendStr(String str) {
        outPortFrames = new OutPortFrames(str);
        byte[] allBytes = outPortFrames.portFrames[0].getAllBytes();
        sendData(allBytes);
    }

    /**
     * 初始化发送文件
     * @param file
     */
    public void initSendFile(File file) {
        outPortFrames = new OutPortFrames(file);
        outPortFrames.fileOutNum=0;
        sendFile();

    }

    /**
     * 发送持续文件
     */
    public void sendFile() {
        sendData(outPortFrames.portFrames[outPortFrames.fileOutNum].getAllBytes());
    }

    /**
     * 向串口发送数据
     *
     * @param data 发送的数据
     */
    public void sendData(byte[] data) {
        PrintStream printStream = null;
        try {
            printStream = new PrintStream(serialPort.getOutputStream());  //获取串口的输出流
            printStream.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (printStream != null) {
                printStream.close();
            }
        }
    }

    /**
     * 读取输入流
     *
     * @return
     */
    public InputStream readInputStream() {
        try {
            return serialPort.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 读取输入字节
     *
     * @return
     */
    public byte[] readByteData() {
        InputStream is = null;
        byte[] bytes = null;
        try {
            is = readInputStream(); //获取输入流
            int bufflenth = is.available(); //获取数据长度
            while (bufflenth != 0) {
                bytes = new byte[bufflenth];
                is.read(bytes);
                bufflenth = is.available();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }



    /**
     * 监听
     *
     * @param listener
     */
    public void setListenerToSerialPort(SerialPortEventListener listener) {
        try {
            //给串口添加事件监听
            serialPort.addEventListener(listener);
        } catch (TooManyListenersException e) {
            e.printStackTrace();
        }
        serialPort.notifyOnDataAvailable(true);//串口有数据监听
        serialPort.notifyOnBreakInterrupt(true);//中断事件监听
    }

}

