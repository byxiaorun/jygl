package jygl.controller;

import jygl.beans.Page;
import jygl.beans.Recruitresume;
import jygl.beans.Resume;
import jygl.beans.User;
import jygl.common.Utils;
import jygl.dao.ResumeDao;
import jygl.dao.service.ResumeDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ResumeServlet extends HttpServlet {
    ResumeDao dao = new ResumeDaoImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        int sid = 0;
        switch (action) {
            case "add":
                add(req,resp);
                break;
            case "edit":
                edit(req,resp);
                break;
            case "update":
                update(req,resp);
            case "send":
                send(req,resp);
            case "list":
                list(req,resp);
            default:
                break;
        }
    }

    private void list(HttpServletRequest req, HttpServletResponse resp) {
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
        page = dao.getRecruitresumeList(page,cid);
        req.setAttribute("page", page);
        try {
            Utils.gotoPage("company/recruitresumeList.jsp", req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void send(HttpServletRequest req, HttpServletResponse resp) {
        Recruitresume recruitresume = new Recruitresume();
        recruitresume.setSid(Integer.parseInt(req.getParameter("sid")));
        recruitresume.setRid(Integer.parseInt(req.getParameter("rid")));
        recruitresume.setCid(Integer.parseInt(req.getParameter("cid")));
        //调用dao的添加方法
        boolean flag = dao.sendResume(recruitresume);
        if (flag) {
            try {
                resp.getWriter().print("简历发送成功");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void update(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Resume resume = new Resume();
        resume.setSid(Integer.parseInt(req.getParameter("sid")));
        resume.setSname(req.getParameter("sname"));
        resume.setGender(req.getParameter("gender"));
        resume.setBirthdate(req.getParameter("birthdate"));
        resume.setNation(req.getParameter("nation"));
        resume.setPolitics(req.getParameter("politics"));
        resume.setGraduation(req.getParameter("graduation"));
        resume.setSchool(req.getParameter("school"));
        resume.setMajor(req.getParameter("major"));
        resume.setEducation(req.getParameter("education"));
        resume.setEmail(req.getParameter("email"));
        resume.setPhone(req.getParameter("phone"));
        resume.setForeignlanguage(req.getParameter("foreignlanguage"));
        resume.setHobby(req.getParameter("hobby"));
        resume.setPractice(req.getParameter("practice"));
        resume.setPosition(req.getParameter("position"));
        resume.setHonor(req.getParameter("honor"));
        resume.setResearch(req.getParameter("research"));
        resume.setSelfevaluation(req.getParameter("selfevaluation"));
        //调用dao的添加方法
        boolean flag = dao.updateResume(resume);
        if (flag) {
            resp.getWriter().print("修改成功");
        }
    }

    private void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sidStr = req.getParameter("sid");
        int sid = 0;
        if (sidStr == null) {
            return;
        } else {
            try {
                sid = Integer.parseInt(sidStr);
            } catch (NumberFormatException e) {
                return;
            }
        }
        Resume resume=dao.findResume(sid);
        req.setAttribute("resume",resume);
        HttpSession session=req.getSession();
        User u= (User) session.getAttribute("user");
        if (u.getUsertypes().equals("student")){
            Utils.gotoPage("student/editResume.jsp",req,resp);
        }else
            {Utils.gotoPage("company/Resume.jsp",req,resp);}
    }

    private void add(HttpServletRequest req, HttpServletResponse resp) {
        Resume resume = new Resume();
        resume.setSid(Integer.parseInt(req.getParameter("sid")));
        resume.setSname(req.getParameter("sname"));
        resume.setGender(req.getParameter("gender"));
        resume.setBirthdate(req.getParameter("birthdate"));
        resume.setNation(req.getParameter("nation"));
        resume.setPolitics(req.getParameter("politics"));
        resume.setGraduation(req.getParameter("graduation"));
        resume.setSchool(req.getParameter("school"));
        resume.setMajor(req.getParameter("major"));
        resume.setEducation(req.getParameter("education"));
        resume.setEmail(req.getParameter("email"));
        resume.setPhone(req.getParameter("phone"));
        resume.setForeignlanguage(req.getParameter("foreignlanguage"));
        resume.setHobby(req.getParameter("hobby"));
        resume.setPractice(req.getParameter("practice"));
        resume.setPosition(req.getParameter("position"));
        resume.setHonor(req.getParameter("honor"));
        resume.setResearch(req.getParameter("research"));
        resume.setSelfevaluation(req.getParameter("selfevaluation"));
        //调用dao的添加方法
        boolean flag = dao.addResume(resume);
        if (flag) {
            try {
                resp.getWriter().print("制作成功");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
