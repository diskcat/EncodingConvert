package cn.cslg;

import cn.cslg.UI.FirstUI;
import cn.cslg.UI.SecondUI;
import cn.cslg.UI.ThirdUI;

import javax.swing.*;
import java.awt.*;

public class MainUI {
    private JFrame frame = new JFrame("编码转换");// 框架布局
    private JTabbedPane tabPane = new JTabbedPane();
    public MainUI() {
        double lx = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double ly = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        frame.setLocation(new Point((int) (lx / 2) - 150, (int) (ly / 2) - 150));// 设定窗口出现位置
        frame.setSize(720, 480);// 设定窗口大小
        frame.setContentPane(tabPane);// 设置布局
        tabPane.addTab("项目转换",new FirstUI().getCon());
        tabPane.addTab("文件转换",new SecondUI().getCon());

        frame.setVisible(true);// 窗口可见
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 使能关闭窗口，结束程序
    }

    public static void main(String[] args) {
        new MainUI();
    }
}
