package cn.tanknee.java.pa.entity;

public class ShortItem extends Items {
    private String deadLine;
    
    public ShortItem(String item_name, String item_note, int id) {
        super(item_name, item_note, id);

    }

    public ShortItem() {
    }

    public ShortItem(String item_name, String item_note) {
        super(item_name, item_note);
    }
    
}