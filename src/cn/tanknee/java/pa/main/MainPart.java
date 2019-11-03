package cn.tanknee.java.pa.main;

import cn.tanknee.java.pa.component.AddDialog;
import cn.tanknee.java.pa.component.ProjectMenu;
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
        // 测试数据
        Items shortItem = new ShortItem("Myname", "item_note");
        Items shortItem_2 = new ShortItem("item_name122", "item_note");
        Items shortItem_3 = new LongTimeItem("item_name3", "item_note");
        Items shortItem_4 = new ShortItem("item_name4", "item_note");
        Items shortItem_5 = new CycleItem("item_name523", "item_note123");
        ItemList itemList = new ItemList("Test1");
        itemList.add(shortItem);
        itemList.add(shortItem_2);
        itemList.add(shortItem_3);
        itemList.add(shortItem_4);
        itemList.add(shortItem_5);
        Items shortItem_6 = new CycleItem("asdasd", "zxcsada");
        Items shortItem_7 = new CycleItem("item_name523", "zxcxzcqwe");
        Items shortItem_8 = new CycleItem("item_name523", "item_note123");
        Items shortItem_9 = new CycleItem("item_name523", "item_note123");
        Items shortItem_10 = new CycleItem("item_name523", "item_note123");
        ItemList itemList1 = new ItemList("Test2");
        itemList1.add(shortItem_6);
        itemList1.add(shortItem_7);
        itemList1.add(shortItem_8);
        itemList1.add(shortItem_9);
        itemList1.add(shortItem_10);

        // 滚动列表的展示模块
        ShowComponent ScrollComponent = new ShowComponent(itemList);
        ScrollComponent.addList(itemList1);
        JScrollPane jScrollPane = new JScrollPane(ScrollComponent);
        this.add(jScrollPane, BorderLayout.CENTER);
        JButton settingbtn = new JButton("Setting");
        this.add(settingbtn, BorderLayout.NORTH);
        settingbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ScrollComponent.revalidate();
                ScrollComponent.repaint();
                ScrollComponent.removeAll();
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
        this.setJMenuBar(new ProjectMenu(ScrollComponent));

        // 传入主界面的指针
        JFrame that = this;

        Addbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("H");
                Items items = new CycleItem("test", "test");
                AddDialog addDialog = new AddDialog(that, "添加", items, ScrollComponent);
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
