package hekai.model;
/***********************************************************
 * 版权所有 (C)2020, hekai
 *
 * 文件名称：Grade.java
 * 文件标识：无
 * 内容摘要：模型类，用来存取成绩表中的数据
 * 其它说明：无
 * 当前版本： V1.0
 * 作   者：贺凯
 * 完成日期： 20201215
 **********************************************************/
public class Grade {
    private String Sno;
    private String Cno;
    private String grade;
    public String getSno() {
        return Sno;
    }
    public void setSno(String sno) {
        Sno = sno;
    }
    public String getCno() {
        return Cno;
    }
    public void setCno(String con) {
        Cno = con;
    }
    public String getGrade() {
        return grade;
    }
    public void setGrade(String grade) {
        this.grade = grade;
    }
}
