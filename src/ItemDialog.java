import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ItemDialog extends JDialog {
    public ItemDialog(JFrame jf,Items items) {
        super(jf,items.getItem_name(),true);
        String[] al = {"长期任务","短期任务","周期任务"};
        Map<String,Integer> map = new HashMap<>();
        map.put("LongTimeItem",0);
        map.put("ShortItem",1);
        map.put("CycleItem",2);
        JComboBox<String> jComboBox = new JComboBox<>(al);
        jComboBox.setSelectedIndex(map.get(items.getClass().toString().substring(6)));

        jComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        this.setLayout(null);
        jComboBox.setBounds(30,30,200,20);
        this.add(jComboBox);
        this.setBackground(Color.BLACK);
        this.setSize(500,200);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    public ItemDialog(JFrame jf,String title,Items items){
        super(jf,title,true);
        String[] al = {"长期任务","短期任务","周期任务"};
        JComboBox<String> jComboBox = new JComboBox<>(al);
        this.setLayout(null);
        jComboBox.setBounds(30,30,200,20);
        this.add(jComboBox);
        this.setBackground(Color.BLACK);
        this.setSize(500,200);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }
}
