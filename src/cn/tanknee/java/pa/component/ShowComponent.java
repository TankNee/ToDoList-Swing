package cn.tanknee.java.pa.component;

import cn.tanknee.java.pa.entity.ItemList;
import cn.tanknee.java.pa.entity.Items;
import cn.tanknee.java.pa.utils.DatabaseUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * 必须是可滚动的控件才能实现在ScrollPanel里面滚动！ 这个控件里存放任务条例 这个控件用于负责ScrollPanel里的滚动。
 */
public class ShowComponent extends JPanel implements Scrollable {
    /**
     * 清单链表
     */
    private ArrayList<ItemList> listarray = new ArrayList<>();
    /**
     * 当前链表
     */
    private ItemList currentlist = new ItemList();

    /**
     * 行间距
     */
    private int margin = 100;
    private DatabaseUtils databaseUtils = new DatabaseUtils();


    public ArrayList<ItemList> getListarray() {
        return listarray;
    }

    public void setListarray(ArrayList<ItemList> listarray) {
        this.listarray = listarray;
    }

    public ItemList getCurrentlist() {
        return currentlist;
    }

    public void setCurrentlist(ItemList currentlist) {
        this.currentlist = currentlist;
    }

    public int getMargin() {
        return margin;
    }

    public void setMargin(int margin) {
        this.margin = margin;
    }



    public ShowComponent() {
        this.listarray = databaseUtils.readAllOfDatabase();
        this.currentlist = this.listarray.get(0);
        setLayout(new GridLayout(this.currentlist.getItems().size(), 1, 0, this.margin));
        for (Items items1 : this.currentlist.getItems()) {
            add(new ItemMoudle(items1));
        }
    }

    public ShowComponent(Items items) {
        add(new JLabel(items.getItem_name()));
        add(new JLabel(items.getItem_note()));
    }

    public ShowComponent(ArrayList<Items> items) {

        this.currentlist.setItems(items);
        this.listarray.add(currentlist);
        setLayout(new GridLayout(this.currentlist.getItems().size(), 1, 5, margin));
        for (Items items1 : this.currentlist.getItems()) {
            add(new ItemMoudle(items1));
        }

    }

    public ShowComponent(ItemList itemList) {

//        this.listarray =  databaseUtils.readAllOfDatabase();
        this.listarray.add(itemList);
        this.currentlist = itemList;
        setLayout(new GridLayout(this.currentlist.getItems().size(), 1, 0, this.margin));
        for (Items items1 : this.currentlist.getItems()) {
            add(new ItemMoudle(items1));
        }

//        databaseUtils.saveToDatabase(itemList);
    }

    /**
     * 添加新的条例
     *
     * @param newitems
     */
    public void addNewItem(Items newitems) {

        this.currentlist.getItems().add(newitems);
        refreshComponet();
        repaint();
    }

    /**
     * 添加一组新的条例到当前列表
     * @param itemList
     */
    public void addNewItem(ItemList itemList) {
        // 添加一组条例
        for (Items i : itemList.getItems()) {
            this.currentlist.getItems().add(i);
        }
        refreshComponet();
    }

    public void removeItem(Items items) {
        this.currentlist.getItems().remove(items);
        databaseUtils.removeItemFromDatabase(items, this.currentlist.getListname());
        this.refreshComponet();
    }

    public void changeItem(Items items) {
        databaseUtils.changeItemFromDatabase(items, this.currentlist.getListname());
//        removeAll();
//        int index = 0;
//        for(Items i:this.currentlist.getItems()){
//            if(i.equals(items)){
//                break;
//            }
//            index++;
//        }
        /**
         * TODO 在这里实现修改任务排列位置的功能
         * TODO 大体思路：获取目标位置的index，记住当前位置的index
         * TODO 然后通过不断地交换来实现位置的转移，最后刷新整个容器界面
         */
//        this.currentlist.getItems().indexOf();
//        this.currentlist.getItems().set(index,items);
        refreshComponet();
    }

    public void addList(ItemList itemList) {
        this.listarray.add(itemList);
//        this.currentlist = itemList;
//        refreshComponet();
//        refreshComponet();
    }

    public void changeList(ItemList itemList) {
        this.currentlist = itemList;
        refreshComponet();
    }

    public void refreshComponet() {
        removeAll();
        setLayout(new GridLayout(this.currentlist.getItems().size(), 1, 5, margin));
        for (Items items1 : this.currentlist.getItems()) {
            add(new ItemMoudle(items1));
        }
        revalidate();
        repaint();

        this.getParent().setVisible(true);
    }




    @Override
    public Dimension getPreferredScrollableViewportSize() {
        return null;
    }

    // 下面的方法用于实现鼠标滚动的距离
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
