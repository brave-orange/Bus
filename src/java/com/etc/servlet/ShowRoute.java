/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.etc.servlet;

import com.etc.dao.RelationDAO;
import com.etc.vo.Relation;
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
@WebServlet(name = "ShowRoute", urlPatterns = {"/ShowRoute"})
public class ShowRoute extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Relation>list =null;
        try {
            response.setContentType("text/html;charset=utf-8");
            response.setCharacterEncoding("UTF-8");
            request.setCharacterEncoding("UTF-8");
            String stop = "";
            stop =request.getParameter("stop");
            RelationDAO rdao = new  RelationDAO();
            list =rdao.SelectStop(stop);
        } catch (SQLException ex) {
            Logger.getLogger(ShowRoute.class.getName()).log(Level.SEVERE, null, ex);
        }
        String json_str = "[";
         for (int i = 0; i < list.size(); i++) {
             Relation r = list.get(i);

                    json_str = json_str +"{\"stop\":\""+r.getStop()+"\",\"route\":\""+r.getRoute() +"\",\"position\":\""+r.getPosition()+"\"},"; 
                   
         }
         json_str += "]";
         //System.out.println("showroute.java:"+json_str);
           PrintWriter out = response.getWriter(); 
               out.write(json_str); 
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
