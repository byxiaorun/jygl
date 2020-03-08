package jygl.dao.service;

import jygl.beans.Page;
import jygl.beans.Recruit;
import jygl.common.ConSql;
import jygl.common.TotalPage;
import jygl.dao.RecruitDao;
import jygl.db.ConnectionFactory;
import jygl.db.DBClose;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecruitDaoImpl implements RecruitDao {
    Connection conn=null;
    PreparedStatement pstat=null;
    ResultSet rs=null;
    @Override
    public boolean addrecruit(Recruit recruit) {
        boolean flag=false;
        conn= ConnectionFactory.getConnection();
        String sql="insert into recruit"+
                "(companyname,address,postcode,recruitment,workingplace,positiontype,edurequire,description,branch,linkman,telephone,hostpage,email,cid) " +
                "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        return setre(recruit, flag, sql);
    }

    @Override
    public boolean updaterecruit(Recruit recruit) {
        boolean flag=false;
        conn=ConnectionFactory.getConnection();
        String sql="update recruit set "+
                "companyname=?,address=?,postcode=?,recruitment=?," +
                "workingplace=?,positiontype=?,edurequire=?,description=?," +
                "branch=?,linkman=?,telephone=?,hostpage=?,email=?,cid=? where rid";
        return setre(recruit, flag, sql);
    }

    private boolean setre(Recruit recruit, boolean flag, String sql) {
        try {
            pstat=conn.prepareStatement(sql);
            pstat.setString(1,recruit.getCompanyname());
            pstat.setString(2,recruit.getAddress());
            pstat.setString(3,recruit.getPostcode());
            pstat.setString(4,recruit.getRecruitment());
            pstat.setString(5,recruit.getWorkingplace());
            pstat.setString(6,recruit.getPositiontype());
            pstat.setString(7,recruit.getEdurequire());
            pstat.setString(8,recruit.getDescription());
            pstat.setString(9,recruit.getBranch());
            pstat.setString(10,recruit.getLinkman());
            pstat.setString(11,recruit.getTelephone());
            pstat.setString(12,recruit.getHostpage());
            pstat.setString(13,recruit.getEmail());
            pstat.setInt(14,recruit.getCid());
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
    public Recruit findrecruit(int rid) {
        //连接数据库
        conn= ConnectionFactory.getConnection();
        String sql="select * from recruit where rid=?";
        Recruit recruit=new Recruit();
        try {
            pstat=conn.prepareStatement(sql);
            pstat.setInt(1,rid);
            //执行查询，返回结果
            rs=pstat.executeQuery();
            //判断是否有记录
            if (rs.next()){
                setrecruit(recruit);
                recruit.setPositiontype(rs.getString("positiontype"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBClose.close(rs,pstat,conn);
        }
        return recruit;
    }

    @Override
    public boolean deleterecruit(int rid) {
        String sqlname="recruit";
        String idname="rid";
        return ConSql.deletesql(rid,sqlname,idname);
    }

    @Override
    public int getRecordCount(Page page) {
        String sqlname="recruit";
        return TotalPage.getRecordCount(page,conn,pstat,sqlname,rs);
    }

    @Override
    public int getTotalPage(Page page) {
        String sqlname="recruit";
        return TotalPage.getTotalPage(page,conn,pstat,sqlname,rs);
    }

    @Override
    public Page getList(Page page) {
        List list = new ArrayList();
        Recruit recruit = null;
        conn = ConnectionFactory.getConnection();
        String sql = "select * from recruit limit "
                + (page.getCurrentPage() - 1) * page.getPageSize()
                + "," + page.getPageSize();
        try {
            pstat = conn.prepareStatement(sql);
            rs = pstat.executeQuery();
            while (rs.next()) {
                recruit=new Recruit();
                setrecruit(recruit);
                list.add(recruit);
            }
            page.setList(list);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBClose.close(rs, pstat, conn);
        }
        return page;
    }
    @Override
    public Page getList2(Page page,int cid) {
        List list = new ArrayList();
        Recruit recruit = null;
        conn = ConnectionFactory.getConnection();
        String sql = "select * from recruit where cid=? limit "
                + (page.getCurrentPage() - 1) * page.getPageSize()
                + "," + page.getPageSize();
        try {
            pstat = conn.prepareStatement(sql);
            pstat.setInt(1, cid);
            rs = pstat.executeQuery();
            while (rs.next()) {
                recruit=new Recruit();
                setrecruit(recruit);
                list.add(recruit);
            }
            page.setList(list);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBClose.close(rs, pstat, conn);
        }
        return page;
    }

    private void setrecruit(Recruit recruit) throws SQLException {
        recruit.setRid(rs.getInt("rid"));
        recruit.setCid(rs.getInt("cid"));
        recruit.setCompanyname(rs.getString("companyname"));
        recruit.setAddress(rs.getString("address"));
        recruit.setPostcode(rs.getString("postcode"));
        recruit.setRecruitment(rs.getString("recruitment"));
        recruit.setWorkingplace(rs.getString("workingplace"));
        recruit.setEdurequire(rs.getString("edurequire"));
        recruit.setDescription(rs.getString("description"));
        recruit.setBranch(rs.getString("branch"));
        recruit.setLinkman(rs.getString("linkman"));
        recruit.setTelephone(rs.getString("telephone"));
        recruit.setHostpage(rs.getString("hostpage"));
        recruit.setEmail(rs.getString("email"));
    }
}
