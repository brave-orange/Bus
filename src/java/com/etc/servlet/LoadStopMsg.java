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
@WebServlet(name = "LoadStopMsg", urlPatterns = {"/LoadStopMsg"})
public class LoadStopMsg extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=utf-8");
            response.setCharacterEncoding("UTF-8");
            request.setCharacterEncoding("UTF-8");
            String json_str = "";
            String stopname = request.getParameter("stop");
            StopDAO sdao = new StopDAO();
            Stop s = sdao.SelectByStop(stopname);
            if(s==null)
            {
                json_str="[]";
            }else
            {
             json_str = json_str +"[{\"stop\":\""+s.getStop() +"\",\"longitude\":\""+s.getLongitude()+"\",\"latitude\":\""+s.getLatitude()+"\"}]"; 
            }
             PrintWriter out = response.getWriter(); 
             out.write(json_str); 
        } catch (SQLException ex) {
            Logger.getLogger(LoadStopMsg.class.getName()).log(Level.SEVERE, null, ex);
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
