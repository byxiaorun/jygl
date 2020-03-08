package jygl.dao.service;

import jygl.beans.Company;
import jygl.beans.Page;
import jygl.common.ConSql;
import jygl.common.TotalPage;
import jygl.dao.CompanyDao;
import jygl.db.ConnectionFactory;
import jygl.db.DBClose;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CompanyDaoImpl implements CompanyDao {
    Connection conn = null;
    PreparedStatement pstat = null;
    ResultSet rs = null;

    @Override
    public boolean addCompany(Company company) {
        //是否成功
        boolean flag = false;
        conn = ConnectionFactory.getConnection();
        String sql = "insert into company" +
                "(companyname,unitproperty,licensenumber,industry,unitscale,address" +
                ",webaddress,linkman,telephone,email,postcode,cid) " +
                "values(?,?,?,?,?,?,?,?,?,?,?,?)";
        return setcompany(company, flag, sql);
    }

    private boolean setcompany(Company company, boolean flag, String sql) {
        try {
            pstat = conn.prepareStatement(sql);
            pstat.setString(1, company.getCompanyname());
            pstat.setString(2, company.getUnitproperty());
            pstat.setString(3, company.getLicensenumber());
            pstat.setString(4, company.getIndustry());
            pstat.setString(5, company.getUnitscale());
            pstat.setString(6, company.getAddress());
            pstat.setString(7, company.getWebaddress());
            pstat.setString(8, company.getLinkman());
            pstat.setString(9, company.getTel());
            pstat.setString(10, company.getEmail());
            pstat.setString(11, company.getPostcode());
            pstat.setInt(12, company.getCid());
            int i = pstat.executeUpdate();
            if (i > 0) {
                //如果i>0表示添加成功
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBClose.close(pstat, conn);
        }
        return flag;
    }

    @Override
    public boolean updateCompany(Company company) {
        boolean flag = false;
        conn = ConnectionFactory.getConnection();
        String sql = "update company set " +
                "companyname=?,unitproperty=?,licensenumber=?,industry=?,unitscale=?,"+
                "address=?,webaddress=?,linkman=?,telephone=?,email=?,postcode=?  where cid=?";
        return setcompany(company, flag, sql);
    }

    @Override
    public boolean deleteCompany(int cid) {
        String sqlname="company";
        String idname="cid";
        return ConSql.deletesql(cid,sqlname,idname);
    }

    @Override
    public Company findCompany(int cid) {
        Company company = null;
        conn = ConnectionFactory.getConnection();
        String sql = "select * from company where cid=?";
        try {
            pstat = conn.prepareStatement(sql);
            pstat.setInt(1, cid);
            rs = pstat.executeQuery();
            if (rs.next()) {
                company = new Company();
                company.setCid(rs.getInt("cid"));
                company.setCompanyname(rs.getString("companyname"));
                company.setUnitproperty(rs.getString("unitproperty"));
                company.setLicensenumber(rs.getString("licensenumber"));
                company.setIndustry(rs.getString("industry"));
                company.setUnitscale(rs.getString("unitscale"));
                company.setAddress(rs.getString("address"));
                company.setWebaddress(rs.getString("webaddress"));
                company.setLinkman(rs.getString("linkman"));
                company.setTel(rs.getString("telephone"));
                company.setEmail(rs.getString("email"));
                company.setPostcode(rs.getString("postcode"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBClose.close(rs, pstat, conn);
        }
        return company;
    }

    @Override
    public int getRecordCount(Page page) {
        String sqlname="company";
        return TotalPage.getRecordCount(page,conn,pstat,sqlname,rs);
    }

    @Override
    public int getTotalPage(Page page) {
        String sqlname="company";
        return TotalPage.getTotalPage(page,conn,pstat,sqlname,rs);
    }

    @Override
    public Page getCompanyList(Page page) {
        List list = new ArrayList();
        Company com = null;
        conn = ConnectionFactory.getConnection();
        String sql = "select * from company limit "
                + (page.getCurrentPage() - 1) * page.getPageSize()
                + "," + page.getPageSize();
        try {
            pstat = conn.prepareStatement(sql);
            rs = pstat.executeQuery();
            while (rs.next()) {
                com = new Company();
                com.setCid(rs.getInt("cid"));
                com.setAddress(rs.getString("address"));
                com.setCompanyname(rs.getString("companyname"));
                com.setEmail(rs.getString("email"));
                com.setIndustry(rs.getString("industry"));
                com.setLicensenumber(rs.getString("licensenumber"));
                com.setLinkman(rs.getString("linkman"));
                com.setPostcode(rs.getString("postcode"));
                com.setTel(rs.getString("telephone"));
                com.setUnitproperty(rs.getString("unitproperty"));
                com.setUnitscale(rs.getString("unitscale"));
                com.setWebaddress(rs.getString("webaddress"));
                list.add(com);
            }
            page.setList(list);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBClose.close(rs, pstat, conn);
        }
        return page;
    }
}

