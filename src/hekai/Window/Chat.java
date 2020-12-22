package hekai.Window;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

import javax.swing.*;
/***********************************************************
 * 版权所有 (C)2020, hekai
 *
 * 文件名称：Chat.java
 * 文件标识：无
 * 内容摘要：用户进行反馈的类，该类也是变相的实现了一个Java聊天室的内容
 * 在客户端进行连接，保证服务端开启，便可以进行聊天
 * 否则提示连接失败
 * 其它说明：无
 * 当前版本： V1.0
 * 作   者：贺凯
 * 完成日期： 20201215
 **********************************************************/
public class Chat {
    private final JFrame clientFrame;
    private final JLabel sayLabel;
    private final JLabel nicknameLabel;
    private final JTextField nicknameText;
    private final JTextField sayText;
    private final JButton connectButton;
    private final JButton nicknameButton;
    private final JButton sayButton;
    private final JPanel jPanelNorth;
    private final JPanel jPanelSouth0;
    private final JPanel jPanelSouth1;
    private final JPanel jPanelSouth2;
    private final JTextArea clientTextArea;
    private final JScrollPane scroller;
    private BufferedReader reader;
    private PrintWriter writer;
    private String nickname;
    /**定义IP地址*/
    private final String aServerIP = "127.0.0.1";
    /**定义端口号*/
    private final int aServerPort = 5000;

    // 初始化组件
    public Chat() {
        nickname = "客户端";

        clientFrame = new JFrame();
        jPanelNorth = new JPanel();
        connectButton = new JButton("连接");
        clientTextArea = new JTextArea();
        scroller = new JScrollPane(clientTextArea);
        jPanelSouth0 = new JPanel();
        jPanelSouth1 = new JPanel();
        jPanelSouth2 = new JPanel();
        nicknameLabel = new JLabel("昵称", JLabel.LEFT);
        nicknameText = new JTextField(nickname, 30);
        nicknameButton = new JButton("确认");
        sayLabel = new JLabel("消息", JLabel.LEFT);
        sayText = new JTextField(30);
        sayButton = new JButton("确认");
    }

    // 构建GUI
    private void buildGUI() {
        // 窗口的设置
        clientFrame.setTitle("客户端");
        clientFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        clientFrame.setSize(550, 550);
        clientFrame.setLocationRelativeTo(null);

        // 北区的组件
        jPanelNorth.add(connectButton);
        clientFrame.getContentPane().add(BorderLayout.NORTH, jPanelNorth);

        // 中间的组件
        clientTextArea.setFocusable(false);
        scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        clientFrame.getContentPane().add(BorderLayout.CENTER, scroller);

        // 南区的组件
        jPanelSouth1.add(nicknameLabel);
        jPanelSouth1.add(nicknameText);
        jPanelSouth1.add(nicknameButton);
        jPanelSouth2.add(sayLabel);
        jPanelSouth2.add(sayText);
        jPanelSouth2.add(sayButton);
        jPanelSouth0.setLayout(new BoxLayout(jPanelSouth0, BoxLayout.Y_AXIS));
        jPanelSouth0.add(jPanelSouth1);
        jPanelSouth0.add(jPanelSouth2);
        clientFrame.getContentPane().add(BorderLayout.SOUTH, jPanelSouth0);

        // 设置窗口可见
        clientFrame.setVisible(true);
    }

    // 客户端运行
    public void startUp() {
        buildGUI();

        // 接收服务器消息的线程
        Runnable incomingReader = new Runnable() {
            @Override
            public void run() {
                String message;
                try {
                    while ((message = reader.readLine()) != null) {
                        clientTextArea.append(message + "\n");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        };

        // 监听Connect按钮，实现服务器的连接
        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Socket clientSocket = new Socket(aServerIP, aServerPort);
                    InputStreamReader streamReader = new InputStreamReader(clientSocket.getInputStream());
                    reader = new BufferedReader(streamReader);
                    writer = new PrintWriter(clientSocket.getOutputStream());

                    clientTextArea.append("服务器已连接...\n");

                    Thread readerThread = new Thread(incomingReader);
                    readerThread.start();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(clientFrame, "无法连接服务器!\n请确认服务端是否开启");
                }
            }
        });

        // 监听nickname，设置昵称
        ActionListener nicknameListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String aText = nicknameText.getText();
                if (!aText.equals("")) {
                    nickname = aText;
                }
            }
        };
        nicknameButton.addActionListener(nicknameListener);
        nicknameText.addActionListener(nicknameListener);
        nicknameText.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {
                String aText = nicknameText.getText();
                if (!aText.equals("")) {
                    nickname = aText;
                }
            }
        });

        // 发送消息到服务器
        ActionListener SayListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String aText = sayText.getText();
                if (aText.equals("")) {
                    JOptionPane.showMessageDialog(clientFrame, "内容不能为空！");
                } else {
                    try {
                        writer.println(nickname + "：" + aText);
                        writer.flush();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    sayText.setText("");
                }
            }
        };
        sayButton.addActionListener(SayListener);
        sayText.addActionListener(SayListener);
    }
}

