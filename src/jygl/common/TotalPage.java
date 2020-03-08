package jygl.common;

import com.sun.istack.internal.Pool;
import jygl.beans.Page;
import jygl.dao.MessageDao;
import jygl.db.ConnectionFactory;
import jygl.db.DBClose;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TotalPage {
    //减少重复代码
    public static int getRecordCount(Page page, Connection conn,
                                     PreparedStatement pstat, String sqlname, ResultSet rs) {
        int count=0;
        conn = ConnectionFactory.getConnection();
        String sql = "select count(*) from " + sqlname;
        try {
            pstat = conn.prepareStatement(sql);
            rs = pstat.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBClose.close(rs, pstat, conn);
        }
        return count;
    }
    public static int getTotalPage(Page page, Connection conn,
                                   PreparedStatement pstat, String sqlname, ResultSet rs) {
        int totalPage=0;
        int m = getRecordCount(page,conn,pstat,sqlname,rs) / page.getPageSize();
        if (getRecordCount(page,conn,pstat,sqlname,rs) % page.getPageSize() > 0) {
            totalPage = m + 1;
        } else {
            totalPage = m;
        }
        return totalPage;
    }

}
