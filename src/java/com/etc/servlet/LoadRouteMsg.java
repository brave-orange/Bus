/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.etc.servlet;

import com.etc.dao.RelationDAO;
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
@WebServlet(name = "LoadRouteMsg", urlPatterns = {"/LoadRouteMsg"})
public class LoadRouteMsg extends HttpServlet {

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
            List<String> s = rdao.SelectRoute(routename);
             if(s==null)
            {
                json_str="[]";
            }else
            {
                for (int j = 0; j < s.size(); j++) {
                    if(j==(s.size()-1))
                    {
                        json_str += "{\"stopname\":\""+s.get(j)+"\"}] ";
                    }else{
                        json_str += "{\"stopname\":\""+s.get(j)+"\"},";
                    }
                    
                }  
            }
             System.out.println(json_str);
             PrintWriter out = response.getWriter(); 
             out.write(json_str); 
        } catch (SQLException ex) {
            Logger.getLogger(LoadRouteMsg.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
