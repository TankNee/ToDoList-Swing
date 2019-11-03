package cn.tanknee.component;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class ProjectMenu extends JMenuBar {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public ProjectMenu() {
        super();
        add(createFileMenu());
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
}