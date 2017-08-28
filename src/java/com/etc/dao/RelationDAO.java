/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.etc.dao;


import com.etc.vo.Relation;
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
public class RelationDAO {
    
    public List<String> SelectAllRoute() throws SQLException
    {
        List<String> Routes = new ArrayList<String>();
        Connection conn = JDBCConnectionFactory.getConnection();
        String sql = "select route from relation group by route";
        Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Routes.add(rs.getString(1));
            }
            conn.close();
            return Routes;
            
    }
    
    public List<String> SelectRoute(String route) throws SQLException//查询公交线路上的所有站点
    {
        List<String> Stops = new ArrayList<String>();
        Connection conn = JDBCConnectionFactory.getConnection();
        String sql = "select stop from relation where route=? group by stop order by position asc";
        PreparedStatement ps;
        ps = conn.prepareStatement(sql);
        ps.setString(1, route);
         ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Stops.add(rs.getString(1));
            }
            conn.close();
            return Stops;
    }
    
    public List<Stop> SelectRouteStop(String route) throws SQLException//查询线路上的站点详细信息
    {
        List<Stop> Stops = new ArrayList<Stop>();
        Connection conn = JDBCConnectionFactory.getConnection();
        String sql = "select b.stop,longitude,latitude from stop as a,relation as b where route=? and a.stop=b.stop order by b.position asc";
        PreparedStatement ps;
        ps = conn.prepareStatement(sql);
        ps.setString(1,route);
        ResultSet rs = ps.executeQuery();
        while(rs.next())
        {
            Stops.add(new Stop(rs.getString(1),rs.getDouble(2),rs.getDouble(3)));
        }
        conn.close();
        return Stops;
    }
    
     public List<Relation> SelectStop(String stop) throws SQLException//查询站点有那些公交线路经过
    {
        List<Relation> Stops = new ArrayList<Relation>();
        Connection conn = JDBCConnectionFactory.getConnection();
        String sql = "select * from relation where stop like ?";
        PreparedStatement ps;
        ps = conn.prepareStatement(sql);
        ps.setString(1, "%"+stop+"%");
         ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Stops.add(new Relation(rs.getString(1),rs.getString(2),rs.getString(3)));
            }
            conn.close();
            return Stops;
    }
    public int insert(Relation relation) throws SQLException
    {
         int num = 0;
        PreparedStatement ps;
        String sql = "insert into relation(stop,route,position) values(?,?,?)";
        JDBCConnectionFactory connect = new JDBCConnectionFactory();
        Connection con = connect.getConnection();
       ps = con.prepareStatement(sql);
       ps.setString(1,relation.getStop());
       ps.setString(2, relation.getRoute());
       ps.setString(3, relation.getPosition());
       num = ps.executeUpdate();
        con.close();
        return num;
    }

    public int update(Relation relation) throws SQLException
    {
         int num = 0;
        PreparedStatement ps;
        String sql = "update relation set position=? where stop=? and route=?";
        JDBCConnectionFactory connect = new JDBCConnectionFactory();
        Connection con = connect.getConnection();
       ps = con.prepareStatement(sql);
       ps.setString(1,relation.getPosition());
       ps.setString(2, relation.getStop());
       ps.setString(3, relation.getRoute());
       num = ps.executeUpdate();
        con.close();
        return num;
    }
    public int deleteRoute(String route) throws SQLException
    {
        int num = 0;
        PreparedStatement ps;
        String sql = "delete from relation where route=?";
        JDBCConnectionFactory connect = new JDBCConnectionFactory();
        Connection con = connect.getConnection();
       ps = con.prepareStatement(sql);
       ps.setString(1, route);
       num = ps.executeUpdate();
       con.close();
       return num;
    }
}
