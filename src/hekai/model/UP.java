package hekai.model;
/***********************************************************
 * 版权所有 (C)2020, hekai
 *
 * 文件名称：UP.java
 * 文件标识：无
 * 内容摘要：该类用来定义一个用户类,用户的属性包含用户名和密码
 * 其它说明：UP--UserPassword
 * 当前版本： V1.0
 * 作   者：贺凯
 * 完成日期： 20201115
 **********************************************************/
public class UP {
    private String user;
    private String password;

    public void setUser(String user) {
        this.user = user;
    }

    public String getUser() {
        return user;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
