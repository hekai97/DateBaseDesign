package hekai.Window;

import javax.swing.*;
import java.awt.event.ActionListener;
/***********************************************************
 * 版权所有 (C)2020, hekai
 *
 * 文件名称：TeacherFrame.java
 * 文件标识：无
 * 内容摘要：该类是教师登录后创建的窗口
 * 其它说明：无
 * 当前版本： V1.0
 * 作   者：贺凯
 * 完成日期： 20201215
 **********************************************************/
public class TeacherFrame extends MainFrame{
    JMenuItem item1=new JMenuItem("录入学生成绩");
    JMenuItem item2=new JMenuItem("个人信息");
    public TeacherFrame(String id){
        ActionListener actionListener= e -> {
            if(e.getSource()==item1){
                showGradeTable(id);
            }
            if(e.getSource()==item2){

            }
        };
        Inquire.add(item1);
        item1.addActionListener(actionListener);
    }
    private void showGradeTable(String id){
        frame.setContentPane(new StudentGrade(id,item1));
        frame.setVisible(true);
    }
    public static void main(String[] args) {
        new TeacherFrame("201011221452");
    }
}
