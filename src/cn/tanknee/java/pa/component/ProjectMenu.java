package cn.tanknee.java.pa.component;

import cn.tanknee.java.pa.entity.ItemList;
import cn.tanknee.java.pa.utils.DatabaseUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;

public class ProjectMenu extends JMenuBar {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private ShowComponent showComponent;

    public ProjectMenu(ShowComponent showComponent) {
        super();
        this.showComponent = showComponent;
        add(createFileMenu());
        add(createListMenu());
        add(createSortMenu());
        setVisible(true);
    }

    private JMenu createFileMenu(){
        JMenu fileMenu = new JMenu("FILE");
        fileMenu.setMnemonic(KeyEvent.VK_F);

        /**
         * 添加新的清单列表
         */
        JMenuItem addNewList = new JMenuItem("Add New List", KeyEvent.VK_A);
        addNewList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddDialog addDialog = new AddDialog();
                addDialog.setTitle("Add A New List");
                addDialog.addListDialog(showComponent);
                refreshMenu();
            }
        });
        /**
         * 删除当前清单
         */
        JMenuItem delThisList = new JMenuItem("Delete this List", KeyEvent.VK_D);
        delThisList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DatabaseUtils databaseUtils = new DatabaseUtils();
                databaseUtils.deleteTable(showComponent.getCurrentlist().getListname());
                showComponent.getListarray().remove(showComponent.getCurrentlist());
                showComponent.setCurrentlist(showComponent.getListarray().get(0));
                showComponent.refreshComponet();
                refreshMenu();
            }
        });
        /**
         * 修改当前清单
         */
        JMenuItem changeThisList = new JMenuItem("Change this List", KeyEvent.VK_C);
        changeThisList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChangeAndDeleteDialog changeAndDeleteDialog = new ChangeAndDeleteDialog();
                changeAndDeleteDialog.changeThiList(showComponent);
                refreshMenu();
            }
        });
        /**
         * 任务查找
         */
        /**
         * 打开外部文件并导入
         */
        JMenuItem openExternalSource = new JMenuItem("Open", KeyEvent.VK_O);
        openExternalSource.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        /**
         * 导出文件
         */
        JMenuItem exportSource = new JMenuItem("Export", KeyEvent.VK_E);
        exportSource.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        fileMenu.add(addNewList);
        fileMenu.add(delThisList);
        fileMenu.add(changeThisList);
        fileMenu.add(openExternalSource);
        return fileMenu;
    }

    private JMenu createListMenu() {
        JMenu listMenu = new JMenu("LIST");
        listMenu.setMnemonic(KeyEvent.VK_L);
        for (ItemList itemList : showComponent.getListarray()) {
            JMenuItem item = new JMenuItem(itemList.getListname());
            item.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    showComponent.changeList(itemList);

                }
            });
            listMenu.add(item);

        }
        return listMenu;
    }

    /**
     * 多种排序
     *
     * @return
     */
    private JMenu createSortMenu() {
        JMenu sortMenu = new JMenu("SORT");
        sortMenu.setMnemonic(KeyEvent.VK_S);

        JMenu sortList = new JMenu("Sort List");
        JMenuItem sortListByTime = new JMenuItem("Sort List By Time");
        sortListByTime.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /**
                 * 暂时还未想好怎么排序。
                 */
//                Items[] items = showComponent.getCurrentlistArray();
//                Arrays.sort(items);
//                showComponent.setCurrentlistByArray(items);
//                showComponent.refreshComponet();
                refreshMenu();
            }
        });
        JMenuItem sortListByName = new JMenuItem("Sort List By Name");
        sortListByName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] listname = new String[showComponent.getListarray().size()];
                for (int i = 0; i < listname.length; i++) {
                    listname[i] = showComponent.getListarray().get(i).getListname();
                }
                Arrays.sort(listname);
                ArrayList<ItemList> al = new ArrayList<>();
                for (int i = 0; i < listname.length; i++) {
                    for (ItemList itemList : showComponent.getListarray()) {
                        if (itemList.getListname().equals(listname[i])) {
                            al.add(itemList);
                        }
                    }
                }
                showComponent.setListarray(al);
                refreshMenu();
            }
        });
        sortList.add(sortListByTime);
        sortList.add(sortListByName);

        JMenu sortTask = new JMenu("Sort Task");
        JMenuItem sortTaskByTime = new JMenuItem("Sort Task By Time");
        JMenuItem sortTaskByName = new JMenuItem("Sort Task By Name");
        JMenuItem sortTaskByCompletion = new JMenuItem("Sort Task By Completion");
        sortTask.add(sortTaskByTime);
        sortTask.add(sortTaskByName);
        sortTask.add(sortTaskByCompletion);

        sortMenu.add(sortList);
        sortMenu.add(sortTask);
        return sortMenu;
    }

    public void refreshMenu() {
        removeAll();
        add(createFileMenu());
        add(createListMenu());
        add(createSortMenu());
        revalidate();
        repaint();
    }
}