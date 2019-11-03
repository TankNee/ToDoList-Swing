package cn.tanknee.entity;

public class Items implements Comparable<Items> {
    //条例名称
    private String item_name;
    // 条例主要内容
    private String item_note;
    // 顺序ID用于比较大小
    private int id;

	public String getItem_deadline() {
		return item_deadline;
	}

	public void setItem_deadline(String item_deadline) {
		this.item_deadline = item_deadline;
	}

	private String item_deadline;
    
    public Items(String item_name, String item_note, int id) {
		this.item_name = item_name;
		this.item_note = item_note;
		this.id = id;
	}
    
	public Items(String item_name, String item_note) {
		this.item_name = item_name;
		this.item_note = item_note;
    }

	public Items() {
	}

	public Items(String item_name, String item_note, String item_deadline) {
		this.item_name = item_name;
		this.item_note = item_note;
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
  
	
	

    
}