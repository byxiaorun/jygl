package jygl.dao;

import jygl.beans.Employment;
import jygl.beans.Page;

public interface EmploymentDao {
    boolean addEmloyment(Employment employment);//添加就业信息
    boolean updateEmployment(Employment employment);//修改就业信息
    Employment findEmploymentById(int eid);//查询就业信息
    boolean deleteEmployment(int eid);
    int getRecordCount(Page page);//总数
    int getTotalPage(Page page);//总页数
    Page getList(Page page);//当前页数据
}
