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

    // 标签
    private JLabel item_name = new JLabel("任务名称");
    private JLabel item_note = new JLabel("任务备注");
    private JLabel item_deadline = new JLabel("任务日期");
    private JLabel item_deadline_yaer = new JLabel("年");
    private JLabel item_deadline_month = new JLabel("月");
    private JLabel item_deadline_day = new JLabel("日");
    private JLabel cycle_item_repeat = new JLabel("重复");
    private JLabel long_item_subtask = new JLabel("子任务清单");

    // 文本输入框
    private JTextField input_name = new JTextField();
    private JTextField input_note = new JTextField();
    private JTextField input_deadline_year = new JTextField();
    private JTextField input_deadline_month = new JTextField();
    private JTextField input_deadline_day = new JTextField();
    private JTextField input_cycle_item_repeat = new JTextField();
    // 按钮
    private JButton confirm_btn = new JButton("Confirm");
    private JButton cancel_btn = new JButton("Cancel");
    private JButton enter_sublist = new JButton("Enter The SubTask List");
    // 条例实例
    private Items items1 = null;
    private ProjectMenu projectMenu = null;
    private JFrame jf;

    public ChangeAndDeleteDialog(JFrame jf, String title, Items items, ShowComponent showComponent) {
        super(jf, title, items);
        JDialog that = this;
        /**
         * 各组件： 标签，按钮
         */
        //标签
        item_name.setBounds(10, 10, 80, 20);
        item_note.setBounds(10, 40, 80, 20);
        item_deadline.setBounds(10, 70, 80, 20);
        item_deadline_yaer.setBounds(140, 70, 27, 20);
        item_deadline_month.setBounds(207, 70, 27, 20);
        item_deadline_day.setBounds(274, 70, 27, 20);
        JLabel item_complete = new JLabel("完成");
        item_complete.setBounds(10, 100, 80, 20);


        //文本输入框
        input_name.setBounds(100, item_name.getY(), 200, 20);
        input_name.setText(items.getItem_name());
        input_note.setBounds(100, item_note.getY(), 200, 20);
        input_note.setText(items.getItem_note());
        JRadioButton completeButton = new JRadioButton();
        completeButton.setBounds(100, 100, 20, 20);
        completeButton.setSelected(items.getComplete());
        completeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                items.setComplete(completeButton.isSelected());
            }
        });

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

        input_deadline_year.setBounds(100, item_deadline.getY(), 40, 20);
        input_deadline_year.setText(String.valueOf(c.get(Calendar.YEAR)));
        input_deadline_month.setBounds(167, item_deadline.getY(), 40, 20);
        input_deadline_month.setText(String.valueOf(c.get(Calendar.MONTH) + 1));
        input_deadline_day.setBounds(234, item_deadline.getY(), 40, 20);
        input_deadline_day.setText(String.valueOf(c.get(Calendar.DAY_OF_MONTH)));
        /**
         * 根据不同的条例类型进行不同的显示
         */

        if (items instanceof ShortItem) {

        } else if (items instanceof LongTimeItem) {
            long_item_subtask.setBounds(10, 130, 80, 20);
            enter_sublist.setBounds(100, long_item_subtask.getY(), 200, 20);
            enter_sublist.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    DatabaseUtils databaseUtils = new DatabaseUtils();
                    if (databaseUtils.isTableExist("subtasklistof" + input_name.getText())) {
                        showComponent.changeList("subtasklistof" + input_name.getText());
                    } else {
                        System.out.println("数据表不存在");
                    }

                    ChangeAndDeleteDialog.this.dispose();
                }
            });
            this.add(long_item_subtask);
            this.add(enter_sublist);
        } else if (items instanceof CycleItem) {
            cycle_item_repeat.setBounds(10, 130, 80, 20);
            input_cycle_item_repeat.setBounds(100, cycle_item_repeat.getY(), 200, 20);
            item_deadline.setText("执行日期");
            this.add(cycle_item_repeat);
            this.add(input_cycle_item_repeat);
        }

        // 确认,删除与取消按钮
        confirm_btn.setBounds(input_name.getX() + input_name.getWidth() + 30, item_name.getY(), 100, 20);
        confirm_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                items.setItem_name(input_name.getText());
                items.setItem_note(input_note.getText());
                if (MyStringUtils.isNumber(input_deadline_year.getText()) && MyStringUtils.isNumber(input_deadline_month.getText()) && MyStringUtils.isNumber(input_deadline_day.getText())) {
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
        this.add(item_complete);


        this.add(input_name);
        this.add(input_note);
        this.add(input_deadline_year);
        this.add(input_deadline_month);
        this.add(input_deadline_day);
        this.add(completeButton);

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
