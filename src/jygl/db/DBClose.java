package jygl.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBClose {
    private static void close(ResultSet rs){//关闭查询记录集
        if (rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    private static  void close(Statement stat){//关闭Statement对象
        if (stat!=null){
            try {
                stat.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    private static void close(Connection con){//关闭连接对象
        if (con!=null){
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void close(Statement stat,Connection con){//增删改的关闭的公有方法
        close(stat);
        close(con);
    }
    public static void close(ResultSet rs,Statement stat,Connection con){//查询的关闭的公有方法
        close(rs);
        close(stat);
        close(con);
    }
}
