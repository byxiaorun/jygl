package jygl.controller;

import jygl.beans.Message;
import jygl.beans.Page;
import jygl.beans.User;
import jygl.common.Utils;
import jygl.dao.MessageDao;
import jygl.dao.service.MessageDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class MessageServlet extends HttpServlet {
    MessageDao dao = new MessageDaoImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        int mid = 0;
        if (req.getParameter("mid") != null) {
            String midStr = req.getParameter("mid");
            try {
                mid = Integer.parseInt(midStr);
            } catch (NumberFormatException e) {
                return;
            }
        }
        switch (action) {
            case "list":
                listone(req,resp);
                break;
            case "listall":
                listall(req,resp);
                break;
            case "add":
                add(req,resp);
                break;
            case "showDetail":
                showDetail(mid,req, resp);
                break;
            case "delete":
                delete(req,resp);
            default:
                break;
        }
    }
    private void delete(HttpServletRequest req, HttpServletResponse resp) {
        String page = req.getParameter("page");
        String StrMid = req.getParameter("mid");
        int mid = 0;
        try {
            mid = Integer.parseInt(StrMid);
        } catch (NumberFormatException e) {
            return;
        }
        boolean flag = dao.deleteMessage(mid);
        if (flag == true) {
            try {
                resp.getWriter().print("删除成功");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void showDetail(int mid,HttpServletRequest req, HttpServletResponse resp) {
        Message message = dao.findMessageById(mid);
        req.setAttribute("message", message);
        try {
            Utils.gotoPage("", req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
    private void add(HttpServletRequest req, HttpServletResponse resp) {
        Message message = new Message();
        //获取网页中studentname数据
        message.setUid(Integer.parseInt(req.getParameter("uid")));
        message.setUsername(req.getParameter("username"));
        message.setTitle(req.getParameter("title"));
        message.setContent(req.getParameter("content"));
        message.setMsgtime(req.getParameter("msgtime"));
        //调用dao的添加方法
        boolean flag = dao.addMessage(message);
        if (flag) {
            try {
                resp.getWriter().print("提交成功");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void listall(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //动作
        HttpSession session=req.getSession();
        User u= (User) session.getAttribute("user");
        if (u==null || !u.getUsertypes().equals("admin")){
            Utils.gotoPage("/public/login.jsp",req,resp);
        }else{
        Page page = new Page();
        String pageNum = req.getParameter("page");
        int currentPage = 0;
        if (pageNum == null) {
            currentPage = 1;
        } else {
            try {
                currentPage = Integer.parseInt(pageNum);
            } catch (NumberFormatException e) {
                return;
            }
        }
        page.setCurrentPage(currentPage);
        page.setPageSize(10);
        int recordCount = dao.getRecordCount(page);
        int totalPage = dao.getTotalPage(page);
        page.setRecordCount(recordCount);
        page.setTotalPage(totalPage);
        page = dao.getList(page);
        req.setAttribute("page", page);
        try {
            Utils.gotoPage("admin/message/messageList.jsp", req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }}
    }
    private void listone(HttpServletRequest req, HttpServletResponse resp) {
        int uid= Integer.parseInt(req.getParameter("uid"));
        Page page = new Page();
        String pageNum = req.getParameter("page");
        int currentPage = 0;
        if (pageNum == null) {
            currentPage = 1;
        } else {
            try {
                currentPage = Integer.parseInt(pageNum);
            } catch (NumberFormatException e) {
                return;
            }
        }
        page.setCurrentPage(currentPage);
        page.setPageSize(10);
        int recordCount = dao.getRecordCount(page);
        int totalPage = dao.getTotalPage(page);
        page.setRecordCount(recordCount);
        page.setTotalPage(totalPage);
        page = dao.lookMessage(page,uid);
        req.setAttribute("page", page);
        try {
            Utils.gotoPage("admin/message/umessageList.jsp", req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
