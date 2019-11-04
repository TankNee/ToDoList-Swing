package cn.tanknee.java.pa.component;

import cn.tanknee.java.pa.entity.ItemList;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class ProjectMenu extends JMenuBar {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private ShowComponent showComponent;

    public ProjectMenu(ShowComponent showComponent) {
        super();
        this.showComponent = showComponent;
        add(createFileMenu());
        add(createListMenu());
        setVisible(true);
    }

    private JMenu createFileMenu(){
        JMenu fileMenu = new JMenu("FILE");
        fileMenu.setMnemonic(KeyEvent.VK_F);

        /**
         * 添加新的清单列表
         */
        JMenuItem addNewList = new JMenuItem("Add New List", KeyEvent.VK_A);
        addNewList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddDialog addDialog = new AddDialog();
                addDialog.setTitle("Add A New List");
                addDialog.AddListDialog(showComponent);
                refreshMenu();
            }
        });
        /**
         * 打开外部文件并导入
         */
        JMenuItem openExternalSource = new JMenuItem("Open", KeyEvent.VK_O);
        openExternalSource.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        /**
         * 导出文件
         */
        JMenuItem exportSource = new JMenuItem("Export", KeyEvent.VK_E);
        exportSource.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        fileMenu.add(addNewList);
        fileMenu.add(openExternalSource);
        return fileMenu;
    }

    private JMenu createListMenu() {
        JMenu listMenu = new JMenu("LIST");
        listMenu.setMnemonic(KeyEvent.VK_L);
        for (ItemList itemList : showComponent.getListarray()) {
            JMenuItem item = new JMenuItem(itemList.getListname());
            item.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    showComponent.changeList(itemList);
                }
            });
            listMenu.add(item);

        }
        return listMenu;
    }

    public void refreshMenu() {
        removeAll();
        add(createFileMenu());
        add(createListMenu());
        setVisible(true);
    }
}