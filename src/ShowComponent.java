import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ShowComponent extends JPanel implements ActionListener, Scrollable {
    private ArrayList<Items> items = new ArrayList();

    @Override
    public void setLayout(LayoutManager mgr) {
        super.setLayout(mgr);
    }

    public ShowComponent(Items items) {
        add(new JLabel(items.getItem_name()));
        add(new JLabel(items.getItem_note()));
    }

    public ShowComponent(ArrayList<Items> items) {

        this.items = items;
        setLayout(new GridLayout(this.items.size(), 1, 5, 300));
        for (Items items1 : this.items) {
            add(new JLabel(items1.getItem_name()));
            add(new JLabel(items1.getItem_note()));
        }
    }

    public ShowComponent() {
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public Dimension getPreferredScrollableViewportSize() {
        return null;
    }

    @Override
    public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction) {
        return 30;
    }

    @Override
    public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction) {
        return 30;
    }

    @Override
    public boolean getScrollableTracksViewportWidth() {
        return false;
    }

    @Override
    public boolean getScrollableTracksViewportHeight() {
        return false;
    }
}
