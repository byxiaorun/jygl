package jygl.common;

import jygl.db.ConnectionFactory;
import jygl.db.DBClose;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConSql {//减少重复代码
    public static boolean deletesql(int tid, String sqlname, String idname){
        Connection conn=null;
        PreparedStatement pstat=null;
        ResultSet rs=null;
        boolean flag=false;
        conn=ConnectionFactory.getConnection();
        String sql="delete from "+sqlname+" where "+idname+"=?";
        try {
            pstat=conn.prepareStatement(sql);
            pstat.setInt(1,tid);
            int i=pstat.executeUpdate();
            if (i>0){
                flag=true;
            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        }finally {
            DBClose.close(pstat,conn);
        }
        return flag;
    }

}
