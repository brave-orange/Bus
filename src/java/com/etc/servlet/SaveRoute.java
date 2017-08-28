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
@WebServlet(name = "SaveRoute", urlPatterns = {"/SaveRoute"})
public class SaveRoute extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=utf-8");
            response.setCharacterEncoding("UTF-8");
            request.setCharacterEncoding("UTF-8");
            String stop="";
            String route="";
            String position="";
            stop = request.getParameter("stop");
            route = request.getParameter("route");
            position = request.getParameter("position");
            Relation r = new Relation(stop,route,position);
            RelationDAO rdao = new RelationDAO(); 
            PrintWriter out = response.getWriter(); 
            if(rdao.insert(r)==0)
            {
                out.write("notok"); 
            }else{
                out.write("ok"); 
            }
        } catch (SQLException ex) {
            Logger.getLogger(SaveRoute.class.getName()).log(Level.SEVERE, null, ex);
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
