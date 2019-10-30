import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

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


        Items shortItem = new ShortItem("item_name1", "item_note");
        Items shortItem_2 = new ShortItem("item_name2", "item_note");
        Items shortItem_3 = new ShortItem("item_name3", "item_note");
        this.setJMenuBar(new ProjectMenu());
        System.out.println(shortItem.getClass().toString().substring(6));


        JScrollPane jScrollPane = new JScrollPane(new ItemMoudle(shortItem));
        jScrollPane.add(new ItemMoudle(shortItem_2));
        jScrollPane.add(new ItemMoudle(shortItem_3));
        this.add(jScrollPane,BorderLayout.CENTER);
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
