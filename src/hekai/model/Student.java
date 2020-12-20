package hekai.model;

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
