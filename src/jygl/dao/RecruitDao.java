package jygl.dao;

import jygl.beans.Page;
import jygl.beans.Recruit;

public interface RecruitDao {
    boolean addrecruit(Recruit recruit);
    boolean updaterecruit(Recruit recruit);
    Recruit findrecruit(int rid);
    boolean deleterecruit(int rid);
    int getRecordCount(Page page);//总数
    int getTotalPage(Page page);//总页数
    Page getList(Page page);//当前页数据
    Page getList2(Page page,int cid);//当前页数据
}
