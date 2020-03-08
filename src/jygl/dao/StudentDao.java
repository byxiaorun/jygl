package jygl.dao;

import jygl.beans.Page;
import jygl.beans.Student;

public interface StudentDao {
    Student findstudent(int sid);//查询学生基本信息，返回student实体
    boolean addstudent(Student student);//添加学生信息
    boolean updatestudent(Student student);//修改学生信息
    boolean deletestudent(int sid);//根据sid删除学生信息
    int getRecordCount(Page page);//获取学生的总记录数
    int getTotalPage(Page page);//获取总页数
    Page getStudentList(Page page);//获取当前页的学生列表
}
