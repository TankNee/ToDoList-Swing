package cn.tanknee.java.pa.component;

import cn.tanknee.java.pa.entity.Items;
import cn.tanknee.java.pa.entity.*;
import cn.tanknee.java.pa.entity.ShortItem;
import cn.tanknee.java.pa.utils.DatabaseUtils;
import cn.tanknee.java.pa.utils.MyStringUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DatabaseMetaData;
import java.util.HashMap;
import java.util.Map;

/**
 * 增加条例时弹出的对话框
 *
 * @Author : tanknee
 */
public class AddDialog extends ItemDialog {
    private ShowComponent showComponent;


    public AddDialog() {
        super();
    }

    public AddDialog(ShowComponent showComponent) {
        this.showComponent = showComponent;
    }

    public AddDialog(JFrame jf, String title) {
        super(jf, title);
    }

    public void AddItemDialog(JFrame jf, String title, Items items) {
//        super(jf, title, items);
        JDialog that = this;
        // 存放条例类型的散列表
        Map<String, Integer> map = new HashMap<>();
        map.put("LongTimeItem", 0);
        map.put("ShortItem", 1);
        map.put("CycleItem", 2);

        // 下拉框
        String[] al = {"长期任务", "短期任务", "周期任务"};
        JComboBox<String> jComboBox = new JComboBox<>(al);
        this.add(jComboBox);
        jComboBox.setBounds(100, 10, 200, 20);

        // 标签
        JLabel item_model = new JLabel("任务类型");
        item_model.setBounds(10, 10, 80, 20);
        JLabel item_name = new JLabel("任务名称");
        item_name.setBounds(10, 40, 80, 20);
        JLabel item_note = new JLabel("任务备注");
        item_note.setBounds(10, 70, 80, 20);
        JLabel item_deadline = new JLabel("任务日期");
        item_deadline.setBounds(10, 100, 80, 20);
        JLabel item_deadline_yaer = new JLabel("年");
        item_deadline_yaer.setBounds(140, 100, 27, 20);
        JLabel item_deadline_month = new JLabel("月");
        item_deadline_month.setBounds(207, 100, 27, 20);
        JLabel item_deadline_day = new JLabel("日");
        item_deadline_day.setBounds(274, 100, 27, 20);

        // 文本输入框
        JTextField input_name = new JTextField();
        input_name.setBounds(100, item_name.getY(), 200, 20);
        JTextField input_note = new JTextField();
        input_note.setBounds(100, item_note.getY(), 200, 20);
        JTextField input_deadline_year = new JTextField();
        input_deadline_year.setBounds(100, item_deadline.getY(), 40, 20);
        JTextField input_deadline_month = new JTextField();
        input_deadline_month.setBounds(167, item_deadline.getY(), 40, 20);
        JTextField input_deadline_day = new JTextField();
        input_deadline_day.setBounds(234, item_deadline.getY(), 40, 20);

        // 日历组件
        DateField dateField = new DateField();
        dateField.setBounds(100, item_deadline.getY(), 200, 20);

        // 确认与取消按钮
        JButton confirm_btn = new JButton("Confirm");
        confirm_btn.setBounds(input_name.getX() + input_name.getWidth() + 30, item_model.getY(), 100, 40);
        confirm_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Items items1 = null;
                switch (jComboBox.getSelectedIndex()) {
                    case 0:
                        items1 = new LongTimeItem();
                        break;
                    case 1:
                        items1 = new ShortItem();
                        break;
                    case 2:
                        items1 = new CycleItem();
                        break;
                    default:
                        break;
                }
                System.out.println(items1.getClass().toString().substring(6) + input_name.getText()
                        + input_note.getText() + input_deadline_year.getText() + "-" + input_deadline_month.getText() + "-" + input_deadline_day.getText());
                items1.setItem_name(input_name.getText());
                items1.setItem_note(input_note.getText());
                items1.setItem_deadline(input_deadline_year.getText() + "-" + input_deadline_month.getText() + "-" + input_deadline_day.getText());
                showComponent.addNewItem(items1);
                DatabaseUtils databaseUtils = new DatabaseUtils();
                databaseUtils.saveToDatabase(items1, showComponent);
                jf.setVisible(true);
                that.dispose();
            }
        });

        JButton cancel_btn = new JButton("Cancel");
        cancel_btn.setBounds(input_name.getX() + input_name.getWidth() + 30, item_model.getY() + 70, 100, 40);

        cancel_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                that.dispose();
            }
        });

        this.add(item_model);
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
        this.add(cancel_btn);
        this.setVisible(true);
    }
    public void AddListDialog(ShowComponent showComponent) {
        JDialog that = this;
        this.showComponent = showComponent;
        JLabel listName = new JLabel("List Name：");
        listName.setBounds(80, 30, 80, 20);
        JTextField jTextField = new JTextField();
        jTextField.setBounds(170, 30, 200, 20);


        JButton confirmBtn = new JButton("Confirm");
        confirmBtn.setBounds(80, 90, 130, 30);
        confirmBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!MyStringUtils.isNumber(jTextField.getText())) {
                    ItemList itemList = new ItemList(jTextField.getText());
                    showComponent.getListarray().add(itemList);
                    showComponent.setCurrentlist(itemList);
                    DatabaseUtils databaseUtils = new DatabaseUtils();
                    databaseUtils.saveToDatabase(itemList);
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
