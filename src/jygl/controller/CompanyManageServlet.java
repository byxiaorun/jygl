package jygl.controller;

import jygl.beans.Company;
import jygl.beans.Page;
import jygl.common.Utils;
import jygl.dao.CompanyDao;
import jygl.dao.service.CompanyDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CompanyManageServlet extends HttpServlet {
    private CompanyDao dao = new CompanyDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        int cid = 0;
        if (req.getParameter("cid") != null) {
            String cidStr = req.getParameter("cid");
            try {
                cid = Integer.parseInt(cidStr);
            } catch (NumberFormatException e) {
                return;
            }
        }
        switch (action) {
            case "add":
                add(cid,req, resp);
                break;
            case "list":
                showComapanyList(req, resp);
                break;
            case "delete":
                delete(cid,req,resp);
                break;
            case "showDetail":
                showDetail1(cid,req, resp);
                break;
            case "showDetail2":
                showDetail2(cid,req, resp);
                break;
            case "edit":
                edit(req,resp);
                break;
            case "update":
                update(cid,req,resp);
                break;
            default:
                break;
        }
    }

    private void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cidStr = req.getParameter("cid");
        Company company = new Company();
        int cid = 0;
        if (cidStr == null) {
            return;
        } else {
            try {
                cid = Integer.parseInt(cidStr);
            } catch (NumberFormatException e) {
                return;
            }
        }
        company=dao.findCompany(cid);
        req.setAttribute("company",company);
        Utils.gotoPage("company/editcompanyinfo.jsp",req,resp);
    }

    private void update(int cid, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Company company = new Company();
        //获取网页中studentname数据
        company.setCid(Integer.parseInt(req.getParameter("cid")));
        company.setCompanyname(req.getParameter("companyname"));
        company.setUnitproperty(req.getParameter("unitproperty"));
        company.setIndustry(req.getParameter("industry"));
        company.setLicensenumber(req.getParameter("licensenumber"));
        company.setUnitscale(req.getParameter("unitscale"));
        company.setAddress(req.getParameter("address"));
        company.setWebaddress(req.getParameter("webaddress"));
        company.setLinkman(req.getParameter("linkman"));
        company.setTel(req.getParameter("telephone"));
        company.setEmail(req.getParameter("email"));
        company.setPostcode(req.getParameter("postcode"));
        //调用dao的添加方法
        boolean flag = dao.updateCompany(company);
        if (flag) {
            resp.getWriter().print("true");
        }
    }

    private void add(int cid,HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Company company = new Company();
        //获取jsp页面传进来的值
        String companyname = req.getParameter("companyname");
        String unitproperty = req.getParameter("unitproperty");
        String licensenumber = req.getParameter("licensenumber");
        String industry = req.getParameter("industry");
        String unitscale = req.getParameter("unitscale");
        String address = req.getParameter("address");
        String webaddress = req.getParameter("webaddress");
        String linkman = req.getParameter("contact");
        String tel = req.getParameter("tel");
        String email = req.getParameter("email");
        String postcode = req.getParameter("postcode");
        company.setCid(cid);
        company.setCompanyname(companyname);
        company.setUnitproperty(unitproperty);
        company.setLicensenumber(licensenumber);
        company.setIndustry(industry);
        company.setUnitscale(unitscale);
        company.setAddress(address);
        company.setWebaddress(webaddress);
        company.setLinkman(linkman);
        company.setTel(tel);
        company.setEmail(email);
        company.setPostcode(postcode);
        boolean flag = dao.addCompany(company);
        if (flag == true) {
            req.setAttribute("errorMsg", "注册成功请联系管理员激活账号");
            Utils.gotoPage("/public/login.jsp", req, resp);
        }
    }

    private void showComapanyList(HttpServletRequest req, HttpServletResponse resp) {
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
        page = dao.getCompanyList(page);
        req.setAttribute("page", page);
        try {
            Utils.gotoPage("admin/company/companyList.jsp", req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
    private void showDetail1(int cid,HttpServletRequest req, HttpServletResponse resp) {
        Company company = dao.findCompany(cid);
        req.setAttribute("company", company);
        try {
            Utils.gotoPage("admin/company/comDetail.jsp", req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void showDetail2(int cid,HttpServletRequest req, HttpServletResponse resp) {
        Company company = dao.findCompany(cid);
        req.setAttribute("company", company);
        try {
            Utils.gotoPage("company/comInfo.jsp", req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void delete(int cid,HttpServletRequest req, HttpServletResponse resp) {
        int page=0;
        if (req.getParameter("page") == null) {
            page = 1;
        } else {
            page = Integer.parseInt(req.getParameter("page"));
        }
        boolean flag = dao.deleteCompany(cid);
        if (flag == true) {
            try {
                Utils.gotoPage("companyMange?action=list&page=" + page, req, resp);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}
