package hekai.Window;

import hekai.sql.AdminDBCon;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
/***********************************************************
 * 版权所有 (C)2020, hekai
 *
 * 文件名称：Information.java
 * 文件标识：无
 * 内容摘要：该类单独显示学生的详细信息
 * 其它说明：无
 * 当前版本： V1.0
 * 作   者：贺凯
 * 完成日期： 20201215
 **********************************************************/
public class Information extends JPanel{
    JLabel spaceLabel=new JLabel("    ");
    JLabel snoLabel;
    JLabel nameLabel;
    JLabel sexLabel;
    JLabel ageLabel;
    JLabel idLabel;
    JLabel classLabel;
    JLabel facultyLabel;
    public Information(String id, JMenuItem item){
        setLayout(new FlowLayout(FlowLayout.CENTER,1000,000));
        Connection con= AdminDBCon.getConnection();
        String sql1="select * from test.student where Sno="+id;
        try {
            PreparedStatement statement=con.prepareStatement(sql1);
            statement.execute();
            ResultSet st=statement.executeQuery();
            while(st.next()){
                snoLabel=new JLabel("学号："+st.getString("Sno"));
                nameLabel=new JLabel("姓名："+st.getString("Sname"));
                sexLabel=new JLabel(Integer.parseInt(st.getString("Sid").substring(16,17))%2==0?"性别：女":"性别：男");
                ageLabel=new JLabel("年龄："+ (Calendar.getInstance().get(Calendar.YEAR)-Integer.parseInt(st.getString("Sid").substring(6,10))));
                idLabel=new JLabel("身份证号："+st.getString("Sid"));
                classLabel=new JLabel("班级："+st.getString("Sclassname"));
                facultyLabel=new JLabel("学院："+getFacultyName(st.getString("Sfaculty")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        spaceLabel.setFont(new Font("",Font.BOLD,160));
        snoLabel.setFont(new Font("",Font.BOLD,30));
        nameLabel.setFont(new Font("",Font.BOLD,30));
        sexLabel.setFont(new Font("",Font.BOLD,30));
        ageLabel.setFont(new Font("",Font.BOLD,30));
        idLabel.setFont(new Font("",Font.BOLD,30));
        classLabel.setFont(new Font("",Font.BOLD,30));
        facultyLabel.setFont(new Font("",Font.BOLD,30));
        add(spaceLabel);
        add(snoLabel);
        add(nameLabel);
        add(sexLabel);
        add(ageLabel);
        add(idLabel);
        add(classLabel);
        add(facultyLabel);
    }
    private String getFacultyName(String Fno){
        String s = null;
        Connection con=AdminDBCon.getConnection();
        String sql="select Fname from test.faculty where Fno="+Fno;
        try {
            PreparedStatement preparedStatement=con.prepareStatement(sql);
            preparedStatement.execute();
            ResultSet st=preparedStatement.executeQuery();
            while(st.next()){
                s=st.getString("Fname");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return s;
    }
}
