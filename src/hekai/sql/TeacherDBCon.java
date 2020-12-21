package hekai.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/***********************************************************
 * 版权所有 (C)2020, hekai
 *
 * 文件名称：TeacherDBCon.java
 * 文件标识：无
 * 内容摘要：该类实现了teacher用户数据库的连接
 * 其它说明：无
 * 当前版本： V1.0
 * 作   者：贺凯
 * 完成日期： 20201215
 **********************************************************/
public class TeacherDBCon {
    private static final String driver="com.mysql.cj.jdbc.Driver";
    //定义常量数据库的url
    private static final String url="jdbc:mysql://localhost:3306/test?serverTimezone=UTC";
    //定义数据库的用户名和密码
    private static final String user="teacher";
    private static final String password="hk19990707";
    //定义一个连接
    private static Connection connection=null;
    //静态代码块连接数据库，加载驱动
    //静态方法只执行一次，用于初始化变量
    static {
        try {
            Class.forName(driver);
            connection= DriverManager.getConnection(url,user,password);
        }catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }
    }
    //定义一个静态方法，返回数据库的连接
    public static Connection getConnection(){
        return connection;
    }
}
