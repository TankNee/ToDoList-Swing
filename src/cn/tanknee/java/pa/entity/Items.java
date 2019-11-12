package cn.tanknee.java.pa.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Items implements Comparable<Items> {
    //条例名称
    private String item_name;
    // 条例主要内容
    private String item_note;
	// 数据库ID
    private int id;
	// 截止日期
	private String item_deadline = "19-11-05";
	// 日期的形式
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	// 截止日期的Date表示形式
	private Date item_deadline_date = null;
	// 类名序号
	private Integer classindex;
	// 是否完成的标识符
	private Boolean complete = false;

	public Boolean getComplete() {
		return complete;
	}

	public void setComplete(Boolean complete) {
		this.complete = complete;
	}
	public Items(String item_name, String item_note) {
		this.item_name = item_name;
		this.item_note = item_note;
		try {
			item_deadline_date = sdf.parse(item_deadline);
			System.out.println(item_deadline_date);
		} catch (ParseException pe) {
			pe.printStackTrace();
		}
    }

	public Items(String item_name, String item_note, int id) {
		this.item_name = item_name;
		this.item_note = item_note;
		this.id = id;
		try {
			item_deadline_date = sdf.parse(item_deadline);
			System.out.println(item_deadline_date);
		} catch (ParseException pe) {
			pe.printStackTrace();
		}
	}

	public Items() {
	}

	public Items(String item_name, String item_note, String item_deadline) {
		this.item_name = item_name;
		this.item_note = item_note;
		this.item_deadline = item_deadline;
		try {
			item_deadline_date = sdf.parse(item_deadline);
			System.out.println(item_deadline_date);
		} catch (ParseException pe) {
			pe.printStackTrace();
		}
	}

	public int getClassIndex(String classname) {
		Map<String, Integer> map = new HashMap();
		ShortItem shortItem = new ShortItem();
		LongTimeItem longTimeItem = new LongTimeItem();
		CycleItem cycleItem = new CycleItem();
		map.put(shortItem.getClass().toString(), 0);
		map.put(longTimeItem.getClass().toString(), 1);
		map.put(cycleItem.getClass().toString(), 2);
		return map.get(classname);
	}

	public int getClassIndex() {
		return this.classindex;
	}

	public String getClassName() {
		return this.getClass().getSimpleName();
	}

	public Date getItem_deadline_date() {
		return item_deadline_date;
	}

	public void setItem_deadline_date(Date item_deadline_date) {
		this.item_deadline_date = item_deadline_date;
	}

	public String getItem_deadline() {
		return item_deadline;
	}

	public void setItem_deadline(String item_deadline) {
		this.item_deadline = item_deadline;
	}
	@Override
    public int compareTo(Items i) {
        
        return this.id-i.id;
    }

	public String getItem_name() {
		return item_name;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}

	public String getItem_note() {
		return item_note;
	}

	public void setItem_note(String item_note) {
		this.item_note = item_note;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getClassindex() {
		return classindex;
	}

	public void setClassindex(Integer classindex) {
		this.classindex = classindex;
	}
}