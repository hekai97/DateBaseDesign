package hekai.Window;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TeacherFrame extends MainFrame{
    JMenuItem item1=new JMenuItem("录入学生成绩");
    JMenuItem item2=new JMenuItem("个人信息");
    public TeacherFrame(String id){
        Inquire.add(item1);
        ActionListener actionListener= e -> {
            if(e.getSource()==item1){
                showGradeTable(id);
            }
            if(e.getSource()==item2){

            }
        };
    }
    private void showGradeTable(String id){

    }
    public static void main(String[] args) {
        new TeacherFrame("200001010101");
    }
}
