package cn.tanknee.java.pa.component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RightMouseMenu extends JFrame {
    private static JPopupMenu menu = new JPopupMenu();

    public RightMouseMenu() {
        this.setBounds(new Rectangle(500, 400));
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        menu.setVisible(true);
        this.popRightMouseMenu();
        this.add(menu);
    }

    public void popRightMouseMenu() {
        JMenuItem mAll, mCopy, mCut, mPaste, mDel;
        menu = new JPopupMenu();
        mAll = new JMenuItem("全选(A)");
        menu.add(mAll);
        mCopy = new JMenuItem("复制(C)");
        menu.add(mCopy);
        mCut = new JMenuItem("剪切(T)");
        menu.add(mCut);
        mPaste = new JMenuItem("粘贴(P)");
        menu.add(mPaste);
        mDel = new JMenuItem("删除(D)");
        menu.add(mDel);
        this.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    //弹出右键菜单
                    menu.show(RightMouseMenu.this, e.getX(), e.getY());
                }
            }
        });
        mAll.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("点击了全选菜单");
            }
        });
    }

    public void popRightMouseMenuOfItems(JFrame jf, ShowComponent showComponent) {
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
        this.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    //弹出右键菜单
                    menu.show(jf, e.getX(), e.getY());
                }
            }
        });
    }

    public static void main(String[] args) {
//       System.out.println(args.length);
        double a = 1.0;
        double b = 1;
        float c = (float) 2.01;
        String s = new String("123");
        String s1 = "123";
    }
}
