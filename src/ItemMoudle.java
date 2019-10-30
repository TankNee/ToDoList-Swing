import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.*;

class ItemMoudle extends JPanel{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String name;
    private String note;
    private String type;

    public ItemMoudle(Items i) {
        this.name = i.getItem_name();
        this.note = i.getItem_note();
        this.type = i.getClass().toString().substring(6);
        add(new JLabel(name));
        add(new JLabel(note));
        add(new JLabel(type));
    }

    public void InitMoudle() {
        
    }

    
}