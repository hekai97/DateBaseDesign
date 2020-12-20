package hekai.Tables;

import hekai.model.Teacher;
import hekai.sql.AdminDBCon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeacherList {
    public List<Teacher> TeacherRes(){
        Connection con= AdminDBCon.getConnection();
        String s= "select * from test.teacher";
        List<Teacher> teachers= new ArrayList<>();
        try{
            PreparedStatement preparedStatement = con.prepareStatement(s);
            preparedStatement.execute();
            ResultSet st = preparedStatement.executeQuery();
            while (st.next()) {
                Teacher teacher = new Teacher();
                teacher.setTno(st.getString("Tno"));
                teacher.setTname(st.getString("Tname"));
                teacher.setTfaculty(st.getString("Tfaculty"));
                teachers.add(teacher);
            }
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
        return teachers;
    }
}
