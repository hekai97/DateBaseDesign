package hekai.Tables;

import hekai.model.Grade;
import hekai.sql.AdminDBCon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GradeList {
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
                grade.setCon(st.getString("Cno"));
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
