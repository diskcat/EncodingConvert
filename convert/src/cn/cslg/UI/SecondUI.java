package cn.cslg.UI;

import cn.cslg.service.FirstService;
import cn.cslg.service.SecondService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class SecondUI implements ActionListener {
    private Container con = new Container();
    private String filePath;
    //第一列组件
    JLabel label1 = new JLabel("项目路径");
    JLabel showPath = new JLabel();
    JButton button1 = new JButton("文件路径");// 选择
    //第二列组件
    JLabel label2 = new JLabel("转换前编码");
    JComboBox cmb = new JComboBox();    //创建JComboBox
    //第三列组件
    JLabel label3 = new JLabel("转换后编码");
    JComboBox cmb1=new JComboBox();    //创建JComboBox
    //第四列组件
    JButton button2 = new JButton("确定");// 选择
    JLabel label5 = new JLabel();

    JFileChooser jfc = new JFileChooser();// 文件选择器

    public SecondUI(){
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
        button2.setBounds(180, 210, 70, 20);
        label5.setBounds(270, 210, 500, 20);

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
        con.add(label5);
        con.add(button2);
        //添加事件
        button1.addActionListener(this);
        button2.addActionListener(this);

    }

    public void actionPerformed(ActionEvent e) {
        // 绑定到选择文件，先择文件事件
        if (e.getSource().equals(button1)) {
            jfc.setFileSelectionMode(0);// 设定只能选择到文件
            int state = jfc.showOpenDialog(null);// 此句是打开文件选择器界面的触发语句
            if (state == 1) {
                return;// 撤销则返回
            } else {
                File f = jfc.getSelectedFile();// f为选择到的文件
                filePath = f.getAbsolutePath();
                showPath.setText(f.getAbsolutePath());
            }
        }

        if (e.getSource().equals(button2)){
            SecondService service = new SecondService();
            String transcoding = service.transcoding(filePath, cmb.getSelectedItem().toString(), cmb1.getSelectedItem().toString());
            if(transcoding!=null){
                label5.setText(transcoding);
            }
            if(transcoding==null) {
                label5.setText("您没有选择任何文件");
            }
        }
    }

    public Container getCon() {
        return con;
    }
}
