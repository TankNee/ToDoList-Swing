package cn.tanknee.java.pa.component;

import cn.tanknee.java.pa.entity.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 改变或者删除条例时弹出的对话框
 */
public class ChangeAndDeleteDialog extends ItemDialog {

    public ChangeAndDeleteDialog(JFrame jf, String title, Items items, ShowComponent showComponent) {
        super(jf, title, items);
        JDialog that = this;
        /**
         * 各组件： 标签，按钮
         */
        //标签
        JLabel item_name = new JLabel("任务名称");
        item_name.setBounds(10, 10, 80, 20);
        JLabel item_note = new JLabel("任务备注");
        item_note.setBounds(10, 40, 80, 20);
        JLabel item_deadline = new JLabel("任务日期");
        item_deadline.setBounds(10, 70, 80, 20);

        //文本输入框
        JTextField input_name = new JTextField();
        input_name.setBounds(100, item_name.getY(), 200, 20);
        input_name.setText(items.getItem_name());
        JTextField input_note = new JTextField();
        input_note.setBounds(100, item_note.getY(), 200, 20);
        input_note.setText(items.getItem_note());
        JTextField input_deadline = new JTextField();
        input_deadline.setBounds(100, item_deadline.getY(), 200, 20);
        input_deadline.setText(items.getItem_deadline());

        // 确认,删除与取消按钮
        JButton confirm_btn = new JButton("Confirm");
        confirm_btn.setBounds(input_name.getX() + input_name.getWidth() + 30, item_name.getY(), 100, 20);
        confirm_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                items.setItem_name(input_name.getText());
                items.setItem_note(input_note.getText());
                items.setItem_deadline(input_deadline.getText());
                showComponent.changeItem();
                jf.setVisible(true);
                that.dispose();
            }
        });

        JButton delete_btn = new JButton("Delete");
        delete_btn.setBounds(input_name.getX() + input_name.getWidth() + 30, item_note.getY(), 100, 20);
        delete_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showComponent.removeItem(items);
                jf.setVisible(true);
                that.dispose();
            }
        });
        JButton cancel_btn = new JButton("Cancel");
        cancel_btn.setBounds(input_name.getX() + input_name.getWidth() + 30, item_deadline.getY(), 100, 20);
        cancel_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                that.dispose();
            }
        });

        /**
         * 添加组件到面板
         */
        this.add(item_name);
        this.add(item_note);
        this.add(item_deadline);

        this.add(input_name);
        this.add(input_note);
        this.add(input_deadline);

        this.add(confirm_btn);
        this.add(delete_btn);
        this.add(cancel_btn);

        this.setVisible(true);
    }


}
