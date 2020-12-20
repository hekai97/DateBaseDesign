package hekai.Tables;

import hekai.model.Faculty;
import hekai.model.Teacher;
import hekai.sql.AdminDBCon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FacultyList {
    public List<Faculty> FacultyRes(){
        Connection con= AdminDBCon.getConnection();
        String s= "select * from test.faculty";
        List<Faculty> faculties= new ArrayList<>();
        try{
            PreparedStatement preparedStatement = con.prepareStatement(s);
            preparedStatement.execute();
            ResultSet st = preparedStatement.executeQuery();
            while (st.next()) {
                Faculty faculty=new Faculty();
                faculty.setFno(st.getString("Fno"));
                faculty.setFname(st.getString("Fname"));
                faculties.add(faculty);
            }
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
        return faculties;
    }
}
