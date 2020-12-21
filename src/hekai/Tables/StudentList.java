package hekai.Tables;

import hekai.model.Student;
import hekai.sql.AdminDBCon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentList {
    public List<Student> StudentRes(){
        Connection con= AdminDBCon.getConnection();
        String s= "select * from test.student";
        List<Student> students= new ArrayList<>();
        try{
            PreparedStatement preparedStatement = con.prepareStatement(s);
            preparedStatement.execute();
            ResultSet st = preparedStatement.executeQuery();
            while (st.next()) {
                Student student = new Student();
                student.setSno(st.getString("Sno"));
                student.setSname(st.getString("Sname"));
                student.setSid(st.getString("Sid"));
                student.setSclassname(st.getString("Sclassname"));
                student.setSfaculty(st.getString("Sfaculty"));
                students.add(student);
            }
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
        return students;
    }
}
