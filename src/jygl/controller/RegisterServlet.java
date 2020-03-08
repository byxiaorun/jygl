package jygl.controller;

import jygl.beans.User;
import jygl.dao.UserDao;
import jygl.dao.service.UserDaoImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user=new User();
        String username=req.getParameter("username");
        String password=req.getParameter("password");
        String usertype=req.getParameter("usertypes");
        user.setUsername(username);
        user.setPassword(password);
        user.setUsertypes(usertype);
        //激活状态设为1，表示未激活
        user.setVerify("1");
        //新建userdap对象
        UserDao dao=new UserDaoImpl();
        //检查用户名是否已经被注册
        boolean flag=dao.checkUserName(username);
        if (flag){
            req.setAttribute("errorMsg","该用户已经被注册");
            gotoPage("/public/register.jsp",req,resp);
        }else {
            //新增用户,返回id号
            int id=dao.addUser(user);
            if (id>0){
                //注册成功后的操作
                switch (user.getUsertypes()){
                    //注册成功后根据用户类型转到不同的用户页面
                    case "admin":
                        req.setAttribute("errorMsg","管理员账号注册成功，请联系超级管理员激活");
                        gotoPage("/public/login.jsp",req,resp);
                        //RequestDispatcher dispatcher=req.getRequestDispatcher("public/login.jsp");
                        break;
                    case "student":
                        //传递注册信息
                        req.setAttribute("student",user);
                        req.setAttribute("sid",id);
                        gotoPage("/student/studentInfo.jsp",req,resp);
                        //dispatcher2.forward(req,resp);
                        break;
                    case "company":
                        req.setAttribute("company",user);
                        req.setAttribute("cid",id);
                        gotoPage("/company/companyInfo.jsp",req,resp);
                        break;
                    default:
                        break;
                }
            }else {
                req.setAttribute("errorMsg","注册失败，请认真填写信息");
                gotoPage("/public/register.jsp",req,resp);
            }
        }
    }
    private void gotoPage(String url,HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{
        RequestDispatcher dispatcher=req.getRequestDispatcher(url);
        dispatcher.forward(req,resp);
    }
}
