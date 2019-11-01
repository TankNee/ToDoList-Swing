import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class MainPart extends JFrame {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    // initial GUI
    public void init() {
        JPanel titlePanel = new JPanel();
        JLabel titleLabel = new JLabel("Personal Agency");
        titlePanel.add(titleLabel);
        this.add(titlePanel);
        // 测试数据
        Items shortItem = new ShortItem("myname", "item_note");
        Items shortItem_2 = new ShortItem("item_name122", "item_note");
        Items shortItem_3 = new LongTimeItem("item_name3", "item_note");
        Items shortItem_4 = new ShortItem("item_name4", "item_note");
        Items shortItem_5 = new CycleItem("item_name523", "item_note123");
        ItemList itemList = new ItemList();
        itemList.add(shortItem);
        itemList.add(shortItem_2);
        itemList.add(shortItem_3);
        itemList.add(shortItem_4);
        itemList.add(shortItem_5);

        // 滚动列表的展示模块
        ShowComponent testComponent = new ShowComponent(itemList.getItems());
        JScrollPane jScrollPane = new JScrollPane(testComponent);
        this.add(jScrollPane,BorderLayout.CENTER);

        JButton settingbtn = new JButton("Setting");
        this.add(settingbtn, BorderLayout.NORTH);
        // 底部的面板容器
        JPanel bottompanel = new JPanel();
        bottompanel.setLayout(new BorderLayout());
        // 添加按钮
        JButton Addbtn = new JButton("Add");
        JFrame that = this;
        bottompanel.add(Addbtn, BorderLayout.EAST);
        this.add(bottompanel, BorderLayout.SOUTH);
        // 顶部的菜单
        this.setJMenuBar(new ProjectMenu());
        System.out.println(shortItem.getClass().toString().substring(6));
        Addbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("H");
                Items items = new CycleItem("test", "test");
                AddDialog addDialog = new AddDialog(that, "添加", items);
//                ItemDialog itemDialog = new ItemDialog(that,items);
//                Dialog dialog = new Dialog(that,"Hello",false);
//                dialog.setLocationRelativeTo(null);
//                dialog.setSize(500,200);
//                dialog.setVisible(true);
            }
        });
//        Addbtn.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                super.mouseClicked(e);
//                AddDialog addDialog =new AddDialog(that,"添加新任务",true);
////                Dialog =
//            }
//        });
    }

    // 构建界面
    public MainPart() {
        super("Personal Agency");
        this.setLayout(new BorderLayout());
        this.setSize(360, 720);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        init();
        this.setResizable(false);
        this.setVisible(true);

    }
    

    public static void main(String[] args) {

        System.out.println("Hello World");
        new MainPart();

    }

}
