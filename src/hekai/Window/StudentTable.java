package hekai.Window;

import hekai.model.Student;
import hekai.sql.AdminDBCon;
import hekai.Tables.StudentList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Vector;

public class StudentTable extends JPanel {
    JLabel title=new JLabel("学生信息表",JLabel.CENTER);
    JTable jTable;
    JScrollPane jScrollPane;
    DefaultTableModel defaultTableModel;
    JButton newRowbutton=new JButton("新建一行");
    //JButton inseretTopbutton=new JButton("上方插入一行");
    //JButton insertDownbutton=new JButton("下方插入一行");
    JButton deletebutton=new JButton("删除");
    JButton updatebutton=new JButton("确认修改");
    public StudentTable(JMenuItem item){
        title.setFont(new Font("",Font.BOLD,30));
        setLayout(new BorderLayout());
        StudentList studentList =new StudentList();
        List<Student> list= studentList.StudentRes();
        String[] name={"学号","姓名","身份证号码","班级","院系"};
        Object[][] res=new Object[list.size()][name.length];
        for(int i=0;i<list.size();i++){
            Student student1=list.get(i);
            res[i][0]=student1.getSno();
            res[i][1]=student1.getSname();
            res[i][2]=student1.getSid();
            res[i][3]=student1.getSclassname();
            res[i][4]=student1.getSfaculty();
        }
        defaultTableModel=new DefaultTableModel(res,name);
        jTable=new JTable(defaultTableModel);
        //jTable.setRowHeight(50);
        add(title,BorderLayout.NORTH);
        jScrollPane=new JScrollPane(jTable);
        jScrollPane.setViewportView(jTable);
        add(jScrollPane,BorderLayout.CENTER);
        AddToSouth(res,item);
    }
    private void AddToSouth(Object[][] res,JMenuItem item){
        Connection con=AdminDBCon.getConnection();
        deletebutton.addActionListener(e -> {
            int[] selectedRows = jTable.getSelectedRows();
            //Connection con= AdminDBCon.getConnection();
            if(JOptionPane.showConfirmDialog(null,"确认删除？")==0) {
                if (selectedRows.length > 0) {
                    for (int i = selectedRows.length - 1; i >= 0; i--) {
                        try {
                            String s = "delete from student where Sno=" + defaultTableModel.getValueAt(selectedRows[i], 0);
                            PreparedStatement statement = con.prepareStatement(s);
                            statement.execute();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        defaultTableModel.removeRow(selectedRows[i]);
                    }
                    item.doClick();
                    JOptionPane.showMessageDialog(null, "删除成功");
                }
            }
        });
        newRowbutton.addActionListener(e-> defaultTableModel.addRow(new Vector<>()));
        /*inseretTopbutton.addActionListener(e -> {
            try {
                defaultTableModel.insertRow(jTable.getSelectedRow(), new Object[]{});
            }catch (ArrayIndexOutOfBoundsException ex){
                JOptionPane.showMessageDialog(null,"未选中行");
            }
        });*/
        /*alter+enter确定插入数据****/
        jTable.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if(e.isAltDown()&&e.getKeyCode()==KeyEvent.VK_ENTER){
                    String s="insert into Student values(?,?,?,?,?)";
                    try {
                        PreparedStatement statement=con.prepareStatement(s);
                        statement.setString(1,(String)defaultTableModel.getValueAt(jTable.getSelectedRow(),0));
                        statement.setString(2,(String)defaultTableModel.getValueAt(jTable.getSelectedRow(),1));
                        statement.setString(3,(String)defaultTableModel.getValueAt(jTable.getSelectedRow(),2));
                        statement.setString(4,(String)defaultTableModel.getValueAt(jTable.getSelectedRow(),3));
                        statement.setString(5,(String)defaultTableModel.getValueAt(jTable.getSelectedRow(),4));
                        statement.execute();
                        item.doClick();
                        JOptionPane.showMessageDialog(null,"插入成功");
                    } catch (SQLIntegrityConstraintViolationException ex) {
                        JOptionPane.showMessageDialog(null,"错误\n1.检查学号是否重复\n2.检查是否有空值");
                    }catch (SQLException ey){
                        JOptionPane.showMessageDialog(null,"输入数据不符合规范");
                    }
                }
            }
        });
        /*insertDownbutton.addActionListener(e -> {
            try {
                defaultTableModel.insertRow(jTable.getSelectedRow()+1, new Object[]{});
            }catch (ArrayIndexOutOfBoundsException ex){
                JOptionPane.showMessageDialog(null,"未选中行");
            }
        });*/
        updatebutton.addActionListener(e -> {
            try {
            String sql="update student set "
                    +"Sno='" +defaultTableModel.getValueAt(jTable.getSelectedRow(),0)+"',"
                    +"Sname='"+defaultTableModel.getValueAt(jTable.getSelectedRow(),1)+"',"
                    +"Sid='"+defaultTableModel.getValueAt(jTable.getSelectedRow(),2)+"',"
                    +"Sclassname='"+defaultTableModel.getValueAt(jTable.getSelectedRow(),3)+"',"
                    +"Sfaculty='"+defaultTableModel.getValueAt(jTable.getSelectedRow(),4)+"' "
                    +"where Sno="+/*defaultTableModel.getValueAt(jTable.getSelectedRow(),0)*/res[jTable.getSelectedRow()][0];
                PreparedStatement statement=con.prepareStatement(sql);
                statement.execute();
                JOptionPane.showMessageDialog(null,"修改成功");

            }/*/catch (ArrayIndexOutOfBoundsException ex){
              JOptionPane.showMessageDialog(null,"请刷新");
            } */catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        JPanel panel=new JPanel();
        panel.add(newRowbutton);
        /*panel.add(inseretTopbutton);
        panel.add(insertDownbutton);*/
        panel.add(deletebutton);
        panel.add(updatebutton);
        add(panel,BorderLayout.SOUTH);
    }
}

