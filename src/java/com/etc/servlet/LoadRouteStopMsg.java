/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.etc.servlet;

import com.etc.dao.RelationDAO;
import com.etc.vo.Stop;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author yongcheng
 */
@WebServlet(name = "LoadRouteStopMsg", urlPatterns = {"/LoadRouteStopMsg"})
public class LoadRouteStopMsg extends HttpServlet {
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=utf-8");
            response.setCharacterEncoding("UTF-8");
            request.setCharacterEncoding("UTF-8");
            String json_str = "[";
            String routename = request.getParameter("route");    
            RelationDAO rdao = new RelationDAO();
            List<Stop>stops = rdao.SelectRouteStop(routename);
            for(int i=0;i<stops.size();i++)
            {
                Stop s = stops.get(i);
               
                json_str = json_str +"{\"stop\":\""+s.getStop() +"\",\"longitude\":\""+s.getLongitude()+"\",\"latitude\":\""+s.getLatitude()+"\"},"; 
            
            }
            json_str+="]";
            PrintWriter out = response.getWriter(); 
            out.write(json_str); 
        } catch (SQLException ex) {
            Logger.getLogger(LoadRouteStopMsg.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
