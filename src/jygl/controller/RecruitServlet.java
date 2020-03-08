package jygl.controller;

import jygl.beans.Page;
import jygl.beans.Recruit;
import jygl.beans.User;
import jygl.common.Utils;
import jygl.dao.RecruitDao;
import jygl.dao.service.RecruitDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RecruitServlet extends HttpServlet {
    RecruitDao dao = new RecruitDaoImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        int rid = 0;
        if (req.getParameter("rid") != null) {
            String ridStr = req.getParameter("rid");
            try {
                rid = Integer.parseInt(ridStr);
            } catch (NumberFormatException e) {
                return;
            }
        }
        switch (action) {
            case "list":
                list(req,resp);
                break;
            case "list2":
                list2(req,resp);
                break;
            case "edit":
                edit(req,resp);
                break;
            case "update":
                update(req,resp);
                break;
            case "delete":
                delete(req,resp);
            case "add":
                add(req,resp);
                break;
            default:
                break;
        }
    }

    private void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Recruit recruit = new Recruit();
        recruit.setCid(Integer.parseInt(req.getParameter("cid")));
        recruit.setCompanyname(req.getParameter("companyname"));
        recruit.setAddress(req.getParameter("address"));
        recruit.setPostcode(req.getParameter("postcode"));
        recruit.setRecruitment(req.getParameter("recruitment"));
        recruit.setWorkingplace(req.getParameter("workingplace"));
        recruit.setPositiontype(req.getParameter("positiontype"));
        recruit.setEdurequire(req.getParameter("edurequire"));
        recruit.setDescription(req.getParameter("description"));
        recruit.setBranch(req.getParameter("branch"));
        recruit.setLinkman(req.getParameter("linkman"));
        recruit.setTelephone(req.getParameter("telephone"));
        recruit.setHostpage(req.getParameter("hostpage"));
        recruit.setEmail(req.getParameter("email"));
        //调用dao的添加方法
        boolean flag = dao.addrecruit(recruit);
        if (flag) {
            resp.getWriter().print("添加成功");
        }
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) {
        String page = req.getParameter("page");
        String StrRid = req.getParameter("rid");
        int rid = 0;
        try {
            rid = Integer.parseInt(StrRid);
        } catch (NumberFormatException e) {
            return;
        }
        boolean flag = dao.deleterecruit(rid);
        if (flag == true) {
            try {
                resp.getWriter().print("删除成功");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void list2(HttpServletRequest req, HttpServletResponse resp) {
        int cid= Integer.parseInt(req.getParameter("cid"));
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
        page = dao.getList2(page,cid);
        req.setAttribute("page", page);
        try {
            Utils.gotoPage("admin/recruit/recruitList.jsp", req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ridStr = req.getParameter("rid");
        Recruit recruit = new Recruit();
        int rid = 0;
        if (ridStr == null) {
            return;
        } else {
            try {
                rid = Integer.parseInt(ridStr);
            } catch (NumberFormatException e) {
                return;
            }
        }
        recruit=dao.findrecruit(rid);
        req.setAttribute("recruit",recruit);
        Utils.gotoPage("admin/recruit/editrecruit.jsp",req,resp);
    }

    private void update(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Recruit recruit = new Recruit();
        //获取网页中studentname数据
        recruit.setRid(Integer.parseInt(req.getParameter("rid")));
        recruit.setCid(Integer.parseInt(req.getParameter("cid")));
        recruit.setCompanyname(req.getParameter("companyname"));
        recruit.setAddress(req.getParameter("address"));
        recruit.setPostcode(req.getParameter("postcode"));
        recruit.setRecruitment(req.getParameter("recruitment"));
        recruit.setWorkingplace(req.getParameter("workingplace"));
        recruit.setPositiontype(req.getParameter("positiontype"));
        recruit.setEdurequire(req.getParameter("edurequire"));
        recruit.setDescription(req.getParameter("description"));
        recruit.setBranch(req.getParameter("branch"));
        recruit.setLinkman(req.getParameter("linkman"));
        recruit.setTelephone(req.getParameter("telephone"));
        recruit.setHostpage(req.getParameter("hostpage"));
        recruit.setEmail(req.getParameter("email"));
        //调用dao的添加方法
        boolean flag = dao.updaterecruit(recruit);
        if (flag) {
            resp.getWriter().print("true");
        }
    }
    private void list(HttpServletRequest req, HttpServletResponse resp) {
        Page page = new Page();
        //页码
        String pageNum = req.getParameter("page");
        //当前页码
        int currentPage = 0;
        if (pageNum == null) {
            //默认第一页
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
            //动作
            HttpSession session=req.getSession();
            String action=req.getParameter("action");
            User u= (User) session.getAttribute("user");
            if (!u.getUsertypes().equals("admin")){
                Utils.gotoPage("admin/recruit/srecruitList.jsp",req,resp);
            }else{
            Utils.gotoPage("admin/recruit/recruitList.jsp", req, resp);}
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
