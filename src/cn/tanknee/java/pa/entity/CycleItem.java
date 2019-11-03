package cn.tanknee.java.pa.entity;

public class CycleItem extends Items {

    private String ExecuteDate;// 执行日期
    private int repeatTime;// 重复次数
    private String repeatPeriod;// 重复周期

    public CycleItem(String item_name, String item_note, int id) {
        super(item_name, item_note, id);
    }

    public CycleItem() {
    }

    public CycleItem(String item_name, String item_note) {
        super(item_name, item_note);
    }

}