package cn.tanknee.java.pa.main;

import cn.tanknee.java.pa.component.AddDialog;
import cn.tanknee.java.pa.component.ProjectMenu;
import cn.tanknee.java.pa.component.SearchDialog;
import cn.tanknee.java.pa.component.ShowComponent;
import cn.tanknee.java.pa.entity.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainPart extends JFrame {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    // initial GUI
    public void init() {
        JPanel titlePanel = new JPanel();
        JLabel titleLabel = new JLabel("Personal Agency");
        titlePanel.add(titleLabel);
        this.add(titlePanel);

        // 滚动列表的展示模块
        ShowComponent scrollComponent = new ShowComponent();
        JScrollPane jScrollPane = new JScrollPane(scrollComponent);
        this.add(jScrollPane, BorderLayout.CENTER);

        // 搜寻按钮
        JButton searchbtn = new JButton("Search ");
        this.add(searchbtn, BorderLayout.NORTH);
        searchbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SearchDialog searchDialog = new SearchDialog(scrollComponent);
//                searchbtn.setText("Search in "+scrollComponent.getCurrentlist().getListname());
            }
        });

        // 底部的面板容器
        JPanel bottompanel = new JPanel();
        bottompanel.setLayout(new BorderLayout());

        // 添加按钮
        JButton Addbtn = new JButton("Add");

        bottompanel.add(Addbtn, BorderLayout.EAST);
        this.add(bottompanel, BorderLayout.SOUTH);

        // 顶部的菜单
        ProjectMenu projectMenu = new ProjectMenu(scrollComponent);
        this.setJMenuBar(projectMenu);
        scrollComponent.setProjectMenu(projectMenu);
        // 传入主界面的指针
        JFrame that = this;

        Addbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("H");
                Items items = new CycleItem("test", "test");
//                AddDialog addDialog = new AddDialog(that, "添加", items, ScrollComponent);
                AddDialog addDialog = new AddDialog(scrollComponent);
                addDialog.setProjectMenu(projectMenu);
                addDialog.addItemDialog(that, "Add a Item", items);
            }
        });
    }

    // 构建界面
    public MainPart() {
        super("Personal Agency");
        this.setLayout(new BorderLayout());
        this.setSize(360, 720);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        init();
        this.setResizable(false);
        this.setVisible(true);

    }

    public static void main(String[] args) {

        System.out.println("Hello World");
        new MainPart();

    }

}
