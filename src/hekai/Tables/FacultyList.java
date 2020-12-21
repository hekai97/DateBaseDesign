package hekai.Tables;

import hekai.model.Faculty;
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
 * 文件名称：Faculty.java
 * 文件标识：无
 * 内容摘要：该类返回从学院表中得到数据，将其作为数组返回
 * 其它说明：无
 * 当前版本： V1.0
 * 作   者：贺凯
 * 完成日期： 20201215
 **********************************************************/
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
