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
        JMenuItem item=new JMenuItem("New",KeyEvent.VK_N);
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        fileMenu.add(item);
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