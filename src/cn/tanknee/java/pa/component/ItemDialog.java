package cn.tanknee.java.pa.component;

import cn.tanknee.java.pa.entity.Items;

import javax.swing.*;
import java.awt.*;

public class ItemDialog extends JDialog {

    public ItemDialog(JFrame jf, Items items) {
        super(jf, items.getItem_name(), true);
        this.setLayout(null);
        this.setBackground(Color.BLACK);
        this.setSize(500, 200);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
    }


    public ItemDialog(JFrame jf, String title, Items items) {
        super(jf, title, true);
        this.setLayout(null);
        this.setBackground(Color.BLACK);
        this.setSize(500, 200);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
//        this.setVisible(true);

    }
}