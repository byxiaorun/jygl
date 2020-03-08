package jygl.dao;

import jygl.beans.Company;
import jygl.beans.Page;

public interface CompanyDao {
    boolean addCompany(Company company);//把企业信息添加
    boolean updateCompany(Company company);
    boolean deleteCompany(int cid);
    Company findCompany(int cid);
    int getRecordCount(Page page);//获取学生的总记录数
    int getTotalPage(Page page);//获取总页数
    Page getCompanyList(Page page);//获取当前页的学生列表
}
