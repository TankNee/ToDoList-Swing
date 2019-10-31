import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
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

        Items shortItem = new ShortItem("myname", "item_note");
        Items shortItem_2 = new ShortItem("item_name122", "item_note");
        Items shortItem_3 = new ShortItem("item_name3", "item_note");
        Items shortItem_4 = new ShortItem("item_name4", "item_note");
        Items shortItem_5 = new ShortItem("item_name5123", "item_note123");
        ItemList itemList = new ItemList();
        itemList.add(shortItem);
        itemList.add(shortItem_2);
        itemList.add(shortItem_3);
        itemList.add(shortItem_4);
        itemList.add(shortItem_5);


        ShowComponent testComponent = new ShowComponent(itemList.getItems());
        JScrollPane jScrollPane = new JScrollPane(testComponent);
//        jScrollPane.add(testComponent);
//        jScrollPane.add(new ItemMoudle(shortItem_2));
//        jScrollPane.add(new ItemMoudle(shortItem_3));

        this.add(jScrollPane,BorderLayout.CENTER);

        JButton settingbtn = new JButton("Setting");
        this.add(settingbtn, BorderLayout.NORTH);

        JPanel bottompanel = new JPanel();
        bottompanel.setLayout(new BorderLayout());
        JButton Addbtn = new JButton("Add");
        bottompanel.add(Addbtn, BorderLayout.EAST);
        this.add(bottompanel, BorderLayout.SOUTH);
        this.setJMenuBar(new ProjectMenu());
        System.out.println(shortItem.getClass().toString().substring(6));

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
