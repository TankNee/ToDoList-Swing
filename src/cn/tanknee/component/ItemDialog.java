package cn.tanknee.component;

import cn.tanknee.entity.Items;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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
