package jygl.dao.service;

import jygl.beans.Message;
import jygl.beans.Page;
import jygl.common.ConSql;
import jygl.common.TotalPage;
import jygl.dao.MessageDao;
import jygl.db.ConnectionFactory;
import jygl.db.DBClose;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MessageDaoImpl implements MessageDao {
    //连接对象
    Connection conn=null;
    //参数对象
    PreparedStatement pstat=null;
    //结果集合对象
    ResultSet rs=null;

    @Override
    public boolean addMessage(Message message) {
        boolean flag=false;
        conn= ConnectionFactory.getConnection();
        String sql="insert into message" +
                "(uid,username,title,msgtime,content,reply,replytime) values(?,?,?,?,?,?,?)";
        try {
            pstat=conn.prepareStatement(sql);
            pstat.setInt(1, message.getUid());
            pstat.setString(2,message.getUsername());
            pstat.setString(3,message.getTitle());
            pstat.setString(4,message.getMsgtime());
            pstat.setString(5,message.getContent());
            pstat.setString(6,message.getReply());
            pstat.setString(7,message.getReplytime());
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
    public boolean deleteMessage(int mid) {
        String sqlname="message";
        String idname="mid";
        return ConSql.deletesql(mid,sqlname,idname);
    }

    @Override
    public Message findMessageById(int mid) {
        Message message = null;
        conn = ConnectionFactory.getConnection();
        String sql = "select * from message where mid=?";
        try {
            pstat = conn.prepareStatement(sql);
            pstat.setInt(1, mid);
            rs = pstat.executeQuery();
            if (rs.next()) {
                message = getMessage();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBClose.close(rs, pstat, conn);
        }
        return message;
    }

    @Override
    public Page lookMessage(Page page,int uid) {
        List list = new ArrayList();
        Message message=null;
        conn = ConnectionFactory.getConnection();
        String sql = "select * from message where uid=? limit "
                + (page.getCurrentPage() - 1) * page.getPageSize()
                + "," + page.getPageSize();
        try {
            pstat = conn.prepareStatement(sql);
            pstat.setInt(1, uid);
            rs = pstat.executeQuery();
            while (rs.next()) {
                message = getMessage();
                list.add(message);
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
    public int getRecordCount(Page page) {
        String sqlname="message";
        return TotalPage.getRecordCount(page,conn,pstat,sqlname,rs);
    }

    @Override
    public int getTotalPage(Page page) {
        String sqlname="message";
        return TotalPage.getTotalPage(page,conn,pstat,sqlname,rs);
    }

    @Override
    public Page getList(Page page) {
        List list = new ArrayList();
        Message message = null;
        conn = ConnectionFactory.getConnection();
        String sql = "select * from message limit "
                + (page.getCurrentPage() - 1) * page.getPageSize()
                + "," + page.getPageSize();
        try {
            pstat = conn.prepareStatement(sql);
            rs = pstat.executeQuery();
            while (rs.next()) {
                message = getMessage();
                list.add(message);
            }
            page.setList(list);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBClose.close(rs, pstat, conn);
        }
        return page;
    }

    private Message getMessage() throws SQLException {
        Message message;
        message=new Message();
        message.setMid(rs.getInt("mid"));
        message.setUid(rs.getInt("uid"));
        message.setUsername(rs.getString("username"));
        message.setTitle(rs.getString("title"));
        message.setMsgtime(rs.getString("msgtime"));
        message.setContent(rs.getString("content"));
        message.setReply(rs.getString("reply"));
        message.setReplytime(rs.getString("replytime"));
        return message;
    }
}
