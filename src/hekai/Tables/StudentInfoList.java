package hekai.Tables;

import hekai.model.Student_info;
import hekai.sql.StudentDBCon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/***********************************************************
 * 版权所有 (C)2020, hekai
 *
 * 文件名称：StudentInfoList.java
 * 文件标识：无
 * 内容摘要：该类得到数据库中的学生信息，并且将其放在ArrayList中返回
 * 其它说明：无
 * 当前版本： V1.0
 * 作   者：贺凯
 * 完成日期： 20201215
 **********************************************************/
public class StudentInfoList {
    public List<Student_info> StudentRes(){
        Connection con= StudentDBCon.getConnection();
        String s= "select * from test.student_info";
        List<Student_info> students= new ArrayList<>();
        try{
            PreparedStatement preparedStatement = con.prepareStatement(s);
            preparedStatement.execute();
            ResultSet st = preparedStatement.executeQuery();
            while (st.next()) {
                Student_info student = new Student_info();
                student.setSno(st.getString("学号"));
                student.setName(st.getString("姓名"));
                student.setSex(st.getString("性别"));
                student.setAge(st.getDouble("年龄"));
                student.setFaculty(st.getString("学院"));
                students.add(student);
            }
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
        return students;
    }
}
