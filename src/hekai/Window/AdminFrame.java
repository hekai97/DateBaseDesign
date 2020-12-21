package hekai.Window;

import javax.swing.*;
import java.awt.event.ActionListener;
/***********************************************************
 * 版权所有 (C)2020, hekai
 *
 * 文件名称：AdminFrame.java
 * 文件标识：无
 * 内容摘要：这个类所实现的界面是管理员登入时候的界面
 * 其它说明：无
 * 当前版本： V1.0
 * 作   者：贺凯
 * 完成日期： 20201215
 **********************************************************/
public class AdminFrame extends MainFrame{
    JMenuItem faculty=new JMenuItem("学院");
    JMenuItem course=new JMenuItem("课程");
    JMenuItem grade=new JMenuItem("成绩");
    JMenuItem student=new JMenuItem("学生");
    JMenuItem teacher=new JMenuItem("教师");
    //JMenuItem teacherUser=new JMenuItem("教师登录信息");
    //JMenuItem studentUser=new JMenuItem("学生登录信息");
    ActionListener actionListener= e -> {
        if(e.getSource()==student){
            showStudentTable();
        }
        if(e.getSource()==teacher){
            showTeacherList();
        }
        if(e.getSource()==faculty){
            showFaculty();
        }
        if(e.getSource()==course){
            showCourse();
        }
        if(e.getSource()==grade){
            showGrade();
        }
    };

    private void showStudentTable(){
        frame.setContentPane(new StudentTable(student));
        //frame.setContentPane(new JScrollPane(jTable));
        frame.setVisible(true);
    }
    private void showTeacherList(){
        frame.setContentPane(new TeacherTable(teacher));
        frame.setVisible(true);
    }
    private void showFaculty(){
        frame.setContentPane(new FacultyTable(faculty));
        frame.setVisible(true);
    }
    private void showCourse(){
        frame.setContentPane(new CourseTable(course));
        frame.setVisible(true);
    }
    private void showGrade(){
        frame.setContentPane(new GradeTable(grade));
        frame.setVisible(true);
    }

    public AdminFrame(){
        super();
        Inquire.add(course);
        Inquire.add(faculty);
        Inquire.add(grade);
        Inquire.add(student);
        //Inquire.add(studentUser);
        Inquire.add(teacher);
        //Inquire.add(teacherUser);
        student.addActionListener(actionListener);
        teacher.addActionListener(actionListener);
        faculty.addActionListener(actionListener);
        grade.addActionListener(actionListener);
        course.addActionListener(actionListener);
        //studentUser.addActionListener(actionListener);
        //teacherUser.addActionListener(actionListener);
    }

    public static void main(String[] args) {
        new AdminFrame();
    }
}
