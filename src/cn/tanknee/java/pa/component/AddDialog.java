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
    // 标签
    private JLabel item_model = new JLabel("任务类型");
    private JLabel item_name = new JLabel("任务名称");
    private JLabel item_note = new JLabel("任务备注");
    private JLabel item_deadline = new JLabel("任务日期");
    private JLabel item_deadline_yaer = new JLabel("年");
    private JLabel item_deadline_month = new JLabel("月");
    private JLabel item_deadline_day = new JLabel("日");
    private JLabel cycle_item_repeat = new JLabel("重复");
    private JLabel cycle_item_repeat_period = new JLabel("周期");
    private JLabel long_item_subtask = new JLabel("子任务清单");

    // 文本输入框
    private JTextField input_name = new JTextField();
    private JTextField input_note = new JTextField();
    private JTextField input_deadline_year = new JTextField();
    private JTextField input_deadline_month = new JTextField();
    private JTextField input_deadline_day = new JTextField();
    private JTextField input_cycle_item_repeat = new JTextField();
    private JTextField input_cycle_item_repeat_period = new JTextField();
    // 按钮
    private JButton confirm_btn = new JButton("Confirm");
    private JButton cancel_btn = new JButton("Cancel");
    private JButton enter_sublist = new JButton("Create A SubTask List");
    // 条例实例
    private Items items1 = null;
    private ProjectMenu projectMenu = null;
    private JFrame jf;

    public AddDialog() {
        super();
    }

    public AddDialog(ShowComponent showComponent) {
        this.showComponent = showComponent;
    }

    public AddDialog(JFrame jf, String title) {
        super(jf, title);
    }

    public void addItemDialog(JFrame jf, String title, Items items) {
//        super(jf, title, items);
        this.jf = jf;
        this.setTitle(title);
        JDialog that = this;

        // 下拉框
        String[] al = {"ShortItem", "LongTimeItem", "CycleItem"};
        JComboBox<String> jComboBox = new JComboBox<>(al);
        this.add(jComboBox);
        jComboBox.setBounds(100, 10, 200, 20);

        // 标签
        item_model.setBounds(10, 10, 80, 20);
        item_name.setBounds(10, 40, 80, 20);
        item_note.setBounds(10, 70, 80, 20);
        item_deadline.setBounds(10, 100, 80, 20);
        item_deadline_yaer.setBounds(140, 100, 27, 20);
        item_deadline_month.setBounds(207, 100, 27, 20);
        item_deadline_day.setBounds(274, 100, 27, 20);

        // 文本输入框
        input_name.setBounds(100, item_name.getY(), 200, 20);
        input_note.setBounds(100, item_note.getY(), 200, 20);
        input_deadline_year.setBounds(100, item_deadline.getY(), 40, 20);
        input_deadline_month.setBounds(167, item_deadline.getY(), 40, 20);
        input_deadline_day.setBounds(234, item_deadline.getY(), 40, 20);

        // 日历组件
        DateField dateField = new DateField();
        dateField.setBounds(100, item_deadline.getY(), 200, 20);
        // 子清单操作按钮
        enter_sublist.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        // 下拉框动作
        jComboBox.setSelectedIndex(-1);
        jComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object selectedItem = jComboBox.getSelectedItem();
                if ("ShortItem".equals(selectedItem)) {
                    AddDialog.this.remove(cycle_item_repeat);
                    AddDialog.this.remove(input_cycle_item_repeat);
                    AddDialog.this.remove(long_item_subtask);
                    AddDialog.this.remove(enter_sublist);
                    item_deadline.setText("任务日期");
                    AddDialog.this.repaint();
                    System.out.println(jComboBox.getSelectedItem());
                } else if ("LongTimeItem".equals(selectedItem)) {
                    AddDialog.this.remove(cycle_item_repeat);
                    AddDialog.this.remove(input_cycle_item_repeat);
                    item_deadline.setText("任务日期");
                    long_item_subtask.setBounds(10, 130, 80, 20);
                    enter_sublist.setBounds(100, long_item_subtask.getY(), 200, 20);
                    enter_sublist.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            createSubTaskList();
                        }
                    });
                    AddDialog.this.add(long_item_subtask);
                    AddDialog.this.add(enter_sublist);
                    AddDialog.this.repaint();
                } else if ("CycleItem".equals(selectedItem)) {
                    AddDialog.this.remove(long_item_subtask);
                    AddDialog.this.remove(enter_sublist);
                    cycle_item_repeat.setBounds(10, 130, 80, 20);
                    input_cycle_item_repeat.setBounds(100, cycle_item_repeat.getY(), 70, 20);
                    cycle_item_repeat_period.setBounds(200, cycle_item_repeat.getY(), 40, 20);
                    input_cycle_item_repeat_period.setBounds(260, cycle_item_repeat.getY(), 40, 20);
                    item_deadline.setText("执行日期");
                    AddDialog.this.add(cycle_item_repeat_period);
                    AddDialog.this.add(input_cycle_item_repeat_period);
                    AddDialog.this.add(cycle_item_repeat);
                    AddDialog.this.add(input_cycle_item_repeat);
                    AddDialog.this.repaint();

                }

            }
        });
        // 确认与取消按钮
        confirm_btn.setBounds(input_name.getX() + input_name.getWidth() + 30, item_model.getY(), 100, 40);
        confirm_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                switch (jComboBox.getSelectedIndex()) {
                    case 1:
                        items1 = new LongTimeItem();
                        ((LongTimeItem) items1).test();
                        break;
                    case 0:
                        items1 = new ShortItem();
                        break;
                    case 2:
                        items1 = new CycleItem();
                        break;
                    default:
                        break;
                }
                if (jComboBox.getSelectedIndex() == -1) {
                    System.out.println("请选择一个任务类型");
                    jf.setVisible(true);
                    dispose();
                } else {
                    items1.setItem_name(input_name.getText());
                    items1.setItem_note(input_note.getText());
                    if (MyStringUtils.isNumber(input_deadline_year.getText()) && MyStringUtils.isNumber(input_deadline_month.getText()) && MyStringUtils.isNumber(input_deadline_day.getText())) {
                        items.setItem_deadline(input_deadline_year.getText() + "-" + input_deadline_month.getText() + "-" + input_deadline_day.getText());
                    } else {
                        System.out.println("输入错误");
                        items.setItem_deadline("2019-11-09");
                    }
                    items.setComplete(false);
                    if (items1 instanceof LongTimeItem) {
                        createSubTaskList();
                    } else if (items1 instanceof CycleItem) {
                        ((CycleItem) items1).setRepeatTime(Integer.parseInt(input_cycle_item_repeat.getText()));
                    }
                    showComponent.addNewItem(items1);
                    DatabaseUtils databaseUtils = new DatabaseUtils();
                    databaseUtils.saveToDatabase(items1, showComponent);

                    jf.setVisible(true);
                    that.dispose();
                }
            }
        });


        cancel_btn.setBounds(input_name.getX() + input_name.getWidth() + 30, item_model.getY() + 70, 100, 40);

        cancel_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                that.dispose();
            }
        });
        RefreshDialog();
        this.setVisible(true);

    }

    public void addListDialog(ShowComponent showComponent) {
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

    /**
     * 添加刷新当前界面
     *
     * @Author TankNee
     */
    public void RefreshDialog() {

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

    }

    public ProjectMenu getProjectMenu() {
        return projectMenu;
    }

    public void setProjectMenu(ProjectMenu projectMenu) {
        this.projectMenu = projectMenu;
    }

    public void createSubTaskList() {
        if (!input_name.getText().isEmpty() && !input_note.getText().isEmpty() && !input_deadline_year.getText().isEmpty() && !input_deadline_day.getText().isEmpty() && !input_deadline_month.getText().isEmpty()) {
            ItemList itemList = new ItemList("subtasklistof" + input_name.getText());
            showComponent.getListarray().add(itemList);
//            showComponent.setCurrentlist(itemList);
            DatabaseUtils databaseUtils = new DatabaseUtils();
            databaseUtils.saveToDatabase(itemList);
            jf.setVisible(true);
            AddDialog.this.dispose();
            projectMenu.refreshMenu();
            showComponent.refreshComponet();

        } else {
            System.out.println("请先输入");
            jf.setVisible(true);
            AddDialog.this.dispose();
        }
    }
}
