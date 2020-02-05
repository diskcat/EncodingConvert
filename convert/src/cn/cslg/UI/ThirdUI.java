package cn.cslg.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.StringTokenizer;

public class ThirdUI implements ActionListener {

    Container con = new Container();
    JLabel label1 = new JLabel("转换前格式");
    JComboBox cmb = new JComboBox();
    JLabel label2 = new JLabel("转换后格式");
    JComboBox cmb1 = new JComboBox();
    JTextArea ta1 = new JTextArea();
    JTextArea ta2 = new JTextArea();
    JButton button = new JButton("转换");
    public ThirdUI(){
        con.setLayout(null);
        label1.setBounds(60,30,70,20);
        cmb.setBounds(150,30,70,20);

        label2.setBounds(460,30,70,20);
        cmb1.setBounds(550,30,70,20);
        cmb.addItem("GBK");    //向下拉列表中添加一项
        cmb.addItem("UTF-8");
        cmb.addItem("UTF-16BE");
        cmb.addItem("Unicode");

        ta1.setBounds(20,80,280,180);
        button.setBounds(320,240,70,20);
        ta2.setBounds(410,80,280,180);
        cmb1.addItem("GBK");    //向下拉列表中添加一项
        cmb1.addItem("UTF-8");
        cmb1.addItem("UTF-16BE");
        cmb1.addItem("Unicode");

        con.add(label1);
        con.add(cmb);
        con.add(label2);
        con.add(cmb1);
        con.add(ta1);
        con.add(button);
        con.add(ta2);
        button.addActionListener(this);
    }

    public Container getCon() {
        return con;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(button)){
            //1.获取转换前的字符串
            String text = ta1.getText();
            InputStream is = new ByteArrayInputStream(text.getBytes());
            OutputStream os = new ByteArrayOutputStream();
            InputStreamReader isr = null;
            OutputStreamWriter osw = null;
            String result = null;
            //2.将文本作为输入流
            try {
                isr = new InputStreamReader(new FileInputStream("E:\\java\\convert\\src\\test.txt"),cmb.getSelectedItem().toString());
                osw = new OutputStreamWriter(new FileOutputStream("E:\\java\\convert\\src\\test01.txt"),cmb1.getSelectedItem().toString());
                char[] chars = new char[1024];
                int len = 0;
                //把数据写入os输出流
                while ((len = isr.read(chars)) != -1) {
                    result = new String(chars, 0, len);
                    osw.write(chars,0,len);
                }
                System.out.println(result);
            } catch (UnsupportedEncodingException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                try {
                    isr.close();
                    osw.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
