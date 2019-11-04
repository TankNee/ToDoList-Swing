package cn.tanknee.java.pa.entity;

public class ShortItem extends Items {
    private String deadLine;
    // 类名序号
    private Integer classindex = 0;
    private String classname = "ShortItem";

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

    public ShortItem(String item_name, String item_note, int id) {
        super(item_name, item_note, id);

    }

    public ShortItem() {
    }

    public ShortItem(String item_name, String item_note) {
        super(item_name, item_note);
    }
    
}