package jygl.controller;

import jygl.beans.Employment;
import jygl.beans.Page;
import jygl.common.Utils;
import jygl.dao.EmploymentDao;
import jygl.dao.service.EmploymentDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EmploymentManageServlet extends HttpServlet {
    EmploymentDao dao = new EmploymentDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");
        switch (action) {
            case "add":
                add(req, resp);
                break;
            case "list":
                list(req, resp);
                break;
            case "delete":
                delete(req, resp);
                break;
            case "edit":
                edit(req, resp);
                break;
            case "update":
                update(req,resp);
                break;
            default:
                break;
        }
    }

    private void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Employment employment = new Employment();
        //获取网页中studentname数据
        employment.setEid(Integer.parseInt(req.getParameter("eid")));
        employment.setStudentname(req.getParameter("studentname"));
        employment.setSchool(req.getParameter("school"));
        employment.setCompanyname(req.getParameter("companyname"));
        employment.setPosition(req.getParameter("position"));
        //调用dao的添加方法
        boolean flag = dao.updateEmployment(employment);
        if (flag) {
            resp.getWriter().print("true");
        }
    }


    private void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Employment employment = new Employment();
        //获取网页中studentname数据
        employment.setStudentname(req.getParameter("studentname"));
        employment.setSchool(req.getParameter("school"));
        employment.setCompanyname(req.getParameter("companyname"));
        employment.setPosition(req.getParameter("position"));
        //调用dao的添加方法
        boolean flag = dao.addEmloyment(employment);
        if (flag) {
            Utils.gotoPage("employmentManage?action=list", req, resp);
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
            Utils.gotoPage("admin/employment/employmentList.jsp", req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String eidString = req.getParameter("eid");
        String pageNo = req.getParameter("page");
        int eid = 0, currentpage = 0;
        if (eidString == null) {
            return;
        } else {
            try {
                eid = Integer.parseInt(eidString);
            } catch (NumberFormatException e) {
                return;
            }
        }
        if (pageNo == null) {
            currentpage = 1;
        } else {
            try {
                currentpage = Integer.parseInt(pageNo);
            } catch (NumberFormatException e) {
                return;
            }
        }
        boolean flag = dao.deleteEmployment(eid);
        if (flag) {
            Utils.gotoPage("employmentManage?action=list&page="
                    + currentpage, req, resp);
        }
    }

    private void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String eidStr = req.getParameter("eid");
        int eid = 0;
        if (eidStr == null) {
            return;
        } else {
            try {
                eid = Integer.parseInt(eidStr);
            } catch (NumberFormatException e) {
                return;
            }
        }
        Employment employment=dao.findEmploymentById(eid);
        req.setAttribute("employment",employment);
        Utils.gotoPage("admin/employment/editEmployment.jsp",req,resp);
    }

}
