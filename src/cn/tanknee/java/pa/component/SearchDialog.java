package cn.tanknee.java.pa.component;

import cn.tanknee.java.pa.entity.ItemList;
import cn.tanknee.java.pa.utils.DatabaseUtils;
import cn.tanknee.java.pa.utils.MyStringUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchDialog extends ItemDialog {

    public SearchDialog(ShowComponent showComponent) {
        super();
        setTitle("搜索");
        JDialog that = this;
        JLabel searchcontext = new JLabel("Searching for ：");
        searchcontext.setBounds(80, 30, 120, 20);
        JTextField jTextField = new JTextField();
        jTextField.setBounds(170, 30, 200, 20);


        JButton confirmBtn = new JButton("Confirm");
        confirmBtn.setBounds(80, 90, 130, 30);
        confirmBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showComponent.searchInList(jTextField.getText());
                dispose();
            }
        });
        JButton cancelBtn = new JButton("Cancel");
        cancelBtn.setBounds(240, 90, 130, 30);
        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                that.dispose();
            }
        });

        add(searchcontext);
        add(jTextField);
        add(confirmBtn);
        add(cancelBtn);
        this.setVisible(true);
    }

}
