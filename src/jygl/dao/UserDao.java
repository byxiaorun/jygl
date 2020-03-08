package jygl.dao;

import jygl.beans.Page;
import jygl.beans.User;

import java.util.List;

public interface UserDao {
    User findUser(User user);//查找用户是否存在
    User findUserById(int id);//根据id号查找用户
    int addUser(User user);//增加用户
    boolean deleteUser(int id);//删除用户
    int updateUser(User user);//修改用户
    boolean checkUserName(String username);//检查用户名是否重复
    List<User> query(String where,Object[] whereArgs);//查询用户列表
    //分页
    int getRecordCount(Page page);//获取用户的总记录数
    int getTotalPage(Page page);//获取总页数
    Page getUserList(Page page);//获取当前页的用户列表
    //设置用户类型
    boolean activeUser(int id);//已激活用户
    boolean invalid(int id);//不激活用户,未通过审核
    boolean disableUser(int id);//被禁用
    boolean updatepassword(int id,String password);//修改密码
}
