package hekai.verify;


import hekai.model.UP;
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
 * 文件名称：UserAndPassword.java
 * 文件标识：无
 * 内容摘要：该类用来对数据库中的登录用户进行验证，验证用户名和密码等操作
 * 其它说明：无
 * 当前版本： V1.0
 * 作   者：贺凯
 * 完成日期： 20201115
 **********************************************************/
public class StudentUP {
    List<UP> list = new ArrayList<>();
    Connection con= AdminDBCon.getConnection();
    /**
     * 该方法从数据库中取值
     * 然后将取到的用户名和密码放在ArrayList中
     * */
    /**
     * 该函数的作用是用来判断传入的用户名和密码是否正确
     * 返回值：1、代表用户名正确，密码正确
     *       2、代表用户名正确但是密码错误
     *       3、代表用户名不正确
     *       */
    public int verifyUserPassword(int id,String user, String password){
        MD5encryption md5encryption=new MD5encryption();
        String sql;
        if(id==2){
            sql="select * from test.studentuser where Sno="+user;
            try {
                PreparedStatement preparedStatement=con.prepareStatement(sql);
                preparedStatement.execute();
                ResultSet st=preparedStatement.executeQuery();
                while(st.next()){
                    if(md5encryption.MD5encrypt(password).equals(st.getString("Spassword"))){
                        return 1;
                    }else {
                        return 2;
                    }
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }else{
            sql="select * from test.teacheruser where Tno="+user;
            try {
                PreparedStatement preparedStatement=con.prepareStatement(sql);
                preparedStatement.execute();
                ResultSet st=preparedStatement.executeQuery();
                while(st.next()){
                    if(md5encryption.MD5encrypt(password).equals(st.getString("Tpassword"))){
                        return 1;
                    }else {
                        return 2;
                    }
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return 3;
    }
}
