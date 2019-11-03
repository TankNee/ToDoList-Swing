package cn.tanknee.component;

import cn.tanknee.entity.CycleItem;
import cn.tanknee.entity.Items;
import cn.tanknee.entity.LongTimeItem;
import cn.tanknee.entity.ShortItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

/**
 * 增加条例时弹出的对话框
 *
 * @Author : tanknee
 */
public class AddDialog extends ItemDialog {
    public AddDialog(JFrame jf, String title, Items items, ShowComponent showComponent) {
        super(jf, title, items);
        JDialog that = this;
        //存放条例类型的散列表
        Map<String, Integer> map = new HashMap<>();
        map.put("LongTimeItem", 0);
        map.put("ShortItem", 1);
        map.put("CycleItem", 2);

        //下拉框
        String[] al = {"长期任务", "短期任务", "周期任务"};
        JComboBox<String> jComboBox = new JComboBox<>(al);
        this.add(jComboBox);
        jComboBox.setBounds(100, 10, 200, 20);

        //标签
        JLabel item_model = new JLabel("任务类型");
        item_model.setBounds(10, 10, 80, 20);
        JLabel item_name = new JLabel("任务名称");
        item_name.setBounds(10, 40, 80, 20);
        JLabel item_note = new JLabel("任务备注");
        item_note.setBounds(10, 70, 80, 20);
        JLabel item_deadline = new JLabel("任务日期");
        item_deadline.setBounds(10, 100, 80, 20);

        //文本输入框
        JTextField input_name = new JTextField();
        input_name.setBounds(100, item_name.getY(), 200, 20);
        JTextField input_note = new JTextField();
        input_note.setBounds(100, item_note.getY(), 200, 20);
        JTextField input_deadline = new JTextField();
        input_deadline.setBounds(100, item_deadline.getY(), 200, 20);

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
                System.out.println(items1.getClass().toString().substring(6) + input_name.getText() + input_note.getText() + input_deadline.getText());
                items1.setItem_name(input_name.getText());
                items1.setItem_note(input_note.getText());
                items1.setItem_deadline(input_deadline.getText());
                showComponent.addNewItem(items1);
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

        this.add(input_name);
        this.add(input_note);
        this.add(input_deadline);
        this.add(confirm_btn);
        this.add(cancel_btn);
        this.setVisible(true);

    }
}
