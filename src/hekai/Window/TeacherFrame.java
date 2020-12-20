package hekai.Window;

import javax.swing.*;

public class TeacherFrame extends MainFrame{
    JMenuItem item1=new JMenuItem("录入学生成绩");
    public TeacherFrame(){
        Inquire.add(item1);
    }

    public static void main(String[] args) {
        new TeacherFrame();
    }
}
