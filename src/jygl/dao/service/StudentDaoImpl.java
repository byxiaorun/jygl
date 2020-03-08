package jygl.dao.service;

import jygl.beans.Page;
import jygl.beans.Student;
import jygl.common.ConSql;
import jygl.common.TotalPage;
import jygl.dao.StudentDao;
import jygl.db.ConnectionFactory;
import jygl.db.DBClose;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements StudentDao {
    Connection conn;
    PreparedStatement psat=null;
    ResultSet rs=null;
    @Override
    public Student findstudent(int sid) {
        //连接数据库
        conn= ConnectionFactory.getConnection();
        String sql="select * from student where sid=?";
        Student student=new Student();
        try {
            psat=conn.prepareStatement(sql);
            psat.setInt(1,sid);
            //执行查询，返回结果
            rs=psat.executeQuery();
            //判断是否有记录
            if (rs.next()){
                setstudent(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBClose.close(rs,psat,conn);
        }
        return student;
    }

    @Override
    public boolean addstudent(Student student) {
        boolean flag=false;
        conn=ConnectionFactory.getConnection();
        String sql="insert into student values(?,?,?,?,?,?,?,?,?,?)";
        try {
            psat=conn.prepareStatement(sql);
            psat.setInt(1,student.getSid());
            psat.setString(2,student.getSname());
            psat.setString(3,student.getGender());
            psat.setString(4,student.getIdnumber());
            psat.setString(5,student.getSchool());
            psat.setString(6,student.getDepartment());
            psat.setString(7,student.getMajor());
            psat.setString(8,student.getEducation());
            psat.setString(9,student.getEntrancedate());
            psat.setString(10,student.getNativeplace());
            //执行，返回受影响的行数
            int i=psat.executeUpdate();
            if (i>0){
                flag=true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBClose.close(psat,conn);
        }
        return flag;
    }

    @Override
    public boolean updatestudent(Student student) {
        boolean flag=false;
        conn=ConnectionFactory.getConnection();
        String sql="update student set " +
                "sname=?,gender=?,idnumber=?,school=?,department=?,major=?,education=?,entrancedate=?,nativeplace=? " +
                "where sid=?";
        try {
            psat=conn.prepareStatement(sql);
            psat.setString(1,student.getSname());
            psat.setString(2,student.getGender());
            psat.setString(3,student.getIdnumber());
            psat.setString(4,student.getSchool());
            psat.setString(5,student.getDepartment());
            psat.setString(6,student.getMajor());
            psat.setString(7,student.getEducation());
            psat.setString(8,student.getEntrancedate());
            psat.setString(9,student.getNativeplace());
            psat.setInt(10,student.getSid());
            int i=psat.executeUpdate();
            if (i>0){
                flag=true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBClose.close(psat,conn);
        }
        return flag;
    }

    @Override
    public boolean deletestudent(int sid) {
        String sqlname="student";
        String idname="sid";
        return ConSql.deletesql(sid,sqlname,idname);
    }

    @Override
    public int getRecordCount(Page page) {
        String sqlname="student";
        return TotalPage.getRecordCount(page,conn,psat,sqlname,rs);
    }

    @Override
    public int getTotalPage(Page page) {
        String sqlname="student";
        return TotalPage.getTotalPage(page,conn,psat,sqlname,rs);
    }

    @Override
    public Page getStudentList(Page page) {
        Student student=null;
        List studentList=new ArrayList();
        conn=ConnectionFactory.getConnection();
        String sql="select * from student order by sid asc limit "
                +(page.getCurrentPage()-1)*page.getPageSize()
                +","+page.getPageSize();
        try {
            psat=conn.prepareStatement(sql);
            rs=psat.executeQuery();
            while (rs.next()){
                student=new Student();
                setstudent(student);
                studentList.add(student);
            }
            page.setList(studentList);
        } catch (SQLException e) {
            e.printStackTrace();
    }finally {
            DBClose.close(rs,psat,conn);
        }
        return page;
    }

    private void setstudent(Student student) throws SQLException {
        student.setSid(rs.getInt("sid"));
        student.setSname(rs.getString("sname"));
        student.setGender(rs.getString("gender"));
        student.setIdnumber(rs.getString("idnumber"));
        student.setSchool(rs.getString("school"));
        student.setDepartment(rs.getString("department"));
        student.setMajor(rs.getString("major"));
        student.setEducation(rs.getString("education"));
        student.setEntrancedate(rs.getString("entrancedate"));
        student.setNativeplace(rs.getString("nativeplace"));
    }

}
