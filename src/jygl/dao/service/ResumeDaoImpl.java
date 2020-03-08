package jygl.dao.service;

import jygl.beans.Page;
import jygl.beans.Recruitresume;
import jygl.beans.Resume;
import jygl.common.TotalPage;
import jygl.dao.ResumeDao;
import jygl.db.ConnectionFactory;
import jygl.db.DBClose;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResumeDaoImpl implements ResumeDao {
    //连接对象
    Connection conn=null;
    //参数对象
    PreparedStatement pstat=null;
    //结果集合对象
    ResultSet rs=null;
    @Override
    public boolean addResume(Resume resume) {
        boolean flag=false;
        conn= ConnectionFactory.getConnection();
        String sql="insert into resume" +
                "(sid,sname,gender,birthdate,nation,politics,graduation,school,major,education,email,phone,foreignlanguage,hobby,practice,position,honor,research,selfevaluation) " +
                "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            pstat=conn.prepareStatement(sql);
            pstat.setInt(1, resume.getSid());
            pstat.setString(2,resume.getSname());
            pstat.setString(3,resume.getGender());
            pstat.setString(4,resume.getBirthdate());
            pstat.setString(5,resume.getNation());
            pstat.setString(6,resume.getPolitics());
            pstat.setString(7,resume.getGraduation());
            pstat.setString(8,resume.getSchool());
            pstat.setString(9,resume.getMajor());
            pstat.setString(10,resume.getEducation());
            pstat.setString(11,resume.getEmail());
            pstat.setString(12,resume.getPhone());
            pstat.setString(13,resume.getForeignlanguage());
            pstat.setString(14,resume.getHobby());
            pstat.setString(15,resume.getPractice());
            pstat.setString(16,resume.getPosition());
            pstat.setString(17,resume.getHonor());
            pstat.setString(18,resume.getResearch());
            pstat.setString(19,resume.getSelfevaluation());
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
    public boolean updateResume(Resume resume) {
        boolean flag=false;
        conn=ConnectionFactory.getConnection();
        String sql="update resume set " +
                "sname=?,gender=?,birthdate=?,nation=?,politics=?,graduation=?,school=?,major=?,education=?,email=?,phone=?,foreignlanguage=?,hobby=?,practice=?,position=?,honor=?,research=?,selfevaluation=? " +
                "where sid=?";
        try {
            pstat=conn.prepareStatement(sql);
            pstat.setInt(19, resume.getSid());
            pstat.setString(1,resume.getSname());
            pstat.setString(2,resume.getGender());
            pstat.setString(3,resume.getBirthdate());
            pstat.setString(4,resume.getNation());
            pstat.setString(5,resume.getPolitics());
            pstat.setString(6,resume.getGraduation());
            pstat.setString(7,resume.getSchool());
            pstat.setString(8,resume.getMajor());
            pstat.setString(9,resume.getEducation());
            pstat.setString(10,resume.getEmail());
            pstat.setString(11,resume.getPhone());
            pstat.setString(12,resume.getForeignlanguage());
            pstat.setString(13,resume.getHobby());
            pstat.setString(14,resume.getPractice());
            pstat.setString(15,resume.getPosition());
            pstat.setString(16,resume.getHonor());
            pstat.setString(17,resume.getResearch());
            pstat.setString(18,resume.getSelfevaluation());
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
    public boolean deleteResume(int sid) {
        return false;
    }

    @Override
    public Resume findResume(int sid) {
        Resume resume = null;
        conn = ConnectionFactory.getConnection();
        String sql = "select * from resume where sid=?";
        try {
            pstat = conn.prepareStatement(sql);
            pstat.setInt(1, sid);
            rs = pstat.executeQuery();
            if (rs.next()) {
                resume=new Resume();
                resume.setSid(rs.getInt("sid"));
                resume.setSname(rs.getString("sname"));
                resume.setGender(rs.getString("gender"));
                resume.setBirthdate(rs.getString("birthdate"));
                resume.setNation(rs.getString("nation"));
                resume.setPolitics(rs.getString("politics"));
                resume.setGraduation(rs.getString("graduation"));
                resume.setSchool(rs.getString("school"));
                resume.setMajor(rs.getString("major"));
                resume.setEducation(rs.getString("education"));
                resume.setEmail(rs.getString("email"));
                resume.setPhone(rs.getString("phone"));
                resume.setForeignlanguage(rs.getString("foreignlanguage"));
                resume.setHobby(rs.getString("hobby"));
                resume.setPractice(rs.getString("practice"));
                resume.setPosition(rs.getString("position"));
                resume.setHonor(rs.getString("honor"));
                resume.setResearch(rs.getString("research"));
                resume.setSelfevaluation(rs.getString("selfevaluation"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBClose.close(rs, pstat, conn);
        }
        return resume;
    }


    @Override
    public int getRecordCount(Page page) {
        String sqlname="recruitresume";
        return TotalPage.getRecordCount(page,conn,pstat,sqlname,rs);
    }

    @Override
    public int getTotalPage(Page page) {
        String sqlname="recruitresume";
        return TotalPage.getTotalPage(page,conn,pstat,sqlname,rs);
    }

    @Override
    public Page getRecruitresumeList(Page page,int cid) {
        List list = new ArrayList();
        Recruitresume recruitresume=null;
        conn = ConnectionFactory.getConnection();
        String sql = "select * from recruitresume where cid=? limit "
                + (page.getCurrentPage() - 1) * page.getPageSize()
                + "," + page.getPageSize();
        try {
            pstat = conn.prepareStatement(sql);
            pstat.setInt(1, cid);
            rs = pstat.executeQuery();
            while (rs.next()) {
                recruitresume=new Recruitresume();
                recruitresume.setRid(rs.getInt("rid"));
                recruitresume.setCid(rs.getInt("cid"));
                recruitresume.setSid(rs.getInt("sid"));
                list.add(recruitresume);
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
    public boolean sendResume(Recruitresume recruitresume) {
        boolean flag=false;
        conn= ConnectionFactory.getConnection();
        String sql="insert into recruitresume" +
                "(rid,cid,sid) " +
                "values(?,?,?)";
        try {
            pstat=conn.prepareStatement(sql);
            pstat.setInt(1, recruitresume.getRid());
            pstat.setInt(2, recruitresume.getCid());
            pstat.setInt(3, recruitresume.getSid());
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
}
