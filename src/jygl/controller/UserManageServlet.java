package jygl.controller;

import jygl.beans.Page;
import jygl.beans.User;
import jygl.common.Utils;
import jygl.dao.UserDao;
import jygl.dao.service.UserDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class UserManageServlet extends HttpServlet {
    UserDao dao = new UserDaoImpl();

    //创建UserDao
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //动作

        String action = req.getParameter("action");

        //获取参数action，区分不同操作
        switch (action) {
            case "list":
                list(req, resp);
                break;
            case "active":
                active(req, resp);
                break;
            case "invalid":
                invalid(req, resp);
                break;
            case "disable":
                disable(req, resp);
                break;
            case "updatepassword":
                updatepassword(req,resp);
                break;
            default:
                break;
        }
        }
    private void disable(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User u = (User) session.getAttribute("user");
        if (u == null || !u.getUsertypes().equals("admin")) {
            Utils.gotoPage("/public/login.jsp", req, resp);
        }else{
        int id = 0;
        //获取传递过来的id
        try {
            id = Integer.parseInt(req.getParameter("id"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        dao.disableUser(id);
        Utils.gotoPage("userManage?action=list", req, resp);
    }}

    private void invalid(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User u = (User) session.getAttribute("user");
        if (u == null || !u.getUsertypes().equals("admin")) {
            Utils.gotoPage("/public/login.jsp", req, resp);
        }else{
        int id = 0;
        //获取传递过来的id
        try {
            id = Integer.parseInt(req.getParameter("id"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        dao.invalid(id);
        Utils.gotoPage("userManage?action=list", req, resp);
    }}

    private void active(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User u = (User) session.getAttribute("user");
        if (u == null || !u.getUsertypes().equals("admin")) {
            Utils.gotoPage("/public/login.jsp", req, resp);
        }else{
        int id = 0;
        //获取传递过来的id
        try {
            id = Integer.parseInt(req.getParameter("id"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        dao.activeUser(id);
        Utils.gotoPage("userManage?action=list", req, resp);
    }}

    private void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User u = (User) session.getAttribute("user");
        if (u == null || !u.getUsertypes().equals("admin")) {
            Utils.gotoPage("/public/login.jsp", req, resp);
        }else{
        //显示用户列表
        Page page = new Page();
        String pageNum = req.getParameter("page");
        //从请求参数中获取当前是第几页
        int currentPage = 0;
        if (pageNum == null) {
            //如果没传递页码,默认第一页
            currentPage = 1;
        } else {
            currentPage = Integer.parseInt(pageNum);
        }
        //条件筛选
        String where = req.getParameter("strWhere");
        if (where == null) {
            where = "";
        }
        page.setStrWhere(where);
        //设置当前页码
        page.setCurrentPage(currentPage);
        page.setPageSize(10);
        //获取记录总数
        int totalCount = dao.getRecordCount(page);
        //获取的记录总数传给page对象的RecordCount
        page.setRecordCount(totalCount);
        //获取总页数
        int totalPage = dao.getTotalPage(page);
        page.setTotalPage(totalPage);
        //获取当前页的记录总数
        page = dao.getUserList(page);
        //将当前页的记录列表传给jsp
        req.setAttribute("page", page);
        //转到用户列表页面
        Utils.gotoPage("admin/userList.jsp", req, resp);
    }}

    private void updatepassword(HttpServletRequest req, HttpServletResponse resp) {
        String strid = req.getParameter("id");
        int id=0;
        String oldpassword=req.getParameter("oldpassword");
        String newpassword=req.getParameter("newpassword");
        //获取传递过来的id
        try{
            id = Integer.parseInt(strid);
            User user=dao.findUserById(id);
            String password1=user.getPassword();
            if (oldpassword.equals(password1)){
                dao.updatepassword(id,newpassword);
                PrintWriter out = resp.getWriter();
                out.print("<script>alert('更新成功,请重新登录'); window.location='/jygl/layout' </script>");
            }else {
                resp.getWriter().print("原密码不正确");
            }
        }catch (NumberFormatException | IOException e){
            e.printStackTrace();
        }
    }
}
