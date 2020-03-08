package jygl.dao.service;

import jygl.beans.Employment;
import jygl.beans.Page;
import jygl.common.ConSql;
import jygl.common.TotalPage;
import jygl.dao.EmploymentDao;
import jygl.db.ConnectionFactory;
import jygl.db.DBClose;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmploymentDaoImpl implements EmploymentDao {
    //连接对象
    Connection conn=null;
    //参数对象
    PreparedStatement pstat=null;
    //结果集合对象
    ResultSet rs=null;

    @Override
    public boolean addEmloyment(Employment employment) {
        boolean flag=false;
        conn= ConnectionFactory.getConnection();
        String sql="insert into employment" +
                "(studentname,school,companyname,position) values(?,?,?,?)";
        try {
            pstat=conn.prepareStatement(sql);
            pstat.setString(1,employment.getStudentname());
            pstat.setString(2,employment.getSchool());
            pstat.setString(3,employment.getCompanyname());
            pstat.setString(4,employment.getPosition());
            int i=pstat.executeUpdate();
            if (i>0){
                flag=true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBClose.close(pstat,conn);
        }
        return flag;
    }

    @Override
    public boolean updateEmployment(Employment employment) {
        boolean flag=false;
        conn=ConnectionFactory.getConnection();
        String sql="update employment set " +
                "studentname=?,school=?,companyname=?,position=? where eid=?";
        try {
            pstat=conn.prepareStatement(sql);
            pstat.setString(1,employment.getStudentname());
            pstat.setString(2,employment.getSchool());
            pstat.setString(3,employment.getCompanyname());
            pstat.setString(4,employment.getPosition());
            pstat.setInt(5,employment.getEid());
            int i=pstat.executeUpdate();
            if (i>0){
                flag=true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBClose.close(pstat,conn);
        }
        return flag;
    }

    @Override
    public Employment findEmploymentById(int eid) {
        Employment employment = null;
        conn = ConnectionFactory.getConnection();
        String sql = "select * from employment where eid=?";
        try {
            pstat = conn.prepareStatement(sql);
            pstat.setInt(1, eid);
            rs = pstat.executeQuery();
            if (rs.next()) {
                employment = getEmployment();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBClose.close(rs, pstat, conn);
        }
        return employment;
    }

    @Override
    public boolean deleteEmployment(int eid) {
        String sqlname="employment";
        String idname="eid";
        return ConSql.deletesql(eid,sqlname,idname);
    }

    @Override
    public int getRecordCount(Page page) {
        String sqlname="employment";
        return TotalPage.getRecordCount(page,conn,pstat,sqlname,rs);
    }

    @Override
    public int getTotalPage(Page page) {
        String sqlname="employment";
        return TotalPage.getTotalPage(page,conn,pstat,sqlname,rs);
    }

    @Override
    public Page getList(Page page) {
        List list = new ArrayList();
        Employment employment = null;
        conn = ConnectionFactory.getConnection();
        String sql = "select * from employment limit "
                + (page.getCurrentPage() - 1) * page.getPageSize()
                + "," + page.getPageSize();
        try {
            pstat = conn.prepareStatement(sql);
            rs = pstat.executeQuery();
            while (rs.next()) {
                employment = getEmployment();
                list.add(employment);
            }
            page.setList(list);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBClose.close(rs, pstat, conn);
        }
        return page;
    }

    private Employment getEmployment() throws SQLException {
        Employment employment;
        employment=new Employment();
        employment.setEid(rs.getInt("eid"));
        employment.setStudentname(rs.getString("studentname"));
        employment.setSchool(rs.getString("school"));
        employment.setCompanyname(rs.getString("companyname"));
        employment.setPosition(rs.getString("position"));
        return employment;
    }
}
