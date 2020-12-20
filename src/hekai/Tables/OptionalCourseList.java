package hekai.Tables;

import hekai.model.Course;
import hekai.sql.AdminDBCon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OptionalCourseList {
    /*public List<Course> CourseRes(String id){
        Connection con= AdminDBCon.getConnection();
        String s= "select * from test.Course where Cno not in (select test.grade.Cno from test.grade where Sno="+id+")";
        return getCourses(con, s);
    }*/
}
