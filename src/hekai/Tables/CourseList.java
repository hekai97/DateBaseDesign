package hekai.Tables;

import hekai.model.Course;
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
 * 文件名称：CourseList.java
 * 文件标识：无
 * 内容摘要：该类返回从课程表中得到数据，将其作为数组返回
 * 其它说明：无
 * 当前版本： V1.0
 * 作   者：贺凯
 * 完成日期： 20201215
 **********************************************************/
public class CourseList {
    public List<Course> CourseRes(){
        Connection con= AdminDBCon.getConnection();
        String s= "select * from test.Course";
        List<Course> courses= new ArrayList<>();
        try{
            PreparedStatement preparedStatement = con.prepareStatement(s);
            preparedStatement.execute();
            ResultSet st = preparedStatement.executeQuery();
            while (st.next()) {
                Course course = new Course();
                course.setCno(st.getString("Cno"));
                course.setCname(st.getString("Cname"));
                course.setCtno(st.getString("Ctno"));
                course.setCcredit(st.getString("Ccredit"));
                course.setCtime(st.getString("Ctime"));
                courses.add(course);
            }
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
        return courses;
    }
    public List<Course> CourseRes(boolean res,String id){
        Connection con= AdminDBCon.getConnection();
        String s;
        if(res) {
             s= "select Cno,Cname,Tname,Ccredit,Ctime from test.Course,test.teacher"
                     + " where Cno not in (select test.grade.Cno from test.grade where Sno="+id
                     + ") AND course.Ctno =teacher.Tno" ;
        }
        else{
            s="select Cno,Cname,Tname,Ccredit,Ctime from test.Course,test.teacher"
                    + " where Cno in (select test.grade.Cno from test.grade where Sno="+id
                    + ") AND course.Ctno =teacher.Tno" ;
        }
        return getCourses(con, s);
    }

    static List<Course> getCourses(Connection con, String s) {
        List<Course> courses= new ArrayList<>();
        try{
            PreparedStatement preparedStatement = con.prepareStatement(s);
            preparedStatement.execute();
            ResultSet st = preparedStatement.executeQuery();
            while (st.next()) {
                Course course = new Course();
                course.setCno(st.getString("Cno"));
                course.setCname(st.getString("Cname"));
                course.setCtno(st.getString("Tname"));
                course.setCcredit(st.getString("Ccredit"));
                course.setCtime(st.getString("Ctime"));
                courses.add(course);
            }
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
        return courses;
    }
}
