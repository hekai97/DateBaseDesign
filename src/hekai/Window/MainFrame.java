package hekai.Window;

import javax.swing.*;
import java.awt.*;

public class MainFrame{
    JFrame frame=new JFrame("教务系统");
    JMenuBar jMenuBar=new JMenuBar();
    JMenu filemenu=new JMenu("文件");
    JMenuItem saveasItem=new JMenuItem("另存为");
    JMenuItem exitItem=new JMenuItem("退出");
    //JMenu Operatemenu=new JMenu("操作");
    //JMenuItem IncreaseItem=new JMenuItem("添加");
    //JMenuItem DeleteItem=new JMenuItem("删除");
    //JMenuItem UpdateItem=new JMenuItem("更改");
    JMenu Inquire=new JMenu("查询");
    JMenu helpmenu=new JMenu("帮助");
    JMenuItem chatItem=new JMenuItem("反馈");
    JMenuItem descriptionItem=new JMenuItem("说明");
    public MainFrame(){
        frame.setSize(1000,800);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        jMenuBar.add(filemenu);
        //jMenuBar.add(Operatemenu);
        jMenuBar.add(Inquire);
        jMenuBar.add(helpmenu);
        filemenu.add(saveasItem);
        filemenu.add(exitItem);
        exitItem.addActionListener(e-> {
            frame.dispose();
            Login login=new Login();
            login.CreateWindow();
        });
        //Operatemenu.add(IncreaseItem);
        //Operatemenu.add(DeleteItem);
        //Operatemenu.add(UpdateItem);
        //Operatemenu.add(Inquire);
        helpmenu.add(chatItem);
        helpmenu.add(descriptionItem);
        frame.setJMenuBar(jMenuBar);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new MainFrame();
    }
}