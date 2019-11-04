package cn.tanknee.java.pa.entity;

import java.util.ArrayList;

public class LongTimeItem extends Items {
    private String deadLine;// 截止日期
    private ItemList subItem = new ItemList();// 子任务链表
    // 类名序号
    private Integer classindex = 1;
    private String classname = "LongTimeItem";

    @Override
    public Integer getClassindex() {
        return classindex;
    }

    @Override
    public void setClassindex(Integer classindex) {
        this.classindex = classindex;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public LongTimeItem(String item_name, String item_note) {
        super(item_name, item_note);
        // TODO Auto-generated constructor stub
    }

    public LongTimeItem() {
    }

    public LongTimeItem(String item_name, String item_note, int id) {
        super(item_name, item_note, id);
    }

}