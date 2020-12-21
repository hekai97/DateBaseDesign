package hekai.Tables;

import hekai.model.Grade;
import hekai.sql.AdminDBCon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/***********************************************************
 * 版权所有 (C)2020, hekai
 *
 * 文件名称：GradeList.java
 * 文件标识：无
 * 内容摘要：该类根据不同的用户从数据库中筛选出不同的成绩值，然后将其返回
 * 其它说明：无
 * 当前版本： V1.0
 * 作   者：贺凯
 * 完成日期： 20201215
 **********************************************************/
public class GradeList {
    //返回表的完整结构
    public List<Grade> GradeRes(){
        Connection con= AdminDBCon.getConnection();
        String s= "select * from test.grade";
        List<Grade> grades= new ArrayList<>();
        try{
            PreparedStatement preparedStatement = con.prepareStatement(s);
            preparedStatement.execute();
            ResultSet st = preparedStatement.executeQuery();
            while (st.next()) {
                Grade grade = new Grade();
                grade.setSno(st.getString("Sno"));
                grade.setCno(st.getString("Cno"));
                grade.setGrade(st.getString("grade"));
                grades.add(grade);
            }
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
        return grades;
    }
    public List<Grade> GradeResForSutdent(String id){
        Connection con= AdminDBCon.getConnection();
        String s= "select Cname,grade from test.course,test.grade where grade.Cno=course.Cno AND grade.Sno="+id;
        List<Grade> grades= new ArrayList<>();
        try{
            PreparedStatement preparedStatement = con.prepareStatement(s);
            preparedStatement.execute();
            ResultSet st = preparedStatement.executeQuery();
            while (st.next()) {
                Grade grade = new Grade();
                grade.setCno(st.getString("Cname"));
                grade.setGrade(st.getString("grade"));
                grades.add(grade);
            }
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
        return grades;
    }
    public List<Grade> GradeResForTeacher(String id){
        Connection con= AdminDBCon.getConnection();
        String s= "select Sname,grade from test.student,test.grade " +
                "where grade.Cno in (" +
                "select Cno from test.course where course.Ctno=" +id+")"+
                "AND student.Sno=grade.Sno";
        List<Grade> grades= new ArrayList<>();
        try{
            PreparedStatement preparedStatement = con.prepareStatement(s);
            preparedStatement.execute();
            ResultSet st = preparedStatement.executeQuery();
            while (st.next()) {
                Grade grade = new Grade();
                grade.setSno(st.getString("Sname"));
                grade.setGrade(st.getString("grade"));
                grades.add(grade);
            }
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
        return grades;
    }
}
