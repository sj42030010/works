// Decompiled by DJ v3.7.7.81 Copyright 2004 Atanas Neshkov  Date: 2009-7-24 11:44:44
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   DbConn.java

package com.xh.zfb.util;

import java.io.*;
import java.net.URLDecoder;
import java.sql.*;
import java.util.List;
import java.util.Properties;

// Referenced classes of package wap.util:
//            LogW

public class DbConn
    implements Serializable
{

    public DbConn()
    {
        conn = null;
        stmt = null;
        rs = null;
        dbType = 0;
        conn = getConnection();
    }

    public Connection getConnection()
    {
        if(conn == null)
            try
            {
            	String fileName = null;
//                fileName = (new StringBuilder(String.valueOf(getClass().getClassLoader().getResource("").getPath()))).append("config.properties").toString();
//                fileName = URLDecoder.decode(fileName, "gb2312");
//                Properties p = new Properties();
//                InputStream is = new FileInputStream(fileName);
//                p.load(is);
//                dbType = Integer.parseInt(p.getProperty("DBType"));
//                String driver = p.getProperty("DBDriver");
//                String url = p.getProperty("DBUrl");
//                String user = p.getProperty("DBUser");
//                String password = p.getProperty("DBPass");
//                is.close();
            	String driver = "com.mysql.jdbc.Driver";
                String url = "jdbc:mysql://localhost:3306/xhpay?characterEncoding=utf-8&autoReconnect=true";
                String user = "songjian";
//                String password = "12qwaszx";
                String password = "773726791231";
//                System.out.println(url+";"+user+";"+password);
                Class.forName(driver).newInstance();
                conn = DriverManager.getConnection(url, user, password);
            }
            catch(Exception ex)
            {
            	ex.printStackTrace();
                System.out.println((new StringBuilder("DbConn getConnection error:")).append(ex.getMessage()).toString());
            }
        return conn;
    }

    public void closeConnection()
    {
        try
        {
            if(conn != null)
            {
                if(stmt != null)
                {
                    stmt.close();
                    stmt = null;
                }
                conn.close();
                conn = null;
            }
        }
        catch(Exception ex)
        {
            System.out.println((new StringBuilder("DbConn closeConnection error:")).append(ex.getMessage()).toString());
        }
    }

    public ResultSet executeQuery(String sql, boolean pre)
        throws SQLException
    {
        rs = null;
        Connection conn1 = null;
        try
        {
            conn1 = getConnection();
        }
        catch(Exception exception) { }
        if(conn1 == null)
            System.out.println("conn is null!");
        else
        if(pre)
        {
            stmt = conn1.createStatement(1004, 1007);
            rs = stmt.executeQuery(sql);
        } else
        if(!pre)
        {
            stmt = conn1.createStatement();
            rs = stmt.executeQuery(sql);
        }
        return rs;
    }

    public void closeResultSet()
    {
        try
        {
            if(stmt != null)
            {
                stmt.close();
                stmt = null;
            }
            if(rs != null)
            {
                rs.close();
                rs = null;
            }
        }
        catch(Exception exception) { }
    }

    public boolean execute(String sql)
        throws SQLException
    {
        boolean bupdate = false;
        Connection conn1 = getConnection();
        if(conn1 != null)
        {
            stmt = conn1.createStatement();
            int rowCount = stmt.executeUpdate(sql);
            bupdate = true;
            stmt = null;
        }
        return bupdate;
    }

    public boolean executeUpdate(String sql)
        throws SQLException
    {
        boolean bupdate = false;
        Connection conn1 = getConnection();
        if(conn1 != null)
        {
            stmt = conn1.createStatement();
            int rowCount = stmt.executeUpdate(sql);
            stmt.close();
            bupdate = true;
            stmt = null;
        }
        return bupdate;
    }

    public int mt(String spNumber, String mobile, String serviceID, String subject, String medias, String linkID, String transactionID)
    {
        int result = 999;
        CallableStatement cs = null;
        String s;
        try
        {
            Connection conn1 = getConnection();
            cs = conn1.prepareCall("{call sp_sys_MT(?,?,?,?,?,?,?,?,?)}");
            cs.setString(1, spNumber);
            cs.setString(2, mobile);
            cs.setString(3, serviceID);
            cs.setString(4, subject);
            cs.setString(5, medias);
            cs.setString(6, linkID);
            cs.setString(7, transactionID);
            cs.setDate(8, null);
            cs.registerOutParameter(9, 4);
            cs.execute();
            cs.close();
            result = cs.getInt(9);
        }
        catch(Exception ex)
        {
            s = ex.getMessage();
        }
        return result;
    }

    public void TransProcess(String sql[])
    {
        try
        {
            Connection conn1 = getConnection();
            if(conn1 == null)
                throw new SQLException("connection is null");
            if(sql == null || sql.length == 0)
                throw new SQLException("sql is empty");
            conn1.setAutoCommit(false);
            stmt = conn1.createStatement();
            for(int i = 0; i < sql.length; i++)
            {
                String _sql = sql[i];
                if(_sql != null && _sql != "")
                {
//                    LogW.info(_sql);
                    stmt.addBatch(_sql);
                }
            }

            stmt.executeBatch();
            conn1.commit();
            stmt = null;
        }
        catch(Exception exception) { }
    }

    public void TransProcess(List sqlList)
    {
        try
        {
            Connection conn1 = getConnection();
            if(conn1 == null)
                throw new SQLException("connection is null");
            if(sqlList == null || sqlList.size() == 0)
                throw new SQLException("sql is empty");
            conn1.setAutoCommit(false);
            stmt = conn1.createStatement();
            for(int i = 0; i < sqlList.size(); i++)
            {
                String sql = (String)sqlList.get(i);
                if(sql != null && sql != "")
                    stmt.addBatch(sql);
            }

            stmt.executeBatch();
            conn1.commit();
            stmt = null;
        }
        catch(Exception exception) { }
    }

    Connection conn;
    Statement stmt;
    ResultSet rs;
    public int dbType;
}