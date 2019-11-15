package cn.tanknee.java.pa.component;

import cn.tanknee.java.pa.entity.ItemList;
import cn.tanknee.java.pa.entity.Items;
import cn.tanknee.java.pa.utils.DatabaseUtils;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

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
    /**
     * 数据库工具
     */
    private DatabaseUtils databaseUtils = new DatabaseUtils();

    private ProjectMenu projectMenu = null;
    public ArrayList<ItemList> getListarray() {
        return listarray;
    }

    public void setListarray(ArrayList<ItemList> listarray) {
        this.listarray = listarray;
    }

    public ItemList getCurrentlist() {
        return currentlist;
    }

    public Items[] getCurrentlistArray() {
        Items[] itemLists = new Items[listarray.size()];
        for (int i = 0; i < listarray.size(); i++) {
            itemLists[i] = currentlist.getItems().get(i);
        }
        return itemLists;
    }

    public void setCurrentlistByArray(Items[] items) {
        for (int i = 0; i < items.length; i++) {
            this.currentlist.getItems().set(i, items[i]);
        }


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
        if (this.listarray.size() == 0) {
            // 说明数据库中没有数据
            // 创建一个新表
            AddDialog addDialog = new AddDialog();
            addDialog.addListDialog(this);
        }
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
        refreshComponet();
    }

    public void addList(ItemList itemList) {
        this.listarray.add(itemList);
    }

    public void changeList(ItemList itemList) {
        this.currentlist = itemList;
        refreshComponet();
    }

    public void changeList(String listName) {
        ItemList itemList = null;
        for (ItemList i : listarray) {
            if (i.getListname().equals(listName)) {
                itemList = i;
                break;
            }
        }
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
        if (this.getParent() != null) {
            this.getParent().setVisible(true);
        }
    }

    public void searchInList(String context) {
        ArrayList<Items> al = new ArrayList<>();
        for (Items i : currentlist.getItems()) {
            if (i.getItem_name().contains(context)) {
                al.add(i);
                continue;
//                System.out.println();
            } else if (i.getItem_note().contains(context)) {
                al.add(i);
                continue;
            }
        }
        showSearchResult(al);
    }

    public void showSearchResult(ArrayList<Items> items) {
        removeAll();
        setLayout(new GridLayout(items.size(), 1, 5, margin));
        for (Items items1 : items) {
            add(new ItemMoudle(items1));
        }
        revalidate();
        repaint();
        if (this.getParent() != null) {
            this.getParent().setVisible(true);
        }
    }

    public void sortItemByTime() {
        Collections.sort(this.currentlist.getItems(), new Comparator<Items>() {
            @Override
            public int compare(Items o1, Items o2) {
                return o1.getId() - (o2.getId());
            }
        });
        refreshComponet();
    }

    public void sortItemByName() {
        Collections.sort(this.currentlist.getItems(), new Comparator<Items>() {
            @Override
            public int compare(Items o1, Items o2) {
                return o1.getItem_name().compareTo(o2.getItem_name());
            }
        });
        refreshComponet();
    }

    public void sortItemByCompletion() {
        Collections.sort(this.currentlist.getItems(), new Comparator<Items>() {
            @Override
            public int compare(Items o1, Items o2) {
                return o1.getItem_deadline().compareTo(o2.getItem_deadline());
            }
        });
        refreshComponet();
    }

    public void setProjectMenu(ProjectMenu projectMenu) {
        this.projectMenu = projectMenu;
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
