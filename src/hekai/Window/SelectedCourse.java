package hekai.Window;

import hekai.Tables.CourseList;
import hekai.Tables.OptionalCourseList;
import hekai.model.Course;
import hekai.sql.AdminDBCon;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class SelectedCourse extends JPanel{
    JLabel title=new JLabel("所选课程",JLabel.CENTER);
    DefaultTableModel dtm;
    JTable table;
    JScrollPane jScrollPane;
    JButton selectButton=new JButton("退课");
    public SelectedCourse(String id, JMenuItem item){
        title.setFont(new Font("",Font.BOLD,20));
        setLayout(new BorderLayout());
        /*OptionalCourseList optionalCourseList=new OptionalCourseList();
        List<Course> list=optionalCourseList.CourseRes(id);*/
        CourseList courseList=new CourseList();
        List<Course> list=courseList.CourseRes(false,id);
        String[] name={"课程号","课程名","任课教师","学分","学时"};
        Object[][] res=new Object[list.size()][name.length];
        for(int i=0;i<list.size();i++){
            Course course1=list.get(i);
            res[i][0]=course1.getCno();
            res[i][1]=course1.getCname();
            res[i][2]=course1.getCtno();
            res[i][3]=course1.getCcredit();
            res[i][4]=course1.getCtime();
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
        selectButton.addActionListener(e-> {
            if (JOptionPane.showConfirmDialog(null, "确认退课？") == 0) {

                String sql = "delete from test.grade where Sno=? And Cno=?";
                try {
                    PreparedStatement preparedStatement = con.prepareStatement(sql);
                    preparedStatement.setString(1, id);
                    preparedStatement.setString(2, (String) dtm.getValueAt(table.getSelectedRow(), 0));
                    preparedStatement.execute();
                    item.doClick();
                    JOptionPane.showMessageDialog(null, "退课成功");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
        JPanel panel=new JPanel();
        panel.add(selectButton);
        add(panel,BorderLayout.SOUTH);
    }
}
