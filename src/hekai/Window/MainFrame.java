package hekai.Window;

import javax.swing.*;
import java.awt.*;
/***********************************************************
 * 版权所有 (C)2020, hekai
 *
 * 文件名称：MainFrame.java
 * 文件标识：无
 * 内容摘要：这是主窗口，之后的管理员窗口以及学生，老师的窗口都是继承该窗口
 * 其它说明：无
 * 当前版本： V1.0
 * 作   者：贺凯
 * 完成日期： 20201215
 **********************************************************/
public class MainFrame{
    JFrame frame=new JFrame("教务系统");
    JMenuBar jMenuBar=new JMenuBar();
    JMenu filemenu=new JMenu("文件");
    //JMenuItem saveasItem=new JMenuItem("另存为");
    JMenuItem loginItem=new JMenuItem("返回登录界面");
    JMenuItem exitItem=new JMenuItem("退出");
    JMenu Inquire=new JMenu("查询");
    //JMenu helpmenu=new JMenu("帮助");
    //JMenuItem chatItem=new JMenuItem("反馈");
    //JMenuItem descriptionItem=new JMenuItem("说明");
    public MainFrame(){
        frame.setSize(1000,800);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        jMenuBar.add(filemenu);
        jMenuBar.add(Inquire);
        //jMenuBar.add(helpmenu);
        filemenu.add(loginItem);
        filemenu.add(exitItem);
        loginItem.addActionListener(e->{
            frame.dispose();
            Login login=new Login();
            login.CreateWindow();
        });
        exitItem.addActionListener(e-> System.exit(0));
        //helpmenu.add(chatItem);
        /*chatItem.addActionListener(e->{
            Chat chat=new Chat();
            chat.startUp();
        });*/
        //helpmenu.add(descriptionItem);
        frame.setJMenuBar(jMenuBar);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new MainFrame();
    }
}