package hekai.model;
/***********************************************************
 * 版权所有 (C)2020, hekai
 *
 * 文件名称：Faculty.java
 * 文件标识：无
 * 内容摘要：模型类，用来存取学院表中的数据
 * 其它说明：无
 * 当前版本： V1.0
 * 作   者：贺凯
 * 完成日期： 20201215
 **********************************************************/
public class Faculty {
    private String Fno;
    private String Fname;
    public String getFno() {
        return Fno;
    }
    public void setFno(String fno) {
        Fno = fno;
    }
    public String getFname() {
        return Fname;
    }
    public void setFname(String fname) {
        Fname = fname;
    }
}
