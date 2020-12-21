package hekai.Window;

import javax.swing.*;
import java.awt.event.ActionListener;

public class StudentFrame extends MainFrame{
    JMenuItem item1=new JMenuItem("查询可选课程");
    JMenuItem item2=new JMenuItem("查询已选课程");
    JMenuItem item3=new JMenuItem("查看个人成绩");
    JMenuItem item4 =new JMenuItem("个人信息");
    public StudentFrame(String id){
        ActionListener actionListener= e -> {
            if(e.getSource()==item1){
                showOptionalCourse(id);
            }
            if(e.getSource()==item2){
                showSelectedCourse(id);
            }
            if(e.getSource()==item3){
                showGrade(id);
            }
            if(e.getSource()== item4){
                showInformation(id);
            }
        };
        Inquire.add(item1);
        Inquire.add(item2);
        Inquire.add(item3);
        Inquire.add(item4);
        item1.addActionListener(actionListener);
        item2.addActionListener(actionListener);
        item3.addActionListener(actionListener);
        item4.addActionListener(actionListener);
    }
    public void showOptionalCourse(String id){
        frame.setContentPane(new OptionalCourse(id,item1));
        frame.setVisible(true);
    }
    public void showSelectedCourse(String id){
        frame.setContentPane(new SelectedCourse(id,item2));
        frame.setVisible(true);
    }
    public void showGrade(String id){
        frame.setContentPane(new StudentGrade(id,item3));
        frame.setVisible(true);
    }
    public void showInformation(String id){
        frame.setContentPane(new Information(id, item4));
        frame.setVisible(true);
    }


    public static void main(String[] args) {
        new StudentFrame("201958503103");
    }
}
