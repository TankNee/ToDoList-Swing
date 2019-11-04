package cn.tanknee.java.pa.utils;

import cn.tanknee.java.pa.component.ShowComponent;
import cn.tanknee.java.pa.entity.*;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseUtils {
    private static String driver = "com.mysql.jdbc.Driver";
    private static String url_cloud = "jdbc:MySQL://47.102.45.176:3306/personalagency?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=UTC";
    private static String url = "jdbc:MySQL://localhost:3306/personalagency?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=UTC";
    private static String user_cloud = "personalagency";
    private static String user = "root";
    private static String password = "451000";

    public static void main(String[] args) {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:MySQL://localhost:3306/personalagency?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=UTC";
        String user = "root";
        String password = "451000";
        try {
            Class.forName(driver);
            Connection c = DriverManager.getConnection(url, user, password);
            if (!c.isClosed()) {
                System.out.println("Succeed");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public DatabaseUtils() {
        try {
            Class.forName(driver);
            Connection c = DriverManager.getConnection(url, user, password);
            if (!c.isClosed()) {
                System.out.println("Succeed");
            }
            c.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将数据保存到数据库内
     *
     * @param itemList(条例链表)
     */
    public void saveToDatabase(ItemList itemList) {
        try {
            Class.forName(driver);
            Connection c = DriverManager.getConnection(url, user, password);
            if (!c.isClosed()) {
                System.out.println("Succeed");
                Statement statement = c.createStatement();
                String createDataBase = "create table " + itemList.getListname() + " (id int not null primary key AUTO_INCREMENT, name varchar(255),type varchar(255),note varchar(255),deadline varchar(255),subtask varchar(255));";
                if (!isTableExist(itemList.getListname())) {
                    statement.execute(createDataBase);
                } else {
                    System.out.println("数据表已存在");
                }
                /**
                 * TODO 此处还存在问题！
                 */
                for (Items i : itemList.getItems()) {
                    String savedata = "insert into " + itemList.getListname() + " values(NULL,?,?,?,?,?);";
                    PreparedStatement stmt = c.prepareStatement(savedata);
                    stmt.setString(1, i.getItem_name());
                    stmt.setString(2, i.getClassName());
                    stmt.setString(3, i.getItem_note());
                    stmt.setString(4, i.getItem_deadline());
                    stmt.setString(5, i.getItem_name());
                    stmt.executeLargeUpdate();
                }
                statement.close();
            }
            c.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println(e.toString());
        } finally {

        }

    }

    /**
     * @param i
     */
    public void saveToDatabase(Items i, ShowComponent showComponent) {
        try {
            Class.forName(driver);
            Connection c = DriverManager.getConnection(url, user, password);
            if (!c.isClosed()) {

                System.out.println("Succeed");
                Statement statement = c.createStatement();
                String createDataBase = "create table " + showComponent.getCurrentlist().getListname() + " (id int not null primary key AUTO_INCREMENT, name varchar(255),type varchar(255),note varchar(255),deadline varchar(255),subtask varchar(255));";
                if (!isTableExist(showComponent.getCurrentlist().getListname())) {
                    statement.execute(createDataBase);
                } else {
                    System.out.println("数据表已存在");
                }
                String savedata = "insert into " + showComponent.getCurrentlist().getListname() + " values(NULL,?,?,?,?,?);";
                PreparedStatement stmt = c.prepareStatement(savedata);
                stmt.setString(1, i.getItem_name());
                stmt.setString(2, i.getClassName());
                stmt.setString(3, i.getItem_note());
                stmt.setString(4, i.getItem_deadline());
                stmt.setString(5, i.getItem_name());
                stmt.executeLargeUpdate();
                statement.close();
            }
            c.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println(e.toString());
        } finally {

        }

    }

    /**
     * 判断表是否存在
     *
     * @param tablename
     * @return
     */
    public boolean isTableExist(String tablename) {
        try {
            Class.forName(driver);
            Connection c = DriverManager.getConnection(url, user, password);
            ResultSet rs = c.getMetaData().getTables(null, null, tablename, null);
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<ItemList> readAllOfDatabase() {
        ArrayList<ItemList> listarray = new ArrayList<>();
        ArrayList<String> tablename = new ArrayList<>();
        try {

            Class.forName(driver);
            Connection c = DriverManager.getConnection(url, user, password);
            Statement statement = c.createStatement();
            DatabaseMetaData databaseMetaData = c.getMetaData();
            ResultSet rs1 = databaseMetaData.getTables(null, null, null, new String[]{"TABLE"});
            while (rs1.next()) {
                tablename.add(rs1.getString(3));
            }
            for (String name : tablename) {
                ItemList itemList = new ItemList(name);
                String readtsql = "select * from " + name + ";";
                ResultSet rs2 = statement.executeQuery(readtsql);
                while (rs2.next()) {
                    Items items = null;
                    switch (rs2.getString("type")) {
                        case "LongTimeItem":
                            items = new LongTimeItem();
                            break;
                        case "ShortItem":
                            items = new ShortItem();
                            break;
                        case "CycleItem":
                            items = new CycleItem();
                            break;
                        default:
                            break;
                    }
                    items.setItem_name(rs2.getString("name"));
                    items.setItem_note(rs2.getString("note"));
                    items.setItem_deadline(rs2.getString("deadline"));
                    itemList.add(items);
                }
                listarray.add(itemList);
                rs2.close();
            }
//            ResultSet rs = statement.executeQuery();
            rs1.close();
            statement.close();
            c.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listarray;
    }
}
