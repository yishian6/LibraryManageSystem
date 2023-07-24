package com.programme.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import javax.sql.DataSource;
import java.sql.*;
import java.util.Properties;

public class DBUtils {
    private static Properties pro=new Properties();
    private static DataSource dataSource = null;
    private DBUtils(){
    }
    static {
        try {
            pro.load(DBUtils.class.getResourceAsStream("/druid.properties"));
            dataSource= DruidDataSourceFactory.createDataSource(pro);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public static void close(Connection conn, PreparedStatement ps, ResultSet rs){
        if (rs != null) {
            try{
                rs.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }

        if (ps != null) {
            try{
                ps.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }

        if (conn != null) {
            try{
                conn.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
    public static void close(Connection conn, PreparedStatement ps){
        if (ps != null) {
            try{
                ps.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }

        if (conn != null) {
            try{
                conn.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
    public static void close(Connection conn, Statement s){
        if (s != null) {
            try{
                s.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }

        if (conn != null) {
            try{
                conn.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    public static void rollback(Connection conn){
        try {
            conn.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
