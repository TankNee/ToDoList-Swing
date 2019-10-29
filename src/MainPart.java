import javax.swing.*;

public class MainPart extends JFrame {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    public  void init() {
        JPanel titlePanel = new JPanel();
        JLabel titleLabel = new JLabel("Personal Agency");
        titlePanel.add(titleLabel);
        this.add(titlePanel);

    }
    public MainPart() {
        super("Personal Agency");

        this.setSize(360,720);
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
