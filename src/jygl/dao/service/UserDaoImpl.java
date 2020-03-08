package jygl.dao.service;

import jygl.beans.Page;
import jygl.beans.User;
import jygl.dao.UserDao;
import jygl.db.ConnectionFactory;
import jygl.db.DBClose;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    Connection conn = null;
    //连接对象
    PreparedStatement psat = null;
    //准备参数
    ResultSet rs = null;
    //结果集合

    @Override
    public User findUser(User user) {
        conn = ConnectionFactory.getConnection();
        if (conn==null){
            return null;
        }
        //连接数据库
        String sql = "select * from user where username=? and password=? and usertypes=?";
        try {
            psat = conn.prepareStatement(sql);
            //创建预编译的prepareStatement对象
            psat.setString(1, user.getUsername());
            psat.setString(2, user.getPassword());
            psat.setString(3, user.getUsertypes());
            //设置sql语句问号的参数值
            rs = psat.executeQuery();
            //执行查询
            if (!rs.next()) {
                //不能rs往下读
                return null;
            } else {//存在返回数据
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setUsertypes(rs.getString("usertypes"));
                user.setVerify(rs.getString("verify"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBClose.close(rs, psat, conn);
            //关闭数据库
        }
        return null;
    }

    @Override
    public User findUserById(int id) {
        conn = ConnectionFactory.getConnection();
        //连接数据库
        if (conn==null){
            return null;
        }
        //根据id好查找用户
        String sql = "select * from user where id=?";
        try {
            psat = conn.prepareStatement(sql);
            //创建预编译的prepareStatement对象
            psat.setInt(1, id);
            //设置sql语句问号的参数值
            rs = psat.executeQuery();
            //执行查询
            if (!rs.next()) {
                //不能rs往下读
                return null;
            } else {//存在返回数据
                User user=new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setUsertypes(rs.getString("usertypes"));
                user.setVerify(rs.getString("verify"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBClose.close(rs, psat, conn);
            //关闭数据库
        }
        return null;
    }

    @Override
    public int addUser(User user) {//添加/插入用户
        conn = ConnectionFactory.getConnection();
        String sql = "insert into user(username,password,usertypes,verify)values(?,?,?,?)";
        int i = 0;
        try {
            psat = conn.prepareStatement(sql);
            psat.setString(1, user.getUsername());
            psat.setString(2, user.getPassword());
            psat.setString(3, user.getUsertypes());
            psat.setString(4, user.getVerify());
            i = psat.executeUpdate();
            //返回受影响的行数
            if (i > 0) {
                user = findUser((user));
                //查找添加的用户

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBClose.close(psat, conn);
        }
        return user.getId();
        //返回添加用户的id号
    }

    @Override
    public boolean deleteUser(int id) {
        //删除用户根据id，返回是否删除成功
        conn = ConnectionFactory.getConnection();
        String sql = "delete from user where id=?";
        int i = 0;
        try {
            psat = conn.prepareStatement(sql);
            psat.setInt(1, id);
            i = psat.executeUpdate();
            //受影响的行数
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBClose.close(psat, conn);
        }
        return i>0?true:false;
    }

    @Override
    public int updateUser(User user) {//更新用户
        conn = ConnectionFactory.getConnection();
        String sql = "update user set username=?,password=?,usertypes=?,verify=? where id=?";
        int i = 0;
        try {
            psat = conn.prepareStatement(sql);
            psat.setString(1, user.getUsername());
            psat.setString(2, user.getPassword());
            psat.setString(3, user.getUsertypes());
            psat.setString(4, user.getVerify());
            psat.setInt(5, user.getId());
            i = psat.executeUpdate();//返回受影响的行数
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBClose.close(psat, conn);
        }
        return i;
    }

    @Override
    public boolean checkUserName(String username) {//检查是否存在相同用户名
        conn = ConnectionFactory.getConnection();
        String sql = "select * from user where username=?";
        boolean flag = false;
        //设定一个变量表示是否存在
        try {
            psat = conn.prepareStatement(sql);
            psat.setString(1, username);
            rs = psat.executeQuery();
            //执行查询返回结果给rs
            if (!rs.next()) {
                //如果rs无下一位
                flag = false;
            } else {
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBClose.close(rs, psat, conn);
        }
        return flag;
    }

    @Override
    public List<User> query(String where, Object[] whereArgs) {//返回所有用户列表
        conn = ConnectionFactory.getConnection();
        String sql = "select * from user";
        List<User> list = null;
        User user=null;
        try {
            if (!where.isEmpty()) {
                //如果条件不为空
                sql = sql + " where " + where;
                //拼接条件到sql后面
                psat = conn.prepareStatement(sql);
                for (int i = 0; i < whereArgs.length; i++) {
                    psat.setObject(i + 1, whereArgs[i]);
                }
            } else {
                psat = conn.prepareStatement(sql);
            }
            rs=psat.executeQuery();
            //执行查询，返回结果集
            if (rs.next()){
                //结果集不为空
                rs.first();
                list=new ArrayList<>();
                while (rs.next()){
                    //循环读取
                    user=new User();
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    user.setVerify(rs.getString("verify"));
                    user.setId(rs.getInt("id"));
                    list.add(user);
                    //添加到列表
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBClose.close(rs,psat,conn);
        }
        return list;
    }

    @Override
    public int getRecordCount(Page page) {
        int recordCount=0;
        conn=ConnectionFactory.getConnection();
        String sql="select count(*) from user "+page.getStrWhere();
        try {
            psat=conn.prepareStatement(sql);
            rs=psat.executeQuery();
            if (rs.next()){
                recordCount=rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBClose.close(rs,psat,conn);
        }
        return recordCount;
    }

    @Override
    public int getTotalPage(Page page) {
        int totalPage=0;
        int recordount=getRecordCount(page);
        int pagesize=page.getPageSize();
        //页数=记录总数/每页大小
        int m=recordount/pagesize;
        if (recordount%pagesize>0){
            //如果余数大于0,则多加一页
            totalPage=m+1;
        }else {
            totalPage=m;
        }
        return totalPage;
    }

    @Override
    public Page getUserList(Page page) {
        //当前页的用户列表
        List list=new ArrayList();
        User user=null;
        conn=ConnectionFactory.getConnection();
        String sql="select * from user "
                +page.getStrWhere()
                +" limit "
                +(page.getCurrentPage()-1)*page.getPageSize()+","+page.getPageSize();
        try {
            psat=conn.prepareStatement(sql);
            rs=psat.executeQuery();
            while (rs.next()){
                user=new User();
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setUsertypes(rs.getString("usertypes"));
                user.setVerify(rs.getString("verify"));
                user.setId(rs.getInt("id"));
                list.add(user);
            }
            page.setList(list);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBClose.close(rs,psat,conn);
        }
        return page;
    }

    @Override
    public boolean activeUser(int id) {
        //激活用户
        return setUser(id,"2");
    }

    @Override
    public boolean invalid(int id) {
        //不激活用户
        return setUser(id,"3");
    }

    @Override
    public boolean disableUser(int id) {
        //禁用用户
        return setUser(id,"1");
    }

    @Override
    public boolean updatepassword(int id, String password){
        //设置用户
        boolean flag=false;
        conn=ConnectionFactory.getConnection();
        String sql="update user set password=? where id=?";
        try {
            psat=conn.prepareStatement(sql);
            psat.setString(1,password);
            psat.setInt(2,id);
            int i=psat.executeUpdate();
            if (i>0){
                flag=true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBClose.close(psat,conn);
        }
        return flag;
    }

    private boolean setUser(int id,String verifyValue){
        //设置用户
        boolean flag=false;
        conn=ConnectionFactory.getConnection();
        String sql="update user set verify=? where id=?";
        try {
            psat=conn.prepareStatement(sql);
            psat.setString(1,verifyValue);
            psat.setInt(2,id);
            int i=psat.executeUpdate();
            if (i>0){
                flag=true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBClose.close(psat,conn);
        }
        return flag;
    }
}
