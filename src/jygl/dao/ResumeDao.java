package jygl.dao;

import jygl.beans.Page;
import jygl.beans.Recruitresume;
import jygl.beans.Resume;

public interface ResumeDao {
    boolean addResume(Resume resume);//添加简历信息
    boolean updateResume(Resume resume);
    boolean deleteResume(int sid);
    Resume findResume(int sid);
    int getRecordCount(Page page);
    int getTotalPage(Page page);
    Page getRecruitresumeList(Page page,int cid);
    boolean sendResume(Recruitresume recruitresume);
}
