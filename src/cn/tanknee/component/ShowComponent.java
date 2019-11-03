package cn.tanknee.component;

import cn.tanknee.entity.ItemList;
import cn.tanknee.entity.Items;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

//必须是可滚动的控件才能实现在ScrollPanel里面滚动！
//这个控件里存放任务条例
//这个控件用于负责ScrollPanel里的滚动。
public class ShowComponent extends JPanel implements ActionListener, Scrollable {
    private ArrayList<Items> items = new ArrayList();
    private int margin = 100;

    public ShowComponent() {
    }

    public ShowComponent(Items items) {
        add(new JLabel(items.getItem_name()));
        add(new JLabel(items.getItem_note()));
    }

    public ShowComponent(ArrayList<Items> items) {

        this.items = items;
        setLayout(new GridLayout(this.items.size(), 1, 5, margin));
        for (Items items1 : this.items) {
            add(new ItemMoudle(items1));
        }

    }


    public void addNewItem(Items newitems) {
        removeAll();
        items.add(newitems);
        setLayout(new GridLayout(this.items.size(), 1, 5, margin));
        for (Items items1 : this.items) {
            add(new ItemMoudle(items1));
        }
        repaint();
    }

    public void addNewItem(ItemList itemList) {
        // 添加一组条例
        removeAll();
        for (Items i : itemList.getItems()) {
            items.add(i);
        }
        setLayout(new GridLayout(this.items.size(), 1, 5, margin));
        for (Items items1 : this.items) {
            add(new ItemMoudle(items1));
        }
    }

    public void removeItem(Items items) {
        removeAll();
        this.items.remove(items);
        setLayout(new GridLayout(this.items.size(), 1, 5, margin));
        for (Items items1 : this.items) {
            add(new ItemMoudle(items1));
        }
    }

    public void changeItem() {
        removeAll();
        for (Items items1 : this.items) {
            add(new ItemMoudle(items1));
        }
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
