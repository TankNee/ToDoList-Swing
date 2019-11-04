package cn.tanknee.java.pa.entity;

public class CycleItem extends Items {

    private String ExecuteDate;// 执行日期
    private int repeatTime;// 重复次数
    private String repeatPeriod;// 重复周期
    // 类名序号
    private Integer classindex = 2;
    private String classname = "CycleItem";

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

    public CycleItem(String item_name, String item_note, int id) {
        super(item_name, item_note, id);
    }

    public CycleItem() {
    }

    public CycleItem(String item_name, String item_note) {
        super(item_name, item_note);
    }

}