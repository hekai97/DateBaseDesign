package hekai.Window;

import hekai.model.Teacher;
import hekai.sql.AdminDBCon;
import hekai.Tables.TeacherList;

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
/***********************************************************
 * 版权所有 (C)2020, hekai
 *
 * 文件名称：TeacherTable.java
 * 文件标识：无
 * 内容摘要：该类从教师表中得到数据，然后将其以表格的形式展示在管理员的窗口上
 * 其它说明：无
 * 当前版本： V1.0
 * 作   者：贺凯
 * 完成日期： 20201215
 **********************************************************/
public class TeacherTable extends JPanel {
    JLabel title=new JLabel("教师信息表",JLabel.CENTER);
    JTable jTable;
    JScrollPane jScrollPane;
    DefaultTableModel defaultTableModel;
    JButton newRowbutton=new JButton("新建一行");
    JButton deletebutton=new JButton("删除");
    JButton updatebutton=new JButton("确认修改");
    public TeacherTable(JMenuItem item){
        title.setFont(new Font("",Font.BOLD,30));
        setLayout(new BorderLayout());
        TeacherList teacherList =new TeacherList();
        List<Teacher> list= teacherList.TeacherRes();
        String[] name={"教师编号","姓名","院系"};
        Object[][] res=new Object[list.size()][name.length];
        for(int i=0;i<list.size();i++){
            Teacher teacher1=list.get(i);
            res[i][0]=teacher1.getTno();
            res[i][1]=teacher1.getTname();
            res[i][2]=teacher1.getTfaculty();
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
            if(JOptionPane.showConfirmDialog(null,"确认删除？")==0) {
                if (selectedRows.length > 0) {
                    for (int i = selectedRows.length - 1; i >= 0; i--) {
                        try {
                            String s = "delete from test.teacher where Tno=" + defaultTableModel.getValueAt(selectedRows[i], 0);
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
        newRowbutton.addActionListener(e->{
            defaultTableModel.addRow(new Vector<>());
        });
        /*alter+enter确定插入数据****/
        jTable.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if(e.isAltDown()&&e.getKeyCode()==KeyEvent.VK_ENTER){
                    String s="insert into test.teacher values(?,?,?)";
                    try {
                        PreparedStatement statement=con.prepareStatement(s);
                        statement.setString(1,(String)defaultTableModel.getValueAt(jTable.getSelectedRow(),0));
                        statement.setString(2,(String)defaultTableModel.getValueAt(jTable.getSelectedRow(),1));
                        statement.setString(3,(String)defaultTableModel.getValueAt(jTable.getSelectedRow(),2));
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
        updatebutton.addActionListener(e -> {
            try {
                String sql="update test.teacher set "
                        +"Tno='" +defaultTableModel.getValueAt(jTable.getSelectedRow(),0)+"',"
                        +"Tname='"+defaultTableModel.getValueAt(jTable.getSelectedRow(),1)+"',"
                        +"Tfaculty='"+defaultTableModel.getValueAt(jTable.getSelectedRow(),2)+"' "
                        +"where Tno="+res[jTable.getSelectedRow()][0];
                PreparedStatement statement=con.prepareStatement(sql);
                statement.execute();
                JOptionPane.showMessageDialog(null,"修改成功");
            }catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        JPanel panel=new JPanel();
        panel.add(newRowbutton);
        panel.add(deletebutton);
        panel.add(updatebutton);
        add(panel,BorderLayout.SOUTH);
    }
}
