package hekai.Window;

import hekai.model.Grade;
import hekai.sql.AdminDBCon;
import hekai.Tables.GradeList;

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

public class GradeTable extends JPanel {
    JLabel title=new JLabel("学生成绩表",JLabel.CENTER);
    JTable jTable;
    JScrollPane jScrollPane;
    DefaultTableModel defaultTableModel;
    JButton newRowbutton=new JButton("新建一行");
    JButton deletebutton=new JButton("删除");
    JButton updatebutton=new JButton("确认修改");
    public GradeTable(JMenuItem item){
        title.setFont(new Font("",Font.BOLD,30));
        setLayout(new BorderLayout());
        GradeList gradeList=new GradeList();
        List<Grade> list= gradeList.GradeRes();
        String[] name={"学号","课程号","成绩"};
        Object[][] res=new Object[list.size()][name.length];
        for(int i=0;i<list.size();i++){
            Grade grade1=list.get(i);
            res[i][0]=grade1.getSno();
            res[i][1]=grade1.getCon();
            res[i][2]=grade1.getGrade();
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
        Connection con= AdminDBCon.getConnection();
        deletebutton.addActionListener(e -> {
            int[] selectedRows = jTable.getSelectedRows();
            if(JOptionPane.showConfirmDialog(null,"确认删除？")==0) {
                if (selectedRows.length > 0) {
                    for (int i = selectedRows.length - 1; i >= 0; i--) {
                        try {
                            String s = "delete from test.grade where Sno=" + defaultTableModel.getValueAt(selectedRows[i], 0);
                            PreparedStatement statement = con.prepareStatement(s);
                            statement.execute();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        defaultTableModel.removeRow(selectedRows[i]);
                    }
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
                    String s="insert into test.grade values(?,?,?)";
                    try {
                        PreparedStatement statement=con.prepareStatement(s);
                        statement.setString(1,(String)defaultTableModel.getValueAt(jTable.getSelectedRow(),0));
                        statement.setString(2,(String)defaultTableModel.getValueAt(jTable.getSelectedRow(),1));
                        statement.setString(3,(String)defaultTableModel.getValueAt(jTable.getSelectedRow(),2));
                        statement.execute();
                        item.doClick();
                        JOptionPane.showMessageDialog(null,"插入成功");
                    } catch (SQLIntegrityConstraintViolationException ex) {
                        JOptionPane.showMessageDialog(null,"错误\n1.检查学号或教师编号是否重复\n2.检查是否有空值");
                    }catch (SQLException ey){
                        JOptionPane.showMessageDialog(null,"输入数据不符合规范");
                    }
                }
            }
        });
        updatebutton.addActionListener(e -> {
            String sql="update test.grade set "
                    +"Sno'" +defaultTableModel.getValueAt(jTable.getSelectedRow(),0)+"',"
                    +"Cno='"+defaultTableModel.getValueAt(jTable.getSelectedRow(),1)+"',"
                    +"grade='"+defaultTableModel.getValueAt(jTable.getSelectedRow(),2)+"' "
                    +"where Sno="+res[jTable.getSelectedRow()][0];
            try {
                PreparedStatement statement=con.prepareStatement(sql);
                statement.execute();
                JOptionPane.showMessageDialog(null,"修改成功");
            } catch (SQLException throwables) {
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
