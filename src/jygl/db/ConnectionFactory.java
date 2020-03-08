package jygl.db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author Student
 */
public class ConnectionFactory{
    private static String DRIVER="";
    private static String URL="";
    private static String UserName="";
    private static String PassWord="";
    private ConnectionFactory(){

    }
    static {
        getProperties();
    }
    private static void getProperties(){
        //读取数据库的配置信息
        Thread curThread=Thread.currentThread();
        //获取当前运行的线程
        ClassLoader loader=curThread.getContextClassLoader();
        //获取类加载器
        InputStream inStream=loader.getResourceAsStream("jdbc.properties");
        //获取文件的输入流
        Properties prop=new Properties();
        //创建属性对象
        try {
            prop.load(inStream);
            //将输入流的属性保存在属性对象中
        } catch (IOException e) {
            e.printStackTrace();
        }
        DRIVER=prop.getProperty("driver");
        URL=prop.getProperty("uri");
        UserName=prop.getProperty("username");
        PassWord=prop.getProperty("password");
    }

    public static Connection getConnection(){
        //连接数据库
        Connection conn=null;
        try {
            Class.forName(DRIVER);
            //加载数据库驱动程序
            conn= DriverManager.getConnection(URL,UserName,PassWord);
            //连接数据库
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

}
