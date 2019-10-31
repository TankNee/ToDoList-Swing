import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

//必须是可滚动的控件才能实现在ScrollPanel里面滚动！
//这个控件里存放任务条例
//这个控件用于负责scrollpanel里的滚动。
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
        setLayout(new GridLayout(this.items.size(), 1, 5, 20));
        for (Items items1 : this.items) {
//            add(new JLabel(items1.getItem_name()));
//            add(new JLabel(items1.getItem_note()));
            add(new ItemMoudle(items1));
            add(new Label("-----------------------"));
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

    //下面的方法用于实现鼠标滚动的距离
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
