package jygl.dao;

import jygl.beans.Message;
import jygl.beans.Page;

public interface MessageDao {
    boolean addMessage(Message message);//添加留言信息
    boolean deleteMessage(int mid);//删除留言
    Message findMessageById(int mid);//查询留言信息
    Page lookMessage(Page page,int uid);//查看自己留言的信息
    int getRecordCount(Page page);//总数
    int getTotalPage(Page page);//总页数
    Page getList(Page page);//当前页数据
}
