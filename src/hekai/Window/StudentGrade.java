package hekai.Window;

import hekai.Tables.GradeList;
import hekai.model.Grade;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class StudentGrade extends JPanel {
    JLabel title=new JLabel("所选课程成绩",JLabel.CENTER);
    DefaultTableModel dtm;
    JTable table;
    JScrollPane jScrollPane;
    public StudentGrade(String id,JMenuItem item){
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
    }
}
