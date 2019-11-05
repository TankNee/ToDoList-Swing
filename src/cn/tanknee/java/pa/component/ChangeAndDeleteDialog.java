package cn.tanknee.java.pa.component;

import cn.tanknee.java.pa.entity.*;
import cn.tanknee.java.pa.utils.DatabaseUtils;
import cn.tanknee.java.pa.utils.MyStringUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

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
        JLabel item_deadline_yaer = new JLabel("年");
        item_deadline_yaer.setBounds(140, 70, 27, 20);
        JLabel item_deadline_month = new JLabel("月");
        item_deadline_month.setBounds(207, 70, 27, 20);
        JLabel item_deadline_day = new JLabel("日");
        item_deadline_day.setBounds(274, 70, 27, 20);


        //文本输入框
        JTextField input_name = new JTextField();
        input_name.setBounds(100, item_name.getY(), 200, 20);
        input_name.setText(items.getItem_name());
        JTextField input_note = new JTextField();
        input_note.setBounds(100, item_note.getY(), 200, 20);
        input_note.setText(items.getItem_note());
//        JTextField input_deadline = new JTextField();
//        input_deadline.setBounds(100, item_deadline.getY(), 200, 20);
//        input_deadline.setText(items.getItem_deadline());
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            c.setTime(sdf.parse(items.getItem_deadline()));
        } catch (ParseException pe) {
            try {
                c.setTime(sdf.parse("2019-11-05"));
            } catch (ParseException pe2) {
                pe2.printStackTrace();
            }
            pe.printStackTrace();
        }

        JTextField input_deadline_year = new JTextField();
        input_deadline_year.setBounds(100, item_deadline.getY(), 40, 20);
        input_deadline_year.setText(String.valueOf(c.get(Calendar.YEAR)));
        JTextField input_deadline_month = new JTextField();
        input_deadline_month.setBounds(167, item_deadline.getY(), 40, 20);
        input_deadline_month.setText(String.valueOf(c.get(Calendar.MONTH) + 1));
        JTextField input_deadline_day = new JTextField();
        input_deadline_day.setBounds(234, item_deadline.getY(), 40, 20);
        input_deadline_day.setText(String.valueOf(c.get(Calendar.DAY_OF_MONTH)));


        // 确认,删除与取消按钮
        JButton confirm_btn = new JButton("Confirm");
        confirm_btn.setBounds(input_name.getX() + input_name.getWidth() + 30, item_name.getY(), 100, 20);
        confirm_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                items.setItem_name(input_name.getText());
                items.setItem_note(input_note.getText());
                if (!MyStringUtils.isNumber(input_deadline_year.getText()) && !MyStringUtils.isNumber(input_deadline_month.getText()) && !MyStringUtils.isNumber(input_deadline_day.getText())) {
                    items.setItem_deadline(input_deadline_year.getText() + "-" + input_deadline_month.getText() + "-" + input_deadline_day.getText());
                } else {
                    items.setItem_deadline("2019-11-09");
                }
                showComponent.changeItem(items);
//                showComponent.refreshComponet();
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
        this.add(item_deadline_yaer);
        this.add(item_deadline_month);
        this.add(item_deadline_day);


        this.add(input_name);
        this.add(input_note);
        this.add(input_deadline_year);
        this.add(input_deadline_month);
        this.add(input_deadline_day);

        this.add(confirm_btn);
        this.add(delete_btn);
        this.add(cancel_btn);

        this.setVisible(true);
    }


    public ChangeAndDeleteDialog() {

    }

    public void changeThiList(ShowComponent showComponent) {
        this.setTitle("修改列表");
        JDialog that = this;
        JLabel listName = new JLabel("List Name：");
        listName.setBounds(80, 30, 80, 20);
        JTextField jTextField = new JTextField();
        jTextField.setBounds(170, 30, 200, 20);
        jTextField.setText(showComponent.getCurrentlist().getListname());


        JButton confirmBtn = new JButton("Confirm");
        confirmBtn.setBounds(80, 90, 130, 30);
        confirmBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!MyStringUtils.isNumber(jTextField.getText())) {
                    DatabaseUtils databaseUtils = new DatabaseUtils();
                    databaseUtils.renameTable(showComponent.getCurrentlist().getListname(), jTextField.getText());
                    showComponent.getCurrentlist().setListname(jTextField.getText());
                    dispose();
                    showComponent.refreshComponet();
                } else {
                    System.out.println("不可以使用纯数字的名称");
                    dispose();
                }
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

        add(listName);
        add(jTextField);
        add(confirmBtn);
        add(cancelBtn);
        this.setVisible(true);

    }
}
