package cn.tanknee.java.pa.entity;

import java.util.ArrayList;


/**
 * 条例清单，实现切换清单功能时需要用到该功能
 */
public class ItemList {
    // 存储条例的链表
    private ArrayList<Items> items = new ArrayList();
    private String listname;
    private int id;

    public String getListname() {
        return listname;
    }

    public void setListname(String listname) {
        this.listname = listname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ItemList(ArrayList<Items> items){
        this.items = items;
    }

    public ItemList() {
    }

    public ItemList(String listname) {
        this.listname = listname;
    }

    public ItemList(ArrayList<Items> items, String listname) {
        this.items = items;
        this.listname = listname;
    }

    // 添加条例到清单中
    public void add(Items i){
        this.items.add(i);
    }

    public void remove(Items i) {
        this.items.remove(i);
    }

    public ArrayList<Items> getItems() {
        return items;
    }

    public void setItems(ArrayList<Items> items) {
        this.items = items;
    }
}