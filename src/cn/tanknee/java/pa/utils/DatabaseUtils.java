package cn.tanknee.java.pa.utils;

import cn.tanknee.java.pa.entity.ItemList;
import cn.tanknee.java.pa.entity.Items;

import java.sql.*;

public class DatabaseUtils {
    private String driver = "com.mysql.jdbc.Driver";
    private String url = "jdbc:MySQL://localhost:3306/personalagency?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=UTC";
    private String user = "root";
    private String password = "451000";

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
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将数据保存到数据库内
     *
     * @param itemList
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
//                for(Items i:itemList.getItems()){
//                    String savedata = "insert into "+itemList.getListname()+"values (NULL,?,?,?,?,?)";
//                    PreparedStatement stmt = (PreparedStatement) c.prepareStatement(savedata);
//                    stmt.setString(1,i.getItem_name());
//                    stmt.setString(2,i.getClass().toString().substring(6));
//                    stmt.setString(3,i.getItem_note());
//                    stmt.setString(4,i.getItem_deadline());
//                    stmt.setString(5,i.getItem_name());
//                    stmt.executeLargeUpdate();
//                }

            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println(e.toString());
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
}
