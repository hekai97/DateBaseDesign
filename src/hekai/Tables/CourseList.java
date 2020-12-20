package hekai.Tables;

import hekai.model.Course;
import hekai.sql.AdminDBCon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
}
