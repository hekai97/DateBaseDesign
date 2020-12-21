package hekai.Window;

import hekai.Tables.GradeList;
import hekai.model.Grade;
import hekai.sql.AdminDBCon;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
/***********************************************************
 * 版权所有 (C)2020, hekai
 *
 * 文件名称：StudentGrade.java
 * 文件标识：无
 * 内容摘要：该类根据老师和学生的身份，分别返回不同的成绩表
 * 在学生窗口中显示的是课程名加成绩，在教师窗口中显示的是学生名加窗口
 * 其它说明：无
 * 当前版本： V1.0
 * 作   者：贺凯
 * 完成日期： 20201215
 **********************************************************/
public class StudentGrade extends JPanel {
    JLabel title=new JLabel("所选课程成绩",JLabel.CENTER);
    DefaultTableModel dtm;
    JTable table;
    JScrollPane jScrollPane;
    JButton updatebutton=new JButton("确认修改");
    public StudentGrade(String id){
        title.setFont(new Font("",Font.BOLD,20));
        setLayout(new BorderLayout());
        GradeList gradeList=new GradeList();
        List<Grade> list=gradeList.GradeResForSutdent(id);
        String[] name={"课程号","成绩"};
        Object[][] res=new Object[list.size()][name.length];
        for(int i=0;i<list.size();i++){
            Grade grade=list.get(i);
            res[i][0]=grade.getCno();
            res[i][1]=grade.getGrade();
        }
        dtm=new DefaultTableModel(res,name);
        table=new JTable(dtm);
        add(title,BorderLayout.NORTH);
        jScrollPane=new JScrollPane(table);
        jScrollPane.setViewportView(table);
        add(jScrollPane,BorderLayout.CENTER);
        table.setEnabled(false);
    }
    public StudentGrade(String id,JMenuItem item){
        title.setFont(new Font("",Font.BOLD,20));
        setLayout(new BorderLayout());
        GradeList gradeList=new GradeList();
        List<Grade> list=gradeList.GradeResForTeacher(id);
        String[] name={"姓名","成绩"};
        Object[][] res=new Object[list.size()][name.length];
        for(int i=0;i<list.size();i++){
            Grade grade=list.get(i);
            res[i][0]=grade.getSno();
            res[i][1]=grade.getGrade();
        }
        dtm=new DefaultTableModel(res,name);
        table=new JTable(dtm);
        add(title,BorderLayout.NORTH);
        jScrollPane=new JScrollPane(table);
        jScrollPane.setViewportView(table);
        add(jScrollPane,BorderLayout.CENTER);
        AddToSouth(res,item,id);
    }
    private void AddToSouth(Object[][] res,JMenuItem item,String id){
        Connection con= AdminDBCon.getConnection();
        updatebutton.addActionListener(e -> {
            String sql="update test.grade set "
                    +"grade='"+dtm.getValueAt(table.getSelectedRow(),1)+"' "
                    +"where Sno=(select Sno from test.student where Sname='"+res[table.getSelectedRow()][0]+"')"
                    +"AND Cno=(select Cno from test.course where Ctno="+id+")";
            try {
                PreparedStatement statement=con.prepareStatement(sql);
                statement.execute();
                item.doClick();
                JOptionPane.showMessageDialog(null,"修改成功");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        JPanel panel=new JPanel();
        panel.add(updatebutton);
        add(panel,BorderLayout.SOUTH);
    }
}
