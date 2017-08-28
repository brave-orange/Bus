/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.etc.dao;

import com.etc.vo.Stop;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author yongcheng
 */
public class StopDAO {
    
    public List<Stop> SelectAll() throws SQLException
    {
        List<Stop> Stops = new ArrayList<Stop>();
        Connection conn = JDBCConnectionFactory.getConnection();
        String sql = "select * from Stop";
        Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sql);
         while(rs.next())
         {
             Stops.add( new Stop(rs.getString(1),rs.getDouble(2),rs.getDouble(3)));
         }
         conn.close();
         return Stops;
    }
        public Stop SelectByStop(String stop) throws SQLException
    {
        PreparedStatement ps;
        Connection conn = JDBCConnectionFactory.getConnection();
        String sql = "select * from Stop where stop=?";
        ps=conn.prepareStatement(sql);
        ps.setString(1, stop);
         ResultSet rs = ps.executeQuery();
         Stop s = null;
         while(rs.next())
         {
           s =  new Stop(rs.getString(1),rs.getDouble(2),rs.getDouble(3));

         }
         conn.close();
         return s;
    }
    public int insert(Stop stop) throws SQLException
    {
         int num = 0;
        PreparedStatement ps;
        String sql = "insert into stop(stop,longitude,latitude) values(?,?,?)";
        JDBCConnectionFactory connect = new JDBCConnectionFactory();
        Connection con = connect.getConnection();
       ps = con.prepareStatement(sql);
       ps.setString(1,stop.getStop());
       ps.setDouble(2, stop.getLongitude());
       ps.setDouble(3, stop.getLatitude());
       num = ps.executeUpdate();
        con.close();
        return num;
    }
    public int delete(String stop) throws SQLException
    {
        int num = 0;
        PreparedStatement ps;
        String sql = "delete from stop where stop=?";
        JDBCConnectionFactory connect = new JDBCConnectionFactory();
        Connection con = connect.getConnection();
       ps = con.prepareStatement(sql);
       ps.setString(1,stop);
       num = ps.executeUpdate();
        con.close();
        return num;
    }
    public int update(Stop stop,String oldstop) throws SQLException
    {
        int num = 0;
        PreparedStatement ps;
        String sql = "update stop set stop=?,longitude=?,latitude=? where stop=?";
        JDBCConnectionFactory connect = new JDBCConnectionFactory();
        Connection con = connect.getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1,stop.getStop());  
        ps.setDouble(2,stop.getLongitude());
        ps.setDouble(3,stop.getLatitude());
           
        ps.setString(4,oldstop);  
        num = ps.executeUpdate();
        con.close();
        return num;
    }
}
