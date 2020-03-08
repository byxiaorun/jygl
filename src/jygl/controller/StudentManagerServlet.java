package jygl.controller;

import jygl.beans.Page;
import jygl.beans.Student;
import jygl.beans.User;
import jygl.common.Utils;
import jygl.dao.StudentDao;
import jygl.dao.service.StudentDaoImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class StudentManagerServlet extends HttpServlet {
    StudentDao dao = new StudentDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String action = req.getParameter("action");
        User u = (User) session.getAttribute("user");
        if (u == null) {
            if (!u.getUsertypes().equals("admin")&&!u.getUsertypes().equals("student")) {
                Utils.gotoPage("/public/login.jsp", req, resp);
                return;
            }
        }
        //获取参数action，区分不同操作
        switch (action) {
            case "sturegister":
                reg(req, resp);
                break;
            case "list":
                showStudentList(req, resp);
                break;
            case "delete":
                delete(req, resp);
                break;
            case "showDetail":
                showDetail1(req, resp);
                break;
            case "showDetail2":
                showDetail2(req, resp);
                break;
            case "edit":
                edit(req,resp);
                break;
            case "update":
                update(req,resp);
                break;
            default:
                break;
        }
    }

    private void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sidStr = req.getParameter("sid");
        Student student = new Student();
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
        student=dao.findstudent(sid);
        req.setAttribute("student",student);
        Utils.gotoPage("student/editstuinfo.jsp",req,resp);
    }

    private void update(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Student student = new Student();
        //获取网页中studentname数据
        student.setSid(Integer.parseInt(req.getParameter("sid")));
        student.setSname(req.getParameter("sname"));
        student.setGender(req.getParameter("gender"));
        student.setIdnumber(req.getParameter("idnumber"));
        student.setSchool(req.getParameter("school"));
        student.setDepartment(req.getParameter("department"));
        student.setMajor(req.getParameter("major"));
        student.setEducation(req.getParameter("education"));
        student.setEntrancedate(req.getParameter("entrancedate"));
        student.setNativeplace(req.getParameter("nativeplace"));
        //调用dao的添加方法
        boolean flag = dao.updatestudent(student);
        if (flag) {
            resp.getWriter().print("true");
        }
    }


    private void reg(HttpServletRequest req, HttpServletResponse resp) {
        //获取sid号
        String id = req.getParameter("sid");
        //转换sid为整型
        int sid = 0;
        sid = Integer.parseInt(id);
        String sname = req.getParameter("sname");
        String gender = req.getParameter("gender");
        String idnumber = req.getParameter("idnumber");
        String school = req.getParameter("school");
        String department = req.getParameter("department");
        String major = req.getParameter("major");
        String education = req.getParameter("education");
        String entrancedate = req.getParameter("entrancedate");
        String nativeplace = req.getParameter("nativeplace");
        Student student = new Student();
        student.setSid(sid);
        student.setSname(sname);
        student.setGender(gender);
        student.setIdnumber(idnumber);
        student.setSchool(school);
        student.setDepartment(department);
        student.setMajor(major);
        student.setEducation(education);
        student.setEntrancedate(entrancedate);
        student.setNativeplace(nativeplace);
        boolean flag = dao.addstudent(student);
        try {
            if (flag == true) {
                //添加成功，提示注册成功
                req.setAttribute("errorMsg", "学生注册成功,请联系管理员激活账号");

                this.gotoPage("/public/login.jsp", req, resp);
            } else {//添加失败，转到基本信息继续填写
                req.setAttribute("sid", student.getSid());
                this.gotoPage("/stu/studentInfo.jsp", req, resp);
            }
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void showStudentList(HttpServletRequest req, HttpServletResponse resp) {
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
        page = dao.getStudentList(page);
        req.setAttribute("page", page);
        try {
            Utils.gotoPage("admin/student/studentList.jsp", req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) {
        String page = req.getParameter("page");
        String StrSid = req.getParameter("sid");
        int sid = 0;
        try {
            sid = Integer.parseInt(StrSid);
        } catch (NumberFormatException e) {
            return;
        }
        boolean flag = dao.deletestudent(sid);
        if (flag == true) {
            try {
                Utils.gotoPage("/studentManage?action=list&page=" + page, req, resp);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void showDetail1(HttpServletRequest req, HttpServletResponse resp) {
        String strsid = req.getParameter("sid");
        int sid = 0;
        try {
            sid = Integer.parseInt(strsid);
        } catch (NumberFormatException e) {
            return;
        }
        Student student = dao.findstudent(sid);
        req.setAttribute("student", student);
        try {
            Utils.gotoPage("admin/student/stuDetail.jsp", req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
    private void showDetail2(HttpServletRequest req, HttpServletResponse resp) {
        String strsid = req.getParameter("sid");
        int sid = 0;
        try {
            sid = Integer.parseInt(strsid);
        } catch (NumberFormatException e) {
            return;
        }
        Student student = dao.findstudent(sid);
        req.setAttribute("student", student);
        try {
            Utils.gotoPage("student/stuInfo.jsp", req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void gotoPage(String url, HttpServletRequest req, HttpServletResponse resp) throws
            ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher(url);
        dispatcher.forward(req, resp);
    }
}
