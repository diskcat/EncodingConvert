package cn.cslg.UI;

import cn.cslg.service.FirstService;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.*;


public class FirstUI implements ActionListener {
    private String filepath;// 选项卡布局
    Container con = new Container();
    //第一列组件
    JLabel label1 = new JLabel("项目路径");
    JLabel showPath = new JLabel();
    JButton button1 = new JButton("项目路径");// 选择
    //第二列组件
    JLabel label2 = new JLabel("转换前编码");
    JComboBox cmb=new JComboBox();    //创建JComboBox
    //第三列组件
    JLabel label3 = new JLabel("转换后编码");
    JComboBox cmb1=new JComboBox();    //创建JComboBox
    //第四列组件
    JLabel label4 = new JLabel("文件后缀");
    JComboBox cmb2=new JComboBox();    //创建JComboBox
    //第五列组件
    JButton button2 = new JButton("确定");// 选择
    JLabel label5 = new JLabel();


    JFileChooser jfc = new JFileChooser();// 文件选择器

    public FirstUI() {  //构造器
        jfc.setCurrentDirectory(new File("d://"));// 文件选择器的初始目录定为d盘

        //第一列位置
        label1.setBounds(180, 90, 70, 20);
        button1.setBounds(270, 90, 100, 20);
        showPath.setBounds(390, 90, 500, 20);

        //第二列位置
        label2.setBounds(180, 130, 70, 20);
        cmb.setBounds(270, 130, 100, 20);
        cmb.addItem("GBK");    //向下拉列表中添加一项
        cmb.addItem("UTF-8");
        cmb.addItem("UTF-16BE");
        cmb.addItem("Unicode");

        //第三列位置
        label3.setBounds(180, 170, 70, 20);
        cmb1.setBounds(270, 170, 100, 20);
        cmb1.addItem("GBK");    //向下拉列表中添加一项
        cmb1.addItem("UTF-8");
        cmb1.addItem("UTF-16BE");
        cmb1.addItem("Unicode");

        //第四列位置
        label4.setBounds(180, 210, 70, 20);
        cmb2.setBounds(270, 210, 100, 20);
        cmb2.addItem(".java");
        cmb2.addItem(".php");
        cmb2.addItem(".txt");
        cmb2.addItem(".html");
        cmb2.addItem(".py");
        cmb2.addItem(".c");
        cmb2.addItem(".jsp");
        cmb2.addItem(".xml");

        //第五列位置
        button2.setBounds(180, 250, 70, 20);
        label5.setBounds(270, 250, 500, 20);

        //容器添加第一列
        con.add(label1);
        con.add(showPath);
        con.add(button1);
        //容器添加第二列
        con.add(label2);
        con.add(cmb);
        //容器添加第三列
        con.add(label3);
        con.add(cmb1);
        //容器添加第四列
        con.add(cmb2);
        con.add(label4);
        //容器添加第五列
        con.add(label5);
        con.add(button2);
        //添加事件
        button1.addActionListener(this);
        button2.addActionListener(this);

//        tabPane.add("项目转换", con);// 添加布局1
    }
    /**
     * 监听的方法
     */
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if (e.getSource().equals(button1)) {// 判断触发方法的按钮是哪个
            jfc.setFileSelectionMode(1);// 设定只能选择到文件夹
            int state = jfc.showOpenDialog(null);// 此句是打开文件选择器界面的触发语句
            if (state == 1) {
                return;
            } else {
                File f = jfc.getSelectedFile();// f为选择到的目录
                filepath = f.getAbsolutePath();
                showPath.setText(f.getAbsolutePath());
            }
        }
        if (e.getSource().equals(button2)) {//在这里面进行编码转换
            if (filepath!=null){
                FirstService service = new FirstService(filepath);
                service.getAllFiles(filepath,cmb.getSelectedItem().toString(),cmb1.getSelectedItem().toString(),cmb2.getSelectedItem().toString());
                label5.setText(service.changePath(filepath));
            }else{
                showPath.setText("路径不能为空");
            }
        }
    }

    public Container getCon() {
        return con;
    }
}
