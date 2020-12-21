package hekai.model;
/***********************************************************
 * 版权所有 (C)2020, hekai
 *
 * 文件名称：Course.java
 * 文件标识：无
 * 内容摘要：模型类，用来存取课程表中的数据
 * 其它说明：无
 * 当前版本： V1.0
 * 作   者：贺凯
 * 完成日期： 20201215
 **********************************************************/
public class Course {
    private String Cno;
    private String Cname;
    private String Ctno;
    private String Ccredit;
    private String Ctime;

    public String getCno() {
        return Cno;
    }

    public void setCno(String cno) {
        Cno = cno;
    }

    public String getCname() {
        return Cname;
    }

    public void setCname(String cname) {
        Cname = cname;
    }

    public String getCtno() {
        return Ctno;
    }

    public void setCtno(String ctno) {
        Ctno = ctno;
    }

    public String getCcredit() {
        return Ccredit;
    }

    public void setCcredit(String ccredit) {
        Ccredit = ccredit;
    }

    public String getCtime() {
        return Ctime;
    }

    public void setCtime(String ctime) {
        Ctime = ctime;
    }
}
