package com.lkw.java;

import gnu.io.SerialPortEvent;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.*;
import java.lang.annotation.Repeatable;
import java.util.List;


public class SerialAssistant {
    @FXML
    private TextArea receiveText;
    @FXML
    private TextField fileTextField;
    @FXML
    private TextArea sendText;
    @FXML
    private Text myMessage;
    @FXML
    private Button openBnt;
    @FXML
    private ComboBox<String> comboBox;
    @FXML
    private ComboBox<Integer> BaudRateBox;
    @FXML
    private ComboBox<String> ParityBox;

    private SerialController serialController;
    private List<String> systemPorts;
    private boolean isOpen = false;


    public SerialAssistant() {
       systemPorts = SerialController.getSystemPort();
        serialController = new SerialController();
    }

    /**
     * 选择串口
     * @param event
     */
    public void onShowComboBox(Event event) {
        comboBox.getItems().addAll(systemPorts);
    }


    /**
     * 选择波特率
     * @param event
     */
    public void onShowBaudRateBox(Event event) {
        BaudRateBox.getItems().addAll(2400, 4800, 9600, 19200, 38400, 57600, 115200);
        BaudRateBox.setVisibleRowCount(4);
    }

    /**
     * 选择校验方法
     * @param event
     */
    public void onShowParityBox(Event event) {
        ParityBox.getItems().addAll("无校验","奇校验","偶校验","校验位为1","校验位为0");
        ParityBox.setVisibleRowCount(4);
    }

    /**
     * 打开串口并实现监听
     * @param actionEvent
     */
    public void onActionOpenBtn(ActionEvent actionEvent) {
        if(comboBox.getValue()==null||BaudRateBox.getValue()==null||getClass()==null){
            myMessage.setText("还没选择完功能");
            return;
        }
        isOpen = !isOpen;
        if (isOpen) {
            if (!serialController.open(comboBox.getValue(), BaudRateBox.getValue(),ParityBox.getValue())) {//如果打开失败
                return;
            }
            serialController.setListenerToSerialPort(ev -> {//有消息传来
                if (ev.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
                    byte[] bytes = serialController.readByteData();
                    //检查请求
                    boolean receive = serialController.inPortFrames.Receive(bytes);
                    //mode==3就跳过
                    if(serialController.inPortFrames.mode!=3&&receive) {
                        byte[] repeat = serialController.inPortFrames.RepeatRequest(receive);
                        serialController.sendData(repeat);
                    }
                    //mode==3并且对方接收正确
                    if(serialController.inPortFrames.mode==3&&serialController.inPortFrames.checkMode_3())
                        serialController.frameNum++;
                    //如果有文件发送(frameNum!=1)
                    if(serialController.frameNum!=-1){
                        serialController.sendFile();
                    }
                    //如果发送帧数等于总帧数,就销毁
                    if(serialController.frameNum== serialController.outPortFrames.getTotalFrameNum())
                        serialController.frameNum=-1;
                    //正常接收对方发的
                    if (serialController.inPortFrames.CheckFull()) {
                        Object o = serialController.inPortFrames.OutPut();
                        acceptString((String) o);
                        /*if (String.class.equals(o))
                            acceptString((String) o);
                        else if (byte[].class.equals(o))//byte保留
                            acceptString((String) o);*/
                    }

                }
            });
            openBnt.setText("关闭串口");
            myMessage.setText("串口已打开");
        } else {
            serialController.close();
            openBnt.setText("打开串口");
            myMessage.setText("串口已关闭");
        }
    }
/*

    */
/**
     * 文件接收
     * @throws IOException
     *//*

    private void acceptFile() throws IOException {
        byte[] bytes = serialController.readByteData();
        mainBytes=concat(mainBytes,bytes);
        if(partFileNum==1){
            FileOutputStream outputStream=new FileOutputStream(writeFilename+".lkw");
            outputStream.write(mainBytes,0,mainBytes.length);
            outputStream.close();
        }
    }

    */
/**
     * 合并数组
     * @param firstArray
     * @param secondArray
     * @return
     *//*

    public byte[] concat(byte[] firstArray, byte[] secondArray) {
        if(firstArray==null){
            return secondArray;
        }
        if(secondArray==null){
            return firstArray;
        }
        byte[] endArray = new byte[firstArray.length+secondArray.length];
        for (int i = 0; i < firstArray.length; i++) {
            endArray[i]=firstArray[i];
        }
        for (int i = 0; i < secondArray.length; i++) {
            endArray[firstArray.length+i]=secondArray[i];
        }
        return endArray;
    }

    */
/**
     * 接收文件篇章页数
     *//*

    private void acceptFileNum() {
             partFileNum = Integer.parseInt(serialController.readData());
        System.out.println("partFileNum已经更改"+partFileNum);
    }
*/

    /**
     * 接收文本信息
     */
    private void acceptString(String str) {

        Platform.runLater(() -> {
            if (receiveText.getLength() < 4000) {
                receiveText.appendText(str);
            } else {
                receiveText.deleteText(0, str.length());
                receiveText.appendText(str);
            }
        });

    }

 /*   *//**
     * 判断是否是文件传输
     * @param str
     *//*
    private void JudgeFileTrans(String str) {
        if (str.substring(0, 6).equals("开始传输文件")) {
            writeFilename = str.substring(6);
            partFileNum = -1;
        }
    }*/

    /**
     * 文本发送按钮
     * @param actionEvent
     */
    public void onActionSendBtn(ActionEvent actionEvent) {
        serialController.sendStr(sendText.getText());
        myMessage.setText("文本已发送");
    }

    /**
     * 文件发送按钮
     * @param actionEvent
     * @throws InterruptedException
     */
    public void onActionSendFileBtn(ActionEvent actionEvent) throws InterruptedException {
        String filepath = fileTextField.getText();
        File file=new File(filepath);
        serialController.initSendFile(file);
        myMessage.setText("文件已发送");
    }

    /**
     * 清除框
     * @param actionEvent
     */
    public void clear(ActionEvent actionEvent) {
        receiveText.clear();
    }


}

