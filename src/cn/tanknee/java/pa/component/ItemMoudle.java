package cn.tanknee.java.pa.component;

import cn.tanknee.java.pa.entity.*;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ItemMoudle extends JPanel implements MouseListener {
    /**
     *
     */

    private static final long serialVersionUID = 1L;
    private String name;
    private String note;
    private String type;
    private String deadline;
    private Items items;
    private JLabel nameLabel = new JLabel();
    private JLabel noteLabel = new JLabel();
    private JLabel typeLabel = new JLabel();
    private JLabel deadlineLabel = new JLabel();

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ItemMoudle(Items i) {
        this.items = i;
        setLayout(new GridLayout(4, 1, 50, 50));
        this.name = i.getItem_name();
        this.note = i.getItem_note();
        this.type = i.getClassName();
        this.deadline = i.getItem_deadline();
        this.setBackground(Color.GRAY);
        nameLabel.setText("Task Name:       " + name);
        noteLabel.setText("Task Note:       " + note);
        typeLabel.setText("Task Type:       " + type);
        deadlineLabel.setText("Task Deadline:       " + deadline);
        add(nameLabel);
        add(noteLabel);
        add(typeLabel);
        add(deadlineLabel);
        setSize(400, 160);
        setBorder(new EtchedBorder(EtchedBorder.RAISED));
        this.addMouseListener(this);
    }

    public void InitMoudle() {

        setLayout(new GridLayout(3, 1));
        add(new JLabel(name));
        add(new JLabel(note));
        add(new JLabel(type));
        setSize(400, 160);
        setBorder(new EtchedBorder(EtchedBorder.RAISED));
        this.addMouseListener(this);
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            System.out.println("Succeed" + this.name);
            JFrame jFrame = (JFrame) this.getRootPane().getParent();
            ShowComponent showPanel = (ShowComponent) this.getParent();
            ChangeAndDeleteDialog changeAndDeleteDialog = new ChangeAndDeleteDialog(jFrame, "Task", this.items, showPanel);
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            JPopupMenu menu = new JPopupMenu();
            JMenuItem mCopy, mCut, mPaste, mDel;
            menu = new JPopupMenu();
            mCopy = new JMenuItem("复制(C)");
            menu.add(mCopy);
            mCut = new JMenuItem("剪切(T)");
            menu.add(mCut);
            mPaste = new JMenuItem("粘贴(P)");
            menu.add(mPaste);
            mDel = new JMenuItem("删除(D)");
            menu.add(mDel);
//            JFrame jf = (JFrame) ItemMoudle.this.getRootPane().getParent();
            menu.show(ItemMoudle.this, e.getX(), e.getY());
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}