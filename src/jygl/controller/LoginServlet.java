package jygl.controller;

import jygl.beans.User;
import jygl.dao.UserDao;
import jygl.dao.service.UserDaoImpl;
import jygl.db.ConnectionFactory;
import jygl.db.DBClose;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {
    public LoginServlet() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       super.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //接收表单传来的数据
        String username=req.getParameter("username");
        String passwd=req.getParameter("password");
        String usertype=req.getParameter("usertype");
        String gotoUrl="";
        HttpSession session=req.getSession();
        User user=new User();
        user.setUsername(username);
        user.setPassword(passwd);
        user.setUsertypes(usertype);
        UserDao dao=new UserDaoImpl();
        User u=dao.findUser(user);
        if (u!=null){
            //如果用户存在
            switch (u.getVerify()){
                //获取用户要审核状态
                case "1":
                    req.setAttribute("errorMsg","用户未激活，请联系管理员审核");
                    gotoUrl="/public/login.jsp";
                    break;
                case "2":
                    //审核通过
                    //记录用户登录的session
                    session.setAttribute("user",u);
                    if (u.getUsertypes().equals("admin")){
                        gotoUrl="/admin/index.jsp";
                    }
                    if (u.getUsertypes().equals("student")){
                        gotoUrl="/student/index.jsp";
                    }
                    if (u.getUsertypes().equals("company")){
                        gotoUrl="/company/index.jsp";
                    }
                    break;
                case "3":
                    //审核不通过
                    req.setAttribute("errorMsg","用户未通过,请如实填写信息");
                    gotoUrl="/public/login.jsp";
                    break;
                 default:
                     break;
            }
        }else {
            checkUserName(username);
            if (checkUserName(username)==false){
                req.setAttribute("errorMsg","此用户名不存在");
            }else {
                req.setAttribute("errorMsg","账号或密码错误");
            }
            gotoUrl="/public/login.jsp";
        }
        if (!gotoUrl.isEmpty()){
            //如果跳转链接不为空
            RequestDispatcher dispatcher=req.getRequestDispatcher(gotoUrl);
            dispatcher.forward(req,resp);
            //页面跳转
        }
    }

    public boolean checkUserName(String username) {//检查是否存在用户名
        Connection conn = null;
        //连接对象
        PreparedStatement psat = null;
        //准备参数
        ResultSet rs = null;
        //结果集合
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
}
