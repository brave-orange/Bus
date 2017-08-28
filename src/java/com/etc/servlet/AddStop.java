/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.etc.servlet;

import com.etc.dao.StopDAO;
import com.etc.vo.Stop;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
@WebServlet(name = "AddStop", urlPatterns = {"/AddStop"})
public class AddStop extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=utf-8");
            response.setCharacterEncoding("UTF-8");
            request.setCharacterEncoding("UTF-8");
            String stop="";
            double longitude = 0.0,latitude = 0.0;
            stop=request.getParameter("stop");
            String oldstop=request.getParameter("oldstopname");
            longitude = Double.valueOf(request.getParameter("long"));
            latitude = Double.valueOf(request.getParameter("lat"));
            StopDAO sdao = new StopDAO();
            if(sdao.SelectByStop(oldstop)==null)
            {
                Stop s =new Stop(stop,longitude,latitude);
                sdao.insert(s);
                response.sendRedirect("admin/admin.jsp");
               
            }else{
                Stop s = new Stop(stop,longitude,latitude);
                
                sdao.update(s,oldstop);
                response.sendRedirect("admin/admin.jsp");
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddStop.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
