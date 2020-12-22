package hekai.model;
/***********************************************************
 * 版权所有 (C)2020, hekai
 *
 * 文件名称：Student.java
 * 文件标识：无
 * 内容摘要：模型类，用来存取学生表中的数据
 * 其它说明：无
 * 当前版本： V1.0
 * 作   者：贺凯
 * 完成日期： 20201215
 **********************************************************/
public class Student {
    private String Sno;
    private String Sname;
    private String Sid;
    private String Sclassname;
    private String Sfaculty;
    public void setSno(String sno) {
        Sno = sno;
    }
    public String getSno() {
        return Sno;
    }
    public void setSname(String sname) {
        Sname = sname;
    }
    public String getSname() {
        return Sname;
    }
    public void setSid(String sid) {
        Sid = sid;
    }
    public String getSid() {
        return Sid;
    }
    public void setSclassname(String sclassname) {
        Sclassname = sclassname;
    }
    public void setSfaculty(String sfaculty) {
        Sfaculty = sfaculty;
    }
    public String getSfaculty() {
        return Sfaculty;
    }
    public String getSclassname() {
        return Sclassname;
    }
}
