package hekai.model;
/***********************************************************
 * 版权所有 (C)2020, hekai
 *
 * 文件名称：Teacher.java
 * 文件标识：无
 * 内容摘要：模型类，用来存取教师表中的数据
 * 其它说明：无
 * 当前版本： V1.0
 * 作   者：贺凯
 * 完成日期： 20201215
 **********************************************************/
public class Teacher {
    private String Tno;
    private String Tname;
    private String Tfaculty;
    public String getTno() {
        return Tno;
    }
    public void setTno(String tno) {
        Tno = tno;
    }
    public String getTname() {
        return Tname;
    }
    public void setTname(String tname) {
        Tname = tname;
    }
    public String getTfaculty() {
        return Tfaculty;
    }
    public void setTfaculty(String tfaculty) {
        Tfaculty = tfaculty;
    }
}
