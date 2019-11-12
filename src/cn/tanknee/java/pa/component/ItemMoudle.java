package cn.tanknee.java.pa.component;

import cn.tanknee.java.pa.entity.*;
import cn.tanknee.java.pa.utils.DatabaseUtils;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private JLabel completeLabel = new JLabel();
    private JLabel nameLabel = new JLabel();
    private JLabel noteLabel = new JLabel();
    private JLabel typeLabel = new JLabel();
    private JLabel deadlineLabel = new JLabel();
    private DatabaseUtils databaseUtils = new DatabaseUtils();

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
        setLayout(new GridLayout(5, 1, 50, 50));
        this.name = i.getItem_name();
        this.note = i.getItem_note();
        this.type = i.getClass().getSimpleName();
        this.deadline = i.getItem_deadline();
        this.setBackground(Color.GRAY);
        completeLabel.setText("Is it completed ?       " + i.getComplete());
        nameLabel.setText("Task Name:       " + name);
        noteLabel.setText("Task Note:       " + note);
        typeLabel.setText("Task Type:       " + type);
        deadlineLabel.setText("Task Deadline:       " + deadline);
        add(completeLabel);
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
        JFrame jFrame = (JFrame) this.getRootPane().getParent();
        ShowComponent showComponent = (ShowComponent) this.getParent();
        JPanel that = this;
        if (e.getButton() == MouseEvent.BUTTON1) {
            System.out.println("Succeed" + this.name);

            ChangeAndDeleteDialog changeAndDeleteDialog = new ChangeAndDeleteDialog(jFrame, "Task", this.items, showComponent);
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            JPopupMenu menu = new JPopupMenu();
            JMenu mCopy, mCut;
            menu = new JPopupMenu();

            mCopy = new JMenu("复制到(C)");
            mCut = new JMenu("剪切到(T)");
            JMenuItem mDel = new JMenuItem("删除(D)");


            for (ItemList itemList : showComponent.getListarray()) {
                JMenuItem item = new JMenuItem(itemList.getListname());
                item.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        itemList.add(ItemMoudle.this.items);
                        showComponent.changeList(itemList);
                        databaseUtils.saveToDatabase(ItemMoudle.this.items, itemList);
                        showComponent.refreshComponet();
                    }
                });
                mCopy.add(item);
            }
            for (ItemList itemList : showComponent.getListarray()) {
                JMenuItem item = new JMenuItem(itemList.getListname());
                item.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        itemList.add(ItemMoudle.this.items);
                        databaseUtils.saveToDatabase(ItemMoudle.this.items, itemList);
                        showComponent.getCurrentlist().remove(ItemMoudle.this.items);
                        databaseUtils.removeItemFromDatabase(ItemMoudle.this.items, showComponent.getCurrentlist().getListname());
                        showComponent.changeList(itemList);
                        showComponent.refreshComponet();
                    }
                });
                mCut.add(item);
            }
            mDel.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    showComponent.removeItem(items);
                }
            });
            menu.add(mCopy);
            menu.add(mCut);
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